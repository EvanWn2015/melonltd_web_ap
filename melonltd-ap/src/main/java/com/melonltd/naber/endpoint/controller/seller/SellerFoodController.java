package com.melonltd.naber.endpoint.controller.seller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

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

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.FoodInfo;
import com.melonltd.naber.rdbms.model.req.vo.FoodItemVo;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.CategoryRelService;
import com.melonltd.naber.rdbms.model.service.FoodInfoSerice;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.CategoryRelVo;
import com.melonltd.naber.rdbms.model.vo.FoodInfoVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class SellerFoodController {
	
	private static List<SwitchStatus> CAN_UPDATE_STATUS = Lists.<SwitchStatus>newArrayList(SwitchStatus.OPEN, SwitchStatus.CLOSE);
	@Autowired
	private AccountInfoService accountInfoService;

	@Autowired
	private CategoryRelService categoryRelService;
	
	@Autowired
	private FoodInfoSerice foodInfoSerice;
	
	@ResponseBody
	@PostMapping(value = "seller/food/list")
	public ResponseEntity<String> getFoodList(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		
		String accountUUID = httpRequest.getHeader("Authorization");
		if (!StringUtils.isBlank(accountUUID)) {
			accountInfoService.clearCacheBuilderByKey(accountUUID);
		}
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);

		ErrorType errorType = checkReqData(req, account);
		LinkedHashMap<String, Object> map = null;
		
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			List<FoodInfoVo> list = foodInfoSerice.findBycategoryUUIDAndRestaurantUUID(req.getUuid(), account.getRestaurant_uuid());
			map = RespData.of(Status.TRUE, null, list);
		}
		
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "seller/food/add")
	public ResponseEntity<String> addFood(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		
		String request = Base64Service.decode(data);
		FoodInfoVo req = JsonHelper.json(request, FoodInfoVo.class);
		
		String accountUUID = httpRequest.getHeader("Authorization");
		if (!StringUtils.isBlank(accountUUID)) {
			accountInfoService.clearCacheBuilderByKey(accountUUID);
		}
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);

		ErrorType errorType = checkAddReqData(req, account);
		LinkedHashMap<String, Object> map = null;
		
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			CategoryRelVo category = categoryRelService.findByRestaurantUUIDAndCategoryRelUUID(account.getRestaurant_uuid(), req.getCategory_uuid());
			if (ObjectUtils.allNotNull(category)) {
				FoodInfoVo vo = foodInfoSerice.save(req);
				if (ObjectUtils.allNotNull(vo)) {
					map = RespData.of(Status.TRUE, null, vo);
				}else {
					map = RespData.of(Status.FALSE, ErrorType.SAVE_ERROR, null);
				}
			}else {
				map = RespData.of(Status.FALSE, ErrorType.SAVE_ERROR, null);
			}
		}
		
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "seller/food/update")
	public ResponseEntity<String> updateFood(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		
		String request = Base64Service.decode(data);
		FoodInfoVo req = JsonHelper.json(request, FoodInfoVo.class);
		
		String accountUUID = httpRequest.getHeader("Authorization");
		if (!StringUtils.isBlank(accountUUID)) {
			accountInfoService.clearCacheBuilderByKey(accountUUID);
		}
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);

		ErrorType errorType = checkUpdateReqData(req, account);
		LinkedHashMap<String, Object> map = null;
		
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			CategoryRelVo category = categoryRelService.findByRestaurantUUIDAndCategoryRelUUID(account.getRestaurant_uuid(), req.getCategory_uuid());
			if (ObjectUtils.allNotNull(category)) {
				FoodInfo food = foodInfoSerice.findByFoodUUIDAndRestaurantUUID(req.getFood_uuid(), account.getRestaurant_uuid());
				if (ObjectUtils.allNotNull(food)) {
					FoodInfoVo vo = foodInfoSerice.update(req,food);
					if (ObjectUtils.allNotNull(vo)) {
						map = RespData.of(Status.TRUE, null, vo);
					}else {
						map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
					}
				}else {
					map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
				}
			}else {
				map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
			}
		}
		
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "seller/food/delete")
	public ResponseEntity<String> deleteFood(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		
		String accountUUID = httpRequest.getHeader("Authorization");
		if (!StringUtils.isBlank(accountUUID)) {
			accountInfoService.clearCacheBuilderByKey(accountUUID);
		}
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);

		ErrorType errorType = checkDeleteReqData(req, account);
		LinkedHashMap<String, Object> map = null;
		
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			FoodInfo info = foodInfoSerice.findByFoodUUIDAndRestaurantUUID(req.getUuid(), account.getRestaurant_uuid());
			if (ObjectUtils.allNotNull(info)) {
				FoodInfoVo vo = foodInfoSerice.delete(info);
				if (ObjectUtils.allNotNull(vo)) {
					map = RespData.of(Status.TRUE, null, "");
				}else {
					map = RespData.of(Status.FALSE, ErrorType.DELETE_ERROR, null);
				}
			}else {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "seller/food/sort")
	public ResponseEntity<String> sortCategoryList(HttpServletRequest httpRequest, 
			@RequestParam(value = "data", required = false) String data) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String request = Base64Service.decode(data);
		List<FoodInfoVo> req = JsonHelper.jsonArray(request, FoodInfoVo[].class);
		LinkedHashMap<String, Object> map = null;
		if (ObjectUtils.allNotNull(account)) {
			List<String> foodUUIDs = req.stream().map(a -> a.getFood_uuid()).collect(Collectors.toList());
			List<FoodInfoVo> foodVos = foodInfoSerice.findByFoodUUIDs(foodUUIDs);
			if (foodVos.size() == req.size()) {
				foodVos.forEach(c -> {
					String top = req.stream().filter(r -> r.getFood_uuid().equals(c.getFood_uuid())).findFirst().get().getTop();
					c.setTop(top);
				});
				List<FoodInfoVo> foodInfoVos = foodInfoSerice.saves(foodVos);
				map = RespData.of(Status.TRUE, null, foodInfoVos);	
			} else {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			}
		} else {
			map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "seller/food/update/status")
	public ResponseEntity<String> changeStatusFood(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		
		String accountUUID = httpRequest.getHeader("Authorization");
		if (!StringUtils.isBlank(accountUUID)) {
			accountInfoService.clearCacheBuilderByKey(accountUUID);
		}
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);

		ErrorType errorType = checkChangeReqData(req, account);
		LinkedHashMap<String, Object> map = null;
		
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			FoodInfo info = foodInfoSerice.findByFoodUUIDAndRestaurantUUID(req.getUuid(), account.getRestaurant_uuid());
			if (ObjectUtils.allNotNull(info)) {
				if (!ObjectUtils.notEqual(SwitchStatus.of(info.getStatus()), SwitchStatus.of(req.getStatus()))) {
					map = RespData.of(Status.FALSE, ErrorType.NOT_BE_CHANGED, null);
				}else {
					FoodInfoVo vo = foodInfoSerice.changeStatus(info, SwitchStatus.of(req.getStatus()));
					if (ObjectUtils.allNotNull(vo)) {
						map = RespData.of(Status.TRUE, null, "");
					}else {
						map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
					}
				}
			}else {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	private static ErrorType checkReqData(ReqData req, AccountInfoVo account) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(account)) {
			return ErrorType.DATABASE_NULL;
		}
		if (StringUtils.isBlank(req.getUuid())) {
			return ErrorType.INVALID;
		}
		if (StringUtils.isBlank(account.getRestaurant_uuid())) {
			return ErrorType.DATABASE_NULL;
		}
		return null;
	}
		
	private static ErrorType checkAddReqData(FoodInfoVo req, AccountInfoVo account) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(account)) {
			return ErrorType.DATABASE_NULL;
		}
		if (StringUtils.isAnyBlank(req.getCategory_uuid(), account.getRestaurant_uuid(), req.getFood_name(), req.getDefault_price())) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(req.getFood_data())) {
			return ErrorType.INVALID;
		}
		
		boolean itemCheck = checkFoodItem(req.getFood_data());
		if (!itemCheck) {
			return ErrorType.FOOD_ITEM_DATA_ERROR;
		}
		
		return null;
	}
	
	private static ErrorType checkUpdateReqData(FoodInfoVo req, AccountInfoVo account) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(account)) {
			return ErrorType.DATABASE_NULL;
		}
		if (StringUtils.isAnyBlank(req.getFood_uuid(), req.getCategory_uuid(), account.getRestaurant_uuid(), req.getFood_name(), req.getDefault_price())) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(req.getFood_data())) {
			return ErrorType.INVALID;
		}
		
		boolean itemCheck = checkFoodItem(req.getFood_data());
		if (!itemCheck) {
			return ErrorType.FOOD_ITEM_DATA_ERROR;
		}
		
		return null;
	}
	
	private static boolean checkFoodItem(FoodItemVo item) {
		long scopes = item.getScopes().stream().filter(s -> !NumberUtils.isCreatable(s.getPrice()) || StringUtils.isBlank(s.getName())).count();
		long opt = item.getOpts().stream().filter(o -> !NumberUtils.isCreatable(o.getPrice()) || StringUtils.isBlank(o.getName())).count();
		long demands = item.getDemands().stream()
				.filter(d -> StringUtils.isBlank(d.getName()) || d.getDatas().stream().filter(dd -> StringUtils.isBlank(dd.getName())).count() != 0).count();
		return scopes == 0 && opt == 0 && demands == 0;
	}
	
	
	private static ErrorType checkDeleteReqData(ReqData req, AccountInfoVo account) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(account)) {
			return ErrorType.DATABASE_NULL;
		}
		if (StringUtils.isAnyBlank(account.getRestaurant_uuid(),req.getUuid())) {
			return ErrorType.INVALID;
		}
		return null;
	}
	
	private static ErrorType checkChangeReqData(ReqData req, AccountInfoVo account) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(account)) {
			return ErrorType.DATABASE_NULL;
		}
		if (StringUtils.isAnyBlank(account.getRestaurant_uuid(),req.getUuid(), req.getStatus())) {
			return ErrorType.INVALID;
		}
		if (!CAN_UPDATE_STATUS.contains(SwitchStatus.of(req.getStatus()))) {
			return ErrorType.INVALID;
		}
		return null;
	}
}
