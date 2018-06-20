package com.melonltd.naber.endpoint.controller;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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

import com.melonltd.naber.constant.RegexConstant;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.push.service.MailSendService;
import com.melonltd.naber.rdbms.model.req.vo.AccountReq;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class AccountController {
	private static final Logger LOGGERO = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private MailSendService mailSendService;

	@ResponseBody
	@PostMapping(value = "account/update/password")
	public ResponseEntity<String> changePassword(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		AccountReq req = JsonHelper.json(request, AccountReq.class);

		String accountUUID = httpRequest.getHeader("Authorization");

		LinkedHashMap<String, Object> map = null;

		ErrorType error = checkRequest(req, true);
		if (ObjectUtils.anyNotNull(error)) {
			map = RespData.of(Status.FALSE, error, null);
		} else {
			AccountInfoVo vo = accountInfoService.getCacheBuilderByKey(accountUUID, true);
			if (!StringUtils.equals(vo.getPhone(), req.getPhone())) {
				map = RespData.of(Status.FALSE, ErrorType.INVALID_PHONE, null);
			} else if (!StringUtils.equals(vo.getPassword(), req.getOld_password())) {
				map = RespData.of(Status.FALSE, ErrorType.INVALID_PASSWORD_ORIGINAL, null);
			} else {
				vo.setPassword(req.getPassword());
				boolean status = accountInfoService.update(vo);
				if (status) {
					map = RespData.of(Status.TRUE, null, null);
				} else {
					map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
				}
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "account/forget/password")
	public ResponseEntity<String> forgetPassword(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		AccountReq req = JsonHelper.json(request, AccountReq.class);

		LinkedHashMap<String, Object> map = null;

		ErrorType error = checkRequest(req, false);
		if (ObjectUtils.anyNotNull(error)) {
			map = RespData.of(Status.FALSE, error, null);
		} else {
			AccountInfoVo vo = accountInfoService.findByAccount(req.getPhone());
			if (!ObjectUtils.anyNotNull(vo)) {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			} else {
				// TODO 忘記密碼相關信息與 密碼長度
//				String newPassword = Tools.getRandomPassword(8);
				boolean status = false;
				try {
//					mailSendService.sendEmail(vo.getEmail(), "forgetPassword", newPassword);
					mailSendService.sendEmail(vo.getEmail(), "forgetPassword", vo.getPassword());
					status = true;
				} catch (UnsupportedEncodingException | MessagingException e) {
					LOGGERO.error("mail send forget password fail phone:{}, mail:{}, msg:{}", req.getPhone(), vo.getEmail(), e.getMessage());
					status = false;
				}
				if (status) {
					map = RespData.of(Status.TRUE, null, null);
//					vo.setPassword(newPassword);
//					if (accountInfoService.update(vo)) {
//						map = RespData.of(Status.TRUE, null, null);
//					} else {
//						map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
//					}
				} else {
					map = RespData.of(Status.FALSE, ErrorType.MAIL_SEND_ERROR, null);
				}
			}
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	private ErrorType checkRequest(AccountReq req, boolean isChange) {

		if (!ObjectUtils.anyNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (isChange) {
			if (!ObjectUtils.anyNotNull(req.getOld_password(), req.getPassword(), req.getPhone())) {
				return ErrorType.INVALID;
			}

			if (!req.getPassword().matches(RegexConstant.REGEX_PASSWORD)) {
				return ErrorType.INVALID_PASSWORD;
			}

			if (StringUtils.equals(req.getPassword(), req.getOld_password())) {
				return ErrorType.INVALID_PASSWORD_REPEAT;
			}
		} else {
			if (!ObjectUtils.anyNotNull(req.getPhone())) {
				return ErrorType.INVALID;
			}
		}

		return null;
	}

}
