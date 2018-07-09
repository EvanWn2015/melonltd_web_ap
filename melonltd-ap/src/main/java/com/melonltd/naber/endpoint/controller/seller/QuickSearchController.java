package com.melonltd.naber.endpoint.controller.seller;

import java.util.LinkedHashMap;
import java.util.List;

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

import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.req.vo.AccountReq;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.OrderInfoService;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.vo.OrderVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class QuickSearchController {
	private static final Logger LOGGERO = LoggerFactory.getLogger(QuickSearchController.class);
//
//	@Autowired
//	private AccountInfoService accountInfoService;
//	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private RestaurantInfoService restaurantInfoService;
	
	private static List<OrderStatus> SELLER_SEARCH_TYPE = OrderStatus.getSellerSearchType();

	@ResponseBody
	@PostMapping(value = "seller/quick/search")
	public ResponseEntity<String> quickSearch(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		AccountReq req = JsonHelper.json(request, AccountReq.class);

		ErrorType errorType = checkReqData(req);
		LinkedHashMap<String, Object> map = null;
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			String accountUUID = httpRequest.getHeader("Authorization");
			RestaurantInfoVo rVo = restaurantInfoService.findByAccountUUID(accountUUID);
			if (ObjectUtils.allNotNull(rVo)) {
				List<OrderVo> list = orderInfoService.findQuickSearchByPhone(rVo.getRestaurant_uuid(), req.getPhone());
				map = RespData.of(Status.TRUE, null, list);
			}else {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			}
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "seller/ordar/list")
	public ResponseEntity<String> orderList(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);

		ErrorType errorType = checkReqData(req);
		LinkedHashMap<String, Object> map = null;
		String accountUUID = httpRequest.getHeader("Authorization");

		if(StringUtils.isBlank(accountUUID)) {
			map = RespData.of(Status.FALSE, ErrorType.HEADESR_ERROR, null);
		}else if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			int page = req.getPage();
			List<OrderVo> list = orderInfoService.findByOrderStatusAndBetweenDate(accountUUID, req.getSearch_type(), req.getDate(), page);
			map = RespData.of(Status.TRUE, null, list);
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	private ErrorType checkReqData(AccountReq req) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}

		if (!ObjectUtils.allNotNull(req.getPhone())) {
			return ErrorType.INVALID;
		}
		return null;
	}

	private ErrorType checkReqData(ReqData req) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}

		if (!ObjectUtils.allNotNull(req.getSearch_type())) {
			return ErrorType.INVALID;
		}

		if (!ObjectUtils.allNotNull(req.getDate())) {
			return ErrorType.INVALID;
		}
//
//		if (!ObjectUtils.allNotNull(req.getUuid())) {
//			return ErrorType.INVALID;
//		}

		if (!SELLER_SEARCH_TYPE.contains(OrderStatus.of(req.getSearch_type()))) {
			return ErrorType.INVALID;
		}

		return null;
	}
}
