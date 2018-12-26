package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
public class VerifyPhoneLogVo implements Serializable {
	private static final long serialVersionUID = 6764817969371558834L;

	private String batch_id;
	private String phone;
	private String code;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
