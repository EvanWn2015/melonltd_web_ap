package com.melonltd.naber.rdbms.model.type;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public enum SwitchStatus {
		OPEN("OPEN"), CLOSE("CLOSE");
	
	private String name;
	
	SwitchStatus(String name){
		this.name = name();
	}
	
	public static SwitchStatus of(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		for (SwitchStatus type : getEnumValues()) {
			if (type.name().equals(name.toUpperCase())) {
				return type;
			}
		}
		return null;
	}
	
	public static List<SwitchStatus> getEnumValues() {
		return Lists.newArrayList(OPEN, CLOSE);
	}

}
