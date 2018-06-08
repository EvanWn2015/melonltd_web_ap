package com.melonltd.naber.endpoint.util;

import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

public class JsonHelper {
	private static Gson GSON = new Gson();

	public static String toJson(Object obj) {
		return GSON.toJson(obj);
	}

	public static <T> T json(String json, Class<T> classOfT) {
		return (T) GSON.fromJson(json, classOfT);
	}

	public static <T> List<T> jsonArray(String json, Class<T[]> types) {
		if (Strings.isNullOrEmpty(json)) {
			return Lists.<T>newArrayList();
		}
		final T[] jsonToObject = GSON.fromJson(json, types);
		return Lists.newArrayList(jsonToObject);
	}

}
