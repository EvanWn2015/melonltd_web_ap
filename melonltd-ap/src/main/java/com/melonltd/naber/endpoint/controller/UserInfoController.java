package com.melonltd.naber.endpoint.controller;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.dao.AccountInfoDao;

@Controller
@RequestMapping({ "" })
public class UserInfoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
	private static final int PAGE_SIZE = 10;

	@Autowired
	AccountInfoDao dao;

	@RequestMapping(value = { "user" }, method = { RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> getAllUserByPage(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page) {

//		List<AccountInfo> users = dao.findByPhoneAndPassword("0987654321", "123456");
//		List<AccountInfo> uuidusers = dao.findByAccountUUID("USER_20180601_97cb451b-6db1-45fd-a92d-9ee3271de286");
		System.out.println(Tools.buildAccountUUID(UUIDType.USER));

		LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
		map.put("status", "true");
		// data.put("error", "");
		map.put("data", "");

		String rr = JsonHelper.toJson(map);
		System.out.println(rr);

		String result = Base64Service.encode(rr);
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@RequestMapping(value = { "users" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> getAllUsers() {
		// List<TestTable> tests = this.service.findAll();
		String result = Base64Service.encode(JsonHelper.toJson(""));
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
