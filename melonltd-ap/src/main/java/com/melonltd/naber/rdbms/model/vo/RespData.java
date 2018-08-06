package com.melonltd.naber.rdbms.model.vo;

import java.util.LinkedHashMap;

import com.google.common.collect.Maps;
import com.melonltd.naber.constant.NaberConstant;
import com.melonltd.naber.endpoint.util.JsonHelper;

public class RespData {
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
		UPDATE_ERROR("0002", "服務器錯誤，更新失敗"),
		DELETE_ERROR("0003", "服務器錯誤，刪除失敗"),
		HEADESR_ERROR("0004", "headers 帳戶 token 認證失敗"),
		DATABASE_NULL("0005", "查無此資料"),
		MAIL_SEND_ERROR("0006", "服務器錯誤，發送mail失敗"),
		NOT_BE_CHANGED("0007", "不可改變該狀態"),
		RESOLVE_ERROR("0008", "資料解析錯誤"),
		ACCOUNT_ERROR("0009", "帳號已經存在"),
		JSON_RESOLVE_ERROR("0010", "Json資料解析錯誤"),
		INVALID("1001", "無效參數"),
		INVALID_PASSWORD("1002", "無效參數，密碼長度不6~20碼範圍內或非英數組合"),
		INVALID_EMAIL("1003", "無效參數，Email格式錯誤"),
		INVALID_SCHOOL("1004", "無效參數，需要學校名稱"),
		INVALID_PASSWORD_REPEAT("1005", "無效參數，新密碼不可與舊密碼相同"),
		INVALID_PHONE("1006", "無效參數，查無屬於此電話號碼帳戶"),
		INVALID_PASSWORD_ORIGINAL("1007", "無效參數，與舊密碼不同"),
		EXCEED("2001", "超過可用次數"), 
		SEND_SMS_FAIL("2002", "該號碼今天請求SMS次數已經用盡，$split請明天再嘗試！！"), 
		VERIFY_CODE_FAIL("2003","驗證失敗"), 
		EXCEED_TIME("2004", "超過驗證時效"),
		RANGE_STORE_IS_CLOSE("3000", "該商家不接單時段$split"),
		STORE_IS_CLOSE("3001", "%s"),
		FOOD_ITEM_CLOSE("3002", "目前品項處於關閉狀態。"),
		FOOD_ITEM_IS_CHANGE("3003", "目前品項內容有所改變，$split請刪除此購物車內訂單重新下訂"),
		CATEGORY_IS_CLOSE("3004", "目前種類處於關閉狀態。"),
		LOGIN_FAIL("4001", "查無此帳戶 或 帳戶已失效"),
		ORDER_MAX_PRICE("5001", "訂單種金額超過 5000"),
		ORDER_UNFINISH_MAX("5003", "未完成訂單數量已經滿3筆"),
		ORDER_FAIL_MAX("5005", "由於您跑單次數過多，$split無法再下任何一筆訂單如有疑問，$split請洽詢NABER客服"),
		FOOD_ITEM_DATA_ERROR("5002", "菜單資料結構錯誤"),
		ORDER_MAX_COUNT("5004", "單筆菜單數量錯誤(1~50)");

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
	
	public static LinkedHashMap<String, Object> of(Status status, ErrorType error, String errorMsg, Object o) {
		LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
		map.put("status", status.name);
		if (Status.FALSE.equals(status)) {
			map.put("err_code", error.code);
			map.put("err_msg", errorMsg);
		}
		map.put("data", o);
		return map;
	}
	

}
