package com.melonltd.naber.endpoint.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.push.service.AnpsPushServcie;
import com.melonltd.naber.rdbms.model.push.service.MailSendService;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class TextController {
	
	@Autowired
	private AnpsPushServcie anpsPushServcie;
	
	@Autowired
	private MailSendService mailSendService;
	
	@ResponseBody
	@GetMapping(value = "test/push")
	public ResponseEntity<String> textPush() {
		
		try {
			mailSendService.sendEmail("abdias.yang@melonltd.com.tw", "修改", "new password is : " + Tools.getRandomPassword(8));
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		anpsPushServcie.push("anpsPushServcie", "XXXX", "my test");
//		List<RestaurantStoredVo> list = restaurantStoredService.findByLatlng("24.9543881","121.2019457",1);
//		
//		list.stream().forEachOrdered(a ->{
//			System.out.println(a);
//		});
//		System.out.println("data"+JsonHelper.toJson(list));
		
//		List<CanStoreRange> ll = JsonHelper.jsonArray("[{\"status\":\"Y\",\"date\":\"08:31 ~ 09:00\"},{\"status\":\"N\",\"date\":\"09:01 ~ 09:30\"}]",CanStoreRange.class);
//		anpsPushServcie.push("A946C4AE9F0C7EDF27AA75D49FE617C1304F921BBFCEE87793CB0BE28C41A442", "", "");
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	


}
