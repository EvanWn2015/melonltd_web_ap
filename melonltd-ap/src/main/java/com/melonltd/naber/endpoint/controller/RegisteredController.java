package com.melonltd.naber.endpoint.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melonltd.naber.constant.RegexConstant;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools.AccountType;
import com.melonltd.naber.rdbms.model.bean.AccountInfo;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.type.Identity;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.ResponseData;
import com.melonltd.naber.rdbms.model.vo.ResponseData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.ResponseData.Status;

@Controller
@RequestMapping(value = { "" }, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class RegisteredController {

	private static List<Identity> needSchoolName = Identity.getNeedSchoolEnumValues();

	@Autowired
	AccountInfoService accountInfoService;
	
	
	@ResponseBody
	@PostMapping(value = "registered/user")
	public ResponseEntity<String> registeredUser(@RequestParam(value = "data", required = false) String req) {
		String request = Base64Service.decode(req);
		AccountInfoVo vo = JsonHelper.json(request, AccountInfoVo.class);

		ErrorType error = verify(vo);
		LinkedHashMap<String, Object> map = null;
		if (error == null) {
			AccountInfo info = accountInfoService.save(vo,AccountType.USER);
			if (ObjectUtils.allNotNull(info)) {
				map = ResponseData.of(Status.TRUE, null, "");
			}else {
				map = ResponseData.of(Status.FALSE, ErrorType.SAVE_ERROR, "");
			}
		}else {
			map = ResponseData.of(Status.FALSE, error, "");
		}
		
		System.out.println(JsonHelper.toJson(map));
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	/**
	 * 
	 * @param vo
	 * @return verify name, email, password, phone, address, identity, school_name
	 */
	private static ErrorType verify(AccountInfoVo vo) {
		if (!ObjectUtils.allNotNull(vo)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(vo.getName())) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(vo.getPassword())) {
			return ErrorType.INVALID;
		}
		if (vo.getPassword().length() < 6 || vo.getPassword().length() > 20) {
			return ErrorType.INVALID_PASSWORD;
		}
		if (!vo.getPassword().matches(RegexConstant.REGEX_PASSWORD)) {
			return ErrorType.INVALID_PASSWORD;
		}
		if (!ObjectUtils.allNotNull(vo.getEmail())) {
			return ErrorType.INVALID_EMAIL;
		}
		if (!vo.getEmail().matches(RegexConstant.REGEX_EMAIL)) {
			return ErrorType.INVALID_EMAIL;
		}
		if (!ObjectUtils.allNotNull(vo.getAddress())) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(vo.getIdentity())) {
			return ErrorType.INVALID;
		}
		if (needSchoolName.contains(Identity.of(vo.getIdentity()))) {
			if (!ObjectUtils.allNotNull(vo.getSchoolName())) {
				return ErrorType.INVALID_SCHOOL;
			}
		}
		return null;
	}
}
