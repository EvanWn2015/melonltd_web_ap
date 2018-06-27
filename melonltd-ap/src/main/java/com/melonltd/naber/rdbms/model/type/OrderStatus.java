package com.melonltd.naber.rdbms.model.type;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public enum OrderStatus {
	UNFINISH("UNFINISH", "訂單未處理"), 
	PROCESSING("PROCESSING", "商家已接收到您的訂單並且開始製作囉!"), 
	CAN_FETCH("CAN_FETCH", "商家已經完成製作您的餐點，可以前往領取囉!"), 
	CANCEL("CANCEL", "不好意思，因為%s，所以商家無法製作您這訂單的餐點，並且取消此訂單了！可以看看別的商家或識別的餐點哦！"), 
	FAIL("FAIL", "由於您沒去拿餐點，被記錄跑單一次！這會影響您使用ＮＡＢＥＲ的權利，之後送單前請特別留意。"), 
	FINISH("FINISH", "訂單已結束");
	
	private String name;
	private String massage;
	
	OrderStatus(String name,String massage){
		this.name = name;
		this.massage = massage;
	}
	
	public String getMssage() {
		return this.massage;
	}
	
	public static OrderStatus of(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		for (OrderStatus type : OrderStatus.values()) {
			if (type.name.equals(name.toUpperCase())) {
				return type;
			}
		}
		return null;
	}
	
	public static List<OrderStatus> getSellerSearchType(){
		return Lists.<OrderStatus>newArrayList(UNFINISH, PROCESSING, CAN_FETCH);
	}
	
	public static List<OrderStatus> getSellerUpdateType(){
		return Lists.<OrderStatus>newArrayList(PROCESSING, CAN_FETCH);
	}
	
	public static List<OrderStatus> getSellerFinishType(){
		return Lists.<OrderStatus>newArrayList(CANCEL, FAIL, FINISH);
	}
	
	public static List<OrderStatus> getNotifyToUserType(){
		return Lists.<OrderStatus>newArrayList(PROCESSING, CAN_FETCH, CANCEL, FAIL);
	}

}
