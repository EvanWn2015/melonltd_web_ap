package com.melonltd.naber.endpoint.controller.seller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.RestaurantCategoryRelService;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantCategoryRelVo;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class SellerCategoryController {
	
	private static List<SwitchStatus> CAN_UPDATE_STATUS = Lists.<SwitchStatus>newArrayList(SwitchStatus.OPEN, SwitchStatus.CLOSE);
	
	@Autowired
	private RestaurantCategoryRelService restaurantCategoryRelService;
	
	@Autowired
	private RestaurantInfoService restaurantInfoService;
	
	
	@ResponseBody
	@PostMapping(value = "seller/category/list")
	public ResponseEntity<String> getCategoryList(HttpServletRequest httpRequest){
		String accountUUID = httpRequest.getHeader("Authorization");
		RestaurantInfoVo rVo = restaurantInfoService.findByAccountUUID(accountUUID);
		LinkedHashMap<String, Object> map = null;
		if (ObjectUtils.allNotNull(rVo)) {
			List<RestaurantCategoryRelVo> list =restaurantCategoryRelService.findAllByRestaurantUUID(rVo.getRestaurant_uuid());
			map = RespData.of(Status.TRUE, null, list);
		}else{
			map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
		}
		
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "seller/category/add")
	public ResponseEntity<String> addCategory(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data){
		String accountUUID = httpRequest.getHeader("Authorization");
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		
		LinkedHashMap<String, Object> map = null;
		ErrorType errorType = checkReqData(req);
		
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			RestaurantInfoVo rVo = restaurantInfoService.findByAccountUUID(accountUUID);
			if (ObjectUtils.allNotNull(rVo)) {
				RestaurantCategoryRelVo relVo = restaurantCategoryRelService.saveCategoryRel(rVo, req.getName());
				if (ObjectUtils.allNotNull(relVo)) {
					map = RespData.of(Status.TRUE, null, relVo);
				}else {
					map = RespData.of(Status.FALSE, ErrorType.SAVE_ERROR, null);
				}
			}else{
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "seller/category/update")
	public ResponseEntity<String> updateCategory(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data){
		String accountUUID = httpRequest.getHeader("Authorization");
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		
		LinkedHashMap<String, Object> map = null;
		ErrorType errorType = checkUpdateReqData(req);
		
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			RestaurantInfoVo rVo = restaurantInfoService.findByAccountUUID(accountUUID);
			RestaurantCategoryRelVo relVo = restaurantCategoryRelService.updateCategoryRelStatus(rVo, req);
			if (ObjectUtils.allNotNull(relVo)) {
				map = RespData.of(Status.TRUE, null, relVo);	
			}else {
				map = RespData.of(Status.FALSE, errorType.UPDATE_ERROR, null);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "seller/category/delete")
	public ResponseEntity<String> deleteCategory(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data){
		String accountUUID = httpRequest.getHeader("Authorization");
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		
		LinkedHashMap<String, Object> map = null;
		ErrorType errorType = checkUpdateReqData(req);
		
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			RestaurantInfoVo rVo = restaurantInfoService.findByAccountUUID(accountUUID);
			RestaurantCategoryRelVo relVo = restaurantCategoryRelService.updateCategoryRelStatus(rVo, req);
			if (ObjectUtils.allNotNull(relVo)) {
				map = RespData.of(Status.TRUE, null, relVo);	
			}else {
				map = RespData.of(Status.FALSE, errorType.UPDATE_ERROR, null);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	private static ErrorType checkReqData(ReqData req) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(req.getName())) {
			return ErrorType.INVALID;
		}
		return null;
	}
	
	
	private static ErrorType checkUpdateReqData(ReqData req) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(req.getUuid())) {
			return ErrorType.INVALID;
		}
		
		if (!ObjectUtils.allNotNull(req.getStatus())) {
			return ErrorType.INVALID;
		}
		
		if (!CAN_UPDATE_STATUS.contains(SwitchStatus.of(req.getStatus()))) {
			return ErrorType.INVALID;
		}
		return null;
	}

}
