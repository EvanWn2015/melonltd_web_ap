package com.melonltd.naber.endpoint.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.mchange.lang.IntegerUtils;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.facade.service.SubmitOrderService;
import com.melonltd.naber.rdbms.model.push.service.PudhSellerService;
import com.melonltd.naber.rdbms.model.req.vo.DemandsItemVo;
import com.melonltd.naber.rdbms.model.req.vo.FoodItemVo;
import com.melonltd.naber.rdbms.model.req.vo.ItemVo;
import com.melonltd.naber.rdbms.model.req.vo.OredeSubimtReq;
import com.melonltd.naber.rdbms.model.req.vo.OredeSubimtReq.OrderData;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.CategoryRelService;
import com.melonltd.naber.rdbms.model.service.FoodInfoSerice;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.service.UserOrderInfoService;
import com.melonltd.naber.rdbms.model.type.Identity;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;
import com.melonltd.naber.rdbms.model.vo.FoodInfoVo;
import com.melonltd.naber.rdbms.model.vo.NotificationVo;
import com.melonltd.naber.rdbms.model.vo.OrderVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class UserOrderController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserOrderController.class);
	//
	@Autowired
	private AccountInfoService accountInfoService;

	@Autowired
	private FoodInfoSerice foodInfoSerice;
	@Autowired
	private UserOrderInfoService userOrderInfoService;
	@Autowired
	private SubmitOrderService submitOrderService;
	@Autowired
	private RestaurantInfoService restaurantInfoService;
	@Autowired
	private PudhSellerService pudhSellerService;
	@Autowired
	private CategoryRelService restaurantCategoryRelService;

	@ResponseBody
	@PostMapping(value = "user/order/subimt")
	public ResponseEntity<String> orderSubimt(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		OredeSubimtReq req = JsonHelper.json(request, OredeSubimtReq.class);

		LinkedHashMap<String, Object> map = null;

		ErrorType errorType = checkRequest(req);
		if (ObjectUtils.anyNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			String accountUUID = httpRequest.getHeader("Authorization");
			AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
			RestaurantInfoVo vo = restaurantInfoService.findByUUID(req.getRestaurant_uuid());
			
			// 
			if (!ObjectUtils.allNotNull(vo, account)) {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			}else if (account.getAccount_uuid().equals("USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a")) {
				// TODO test account
				int price = getPrice(req);
				String bonus = ((int) Math.floor(price / 10d) + "");
				String orders = JsonHelper.toJson(req);
				OrderVo resultOrder = submitOrderService.submitTestOrder(account, Tools.buildUUID(UUIDType.ORDER), vo, req, String.valueOf(price), bonus, orders);
				if (!ObjectUtils.anyNotNull(resultOrder)) {
					LOGGER.error("submit order save fail account : {}, uuid:{} ", accountUUID, req.getRestaurant_uuid());
					map = RespData.of(Status.FALSE, ErrorType.ORDER_UNFINISH_MAX, null);
				} else {
					// push to seller
					NotificationVo notificationVo = new NotificationVo();
					Map<String, String> datas = Maps.newHashMap();
					datas.put("identity", Identity.SELLERS.name());
					datas.put("title", "訂單信息");
					datas.put("message", "您有新訂單！請前往訂單查看！");
					notificationVo.setData(datas);
					pudhSellerService.pushOrderToSeller(resultOrder.getRestaurant_uuid(), notificationVo);
					map = RespData.of(Status.TRUE, null, resultOrder);
				}
//				map = RespData.of(Status.TRUE, null, resultOrder);
			} else {
				int failCount = userOrderInfoService.findByOrderFailByAccountUUID(account.getAccount_uuid());
				if (failCount >= 3) {
					LOGGER.info("Store is close account : {}, uuid:{} ", accountUUID, req.getRestaurant_uuid());
					map = RespData.of(Status.FALSE, ErrorType.ORDER_FAIL_MAX, null);
				} else {
					String hasMsg = checkCanOrderTime(req, vo);
					List<String> foodUUIDs = req.getOrders().stream().map(a -> a.getItem().getFood_uuid()).distinct().collect(Collectors.toList());
					List<String> categoryUUIDs = req.getOrders().stream().map(a -> a.getCategory_uuid()).distinct().collect(Collectors.toList());
					List<FoodInfoVo> foodList = foodInfoSerice.getFoodStatusOpenByUUIDs(foodUUIDs);
					boolean isItemChange = checkLiveItemData(req.getOrders(), foodList);
					int categoryOpens = restaurantCategoryRelService.getStatusByCategoryUUIDs(categoryUUIDs);
					if (StringUtils.isNoneBlank(hasMsg)) {
						LOGGER.info("Store is close account : {}, uuid:{} ", accountUUID, req.getRestaurant_uuid());
						map = RespData.of(Status.FALSE, ErrorType.STORE_IS_CLOSE, hasMsg, null);
					} else if (!isItemChange) {
						LOGGER.info("Store item is change account : {}, uuid:{} ", accountUUID, req.getRestaurant_uuid());
						map = RespData.of(Status.FALSE, ErrorType.FOOD_ITEM_IS_CHANGE, null);
					} else if (foodUUIDs.size() != foodList.size()) {
						LOGGER.info("food item status is close account : {}, food_uuid:{} ", accountUUID, foodUUIDs);
						map = RespData.of(Status.FALSE, ErrorType.FOOD_ITEM_CLOSE, null);
					} else if (categoryUUIDs.size() != categoryOpens) {
						LOGGER.info("category status is close account : {}, food_uuid:{} ", accountUUID, foodUUIDs);
						map = RespData.of(Status.FALSE, ErrorType.CATEGORY_IS_CLOSE, null);
					} else {
						int price = getPrice(req);
						boolean status = checkCount(req);
						// 限制單筆訂單不可超過 5000 或 單筆菜單數量錯誤
						if (price > 5000 || !status) {
							map = status ? RespData.of(Status.FALSE, ErrorType.ORDER_MAX_PRICE, null) : RespData.of(Status.FALSE, ErrorType.ORDER_MAX_COUNT, null);
						} else {
							String bonus = ((int) Math.floor(price / 10d) + "");
							String orders = JsonHelper.toJson(req);
							OrderVo result = submitOrderService.submitOrder(account, Tools.buildUUID(UUIDType.ORDER), vo, req, String.valueOf(price), bonus, orders);
							if (!ObjectUtils.anyNotNull(result)) {
								LOGGER.error("submit order save fail account : {}, uuid:{} ", accountUUID, req.getRestaurant_uuid());
								map = RespData.of(Status.FALSE, ErrorType.ORDER_UNFINISH_MAX, null);
							} else {
								// push to seller
								NotificationVo notificationVo = new NotificationVo();
								Map<String, String> datas = Maps.newHashMap();
								datas.put("identity", Identity.SELLERS.name());
								datas.put("title", "訂單信息");
								datas.put("message", "您有新訂單！請前往訂單查看！");
								notificationVo.setData(datas);
								pudhSellerService.pushOrderToSeller(result.getRestaurant_uuid(), notificationVo);
								map = RespData.of(Status.TRUE, null, result);
							}
						}
					}
				}
			}
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	private static String checkCanOrderTime(OredeSubimtReq req, RestaurantInfoVo vo) {
		String fetch_date = Tools.fromatGMT("HH:mm", req.getFetch_date());
		boolean isStoreOpen = Tools.checkOpenStore(vo.getStore_start(), vo.getStore_end(), fetch_date);
		if (!isStoreOpen) {
			return "您所選的取餐時間，$split並非該商家營業時段。";
		}
		List<DateRangeVo> notCanStoreRangeList = vo.getCan_store_range().stream()
				.filter(a -> SwitchStatus.CLOSE.name().equals(a.getStatus())).collect(Collectors.toList());
		long notCanSize = notCanStoreRangeList.stream().filter(f -> Range
				.<String>between(f.getDate().substring(0, 5), f.getDate().substring(6, 11)).contains(fetch_date))
				.count();
		if (notCanSize > 0) {
			String msg = "";
			for (int i = 0; i < notCanStoreRangeList.size(); i++) {
				msg += "[ " + notCanStoreRangeList.get(i).getDate() + " ]$split";
			}
			return "您所選的取餐時間，該商家無法接單，$split該商家下列的時間不接單:$split" + msg;
		}

		int nowGMT = Tools.getDayOfYear(req.getFetch_date(), "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		long notBusiness = vo.getNot_business().stream()
				.filter(a -> Tools.getDayOfYear(a, "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'") == nowGMT).count();
		if (notBusiness > 0) {
			return "您所選的取餐時間，$split該商家今日不營業。";
		}

		boolean status = Range.<String>between(Tools.getNowGMT(), Tools.getPlusDayGMT(3)).contains(req.getFetch_date());
		if (!status) {
			return "無法接受此訂單。";
		}

		return "";
	}

	private static boolean checkLiveItemData(List<OrderData> orders, List<FoodInfoVo> foodList) {
		List<FoodItemVo> items = orders.stream().map(a -> a.getItem()).collect(Collectors.toList());
		long count = items.stream().filter(o -> {
			Predicate<FoodInfoVo> equalfoodUUID = (a) -> a.getFood_uuid().equals(o.getFood_uuid());
			List<ItemVo> foodScopes = foodList.stream().filter(equalfoodUUID)
					.flatMap(r -> r.getFood_data().getScopes().stream()).collect(Collectors.toList());
			List<ItemVo> foodOpts = foodList.stream().filter(equalfoodUUID)
					.flatMap(r -> r.getFood_data().getOpts().stream()).collect(Collectors.toList());
			List<DemandsItemVo> foodDemands = foodList.stream().filter(equalfoodUUID)
					.flatMap(r -> r.getFood_data().getDemands().stream()).collect(Collectors.toList());
			long c = o.getDemands().stream()
					.filter(d -> foodDemands.stream().filter(b -> StringUtils.equals(b.getName(), d.getName()))
							.flatMap(b -> b.getDatas().stream()).collect(Collectors.toList()).containsAll(d.getDatas()))
					.count();
			return c == foodDemands.size() && foodScopes.containsAll(o.getScopes())
					&& foodOpts.containsAll(o.getOpts());
		}).count();
		return count == items.size();
	}

	private boolean checkCount(OredeSubimtReq req) {
		// long count = req.getOrders().stream().filter(a ->
		// IntegerUtils.parseInt(a.getCount(), 50) > 50).count();
		return req.getOrders().stream().filter(a -> IntegerUtils.parseInt(a.getCount(), 50) > 50).count() == 0;
	}

	private Integer getPrice(OredeSubimtReq req) {
		int result = req.getOrders().stream().mapToInt(a -> {
			int count = Integer.parseInt(a.getCount());
			int price = Integer.parseInt(a.getItem().getPrice());
			int opt = a.getItem().getOpts().stream()
					.mapToInt(o -> o.getPrice() != null ? Integer.parseInt(o.getPrice()) : 0).sum();
			int scopes = a.getItem().getScopes().stream()
					.mapToInt(s -> s.getPrice() != null ? Integer.parseInt(s.getPrice()) : 0).sum();
			if (scopes > 0) {
				return (scopes + opt) * count;
			}
			return (price + opt + scopes) * count;
		}).sum();
		return result;
	}

	@ResponseBody
	@PostMapping(value = "user/order/history")
	public ResponseEntity<String> orderHistory(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {

		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);

		String uuid = httpRequest.getHeader("Authorization");

		LinkedHashMap<String, Object> map = null;
		if (StringUtils.isAllBlank(uuid)) {
			map = RespData.of(Status.FALSE, ErrorType.HEADESR_ERROR, null);
		} else {
			int page = req.getPage();
			List<OrderVo> list = userOrderInfoService.findByAccountUUIDAndPage(uuid, page);
			map = RespData.of(Status.TRUE, null, list);
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	private static boolean checkIsStoreOpen(OredeSubimtReq req, RestaurantInfoVo vo) {
		String fetch_date = Tools.fromatGMT("HH:mm", req.getFetch_date());
		boolean c1 = Tools.checkOpenStore(vo.getStore_start(), vo.getStore_end(), fetch_date);
		List<DateRangeVo> canStoreRange = Tools.checkOpenStoreByRanges(vo.getCan_store_range(), fetch_date);
		int nowGMT = Tools.getDayOfYear(req.getFetch_date(), "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		List<String> notBusiness = vo.getNot_business().stream()
				.filter(a -> Tools.getDayOfYear(a, "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'") == nowGMT)
				.collect(Collectors.toList());
		boolean status = Range.<String>between(Tools.getNowGMT(), Tools.getPlusDayGMT(3)).contains(req.getFetch_date());
		return c1 && canStoreRange.size() == 0 && notBusiness.size() == 0 && status;
	}

	private static ErrorType checkRequest(OredeSubimtReq req) {
		ErrorType error = null;
		if (!ObjectUtils.anyNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (StringUtils.isAllBlank(req.getRestaurant_uuid(), req.getFetch_date())) {
			error = ErrorType.INVALID;
		}
		return error;

	}
}
