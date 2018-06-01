package com.melonltd.naber.rdbms.model.vo;

import java.util.LinkedHashMap;

import com.google.common.collect.Maps;

public class ResponseData {
	public enum Status {
		TRUE("true"), FALSE("false");
		private String status;

		Status(String status) {
			this.status = status;
		}
		public String getStatus() {
			return this.status;
		}
	}
	
	public enum Error {
		
	}

	public static LinkedHashMap<String, Object> of(Status status, String errCode, String errMsg, Object o) {
		LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
		map.put("status", status.getStatus());
		if (Status.FALSE.equals(status)) {
			map.put("err_code", errCode);
			map.put("err_msg", errMsg);
		}
		map.put("data", o);
		return map;
	}

}
