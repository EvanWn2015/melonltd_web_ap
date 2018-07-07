package com.melonltd.naber.endpoint.controller;

import java.util.LinkedHashMap;
import java.util.List;

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
import com.melonltd.naber.rdbms.model.service.FoodInfoSerice;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.FoodInfoVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class FoodMenuController {

	@Autowired
	private FoodInfoSerice foodInfoSerice;

	@ResponseBody
	@PostMapping(value = "restaurant/food/list")
	public ResponseEntity<String> getFoodList(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		LinkedHashMap<String, Object> map = null;

		if (StringUtils.isBlank(req.getUuid())) {
			map = RespData.of(Status.FALSE, ErrorType.INVALID, null);
		} else {
			List<FoodInfoVo> list = foodInfoSerice.findByStatusAndCategoryUUID(SwitchStatus.OPEN.name(), req.getUuid());
			map = RespData.of(Status.TRUE, null, list);
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "restaurant/food/detail")
	public ResponseEntity<String> getFoodDetail(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		LinkedHashMap<String, Object> map = null;

		if (StringUtils.isBlank(req.getUuid())) {
			map = RespData.of(Status.FALSE, ErrorType.INVALID, null);
		} else {
			FoodInfoVo vo = foodInfoSerice.findByFoodUUID(req.getUuid());
			map = RespData.of(Status.TRUE, null, vo);
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
