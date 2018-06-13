package com.melonltd.naber.endpoint.controller.seller;

import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.req.vo.AccountReq;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class ChangeOrdarController {

	
	@ResponseBody
	@PostMapping(value = "seller/update/order")
	public ResponseEntity<String> updateOrder(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		AccountReq req = JsonHelper.json(request, AccountReq.class);

//		ErrorType errorType = checkReqData(req);
		LinkedHashMap<String, Object> map = null;
		

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
