package com.melonltd.naber.endpoint.controller.common;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.service.ActivitiesService;
import com.melonltd.naber.rdbms.model.vo.ActivitiesVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class ActivitiesController {

	@Autowired
	private ActivitiesService activitiesService;
	
	@ResponseBody
	@PostMapping(value = "common/activities/list")
	public ResponseEntity<String> getAllAct(){
		List<ActivitiesVo> list = activitiesService.findByActCategoryAnfFunc("POINT", "UNLIMITED");
		LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, list);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
}
