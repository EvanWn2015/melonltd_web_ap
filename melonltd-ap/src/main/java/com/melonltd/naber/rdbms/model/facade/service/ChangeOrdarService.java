package com.melonltd.naber.rdbms.model.facade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.OrderInfo;
import com.melonltd.naber.rdbms.model.bean.UserOrderLog;
import com.melonltd.naber.rdbms.model.service.OrderInfoService;
import com.melonltd.naber.rdbms.model.service.OrderLogService;
import com.melonltd.naber.rdbms.model.service.SellerOrderFinishService;
import com.melonltd.naber.rdbms.model.service.UserOrderLogService;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.vo.OrderVo;

@Service("changeOrdarService")
public class ChangeOrdarService {

 	private static List<OrderStatus> FINISH_TYPE = OrderStatus.getSellerFinishType();
 	private static List<OrderStatus> UPDATE_TYPE = OrderStatus.getSellerUpdateType();
 	
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private UserOrderLogService userOrderLogService;
	@Autowired
	private OrderLogService orderLogService;
	@Autowired
	private SellerOrderFinishService sellerOrderFinishService;
	
	
	public OrderVo updateOrder(String orderUUID, String status) {
		OrderInfo orderInfo = orderInfoService.findOne(orderUUID);
		UserOrderLog userOrderLog = userOrderLogService.findOne(orderUUID);
			
		return null;
	}
	
	
	
}
