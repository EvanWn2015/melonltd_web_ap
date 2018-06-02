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

	public enum ErrorType {
		EXCEED("1001", "超過可用次數"),
		SEND_SMS_FAIL("1002", "取得驗證碼失敗"),
		VERIFY_CODE_FAIL("1003", "驗證失敗"),
		EXCEED_TIME("1004", "超過驗證時效");

		ErrorType(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		private String code;
		private String msg;
	}

	public static LinkedHashMap<String, Object> of(Status status, ErrorType error, Object o) {
		LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
		map.put("status", status.getStatus());
		if (Status.FALSE.equals(status)) {
			map.put("err_code", error.code);
			map.put("err_msg", error.msg);
		}
		map.put("data", o);
		return map;
	}

}
