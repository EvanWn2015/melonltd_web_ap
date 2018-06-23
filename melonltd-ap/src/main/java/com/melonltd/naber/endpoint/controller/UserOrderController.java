package com.melonltd.naber.endpoint.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mchange.lang.IntegerUtils;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.facade.service.SubmitOrderService;
import com.melonltd.naber.rdbms.model.push.service.PudhSellerService;
import com.melonltd.naber.rdbms.model.req.vo.OredeSubimtReq;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.CategoryFoodRelSerice;
import com.melonltd.naber.rdbms.model.service.RestaurantCategoryRelService;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.service.UserOrderLogService;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;
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
//	@Autowired
//	private UserOrderLogStoredService userOrderLogStoredService;
	
	@Autowired
	private CategoryFoodRelSerice categoryFoodRelSerice;
	@Autowired
	private UserOrderLogService userOrderLogService;
	@Autowired
	private SubmitOrderService submitOrderService;
	@Autowired 
	private RestaurantInfoService restaurantInfoService;
	@Autowired
	private PudhSellerService pudhSellerService;
	@Autowired
	private RestaurantCategoryRelService restaurantCategoryRelService;

	@ResponseBody
	@PostMapping(value = "user/order/subimt")
	public ResponseEntity<String> orderSubimt(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		OredeSubimtReq req = JsonHelper.json(request, OredeSubimtReq.class);

		LinkedHashMap<String, Object> map = null;
		
		ErrorType errorType = checkRequest(req);
		if(ObjectUtils.anyNotNull(errorType)){
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			String accountUUID = httpRequest.getHeader("Authorization");
			RestaurantInfoVo vo = restaurantInfoService.findByUUID(req.getRestaurant_uuid());
			if (!ObjectUtils.allNotNull(vo, accountUUID)) {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);	
			}else {
				boolean isOpen = checkIsStoreOpen(req, vo);
				List<String> foodUUIDs = req.getOrders().stream().map(a -> a.getItem().getFood_uuid()).distinct().collect(Collectors.toList());
				List<String> categoryUUIDs = req.getOrders().stream().map(a -> a.getCategory_uuid()).distinct().collect(Collectors.toList());
				int foodOpens = categoryFoodRelSerice.getFoodStatusOpenByUUIDs(foodUUIDs);
				int categoryOpens = restaurantCategoryRelService.getStatusByCategoryUUIDs(categoryUUIDs);
				if (!isOpen) {
					LOGGER.info("Store is close account : {}, uuid:{} ",accountUUID, req.getRestaurant_uuid());
					map = RespData.of(Status.FALSE, errorType.STORE_IS_CLOSE, null);
				}else if (foodUUIDs.size() != foodOpens || categoryUUIDs.size() != categoryOpens) {
					LOGGER.info("food item or category status is close account : {}, food_uuid:{} ",accountUUID, foodUUIDs);
					map = RespData.of(Status.FALSE, errorType.STATUS_IS_CLOSE, null);	
				}else {
					int price = getPrice(req);
					boolean status = checkCount(req);
					// 限制單筆訂單不可超過 5000  或  單筆菜單數量錯誤
					if (price > 5000 || !status) {
						map = status? RespData.of(Status.FALSE, ErrorType.ORDER_MAX_PRICE, null) : RespData.of(Status.FALSE, ErrorType.ORDER_MAX_COUNT, null);
					}else {
						String bonus = ((int)Math.floor(price / 10d) + "");
						String orders = JsonHelper.toJson(OredeSubimtReq.ofOrders(req.getOrders()));
						OrderVo result = submitOrderService.submitOrder(accountUUID , Tools.buildUUID(UUIDType.ORDER),  vo,  req,  String.valueOf(price),  bonus,  orders);
						if (!ObjectUtils.anyNotNull(result)) {
							LOGGER.error("submit order save fail account : {}, uuid:{} ",accountUUID, req.getRestaurant_uuid());
							map = RespData.of(Status.FALSE, ErrorType.ORDER_UNFINISH_MAX, null);	
						}else {
							// push to seller 
							pudhSellerService.pushOrderToSeller(result.getRestaurant_uuid(), OredeSubimtReq.ofOrders(req.getOrders()) ,OrderStatus.UNFINISH);
							map = RespData.of(Status.TRUE, null, result);
						}
					}
				}
			}
		}
		
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	private boolean checkCount(OredeSubimtReq req) {
		long count = req.getOrders().stream().filter(a -> IntegerUtils.parseInt(a.getCount(), 50) > 50).count();
		return req.getOrders().stream().filter(a -> IntegerUtils.parseInt(a.getCount(), 50) > 50).count() == 0;
	}
	
	private Integer getPrice(OredeSubimtReq req) {
		int result = req.getOrders().stream().mapToInt(a -> {
			int count= Integer.parseInt(a.getCount());
			int price = Integer.parseInt(a.getItem().getPrice());
			int opt = a.getItem().getOpts().stream().mapToInt(o -> o.getPrice() != null ? Integer.parseInt(o.getPrice()) : 0).sum();
			int scopes = a.getItem().getScopes().stream().mapToInt(s -> s.getPrice() != null ? Integer.parseInt(s.getPrice()) : 0).sum();
			if (scopes > 0 ) {
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
			if (page > 0) {
				page = page - 1;
			}
			Sort sort = new Sort(Direction.ASC, "fetchDate");
			Pageable pageable = new PageRequest(page, 10, sort);
			List<OrderVo> list = userOrderLogService.findByAccountUUIDAndPage(uuid, pageable);
			map = RespData.of(Status.TRUE, null, list);
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	
	private static boolean checkIsStoreOpen(OredeSubimtReq req, RestaurantInfoVo vo) {
		String fetch_date = Tools.fromatGMT("HH:mm",req.getFetch_date());
		boolean c1 = Tools.checkOpenStore(vo.getStore_start(), vo.getStore_end(), fetch_date);
		
		List<DateRangeVo> canStoreRange = Tools.checkOpenStoreByRanges(vo.getCan_store_range(), fetch_date);
		
		int nowUTC = Tools.getDayOfYear(req.getFetch_date(),"yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		List<String> notBusiness = vo.getNot_business().stream().filter(a -> {
			return Tools.getDayOfYear(a, "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'") == nowUTC;
		}).collect(Collectors.toList());
			
		boolean	status = Range.<String>between(Tools.getNowGMT(), Tools.getPlusDayGMT(3)).contains(req.getFetch_date());
		return c1 && canStoreRange.size() == 0 && notBusiness.size() == 0 && status;
//		
//		return false;
	} 

	private static ErrorType checkRequest(OredeSubimtReq req) {
		ErrorType error = null;
		if (!ObjectUtils.anyNotNull(req)) {
			return  ErrorType.INVALID;
		}
		if (StringUtils.isAllBlank(req.getRestaurant_uuid(), req.getFetch_date())) {
			error = ErrorType.INVALID;
		}
		return error;

	}
}
