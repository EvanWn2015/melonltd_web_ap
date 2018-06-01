package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mobile_device")
public class MobileDevice implements Serializable {
	private static final long serialVersionUID = 4703671514270687285L;

	private String pushToken;
	private String accountUUID;
	private String category;
	private String createDate;

	@Id
	@Column(name = "push_token", unique = true, nullable = false)
	public String getPushToken() {
		return pushToken;
	}

	@Column(name = "account_uuid")
	public String getAccountUUID() {
		return accountUUID;
	}

	@Column(name = "category")
	public String getCategory() {
		return category;
	}

	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public void setAccountUUID(String accountUUID) {
		this.accountUUID = accountUUID;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
