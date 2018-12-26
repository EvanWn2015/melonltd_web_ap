package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
@Entity
@Table(name = "mobile_device")
public class MobileDevice implements Serializable {
	private static final long serialVersionUID = 4703671514270687285L;

	@Id
	@Column(name = "device_uuid", unique = true, nullable = false)
	private String deviceUUID;
	@Column(name = "device_token")
	private String deviceToken;
	@Column(name = "account_uuid")
	private String accountUUID;
	@Column(name = "device_category")
	private String deviceCategory;
	@Column(name = "create_date")
	private String createDate;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
