package com.melonltd.naber.rdbms.model.type;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public enum OrderStatus {
	UNFINISH("UNFINISH", "訂單未處理"), 
	PROCESSING("PROCESSING", "訂單製作中"), 
	CAN_FETCH("CAN_FETCH", "訂單可領取"), 
	CANCEL("CANCEL", "訂單已取消"), 
	FAIL("FAIL", "訂單已跑單"), 
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
