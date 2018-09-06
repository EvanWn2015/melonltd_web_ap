package com.melonltd.naber.endpoint.controller.seller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

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
import com.melonltd.naber.rdbms.model.facade.service.DeleteCategoryService;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.CategoryRelService;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.CategoryRelVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class SellerCategoryController {

	private static List<SwitchStatus> CAN_UPDATE_STATUS = SwitchStatus.getEnumValues();

	@Autowired
	private CategoryRelService categoryRelService;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private DeleteCategoryService deleteCategoryService;

	@ResponseBody
	@PostMapping(value = "seller/category/list")
	public ResponseEntity<String> getCategoryList(HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		LinkedHashMap<String, Object> map = null;
		if (ObjectUtils.allNotNull(account)) {
			List<CategoryRelVo> list = categoryRelService.findAllByRestaurantUUID(account.getRestaurant_uuid());
			map = RespData.of(Status.TRUE, null, list);
		} else {
			map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "seller/category/add")
	public ResponseEntity<String> addCategory(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String accountUUID = httpRequest.getHeader("Authorization");
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);

		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);

		LinkedHashMap<String, Object> map = null;
		ErrorType errorType = checkReqData(req, account);

		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			CategoryRelVo relVo = categoryRelService.saveCategoryRel(account.getRestaurant_uuid(), req.getName());
			if (ObjectUtils.allNotNull(relVo)) {
				map = RespData.of(Status.TRUE, null, relVo);
			} else {
				map = RespData.of(Status.FALSE, ErrorType.SAVE_ERROR, null);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "seller/category/update")
	public ResponseEntity<String> updateCategory(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String accountUUID = httpRequest.getHeader("Authorization");
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);

		LinkedHashMap<String, Object> map = null;
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		ErrorType errorType = checkUpdateReqData(req, account);

		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			CategoryRelVo relVo = categoryRelService.updateCategoryRelStatus(account.getRestaurant_uuid(), req);
			if (ObjectUtils.allNotNull(relVo)) {
				map = RespData.of(Status.TRUE, null, relVo);
			} else {
				map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
			}
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "seller/category/delete")
	public ResponseEntity<String> deleteCategory(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String accountUUID = httpRequest.getHeader("Authorization");
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);

		LinkedHashMap<String, Object> map = null;
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		ErrorType errorType = checkDeleteReqData(req, account);

		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			CategoryRelVo relVo = deleteCategoryService.deleteCategoryRelStatus(account.getRestaurant_uuid(), req);
			if (ObjectUtils.allNotNull(relVo)) {
				map = RespData.of(Status.TRUE, null, "");
			} else {
				map = RespData.of(Status.FALSE, ErrorType.DELETE_ERROR, null);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "seller/category/sort")
	public ResponseEntity<String> sortCategoryList(HttpServletRequest httpRequest, 
			@RequestParam(value = "data", required = false) String data) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String request = Base64Service.decode(data);
		List<CategoryRelVo> req = JsonHelper.jsonArray(request, CategoryRelVo[].class);
		LinkedHashMap<String, Object> map = null;
		if (ObjectUtils.allNotNull(account)) {
			List<String> categoryUUIDs = req.stream().map(a -> a.getCategory_uuid()).collect(Collectors.toList());
			List<CategoryRelVo> categoryRelVos = categoryRelService.findByCategoryUUIDs(categoryUUIDs, Enable.Y, SwitchStatus.getEnumValues());
			if (categoryRelVos.size() == req.size()) {
				categoryRelVos.forEach(c -> {
					String top = req.stream().filter(r -> r.getCategory_uuid().equals(c.getCategory_uuid())).findFirst().get().getTop();
					c.setTop(top);
				});
				
				List<CategoryRelVo> categoryRels = categoryRelService.saves(categoryRelVos);
				map = RespData.of(Status.TRUE, null, categoryRels);	
			} else {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			}
		} else {
			map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
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

		if (!ObjectUtils.allNotNull(account.getRestaurant_uuid())) {
			return ErrorType.DATABASE_NULL;
		}

		if (!ObjectUtils.allNotNull(req.getName())) {
			return ErrorType.INVALID;
		}
		return null;
	}

	private static ErrorType checkUpdateReqData(ReqData req, AccountInfoVo account) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}

		if (!ObjectUtils.allNotNull(account)) {
			return ErrorType.DATABASE_NULL;
		}

		if (StringUtils.isAnyBlank(account.getRestaurant_uuid(), req.getStatus(), req.getUuid())) {
			return ErrorType.INVALID;
		}

		if (!CAN_UPDATE_STATUS.contains(SwitchStatus.of(req.getStatus()))) {
			return ErrorType.INVALID;
		}
		return null;
	}

	private static ErrorType checkDeleteReqData(ReqData req , AccountInfoVo account) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(account)) {
			return ErrorType.DATABASE_NULL;
		}
		
		if (StringUtils.isAnyBlank(account.getRestaurant_uuid(), req.getUuid())) {
			return ErrorType.DATABASE_NULL;
		}
//		if (req.getPage() == 0) {
//			return ErrorType.DATABASE_NULL;
//		}
		return null;
	}

}
