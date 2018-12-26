package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.vo.OrderVo;

import lombok.Data;

@Data
@Entity
@Table(name = "order_log")
public class OrderLog implements Serializable {
	private static final long serialVersionUID = 2088198035040888362L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "order_uuid", unique = true, nullable = false)
	private String orderUUID;
	@Column(name = "restaurant_uuid")
	private String restaurantUUID;
	@Column(name = "account_uuid")
	private String accountUUID;
	@Column(name = "user_message")
	private String userMessage;
	@Column(name = "create_date")
	private String createDate;
	@Column(name = "update_date")
	private String updateDate;
	@Column(name = "order_type")
	private String orderType;
	@Column(name = "order_price")
	private String orderPrice;
	@Column(name = "order_bonus")
	private String orderBonus;
	@Column(name = "use_bonus")
	private String useBonus;
	@Column(name = "fetch_date")
	private String fetchDate;
	@Column(name = "order_data")
	private String orderData;
	@Column(name = "status")
	private String status;
	@Column(name = "enable")
	private String enable;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	public static OrderLog of(OrderVo vo) {
		OrderLog info = new OrderLog();
		info.orderUUID = vo.getOrder_uuid();
		info.accountUUID = vo.getAccount_uuid();
		info.restaurantUUID = vo.getRestaurant_uuid();
		info.userMessage = vo.getUser_message();
		info.createDate = vo.getCreate_date();
		info.updateDate = vo.getUpdate_date();
		info.orderType = vo.getOrder_type();
		info.orderPrice = vo.getOrder_price();
		info.useBonus = vo.getUse_bonus();
		info.orderBonus = vo.getOrder_bonus();
		info.fetchDate = vo.getFetch_date();
		info.orderData = vo.getOrder_data();
		info.status = vo.getStatus();
		info.enable = vo.getEnable();
		return info;
	}

	public static OrderLog of(OrderInfo vo) {
		OrderLog info = new OrderLog();
		info.orderUUID = vo.getOrderUUID();
		info.accountUUID = vo.getAccountUUID();
		info.restaurantUUID = vo.getRestaurantUUID();
		info.userMessage = vo.getUserMessage();
		info.createDate = vo.getCreateDate();
		info.updateDate = vo.getUpdateDate();
		info.orderType = vo.getOrderType();
		info.orderPrice = vo.getOrderPrice();
		info.useBonus = vo.getUseBonus();
		info.orderBonus = vo.getOrderBonus();
		info.fetchDate = vo.getFetchDate();
		info.orderData = vo.getOrderData();
		info.status = vo.getStatus();
		info.enable = vo.getEnable();
		return info;
	}

}
