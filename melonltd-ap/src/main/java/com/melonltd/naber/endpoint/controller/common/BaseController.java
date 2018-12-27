package com.melonltd.naber.endpoint.controller.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
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

import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.BasisContent;
import com.melonltd.naber.rdbms.model.service.AppVersionLogService;
import com.melonltd.naber.rdbms.model.service.BasisContentService;
import com.melonltd.naber.rdbms.model.service.IdentityTableService;
import com.melonltd.naber.rdbms.model.vo.AdministrativeRegionsVo;
import com.melonltd.naber.rdbms.model.vo.AppVersionLogVo;
import com.melonltd.naber.rdbms.model.vo.IdentityTableVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.SchoolDividedVo;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	private BasisContentService basisContentService;

	@Autowired
	private AppVersionLogService appVersionLogService;

<<<<<<< HEAD
=======
	@Autowired
	private IdentityTableService identityTableService;

	// 檢查 android & ios App 版本
>>>>>>> feature/v1.0.2_developer_evan
	@ResponseBody
	@PostMapping(value = "common/check/app/version")
	public ResponseEntity<String> checkAppVersion(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		LinkedHashMap<String, Object> map = null;
		AppVersionLogVo vo = appVersionLogService.findOneByCategory(request);

		if (ObjectUtils.allNotNull(vo)) {
			map = RespData.of(Status.TRUE, null, vo);
		} else {
			map = RespData.of(Status.FALSE, ErrorType.SERVER_ERROR, null);
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "common/app/intro/bulletin")
	public ResponseEntity<String> getAppIntroBulletin() {
		BasisContent info = basisContentService.getAppIntro();
<<<<<<< HEAD
=======
		// List<String> categorys = JsonHelper.jsonArray(info.getContent(),
		// String[].class);
>>>>>>> feature/v1.0.2_developer_evan
		LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, info.getContent());
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// 取得餐館種類列表
	@ResponseBody
	@PostMapping(value = "common/store/category/list")
	public ResponseEntity<String> getStoreCategorys() {
		BasisContent info = basisContentService.getStoreCategorys();
		List<String> categorys = JsonHelper.jsonArray(info.getContent(), String[].class);
		LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, categorys);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// 取得餐館區域列表
	@ResponseBody
	@PostMapping(value = "common/store/area/list")
	public ResponseEntity<String> getStoreAreas() {
		BasisContent info = basisContentService.getStoreAreas();
		List<String> areas = JsonHelper.jsonArray(info.getContent(), String[].class);
		LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, areas);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// 取得行政區域列表
	@ResponseBody
	@PostMapping(value = "common/subjection/region/list")
	public ResponseEntity<String> getSubjectionRegions() {
		List<AdministrativeRegionsVo> list = basisContentService.findSubjectionRegions();
		LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, list);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// 取得行政區域列表
	@ResponseBody
	@PostMapping(value = "common/school/divided/list")
	public ResponseEntity<String> getSchoolDivided() {
		List<SchoolDividedVo> list = basisContentService.findSchoolDivided();
		LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, list);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "common/identity/table/list")
	public ResponseEntity<String> getIdentityTable() {
		 List<IdentityTableVo> list = identityTableService.findAll();
		LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, list);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
