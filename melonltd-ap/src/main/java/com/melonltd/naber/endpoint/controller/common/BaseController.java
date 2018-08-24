package com.melonltd.naber.endpoint.controller.common;

import java.util.LinkedHashMap;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.service.AppVersionLogService;
import com.melonltd.naber.rdbms.model.vo.AppVersionLogVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
@PropertySource("classpath:/base.properties")
public class BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	private Environment env;
	
	
	@Autowired
	private AppVersionLogService appVersionLogService;
	
	
	// 檢查 android & ios App 版本
	@ResponseBody
	@PostMapping(value = "common/check/app/version")
	public ResponseEntity<String> checkAppVersion(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		LinkedHashMap<String, Object> map = null;
		AppVersionLogVo vo = appVersionLogService.findOneByCategory(request);
		
		if (ObjectUtils.allNotNull(vo)) {
			map = RespData.of(Status.TRUE, null, vo);
		}else {
			map = RespData.of(Status.FALSE,  ErrorType.SERVER_ERROR, null);
		}
		
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	// 取得餐館種類列表
	@ResponseBody
	@PostMapping(value = "common/store/category/list")
	public ResponseEntity<String> getStoreCategorys() {
		String[] springRocks = env.getProperty("base.module.store.category", String[].class);
		LinkedHashMap<String, Object> map = null;
		map = RespData.of(Status.TRUE, null, springRocks);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	
	
}
