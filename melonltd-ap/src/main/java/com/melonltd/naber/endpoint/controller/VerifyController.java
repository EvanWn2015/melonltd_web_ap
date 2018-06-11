package com.melonltd.naber.endpoint.controller;

import java.util.LinkedHashMap;

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
import com.melonltd.naber.rdbms.model.service.VerifyPhoneLogService;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.json.data.VerifyPhoneLogVo;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class VerifyController {

	@Autowired
	VerifyPhoneLogService verifyPhoneLogService;

	// req="0987654321" phone number
	@ResponseBody
	@PostMapping(value = "sms/get/code")
	public ResponseEntity<String> sendSMSGetVerifyCode(@RequestParam(value = "data", required = false) String req) {
		String request = Base64Service.decode(req);
		VerifyPhoneLogVo vo = JsonHelper.json(request, VerifyPhoneLogVo.class);
		LinkedHashMap<String, Object> map = null;
		String batchId = verifyPhoneLogService.sendSMS(vo.getPhone_number());
		if (batchId != null) {
			vo.setBatch_id(batchId);
			map = RespData.of(Status.TRUE, null, vo);
		} else {
			map = RespData.of(Status.FALSE, ErrorType.SEND_SMS_FAIL, null);
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "sms/verify/code")
	public ResponseEntity<String> verifyCode(@RequestParam(value = "data", required = false) String req) {
		String request = Base64Service.decode(req);
		VerifyPhoneLogVo vo = JsonHelper.json(request, VerifyPhoneLogVo.class);
		LinkedHashMap<String, Object> map = null;
		if (StringUtils.isBlank(vo.getCode()) || vo.getCode().length() != 6) {
			map = RespData.of(Status.FALSE, ErrorType.VERIFY_CODE_FAIL, "");
		} else {
			ErrorType error = verifyPhoneLogService.verifyCode(vo.getBatch_id(), vo.getCode());
			map = RespData.of(error == null ? Status.TRUE : Status.FALSE, error, "");
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
