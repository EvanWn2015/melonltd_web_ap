package com.melonltd.naber.endpoint.controller.seller;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.melonltd.naber.rdbms.model.facade.service.ChangeOrdarService;
import com.melonltd.naber.rdbms.model.push.service.PudhSellerService;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.vo.OrderVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class ChangeOrdarController {

	private static List<OrderStatus> CAN_NOTIFY_TYPE = OrderStatus.getNotifyToUserType();
	
	@Autowired
	private ChangeOrdarService changeOrdarService;
	
	@Autowired
	private PudhSellerService pudhSellerService;

	@ResponseBody
	@PostMapping(value = "seller/update/order")
	public ResponseEntity<String> updateOrder(@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);

		ErrorType errorType = checkReqData(req);
		LinkedHashMap<String, Object> map = null;
		
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			OrderStatus changeStatus = OrderStatus.of(req.getType());
			OrderVo vo = changeOrdarService.updateOrder(req.getUuid(), changeStatus);
			
			if (ObjectUtils.allNotNull(vo)) {
				map = RespData.of(Status.TRUE, null, vo);	
				// TODO notify to user
				if (CAN_NOTIFY_TYPE.contains(changeStatus)) {
					String accountUUID = vo.getAccount_uuid();
					pudhSellerService.pushOrderToUser(accountUUID, changeStatus.getMssage());
				}
			}else {
				map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
			}
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	private ErrorType checkReqData(ReqData req) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}

		if (StringUtils.isAnyBlank(req.getUuid(), req.getType())) {
			return ErrorType.INVALID;
		}

		if (!ObjectUtils.notEqual(OrderStatus.UNFINISH, OrderStatus.of(req.getType()))) {
			return ErrorType.NOT_BE_CHANGED;
		}
		return null;
	}
}
