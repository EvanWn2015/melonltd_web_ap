package com.melonltd.naber.rdbms.model.facade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.OrderInfo;
import com.melonltd.naber.rdbms.model.bean.OrderLog;
import com.melonltd.naber.rdbms.model.bean.UserOrderLog;
import com.melonltd.naber.rdbms.model.req.vo.OredeSubimtReq;
import com.melonltd.naber.rdbms.model.service.OrderInfoService;
import com.melonltd.naber.rdbms.model.service.OrderLogService;
import com.melonltd.naber.rdbms.model.service.UserOrderLogService;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.vo.OrderVo;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Service("submitOrderService")
public class SubmitOrderService {

	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private UserOrderLogService userOrderLogService;
	@Autowired
	private OrderLogService orderLogService;

	public void findByAccountUUIDAndPage(String accountUUID, int page) {

	}

	public void getFoodStatusOpenByUUIDs(String[] foodUUIDs) {

	}
	
	public OrderVo submitOrder(String accountUUID, String orderUUID, RestaurantInfoVo vo, OredeSubimtReq req, String price, String bonus, String orders) {
		String date = Tools.getNowUTC();
		OrderInfo orderInfo = newOrderInfo(accountUUID, orderUUID, vo, req, price, bonus, orders, date);
		UserOrderLog userOrderLog = newUserOrderLog(accountUUID, orderUUID, vo, req, price, bonus, orders, date);
		OrderLog orderLog = newOrderLog(accountUUID, orderUUID, vo, req, price, bonus, orders, date);
		orderInfoService.save(orderInfo);
		orderLogService.save(orderLog);
		return userOrderLogService.save(userOrderLog);
	}
	

	private static OrderInfo newOrderInfo(String accountUUID, String orderUUID, RestaurantInfoVo vo, OredeSubimtReq req, String price, String bonus, String orders, String date) {
		OrderInfo info = new OrderInfo();
		info.setAccountUUID(accountUUID);
		info.setOrderUUID(orderUUID);
		info.setRestaurantUUID(vo.getRestaurant_uuid());
		info.setUserMessage(req.getUser_message());
		info.setCreateDate(date);
		info.setUpdateDate(date);
		info.setOrderPrice(price);
		info.setOrderBonus(bonus);
		info.setFetchDate(req.getFetch_date());
		info.setOrderData(orders);
		info.setStatus(OrderStatus.UNFINISH.name());
		info.setEnable(Enable.Y.name());
		return info;
	}
	
	private static OrderLog newOrderLog(String accountUUID, String orderUUID, RestaurantInfoVo vo, OredeSubimtReq req, String price, String bonus, String orders, String date) {
		OrderLog info = new OrderLog();
		info.setAccountUUID(accountUUID);
		info.setOrderUUID(orderUUID);
		info.setRestaurantUUID(vo.getRestaurant_uuid());
		info.setUserMessage(req.getUser_message());
		info.setCreateDate(date);
		info.setUpdateDate(date);
		info.setOrderPrice(price);
		info.setOrderBonus(bonus);
		info.setFetchDate(req.getFetch_date());
		info.setOrderData(orders);
		info.setStatus(OrderStatus.UNFINISH.name());
		info.setEnable(Enable.Y.name());
		return info;
	}

	private static UserOrderLog newUserOrderLog(String accountUUID, String orderUUID, RestaurantInfoVo vo, OredeSubimtReq req, String price, String bonus, String orders, String date) {
		UserOrderLog info = new UserOrderLog();
		info.setAccountUUID(accountUUID);
		info.setOrderUUID(orderUUID);
		info.setRestaurantUUID(vo.getRestaurant_uuid());
		info.setRestaurantName(vo.getName());
		info.setRestaurantAddress(vo.getAddress());
		info.setUserMessage(req.getUser_message());
		info.setCreateDate(date);
		info.setUpdateDate(date);
		info.setOrderPrice(price);
		info.setOrderBonus(bonus);
		info.setFetchDate(req.getFetch_date());
		info.setOrderData(orders);
		info.setStatus(OrderStatus.UNFINISH.name());
		info.setEnable(Enable.Y.name());
		return info;
	}

}
