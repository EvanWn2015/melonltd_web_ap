package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

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

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("seller_name", seller_name)
				.add("phone", phone)
				.add("name", name)
				.add("createDate", create_date)
				.add("deviceId", device_id)
				.add("status", status)
				.toString();
	}

}
