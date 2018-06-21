package com.melonltd.naber.rdbms.model.type;

import java.util.List;

import com.google.common.collect.Lists;

public enum UpLoadType {
	USER, FOOD;

	public static UpLoadType of(String name) {
		for (UpLoadType type : geTypes()) {
			if (type.name().equals(name.toUpperCase())) {
				return type;
			}
		}
		return null;
	}

	public static List<UpLoadType> geTypes() {
		return Lists.newArrayList(UpLoadType.values());
	}
}
