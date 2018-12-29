package com.melonltd.naber.endpoint.controller;

import java.util.LinkedHashMap;
import java.util.List;

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
import com.melonltd.naber.rdbms.model.bean.AccountInfo;
import com.melonltd.naber.rdbms.model.bean.SellerRegistered;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.SellerRegisteredService;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.Identity;
import com.melonltd.naber.rdbms.model.type.UUIDType;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.SellerRegisteredVo;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class RegisteredController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisteredController.class);
	private static List<Identity> needSchoolName = Identity.getNeedSchoolEnumValues();

	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private SellerRegisteredService sellerRegisteredService;

	@ResponseBody
	@PostMapping(value = "registered/user")
	public ResponseEntity<String> registeredUser(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		AccountInfoVo vo = JsonHelper.json(request, AccountInfoVo.class);

		ErrorType error = verifyUser(vo);
		LinkedHashMap<String, Object> map = null;
		if (error == null) {
			vo.setAccount(vo.getPhone());
			AccountInfo info = accountInfoService.save(vo, UUIDType.USER, Enable.Y);
			if (ObjectUtils.allNotNull(info)) {
				map = RespData.of(Status.TRUE, null, AccountInfoVo.of(info, false));
			} else {
				LOGGER.warn("save user error : phone: {}, name: {}", vo.getPhone(), vo.getName());
				map = RespData.of(Status.FALSE, ErrorType.ACCOUNT_ERROR, null);
			}
		} else {
			LOGGER.warn("save user error account vo : {}", vo.toString());
			map = RespData.of(Status.FALSE, error, null);
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "registered/seller")
	public ResponseEntity<String> registeredSeller(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		SellerRegisteredVo vo = JsonHelper.json(request, SellerRegisteredVo.class);
		
		ErrorType error = verifySeller(vo);
		LinkedHashMap<String, Object> map = null;

		if (error == null) {
			SellerRegistered info = sellerRegisteredService.save(vo);
			if (ObjectUtils.allNotNull(info)) {
				map = RespData.of(Status.TRUE, null, null);
			} else {
				LOGGER.warn("save seller error, seller name : {}, device id : {}", vo.getSeller_name(), vo.getDevice_id());
				map = RespData.of(Status.FALSE, ErrorType.SAVE_ERROR, null);
			}
		} else {
			map = RespData.of(Status.FALSE, error, null);
		}
		System.out.println(JsonHelper.toJson(map));
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	/**
	 * @param vo
	 * @return verify name, email, password, phone, address, identity, school_name
	 */
	private static ErrorType verifyUser(AccountInfoVo vo) {
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
//		if (!ObjectUtils.allNotNull(vo.getEmail())) {
//			return ErrorType.INVALID_EMAIL;
//		}
//		if (!vo.getEmail().matches(RegexConstant.REGEX_EMAIL)) {
//			return ErrorType.INVALID_EMAIL;
//		}
		
//		if (!ObjectUtils.allNotNull(vo.getAddress())) {
//			return ErrorType.INVALID;
//		}
		
		// 驗證 手機號、生日、性別 不為空
//		if (StringUtils.isAnyEmpty(vo.getPhone(), vo.getBirth_day(), vo.getGender())) {
//			return ErrorType.INVALID;
//		}
		
		if (StringUtils.isBlank(vo.getPhone())) {
			return ErrorType.INVALID;
		}
		
		// 驗證 手機格式
		if (!vo.getPhone().matches(RegexConstant.REGEX_PHONE)) {
			return ErrorType.INVALID;
		}
		
		if (!ObjectUtils.allNotNull(vo.getIdentity())) {
			return ErrorType.INVALID;
		}
		if (needSchoolName.contains(Identity.of(vo.getIdentity()))) {
			if (!ObjectUtils.allNotNull(vo.getSchool_name())) {
				return ErrorType.INVALID_SCHOOL;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param vo
	 * @return verify name, sellerName, phone, address
	 */
	private ErrorType verifySeller(SellerRegisteredVo vo) {

		if (!ObjectUtils.allNotNull(vo)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(vo.getName())) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(vo.getSeller_name())) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(vo.getPhone())) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(vo.getAddress())) {
			return ErrorType.INVALID;
		}
		return null;
	}
}
