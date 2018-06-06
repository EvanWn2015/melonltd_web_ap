package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "mobile_device")
public class MobileDevice implements Serializable {
	private static final long serialVersionUID = 4703671514270687285L;

	private String deviceUUID;
	private String deviceToken;
	private String accountUUID;
	private String deviceCategory;
	private String createDate;

	@Id
	@Column(name = "device_uuid", unique = true, nullable = false)
	public String getDeviceUUID() {
		return deviceUUID;
	}

	@Column(name = "device_token")
	public String getDeviceToken() {
		return deviceToken;
	}

	@Column(name = "account_uuid")
	public String getAccountUUID() {
		return accountUUID;
	}

	@Column(name = "device_category")
	public String getDeviceCategory() {
		return deviceCategory;
	}

	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}

	public void setDeviceUUID(String deviceUUID) {
		this.deviceUUID = deviceUUID;
	}
	
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public void setAccountUUID(String accountUUID) {
		this.accountUUID = accountUUID;
	}

	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("deviceUUID", deviceUUID)
				.add("deviceToken", deviceToken)
				.add("accountUUID", accountUUID)
				.add("deviceCategory", deviceCategory)
				.add("createDate", createDate)
				.toString();
	}

}
