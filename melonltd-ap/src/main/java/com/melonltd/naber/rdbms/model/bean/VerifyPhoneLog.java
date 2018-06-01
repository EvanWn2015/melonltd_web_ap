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
	private static final long serialVersionUID = -4488549616513079570L;

	private String phone;
	private String verifyCode;
	private String verifyCount;
	private String verifyDate;
	private String batchId;

	@Id
	@Column(name = "phone", unique = true, nullable = false)
	public String getPhone() {
		return phone;
	}

	@Column(name = "verify_code")
	public String getVerifyCode() {
		return verifyCode;
	}

	@Column(name = "verify_count")
	public String getVerifyCount() {
		return verifyCount;
	}

	@Column(name = "verify_date")
	public String getVerifyDate() {
		return verifyDate;
	}
	
	@Column(name = "batch_id")
	public String getBatchId() {
		return batchId;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public void setVerifyCount(String verifyCount) {
		this.verifyCount = verifyCount;
	}

	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}
	
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	
	@Override
	public String toString() {
		return MoreObjects
				.toStringHelper(this.getClass())
				.add("phone",phone)
				.add("verifyCode",verifyCode)
				.add("verifyCount",verifyCount)
				.add("verifyDate",verifyDate)
				.toString();
	}

}
