package com.melonltd.naber.endpoint.controller;

import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.vo.ResponseData;
import com.melonltd.naber.rdbms.model.vo.ResponseData.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;


//{
//  "search_type": "TOP/AREA/CATEGORY/DISTANCE",
//  "area": “台北",
//  "page": “1",
//  "restaurant_category": "早餐店",
//  "latitude": "121.23232",
//  "longitude": "23.3434",
//  "top_limit": "30"
//}

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class RestaurantController {

	private enum SearchType {
		TOP, AREA, CATEGORY, DISTANCE
	}
	
	
	@ResponseBody
	@PostMapping(value = "restaurant/list")
	public ResponseEntity<String> login(@RequestParam(value = "data", required = false) String req) {
		String request = Base64Service.decode(req);
		RestaurantInfoVo vo = JsonHelper.json(request, RestaurantInfoVo.class);
//		List<RestaurantInfo> list = restaurantDistanceService.findByDistance();
//		vo = loginService.checkLoginAndChangeStatusAndIntoDeviceToken(vo.getPhone(), vo.getPassword(),vo.getDevice_token(), category);
		LinkedHashMap<String, Object> map = ResponseData.of(Status.TRUE, null, null);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>("", HttpStatus.OK);
	}

}
