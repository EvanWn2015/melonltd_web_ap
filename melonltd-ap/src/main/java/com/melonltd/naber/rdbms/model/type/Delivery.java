package com.melonltd.naber.rdbms.model.type;

import java.util.List;

import com.google.common.collect.Lists;

// 餐館訂單可接受類型
//外送 send
//內用 eat_in
//外帶 take_out
// 
public enum Delivery {
	SEND("SEND"), 
	IN("IN"),
	OUT("OUT");
	
	private String name;
	
	Delivery(String name){
		this.name = name;
	}
	
	public static Delivery of(String name) {
		for (Delivery type : getEnumValues()) {
			if (type.name.equals(name.toUpperCase())) {
				return type;
			}
		}
		return null;
	}
	
	public static List<Delivery> getEnumValues() {
		return Lists.newArrayList(SEND, IN, OUT);
	}
}
