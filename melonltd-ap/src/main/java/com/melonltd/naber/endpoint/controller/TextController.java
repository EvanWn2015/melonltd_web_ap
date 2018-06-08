package com.melonltd.naber.endpoint.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.push.service.AnpsPushServcie;
import com.melonltd.naber.rdbms.model.stored.bean.RestaurantStored;
import com.melonltd.naber.rdbms.model.stored.service.RestaurantStoredService;
import com.melonltd.naber.rdbms.model.stored.vo.RestaurantStoredVo;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class TextController {
	
	@Autowired
	private AnpsPushServcie anpsPushServcie;
	
	@Autowired
	private RestaurantStoredService restaurantStoredService;
	
	@ResponseBody
	@GetMapping(value = "test/push")
	public ResponseEntity<String> textPush() {
		
		List<RestaurantStoredVo> list = restaurantStoredService.findByLatlng("24.9543881","121.2019457",1);
		
		list.stream().forEachOrdered(a ->{
			System.out.println(a);
		});
		System.out.println("data"+JsonHelper.toJson(list));
		
//		List<CanStoreRange> ll = JsonHelper.jsonArray("[{\"status\":\"Y\",\"date\":\"08:31 ~ 09:00\"},{\"status\":\"N\",\"date\":\"09:01 ~ 09:30\"}]",CanStoreRange.class);
//		anpsPushServcie.push("A946C4AE9F0C7EDF27AA75D49FE617C1304F921BBFCEE87793CB0BE28C41A442", "", "");
		return new ResponseEntity<String>(JsonHelper.toJson(list), HttpStatus.OK);
	}
	
	


}
