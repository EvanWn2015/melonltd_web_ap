package com.melonltd.naber.endpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melonltd.naber.rdbms.model.service.push.AnpsPushServcie;

@Controller
@RequestMapping(value = { "" })
public class TextController {
	
	@Autowired
	private AnpsPushServcie anpsPushServcie;
	
	@ResponseBody
	@GetMapping(value = "test/push")
	public ResponseEntity<String> textPush() {
		anpsPushServcie.push("A946C4AE9F0C7EDF27AA75D49FE617C1304F921BBFCEE87793CB0BE28C41A442", "", "");
		return new ResponseEntity<String>("", HttpStatus.OK);
	}

}
