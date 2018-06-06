package com.melonltd.naber.rdbms.model.type;

import java.util.List;

import com.google.common.collect.Lists;

public enum DeviceCategory {
	IOS, ANDROID, NUKNOWN;

	public static DeviceCategory of(String name) {
		for (DeviceCategory type : getEnumValues()) {
			if (type.name().equals(name.toUpperCase())) {
				return type;
			}
		}
		return NUKNOWN;
	}

	public static List<DeviceCategory> getEnumValues() {
		return Lists.newArrayList(IOS, ANDROID);
	}
}
