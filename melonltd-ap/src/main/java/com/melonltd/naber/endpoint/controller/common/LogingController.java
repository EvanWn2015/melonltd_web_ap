package com.melonltd.naber.endpoint.controller.common;

import java.util.LinkedHashMap;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.melonltd.naber.rdbms.model.facade.service.LoginService;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.MobileDeviceService;
import com.melonltd.naber.rdbms.model.type.DeviceCategory;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.MobileDeviceVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;


// 
@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class LogingController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogingController.class);

	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MobileDeviceService mobileDeviceService;

	// 
	@ResponseBody
	@PostMapping(value = "login")
	public ResponseEntity<String> login(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		AccountInfoVo vo = JsonHelper.json(request, AccountInfoVo.class);
		DeviceCategory category = DeviceCategory.of(vo.getDevice_category());

		vo.setAccount(vo.getPhone());
		vo = loginService.checkLoginAndChangeStatusAndIntoDeviceToken(vo.getAccount(), vo.getPassword(),vo.getDevice_token(), category);
		
		LinkedHashMap<String, Object> map = null;
		if (ObjectUtils.anyNotNull(vo)) {
			 map = RespData.of(Status.TRUE, null, vo);
			 LOGGER.info("login user Account: {}, AccountUUID: {}, Identity: {}, Device: {}, {}", vo.getAccount(), vo.getAccount_uuid(), vo.getIdentity(), vo.getDevice_token(), category );
		}else {
			 map = RespData.of(Status.FALSE, ErrorType.LOGIN_FAIL, vo);
		}
		
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "logout")
	public ResponseEntity<String> logout(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		MobileDeviceVo vo = JsonHelper.json(request, MobileDeviceVo.class);
		accountInfoService.refreshLoginStatus(vo.getAccount_uuid());
		mobileDeviceService.remove(vo);
		
		
		LOGGER.info("logout user AccountUUID: {}, Device: {}, {}", vo.getAccount_uuid(), vo.getDevice_token(), vo.getDevice_category());
		LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, "");
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
