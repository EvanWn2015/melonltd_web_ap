package com.melonltd.naber.endpoint.util;

import com.google.gson.Gson;

public class JsonHelper {
	private static Gson GSON = new Gson();

	public static final String toJson(Object obj) {
		return GSON.toJson(obj);
	}

	public static final <T> T json(String json, Class<T> classOfT) {
		GSON.fromJson(json, classOfT);
		return (T) GSON.fromJson(json, classOfT);
	}
}
