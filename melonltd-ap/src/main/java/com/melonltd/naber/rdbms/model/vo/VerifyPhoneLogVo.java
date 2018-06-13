package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class VerifyPhoneLogVo implements Serializable {
	private static final long serialVersionUID = 6764817969371558834L;

	private String batch_id;
	private String phone;
	private String code;

	public String getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhon(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass()).add("phone", phone).add("code", code)
				.toString();
	}

}
