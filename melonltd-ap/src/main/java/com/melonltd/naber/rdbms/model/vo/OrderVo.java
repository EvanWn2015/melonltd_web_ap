package com.melonltd.naber.rdbms.model.vo;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.OrderInfo;
import com.melonltd.naber.rdbms.model.bean.OrderLog;
import com.melonltd.naber.rdbms.model.bean.SellerOrderFinish;
import com.melonltd.naber.rdbms.model.bean.UserOrderInfo;

import lombok.Data;

@Data
public class OrderVo {

	private String account_name;
	private String account_phone;
	private String restaurant_name;
	private String restaurant_address;

	private String order_uuid;
	private String account_uuid;
	private String restaurant_uuid;
	private String user_message;
	private String create_date;
	private String update_date;
	private String order_type;
	private String order_price;
	private String use_bonus;
	private String order_bonus;
	private String fetch_date;
	private String order_data;
	private String status;
	private String enable;

	public static OrderVo valueOf(SellerOrderFinish info) {
		OrderVo vo = new OrderVo();
		vo.order_uuid = info.getOrderUUID();
		vo.account_uuid = info.getAccountUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.user_message = info.getUserMessage();
		vo.create_date = info.getCreateDate();
		vo.update_date = info.getUpdateDate();
		vo.order_type = info.getOrderType();
		vo.order_price = info.getOrderPrice();
		vo.use_bonus = info.getUseBonus();
		vo.order_bonus = info.getOrderBonus();
		vo.fetch_date = info.getFetchDate();
		vo.order_data = info.getOrderData();
		vo.status = info.getStatus();
		vo.enable = info.getEnable();
		return vo;
	}

	public static OrderVo valueOf(OrderInfo info) {
		OrderVo vo = new OrderVo();
		vo.order_uuid = info.getOrderUUID();
		vo.account_uuid = info.getAccountUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.user_message = info.getUserMessage();
		vo.create_date = info.getCreateDate();
		vo.update_date = info.getUpdateDate();
		vo.order_type = info.getOrderType();
		vo.order_price = info.getOrderPrice();
		vo.use_bonus = info.getUseBonus();
		vo.order_bonus = info.getOrderBonus();
		vo.fetch_date = info.getFetchDate();
		vo.order_data = info.getOrderData();
		vo.status = info.getStatus();
		vo.enable = info.getEnable();
		return vo;
	}

	public static OrderVo valueOf(OrderLog info) {
		OrderVo vo = new OrderVo();
		vo.order_uuid = info.getOrderUUID();
		vo.account_uuid = info.getAccountUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.user_message = info.getUserMessage();
		vo.create_date = info.getCreateDate();
		vo.update_date = info.getUpdateDate();
		vo.order_type = info.getOrderType();
		vo.order_price = info.getOrderPrice();
		vo.use_bonus = info.getUseBonus();
		vo.order_bonus = info.getOrderBonus();
		vo.fetch_date = info.getFetchDate();
		vo.order_data = info.getOrderData();
		vo.status = info.getStatus();
		vo.enable = info.getEnable();
		return vo;
	}

	public static OrderVo valueOf(UserOrderInfo info) {
		OrderVo vo = new OrderVo();
		vo.order_uuid = info.getOrderUUID();
		vo.account_uuid = info.getAccountUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.user_message = info.getUserMessage();
		vo.create_date = info.getCreateDate();
		vo.update_date = info.getUpdateDate();
		vo.order_type = info.getOrderType();
		vo.order_price = info.getOrderPrice();
		vo.use_bonus = info.getUseBonus();
		vo.order_bonus = info.getOrderBonus();
		vo.fetch_date = info.getFetchDate();
		vo.order_data = info.getOrderData();
		vo.status = info.getStatus();
		vo.enable = info.getEnable();
		return vo;
	}

	public static List<OrderVo> valueOfArray(List<UserOrderInfo> infos) {
		List<OrderVo> vos = Lists.newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}

	public static List<OrderVo> valueInfoOfArray(List<OrderInfo> infos) {
		List<OrderVo> vos = Lists.newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}

	public static List<OrderVo> valueOrderFinishOfArray(List<SellerOrderFinish> infos) {
		List<OrderVo> vos = Lists.newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
