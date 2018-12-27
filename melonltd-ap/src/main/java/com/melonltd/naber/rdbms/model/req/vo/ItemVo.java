package com.melonltd.naber.rdbms.model.req.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
public class ItemVo implements Serializable {
	private static final long serialVersionUID = 5041942907035291046L;

	private String name;
	private String price;
	
	@Override
	public boolean equals(Object obj) {
		return StringUtils.equals(this.name, ((ItemVo)obj).getName()) && StringUtils.equals(this.price, ((ItemVo)obj).getPrice());
	}
	
	public boolean equals (ItemVo item) {
		return StringUtils.equals(this.name, item.name) && StringUtils.equals(this.price, item.price);
	}
	
	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
