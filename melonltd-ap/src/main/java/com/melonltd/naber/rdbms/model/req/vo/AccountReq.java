package com.melonltd.naber.rdbms.model.req.vo;

import com.google.common.base.MoreObjects;

public class AccountReq {

	private String phone;
	private String old_password;
	private String password;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("phone",phone)
				.add("old_password",old_password)
				.add("password",password)
				.toString();
	}

}
