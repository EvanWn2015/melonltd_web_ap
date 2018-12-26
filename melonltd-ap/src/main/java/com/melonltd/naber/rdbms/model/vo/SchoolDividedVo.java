package com.melonltd.naber.rdbms.model.vo;

import java.util.List;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
public class SchoolDividedVo {

	private String area;
	private List<String> schools;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
}
