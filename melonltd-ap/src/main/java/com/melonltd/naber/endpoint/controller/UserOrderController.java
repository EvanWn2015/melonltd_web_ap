package com.melonltd.naber.endpoint.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
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
import com.melonltd.naber.constant.NaberConstant;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.OrderCheckHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.facade.service.SubmitOrderService;
import com.melonltd.naber.rdbms.model.push.service.PushService;
import com.melonltd.naber.rdbms.model.req.vo.OredeSubimtReq;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.CategoryRelService;
import com.melonltd.naber.rdbms.model.service.FoodInfoSerice;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.service.UserOrderInfoService;
import com.melonltd.naber.rdbms.model.type.BillingType;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.Identity;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.type.UUIDType;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.FoodInfoVo;
import com.melonltd.naber.rdbms.model.vo.OrderVo;
import com.melonltd.naber.rdbms.model.vo.PushFCMVo;
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
	private PushService pushService;
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

			if (!ObjectUtils.allNotNull(vo, account)) {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			} else if (account.getAccount_uuid()
					.equals("USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a")) {
				int price = getPrice(req);
				String bonus = ((int) Math.floor(price / 10d) + "");
				if (!ObjectUtils.allNotNull(req.getOrder_type())) {
					req.setOrder_type(OredeSubimtReq.setDefault());
				}
				String orders = JsonHelper.toJson(req);
				OrderVo resultOrder = submitOrderService.submitTestOrder(account, Tools.buildUUID(UUIDType.ORDER), vo,
						req, String.valueOf(price), bonus, orders);
				if (!ObjectUtils.anyNotNull(resultOrder)) {
					LOGGER.error("submit order save fail account : {}, uuid:{} ", accountUUID,
							req.getRestaurant_uuid());
					map = RespData.of(Status.FALSE, ErrorType.ORDER_UNFINISH_MAX, null);
				} else {
					// push to seller
					PushFCMVo pushFCMVo = new PushFCMVo();
					pushFCMVo.setNotification(new PushFCMVo.Notify("訂單信息", "您有新訂單！請前往訂單查看！"));
					Map<String, Object> datas = Maps.newHashMap();
					datas.put("identity", Identity.SELLERS.name());
					datas.put("title", "訂單信息");
					datas.put("message", "您有新訂單！請前往訂單查看！");
					pushFCMVo.setData(datas);
					pushService.pushRemoteMessage(resultOrder.getRestaurant_uuid(), pushFCMVo, false);
					map = RespData.of(Status.TRUE, null, resultOrder);
				}
			} else {
				int failCount = userOrderInfoService.findByOrderFailByAccountUUID(account.getAccount_uuid());
				// 若訂單未完成滿三筆，不可在下訂單
				if (failCount >= NaberConstant.ORDER_PRICE_COUNT_MAX) {
					LOGGER.info("Store is close account : {}, uuid:{} ", accountUUID, req.getRestaurant_uuid());
					map = RespData.of(Status.FALSE, ErrorType.ORDER_FAIL_MAX, null);
				} else {
					String hasMsg = OrderCheckHelper.checkCanOrderTime(req, vo);
					List<String> foodUUIDs = req.getOrders().stream().map(a -> a.getItem().getFood_uuid()).distinct()
							.collect(Collectors.toList());
					List<String> categoryUUIDs = req.getOrders().stream().map(a -> a.getCategory_uuid()).distinct()
							.collect(Collectors.toList());
					List<FoodInfoVo> foodList = foodInfoSerice.getFoodStatusOpenByUUIDs(foodUUIDs);
					boolean isItemChange = OrderCheckHelper.checkLiveItemData(req.getOrders(), foodList);
					int categoryOpens = restaurantCategoryRelService.getStatusByCategoryUUIDs(categoryUUIDs, Enable.Y,
							Arrays.asList(SwitchStatus.OPEN));
					if (StringUtils.isNoneBlank(hasMsg)) {
						LOGGER.info("Store is close account : {}, uuid:{} ", accountUUID, req.getRestaurant_uuid());
						map = RespData.of(Status.FALSE, ErrorType.STORE_IS_CLOSE, hasMsg, null);
					} else if (!isItemChange) {
						LOGGER.info("Store item is change account : {}, uuid:{} ", accountUUID,
								req.getRestaurant_uuid());
						map = RespData.of(Status.FALSE, ErrorType.FOOD_ITEM_IS_CHANGE, null);
					} else if (foodUUIDs.size() != foodList.size()) {
						LOGGER.info("food item status is close account : {}, food_uuid:{} ", accountUUID, foodUUIDs);
						map = RespData.of(Status.FALSE, ErrorType.FOOD_ITEM_CLOSE, null);
					} else if (categoryUUIDs.size() != categoryOpens) {
						LOGGER.info("category status is close account : {}, food_uuid:{} ", accountUUID, foodUUIDs);
						map = RespData.of(Status.FALSE, ErrorType.CATEGORY_IS_CLOSE, null);
					} else {
						int price = getPrice(req);
						int useBonus = 0;
						boolean status = checkCount(req);
						// 限制單筆訂單不可超過 5000 或 單筆菜單數量錯誤
						if (price > NaberConstant.ORDER_PRICE_MAX || !status) {
							map = status ? RespData.of(Status.FALSE, ErrorType.ORDER_MAX_PRICE, null)
									: RespData.of(Status.FALSE, ErrorType.ORDER_MAX_COUNT, null);
						} else {
							// 如果該店家不提供紅利，將計算紅利歸零。
							String bonus = vo.getCan_discount().equals("Y") ? ((int) Math.floor(price / 10d) + "")
									: "0";
							// 判斷訂單類型結算方式 (兼容為使用此功能的app)
							if (!ObjectUtils.allNotNull(req.getOrder_type())) {
								req.setOrder_type(OredeSubimtReq.setDefault());
							}
							// 如果單為兌換，紅利歸零
							BillingType billingType = JsonHelper.json(req.getOrder_type().getBilling(),
									BillingType.class);
							if (BillingType.COUPON.equals(billingType)) {
								bonus = "0";
							} else if (BillingType.DISCOUNT.equals(billingType)) {
								useBonus = IntegerUtils.parseInt(req.getUse_bonus(), 0);
								bonus = ((int) Math.floor((price - (useBonus / 10 * 3)) / 10d) + "");
								System.out.println(bonus);
							}
							String orders = JsonHelper.toJson(req);
							OrderVo result = submitOrderService.submitOrder(account, Tools.buildUUID(UUIDType.ORDER),
									vo, req, String.valueOf(price), bonus, orders, true);
							if (!ObjectUtils.anyNotNull(result)) {
								LOGGER.error("submit order save fail account : {}, uuid:{} ", accountUUID,
										req.getRestaurant_uuid());
								map = RespData.of(Status.FALSE, ErrorType.ORDER_UNFINISH_MAX, null);
							} else {
								if (useBonus != 0) {
									AccountInfoVo accout = accountInfoService.getCacheBuilderByKey(accountUUID, false);
									int bonusSum = IntegerUtils.parseInt(accout.getUse_bonus(), 0) + useBonus;
									status = accountInfoService.updateUseBonus(String.valueOf(bonusSum),
											accout.getAccount_uuid());
								}
								PushFCMVo notificationVo = new PushFCMVo();
								notificationVo.setNotification(new PushFCMVo.Notify("訂單信息", "您有新訂單！請前往訂單查看！"));
								Map<String, Object> datas = Maps.newHashMap();
								datas.put("identity", Identity.SELLERS.name());
								datas.put("title", "訂單信息");
								datas.put("message", "您有新訂單！請前往訂單查看！");
								notificationVo.setData(datas);
								pushService.pushRemoteMessage(result.getRestaurant_uuid(), notificationVo, false);
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

	private boolean checkCount(OredeSubimtReq req) {
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
