package com.melonltd.naber.endpoint.controller;

import java.util.LinkedHashMap;
import java.util.List;

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

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.CategoryRelService;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.service.RestaurantLocationTemplateService;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.CategoryRelVo;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class RestaurantController {
//
	@Autowired
	private RestaurantLocationTemplateService restaurantLocationTemplateService;
	
	@Autowired
	private RestaurantInfoService restaurantInfoService;
	
	@Autowired
	private CategoryRelService categoryRelService;

	private enum SearchType {
		TOP("TOP"), AREA("AREA"), CATEGORY("CATEGORY"), DISTANCE("DISTANCE"), NUKNOWN("NUKNOWN");
		private String name;

		SearchType(String name) {
			this.name = name;
		}

		public static SearchType of(String name) {
			for (int i = 0; i < SearchType.values().length; i++) {
				if (SearchType.values()[i].name.equals(name.toUpperCase())) {
					return SearchType.values()[i];
				}
			}
			return NUKNOWN;
		}
	}

	@ResponseBody
	@PostMapping(value = "restaurant/location/template")
	public ResponseEntity<String> getRestaurantLocationTemplate() {
		LinkedHashMap<String, Object> map = null;
		List<RestaurantInfoVo> list = restaurantLocationTemplateService.findAll();
		map = RespData.of(Status.TRUE, null, list);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "restaurant/list")
	public ResponseEntity<String> getRestaurantList(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);

		LinkedHashMap<String, Object> map = null;

		ErrorType errorType = checkRequest(req);
		if (ObjectUtils.anyNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			List<RestaurantInfoVo> list = getRestaurantsByType(req);
			map = RespData.of(Status.TRUE, null, list);
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "restaurant/detail")
	public ResponseEntity<String> getRestaurantDetail(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);

		LinkedHashMap<String, Object> map = null;
		if (StringUtils.isBlank(req.getUuid())) {
			map = RespData.of(Status.FALSE, ErrorType.INVALID, null);
		}else {
			List<CategoryRelVo> list = categoryRelService.findByRestaurantUUID(req.getUuid());
			map = RespData.of(Status.TRUE, null, list);
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	

	private List<RestaurantInfoVo> getRestaurantsByType(ReqData req) {
		switch (SearchType.of(req.getSearch_type())) {
		case TOP:
			return restaurantInfoService.findByTop(30);
		case AREA:
			return restaurantInfoService.findByArea(req.getArea(), req.getPage());
		case CATEGORY:
			return restaurantInfoService.findByCategory(req.getCategory(), req.getPage());
		case DISTANCE:
			return restaurantInfoService.findByUUIDs(req.getUuids());
		case NUKNOWN:
			return Lists.<RestaurantInfoVo>newArrayList();
		default:
			return Lists.<RestaurantInfoVo>newArrayList();
		}
	}

	private static ErrorType checkRequest(ReqData req) {
		ErrorType error = null;
		
		if (StringUtils.isBlank(req.getSearch_type())) {
			return ErrorType.INVALID;
		}
		
		switch (SearchType.of(req.getSearch_type())) {
//		case TOP:
//			if (StringUtils.isBlank(req.getLongitude()) || StringUtils.isBlank(req.getLatitude())) {
//				error = ErrorType.INVALID;
//			}
//			break;
		case AREA:
			if (StringUtils.isBlank(req.getArea())) {
				error = ErrorType.INVALID;
			}
			break;
		case CATEGORY:
			if (StringUtils.isBlank(req.getCategory())) {
				error = ErrorType.INVALID;
			}
			break;
//		case DISTANCE:
//			if (StringUtils.isBlank(req.getLongitude()) || StringUtils.isBlank(req.getLatitude())) {
//				error = ErrorType.INVALID;
//			}
//			break;
		case NUKNOWN:
		default:
			break;
		}
		return error;
	}

}
