package com.melonltd.naber.rdbms.model.vo;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.IdentityTable;

import lombok.Data;

@Data
public class IdentityTableVo {

	private String area;
	private List<Identitys> identitys;

	@Data
	public class Identitys {
		private String name;
		private List<String> items;

		@Override
		public String toString() {
			return JsonHelper.toJson(this);
		}
	}

	public static Identitys of(Identitys id) {
		id.setItems(Lists.newArrayList());
		return id;
	}
	
	public static IdentityTableVo of(IdentityTable info) {
		IdentityTableVo vo = new IdentityTableVo();
		vo.setArea(info.getArea());
		List<Identitys> list =JsonHelper.jsonArray(info.getIdentityList(), Identitys[].class);
		list.stream().map(a -> {
			if (a.getItems().isEmpty() ) {
				return IdentityTableVo.of(a);
			}
			return a;
		}).collect(Collectors.toList());
		
		vo.setIdentitys(list);
		return vo;
	} 
	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
}
