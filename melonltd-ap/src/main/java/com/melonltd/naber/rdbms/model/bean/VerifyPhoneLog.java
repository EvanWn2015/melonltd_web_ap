package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "verify_phone_log")
public class VerifyPhoneLog implements Serializable {
	private static final long serialVersionUID = -6605828460073292074L;
	
	private String batchId;
	private String phone;
	private String verifyCode;
	private String verifyDate;

	@Id
	@Column(name = "batch_id", unique = true, nullable = false)
	public String getBatchId() {
		return batchId;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	@Column(name = "verify_code")
	public String getVerifyCode() {
		return verifyCode;
	}

	@Column(name = "verify_date")
	public String getVerifyDate() {
		return verifyDate;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass()).add("batchId", batchId).add("phone", phone)
				.add("verifyCode", verifyCode).add("verifyDate", verifyDate).toString();
	}

}
