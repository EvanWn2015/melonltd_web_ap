package com.melonltd.naber.endpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melonltd.naber.rdbms.model.push.service.SMSHttpService;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class TextController {
	
	
	@Autowired
	private SMSHttpService smsHttpService;

	@ResponseBody
	@GetMapping(value = "test/push")
	public ResponseEntity<String> textPush() {
		
		System.out.println(smsHttpService.getCreditValue() + "");
		return new ResponseEntity<String>("AAA", HttpStatus.OK);
	}

}
