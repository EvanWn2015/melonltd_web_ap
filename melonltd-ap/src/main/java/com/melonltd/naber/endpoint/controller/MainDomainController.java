package com.melonltd.naber.endpoint.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "" })
public class MainDomainController {
	@RequestMapping(value = { "", "/" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> getMainDomain() {
		return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}
}