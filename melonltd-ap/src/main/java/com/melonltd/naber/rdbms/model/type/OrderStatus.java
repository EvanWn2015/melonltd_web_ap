package com.melonltd.naber.rdbms.model.type;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public enum OrderStatus {
	UNFINISH("UNFINISH"), 
	PROCESSING("PROCESSING"), 
	CAN_FETCH("CAN_FETCH"), 
	CANCEL("CANCEL"), 
	FAIL("FAIL"), 
	FINISH("FINISH");
	
	private String name;
	
	OrderStatus(String name){
		this.name = name;
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
		return Lists.<OrderStatus>newArrayList(PROCESSING, CAN_FETCH, CANCEL, FAIL, FINISH);
	}
	
	public static List<OrderStatus> getSellerFinishType(){
		return Lists.<OrderStatus>newArrayList(CANCEL, FAIL, FINISH);
	}

}
