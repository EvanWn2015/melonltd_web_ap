package com.melonltd.naber.endpoint.controller;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.vo.json.data.OredeSubimtReq;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class AccountController {

	
	@ResponseBody
	@PostMapping(value = "account/update/passwoed")
	public ResponseEntity<String> changePassword(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
//		String request = Base64Service.decode(data);
		OredeSubimtReq req = JsonHelper.json(data, OredeSubimtReq.class);
		
		String accountUUID = httpRequest.getHeader("Authorization");
		
		
		
		
		
		
		
		LinkedHashMap<String, Object> map = null;
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(JsonHelper.toJson(map), HttpStatus.OK);
	}

}
