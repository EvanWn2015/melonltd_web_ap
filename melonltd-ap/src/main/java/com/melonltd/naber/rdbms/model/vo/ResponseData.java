package com.melonltd.naber.rdbms.model.vo;

import java.util.LinkedHashMap;

import com.google.common.collect.Maps;

public class ResponseData {
	public enum Status {
		TRUE("true"), FALSE("false");
		private String name;

		Status(String name) {
			this.name = name;
		}

		public String getStatus() {
			return this.name;
		}
	}

	public enum ErrorType {
		SERVER_ERROR("0000", "服務器錯誤"),
		SAVE_ERROR("0001", "儲存資料失敗"),
		UPDATE_ERROR("0002", "服務器錯誤"),
		DELETE_ERROR("0003", "服務器錯誤"),
		INVALID("1001", "無效參數"),
		INVALID_PASSWORD("1002", "無效參數，密碼長度不6~20碼範圍內或非英數組合"),
		INVALID_EMAIL("1003", "無效參數，Email格式錯誤"),
		INVALID_SCHOOL("1004", "無效參數，需要學校名稱"),
		EXCEED("2001", "超過可用次數"), 
		SEND_SMS_FAIL("2002", "取得驗證碼失敗"), 
		VERIFY_CODE_FAIL("2003","驗證失敗"), 
		EXCEED_TIME("2004", "超過驗證時效");

		ErrorType(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		private String code;
		private String msg;
	}

	public static LinkedHashMap<String, Object> of(Status status, ErrorType error, Object o) {
		LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
		map.put("status", status.name);
		if (Status.FALSE.equals(status)) {
			map.put("err_code", error.code);
			map.put("err_msg", error.msg);
		}
		map.put("data", o);
		return map;
	}
	

}
