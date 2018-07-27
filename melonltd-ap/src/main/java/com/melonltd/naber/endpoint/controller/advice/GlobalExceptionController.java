package com.melonltd.naber.endpoint.controller.advice;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.stream.MalformedJsonException;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;


// 全局錯誤處理
@ControllerAdvice
public class GlobalExceptionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleCustomException(HttpServletRequest req, RuntimeException ex) {
		LOGGER.error("Server Error , {}", ex.getMessage());
		LinkedHashMap<String, Object> map = RespData.of(Status.FALSE, ErrorType.RESOLVE_ERROR, null);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@ExceptionHandler(MalformedJsonException.class)
	public ResponseEntity<String> handleJsonCustomException(HttpServletRequest req, MalformedJsonException ex) {
		LOGGER.error("JSON RESOLVE Error , {}", ex.getMessage());
		LinkedHashMap<String, Object> map = RespData.of(Status.FALSE, ErrorType.JSON_RESOLVE_ERROR, null);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
}
