package com.melonltd.naber.endpoint.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.melonltd.naber.rdbms.model.service.AccountInfoService;

@Controller
@RequestMapping({ "" })
public class LogingController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogingController.class);
	
	@Autowired
	AccountInfoService accountInfoService;
	

}
