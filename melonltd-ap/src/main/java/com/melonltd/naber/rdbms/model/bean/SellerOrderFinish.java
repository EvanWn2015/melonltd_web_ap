package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.vo.OrderVo;

import lombok.Data;

@Data
@Entity
@Table(name = "seller_order_finish")
public class SellerOrderFinish implements Serializable {
	private static final long serialVersionUID = -7662206313159475007L;

	@Id
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

	public static SellerOrderFinish valueOf(OrderVo vo) {
		SellerOrderFinish info = new SellerOrderFinish();
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

}