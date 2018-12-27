package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
public class SellerRegisteredVo implements Serializable {
	private static final long serialVersionUID = -8962923304214041879L;

	private String seller_name;
	private String phone;
	private String address;
	private String name;
	private String create_date;
	private String device_id;
	private String status;

	public enum Status {
		UNFINISHED, FINISHED
	}

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
