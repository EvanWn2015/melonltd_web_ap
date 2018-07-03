package com.melonltd.naber.endpoint.controller.seller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.service.SellerOrderFinishService;
import com.melonltd.naber.rdbms.model.vo.OrderVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;
import com.melonltd.naber.rdbms.model.vo.SellerStatVo;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class StatController {

	@Autowired
	private SellerOrderFinishService sellerOrderFinishService;

	@Autowired
	private RestaurantInfoService restaurantInfoService;

	@ResponseBody
	@PostMapping(value = "seller/stat")
	public ResponseEntity<String> getSellerStat(HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		RestaurantInfoVo rVo = restaurantInfoService.findByAccountUUID(accountUUID);
		LinkedHashMap<String, Object> map = null;
		if (!ObjectUtils.allNotNull(rVo)) {
			map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
		} else {
			SellerStatVo vo = sellerOrderFinishService.findSellerStat(rVo.getRestaurant_uuid());
			map = RespData.of(Status.TRUE, null, vo);
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "seller/stat/log")
	public ResponseEntity<String> getSellerStatLog(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String accountUUID = httpRequest.getHeader("Authorization");
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);

		RestaurantInfoVo rVo = restaurantInfoService.findByAccountUUID(accountUUID);
		LinkedHashMap<String, Object> map = null;
		ErrorType errorType = checkRequest(req);
		if (!ObjectUtils.allNotNull(rVo)) {
			map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
		} else if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			String start = Tools.fromatStartOfDayGMT(req.getStart_date());
			String end = Tools.fromatEndOfDayGMT( req.getEnd_date());
			List<OrderVo> list = sellerOrderFinishService.findFinishByBetweenDate(rVo.getRestaurant_uuid(), start, end, req.getPage());
			map = RespData.of(Status.TRUE, null, list);
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	private static ErrorType checkRequest(ReqData req) {
		ErrorType error = null;
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (StringUtils.isAllBlank(req.getStart_date(), req.getEnd_date())) {
			return ErrorType.INVALID;
		}
		return error;
	}

}
