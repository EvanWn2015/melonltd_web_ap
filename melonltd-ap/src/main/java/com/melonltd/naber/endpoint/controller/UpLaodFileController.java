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

import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.FoodInfoSerice;
import com.melonltd.naber.rdbms.model.type.UpLoadType;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.FoodInfoVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class UpLaodFileController {

	private static final List<UpLoadType> UPLOAD_TYPS = UpLoadType.geTypes();

	@Autowired
	private AccountInfoService accountInfoService;

	@Autowired
	private FoodInfoSerice foodInfoSerice;

	@ResponseBody
	@PostMapping(value = "image/upload")
	public ResponseEntity<String> uploadImage(@RequestParam(value = "data", required = false) String data) {

		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		LinkedHashMap<String, Object> map = null;
		ErrorType errorType = checkRequest(req);

		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			if (UpLoadType.USER.equals(UpLoadType.of(req.getType()))) {
				AccountInfoVo vo = accountInfoService.getCacheBuilderByKey(req.getUuid(), false);
				if (ObjectUtils.allNotNull(vo)) {
					vo.setPhoto(req.getDate());
					boolean status = accountInfoService.updatePhoto(vo);
					map = status ? RespData.of(Status.TRUE, null, "")
							: RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
				} else {
					map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
				}
			} else if (UpLoadType.FOOD.equals(UpLoadType.of(req.getType()))) {
				FoodInfoVo vo = foodInfoSerice.findByFoodUUID(req.getUuid());
				if (ObjectUtils.allNotNull(vo)) {
					vo.setPhoto(req.getDate());
					boolean status = foodInfoSerice.updatePhoto(vo);
					map = status ? RespData.of(Status.TRUE, null, "")
							: RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
				} else {
					map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
				}
			}
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	private ErrorType checkRequest(ReqData req) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}

		if (StringUtils.isAllBlank(req.getUuid())) {
			return ErrorType.INVALID;
		}

		if (StringUtils.isAllBlank(req.getType())) {
			return ErrorType.INVALID;
		}

		if (StringUtils.isAllBlank(req.getDate())) {
			return ErrorType.INVALID;
		}

		if (!UPLOAD_TYPS.contains(UpLoadType.of(req.getType()))) {
			return ErrorType.INVALID;
		}
		return null;
	}

}
