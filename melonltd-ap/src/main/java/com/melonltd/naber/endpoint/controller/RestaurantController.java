package com.melonltd.naber.endpoint.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

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
import com.melonltd.naber.rdbms.model.bean.FoodInfo;
import com.melonltd.naber.rdbms.model.bean.RestaurantPhotoRel;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.CategoryRelService;
import com.melonltd.naber.rdbms.model.service.FoodInfoSerice;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.service.RestaurantLocationTemplateService;
import com.melonltd.naber.rdbms.model.service.RestaurantPhotoRelService;
import com.melonltd.naber.rdbms.model.vo.CategoryRelVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;
import com.melonltd.naber.rdbms.model.vo.StorePhotoVo;
import com.melonltd.naber.rdbms.model.vo.StorePhotoVo.Type;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class RestaurantController {

	@Autowired
	private RestaurantLocationTemplateService restaurantLocationTemplateService;
	
	@Autowired
	private RestaurantInfoService restaurantInfoService;
	
	@Autowired
	private CategoryRelService categoryRelService;
	
	@Autowired
	private RestaurantPhotoRelService restaurantPhotoRelService;
	
	@Autowired
	private FoodInfoSerice foodInfoSerice;

	// 增加 NOT_SCHOOL, SCHOOL_DIVIDED
	private enum SearchType {
		TOP("TOP"), AREA("AREA"), CATEGORY("CATEGORY"), DISTANCE("DISTANCE"), STORE_NAME("STORE_NAME"), NOT_SCHOOL("NOT_SCHOOL"),SCHOOL_DIVIDED("SCHOOL_DIVIDED"), NUKNOWN("NUKNOWN");
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
	
	// TODO
	//　餐館圖片集合
	@ResponseBody
	@PostMapping(value = "restaurant/photo/list")
	public ResponseEntity<String> getRestaurantPhotoDetail(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);

		LinkedHashMap<String, Object> map = null;
		if (StringUtils.isBlank(req.getUuid())) {
			map = RespData.of(Status.FALSE, ErrorType.INVALID, null);
		}else {
			List<RestaurantPhotoRel> restaurantPhotoList = restaurantPhotoRelService.findByRestaurantUUID(req.getUuid());
			List<FoodInfo> foodList = foodInfoSerice.findByRestaurantUUID(req.getUuid());
			
			List<StorePhotoVo> list = Lists.newArrayList();
			list.addAll(restaurantPhotoList.stream()
				.map(a -> StorePhotoVo.newInstance(Type.STORE, a.getPhoto(), ""))
				.collect(Collectors.toList()));
			
			list.addAll(foodList.stream()
				.filter(a -> StringUtils.isNotBlank(a.getPhoto()))
				.map(a -> StorePhotoVo.newInstance(Type.FOOD, a.getPhoto(), a.getFoodUUID()))
				.collect(Collectors.toList()));
			Collections.shuffle(list);
			map = RespData.of(Status.TRUE, null, list);
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	

	private List<RestaurantInfoVo> getRestaurantsByType(ReqData req) {
		switch (SearchType.of(req.getSearch_type())) {
		case SCHOOL_DIVIDED:
			return restaurantInfoService.findByAreaAndName(req.getArea(), req.getName(), req.getPage());
		case NOT_SCHOOL:
			return restaurantInfoService.findByNotSchool(req.getPage());
		case TOP:
			return restaurantInfoService.findByTop(30);
		case AREA:
			return restaurantInfoService.findByArea(req.getArea(), req.getPage());
		case CATEGORY:
			return restaurantInfoService.findByCategory(req.getCategory(), req.getPage());
		case STORE_NAME:
			return restaurantInfoService.findByAdd(req.getName(), req.getPage());
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
		case SCHOOL_DIVIDED:
			error = StringUtils.isAnyBlank(req.getArea(),req.getName()) ? ErrorType.INVALID : null;
			break;
		case AREA:
			error = StringUtils.isBlank(req.getArea()) ? ErrorType.INVALID : null;
			break;
		case CATEGORY:
			error = StringUtils.isBlank(req.getCategory()) ? ErrorType.INVALID : null;
			break;
		case STORE_NAME:
			error = StringUtils.isBlank(req.getName()) ? ErrorType.INVALID : null;
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
