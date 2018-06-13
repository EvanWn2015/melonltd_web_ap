package com.melonltd.naber.endpoint.controller;

import java.util.LinkedHashMap;

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
import com.melonltd.naber.rdbms.model.service.VerifyPhoneLogService;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.VerifyPhoneLogVo;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class VerifyController {

	@Autowired
	VerifyPhoneLogService verifyPhoneLogService;

	// req="0987654321" phone number
	@ResponseBody
	@PostMapping(value = "sms/get/code")
	public ResponseEntity<String> sendSMSGetVerifyCode(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		VerifyPhoneLogVo req = JsonHelper.json(request, VerifyPhoneLogVo.class);
		LinkedHashMap<String, Object> map = null;
		ErrorType errorType = checkReqData(req);
		if (ObjectUtils.anyNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			String batchId = verifyPhoneLogService.sendSMS(req.getPhone());
			if (batchId != null) {
				req.setBatch_id(batchId);
				map = RespData.of(Status.TRUE, null, req);
			} else {
				map = RespData.of(Status.FALSE, ErrorType.SEND_SMS_FAIL, null);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "sms/verify/code")
	public ResponseEntity<String> verifyCode(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		VerifyPhoneLogVo req = JsonHelper.json(request, VerifyPhoneLogVo.class);
		LinkedHashMap<String, Object> map = null;
		if (StringUtils.isBlank(req.getCode()) || req.getCode().length() != 6) {
			map = RespData.of(Status.FALSE, ErrorType.VERIFY_CODE_FAIL, "");
		} else {
			ErrorType error = verifyPhoneLogService.verifyCode(req.getBatch_id(), req.getCode());
			map = RespData.of(error == null ? Status.TRUE : Status.FALSE, error, null);
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	
	private static ErrorType checkReqData(VerifyPhoneLogVo vo) {
		if (!ObjectUtils.anyNotNull(vo)) {
			return ErrorType.INVALID;
		}
		
		if (!ObjectUtils.anyNotNull(vo.getPhone())) {
			return ErrorType.INVALID;
		}
		
		return null;
	}

}
