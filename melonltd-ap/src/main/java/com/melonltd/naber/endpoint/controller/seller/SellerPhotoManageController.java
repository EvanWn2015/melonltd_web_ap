package com.melonltd.naber.endpoint.controller.seller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.melonltd.naber.rdbms.model.bean.RestaurantPhotoRel;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.RestaurantPhotoRelService;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantPhotoRelVo;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class SellerPhotoManageController {
	

	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private RestaurantPhotoRelService restaurantPhotoRelService;
	
	
	@ResponseBody
	@PostMapping(value = "seller/setting/photo/list")
	public ResponseEntity<String> list(HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		LinkedHashMap<String, Object> map = null;
		if (!ObjectUtils.allNotNull(account, account.getRestaurant_uuid())) {
			map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
		} else {
			List<RestaurantPhotoRel> restaurantPhotoList = restaurantPhotoRelService.findByRestaurantUUID(account.getRestaurant_uuid());
			List<RestaurantPhotoRelVo> list = RestaurantPhotoRelVo.valueOfArray(restaurantPhotoList);
			
			if (!ObjectUtils.allNotNull(list)) {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			} else {
				map = RespData.of(Status.TRUE, null, list);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "seller/setting/photo/upload")
	public ResponseEntity<String> upload(HttpServletRequest httpRequest, @RequestParam(value = "data", required = false) String data) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		
		String request = Base64Service.decode(data);
		RestaurantPhotoRelVo req = JsonHelper.json(request, RestaurantPhotoRelVo.class);
		
		LinkedHashMap<String, Object> map = null;
		if (!ObjectUtils.allNotNull(req, account, account.getRestaurant_uuid())) {
			map = RespData.of(Status.FALSE, ErrorType.INVALID, null);
		} else {
			req.setRestaurant_uuid(account.getRestaurant_uuid());
			RestaurantPhotoRel info = restaurantPhotoRelService.save(req);
			if (!ObjectUtils.allNotNull(info)) {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			} else {
				map = RespData.of(Status.TRUE, null, RestaurantPhotoRelVo.valueOf(info));
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	

	@ResponseBody
	@PostMapping(value = "seller/setting/photo/delete")
	public ResponseEntity<String> delete(HttpServletRequest httpRequest, @RequestParam(value = "data", required = false) String data) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		
		LinkedHashMap<String, Object> map = null;
		if (!ObjectUtils.allNotNull(account, req, req.getId())) {
			map = RespData.of(Status.FALSE, ErrorType.INVALID, null);
		} else {
			if (!StringUtils.isNumeric(req.getId())) {
				map = RespData.of(Status.FALSE, ErrorType.INVALID, null);
			}else {
				Long id = NumberUtils.toLong(req.getId());
				boolean status = restaurantPhotoRelService.delete(id);
				if (!status) {
					map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
				} else {
					map = RespData.of(Status.TRUE, null, "OK");
				}
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
}
