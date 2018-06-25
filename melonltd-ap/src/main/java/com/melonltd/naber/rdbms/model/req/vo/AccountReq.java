package com.melonltd.naber.rdbms.model.req.vo;

import com.google.common.base.MoreObjects;

public class AccountReq {

	private String phone;
	private String old_password;
	private String password;
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass()).add("phone", phone).add("email", email)
				.add("old_password", old_password).add("password", password).toString();
	}

}
