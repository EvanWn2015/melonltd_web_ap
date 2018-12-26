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
@Table(name = "verify_phone_log")
public class VerifyPhoneLog implements Serializable {
	private static final long serialVersionUID = -6605828460073292074L;

	@Id
	@Column(name = "batch_id", unique = true, nullable = false)
	private String batchId;
	@Column(name = "phone")
	private String phone;
	@Column(name = "verify_code")
	private String verifyCode;
	@Column(name = "verify_date")
	private String verifyDate;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
