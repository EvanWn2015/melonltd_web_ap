package com.melonltd.naber.rdbms.model.req.vo;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
public class AccountReq {

	private String phone;
	private String old_password;
	private String password;
	private String email;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
