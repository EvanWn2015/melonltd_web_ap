package com.melonltd.naber.endpoint.util;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

public class JsonHelper {
	private static Gson GSON = new Gson();

	public static String toJson(Object obj) {
		return GSON.toJson(obj);
	}

	public static <T> T json(String json, Class<T> classOfT) {
//		GSON.fromJson(json, classOfT);
		return (T) GSON.fromJson(json, classOfT);
	}

	public static <T> List<T> jsonArray(String json, Class<T> classOfT) {
		Type listType = new TypeToken<ArrayList<T>>() {}.getType();
//		if (Strings.isNullOrEmpty(json)) {
//			return Lists.<T>newArrayList();
//		}
		return GSON.fromJson(json, listType);
	}
}
