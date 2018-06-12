package com.melonltd.naber.rdbms.model.req.vo;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class VerifyPhoneLogVo implements Serializable {
	private static final long serialVersionUID = 6764817969371558834L;

	private String batch_id;
	private String phone_number;
	private String code;

	public String getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass()).add("phone_number", phone_number).add("code", code)
				.toString();
	}

}
