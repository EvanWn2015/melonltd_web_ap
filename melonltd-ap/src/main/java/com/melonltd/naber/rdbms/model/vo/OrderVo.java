package com.melonltd.naber.rdbms.model.vo;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.OrderInfo;
import com.melonltd.naber.rdbms.model.bean.OrderLog;
import com.melonltd.naber.rdbms.model.bean.SellerOrderFinish;
import com.melonltd.naber.rdbms.model.bean.UserOrderLog;

public class OrderVo {

	private String order_uuid;
	private String account_uuid;
	private String account_name;
	private String account_phone;
	private String restaurant_uuid;
	private String restaurant_name;
	private String restaurant_address;
	private String user_message;
	private String create_date;
	private String update_date;
	private String order_price;
	private String order_bonus;
	private String fetch_date;
	private String order_data;
	private String status;
	private String enable;


	public String getOrder_uuid() {
		return order_uuid;
	}

	public void setOrder_uuid(String order_uuid) {
		this.order_uuid = order_uuid;
	}

	public String getAccount_uuid() {
		return account_uuid;
	}

	public void setAccount_uuid(String account_uuid) {
		this.account_uuid = account_uuid;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_phone() {
		return account_phone;
	}

	public void setAccount_phone(String account_phone) {
		this.account_phone = account_phone;
	}

	public String getRestaurant_uuid() {
		return restaurant_uuid;
	}

	public void setRestaurant_uuid(String restaurant_uuid) {
		this.restaurant_uuid = restaurant_uuid;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

	public String getRestaurant_address() {
		return restaurant_address;
	}

	public void setRestaurant_address(String restaurant_address) {
		this.restaurant_address = restaurant_address;
	}

	public String getUser_message() {
		return user_message;
	}

	public void setUser_message(String user_message) {
		this.user_message = user_message;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getOrder_price() {
		return order_price;
	}

	public void setOrder_price(String order_price) {
		this.order_price = order_price;
	}

	public String getOrder_bonus() {
		return order_bonus;
	}

	public void setOrder_bonus(String order_bonus) {
		this.order_bonus = order_bonus;
	}

	public String getFetch_date() {
		return fetch_date;
	}

	public void setFetch_date(String fetch_date) {
		this.fetch_date = fetch_date;
	}

	public String getOrder_data() {
		return order_data;
	}

	public void setOrder_data(String order_data) {
		this.order_data = order_data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public static OrderVo valueOf(SellerOrderFinish info){
		OrderVo vo = new OrderVo();
		vo.order_uuid = info.getOrderUUID();
		vo.account_uuid = info.getAccountUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.user_message = info.getUserMessage();
		vo.create_date = info.getCreateDate();
		vo.update_date = info.getUpdateDate();
		vo.order_price = info.getOrderPrice();
		vo.order_bonus = info.getOrderBonus();
		vo.fetch_date = info.getFetchDate();
		vo.order_data = info.getOrderData();
		vo.status = info.getStatus();
		vo.enable = info.getEnable();
		return vo;
	}
	
	public static OrderVo valueOf(OrderInfo info){
		OrderVo vo = new OrderVo();
		vo.order_uuid = info.getOrderUUID();
		vo.account_uuid = info.getAccountUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.user_message = info.getUserMessage();
		vo.create_date = info.getCreateDate();
		vo.update_date = info.getUpdateDate();
		vo.order_price = info.getOrderPrice();
		vo.order_bonus = info.getOrderBonus();
		vo.fetch_date = info.getFetchDate();
		vo.order_data = info.getOrderData();
		vo.status = info.getStatus();
		vo.enable = info.getEnable();
		return vo;
	}
	
	public static OrderVo valueOf(OrderLog info){
		OrderVo vo = new OrderVo();
		vo.order_uuid = info.getOrderUUID();
		vo.account_uuid = info.getAccountUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.user_message = info.getUserMessage();
		vo.create_date = info.getCreateDate();
		vo.update_date = info.getUpdateDate();
		vo.order_price = info.getOrderPrice();
		vo.order_bonus = info.getOrderBonus();
		vo.fetch_date = info.getFetchDate();
		vo.order_data = info.getOrderData();
		vo.status = info.getStatus();
		vo.enable = info.getEnable();
		return vo;
	}
	
	public static OrderVo valueOf(UserOrderLog info){
		OrderVo vo = new OrderVo();
		vo.order_uuid = info.getOrderUUID();
		vo.account_uuid = info.getAccountUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.user_message = info.getUserMessage();
		vo.create_date = info.getCreateDate();
		vo.update_date = info.getUpdateDate();
		vo.order_price = info.getOrderPrice();
		vo.order_bonus = info.getOrderBonus();
		vo.fetch_date = info.getFetchDate();
		vo.order_data = info.getOrderData();
		vo.status = info.getStatus();
		vo.enable = info.getEnable();
		return vo;
	}
	
	public static List<OrderVo> valueOfArray(List<UserOrderLog> infos){
		List<OrderVo> vos = Lists.newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}
	
	public static List<OrderVo> valueInfoOfArray(List<OrderInfo> infos){
		List<OrderVo> vos = Lists.newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}
	
	public static List<OrderVo> valueOrderFinishOfArray(List<SellerOrderFinish> infos){
		List<OrderVo> vos = Lists.newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("order_uuid", order_uuid)
				.add("account_uuid", account_uuid)
				.add("restaurant_uuid", restaurant_uuid)
				.add("restaurant_name", restaurant_name)
				.add("restaurant_address", restaurant_address)
				.add("user_message", user_message)
				.add("create_date", create_date)
				.add("update_date", update_date)
				.add("order_price", order_price)
				.add("order_bonus", order_bonus)
				.add("fetch_date", fetch_date)
				.add("order_data", order_data)
				.add("status", status)
				.add("enable", enable)
				.toString();
	}

}
