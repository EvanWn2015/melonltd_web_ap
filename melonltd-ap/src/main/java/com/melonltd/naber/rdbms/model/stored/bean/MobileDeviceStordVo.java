package com.melonltd.naber.rdbms.model.stored.bean;

import java.io.Serializable;

import com.google.common.base.MoreObjects;
import com.melonltd.naber.rdbms.model.stored.dao.MobileDeviceStord;

public class MobileDeviceStordVo implements Serializable{
	private static final long serialVersionUID = 7725485049002350151L;
	
	private String device_uuid;
	private String device_token;
	private String account_uuid;
	private String device_category;
	private String create_date;
	

	public String getDevice_uuid() {
		return device_uuid;
	}

	public void setDevice_uuid(String device_uuid) {
		this.device_uuid = device_uuid;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public String getAccount_uuid() {
		return account_uuid;
	}

	public void setAccount_uuid(String account_uuid) {
		this.account_uuid = account_uuid;
	}

	public String getDevice_category() {
		return device_category;
	}

	public void setDevice_category(String device_category) {
		this.device_category = device_category;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("device_uuid", device_uuid)
				.add("device_token", device_token)
				.add("account_uuid", account_uuid)
				.add("device_category", device_category)
				.add("create_date", create_date)
				.toString();
	}
	
	public static MobileDeviceStordVo valueOf (MobileDeviceStord info) {
		MobileDeviceStordVo vo = new MobileDeviceStordVo();
		vo.device_uuid = info.getDeviceUUID();
		vo.device_token = info.getDeviceToken();
		vo.account_uuid = info.getAccountUUID();
		vo.device_category = info.getDeviceCategory();
		vo.create_date = info.getCreateDate();
		return vo;
	}
}
