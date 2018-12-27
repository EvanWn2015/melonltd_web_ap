package com.melonltd.naber.rdbms.model.vo;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StorePhotoVo {
	
	public enum Type {
		STORE, FOOD
	}
	
	private Type type;
	private String photo;
	private String uuid;
	public static StorePhotoVo newInstance(Type type, String photo, String uuid) {
		return new StorePhotoVo(type, photo, uuid);
	}
	
	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
