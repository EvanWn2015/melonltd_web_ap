package com.melonltd.naber.rdbms.model.facade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.OrderInfo;
import com.melonltd.naber.rdbms.model.bean.OrderLog;
import com.melonltd.naber.rdbms.model.bean.UserOrderInfo;
import com.melonltd.naber.rdbms.model.req.vo.OredeSubimtReq;
import com.melonltd.naber.rdbms.model.service.OrderInfoService;
import com.melonltd.naber.rdbms.model.service.OrderLogService;
import com.melonltd.naber.rdbms.model.service.UserOrderInfoService;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.OrderVo;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Service("submitOrderService")
public class SubmitOrderService {

	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private UserOrderInfoService userOrderInfoService;
	@Autowired
	private OrderLogService orderLogService;

	public OrderVo submitOrder(AccountInfoVo account, String orderUUID, RestaurantInfoVo vo, OredeSubimtReq req,
			String price, String bonus, String orders) {
		int unfinish_count = userOrderInfoService.findByOrderStatusAccountUUID(account.getAccount_uuid());
		// 限制未完成訂單數量三筆，不可再提交訂單
		if (unfinish_count >= 3) {
			return null;
		} else {
			String date = Tools.getNowGMT();
			OrderInfo orderInfo = newOrderInfo(account, orderUUID, vo, req, price, bonus, orders, date);
			UserOrderInfo userOrderLog = newUserOrderInfo(account, orderUUID, vo, req, price, bonus, orders, date);
			OrderLog orderLog = newOrderLog(account, orderUUID, vo, req, price, bonus, orders, date);
			orderInfoService.save(orderInfo);
			orderLogService.save(orderLog);
			OrderVo orderVo = userOrderInfoService.save(userOrderLog);
			return orderVo;
		}
	}

	public OrderVo submitTestOrder(AccountInfoVo account, String orderUUID, RestaurantInfoVo vo, OredeSubimtReq req,
			String price, String bonus, String orders) {
		String date = Tools.getNowGMT();
		OrderInfo orderInfo = newOrderInfo(account, orderUUID, vo, req, price, bonus, orders, date);
		UserOrderInfo userOrderLog = newUserOrderInfo(account, orderUUID, vo, req, price, bonus, orders, date);
		OrderLog orderLog = newOrderLog(account, orderUUID, vo, req, price, bonus, orders, date);
		orderInfoService.save(orderInfo);
		orderLogService.save(orderLog);
		OrderVo orderVo = userOrderInfoService.save(userOrderLog);
		return orderVo;
	}

	private static OrderInfo newOrderInfo(AccountInfoVo account, String orderUUID, RestaurantInfoVo vo,
			OredeSubimtReq req, String price, String bonus, String orders, String date) {
		OrderInfo info = new OrderInfo();
		info.setAccountUUID(account.getAccount_uuid());
		info.setOrderUUID(orderUUID);
		info.setRestaurantUUID(vo.getRestaurant_uuid());
		info.setUserMessage(req.getUser_message());
		info.setCreateDate(date);
		info.setUpdateDate(date);
		info.setOrderPrice(price);
		info.setUseBonus(req.getUse_bonus());
		info.setOrderBonus(bonus);
		info.setFetchDate(req.getFetch_date());
		info.setOrderData(orders);
		info.setStatus(OrderStatus.UNFINISH.name());
		info.setEnable(Enable.Y.name());
		return info;
	}

	private static OrderLog newOrderLog(AccountInfoVo account, String orderUUID, RestaurantInfoVo vo,
			OredeSubimtReq req, String price, String bonus, String orders, String date) {
		OrderLog info = new OrderLog();
		info.setAccountUUID(account.getAccount_uuid());
		info.setOrderUUID(orderUUID);
		info.setRestaurantUUID(vo.getRestaurant_uuid());
		info.setUserMessage(req.getUser_message());
		info.setCreateDate(date);
		info.setUpdateDate(date);
		info.setOrderPrice(price);
		info.setUseBonus(req.getUse_bonus());
		info.setOrderBonus(bonus);
		info.setFetchDate(req.getFetch_date());
		info.setOrderData(orders);
		info.setStatus(OrderStatus.UNFINISH.name());
		info.setEnable(Enable.Y.name());
		return info;
	}

	private static UserOrderInfo newUserOrderInfo(AccountInfoVo account, String orderUUID, RestaurantInfoVo vo,
			OredeSubimtReq req, String price, String bonus, String orders, String date) {
		UserOrderInfo info = new UserOrderInfo();
		info.setAccountUUID(account.getAccount_uuid());
		info.setOrderUUID(orderUUID);
		info.setRestaurantUUID(vo.getRestaurant_uuid());
		info.setUserMessage(req.getUser_message());
		info.setCreateDate(date);
		info.setUpdateDate(date);
		info.setOrderPrice(price);
		info.setUseBonus(req.getUse_bonus());
//		info.setDiscount(req.getUse_discount());
		info.setOrderBonus(bonus);
		info.setFetchDate(req.getFetch_date());
		info.setOrderData(orders);
		info.setStatus(OrderStatus.UNFINISH.name());
		info.setEnable(Enable.Y.name());
		return info;
	}

}
