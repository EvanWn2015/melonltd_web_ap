package com.melonltd.naber.rdbms.model.req.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.MoreObjects;

public class ItemVo implements Serializable {
	private static final long serialVersionUID = 5041942907035291046L;

	private String name;
	private String price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Override
	public boolean equals(Object obj) {
		return StringUtils.equals(this.name, ((ItemVo)obj).getName()) && StringUtils.equals(this.price, ((ItemVo)obj).getPrice());
	}
	
	public boolean equals (ItemVo item) {
		return StringUtils.equals(this.name, item.name) && StringUtils.equals(this.price, item.price);
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("name",name)
				.add("price",price)
				.toString();
	}

}
