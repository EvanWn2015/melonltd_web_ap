package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.melonltd.naber.rdbms.model.vo.SellerRegisteredVo;

@Entity
@Table(name = "seller_registered")
public class SellerRegistered implements Serializable {
	private static final long serialVersionUID = -7285367027000323478L;

	private String phone;
	private String sellerName;
	private String address;
	private String name;
	private String createDate;
	private String deviceId;
	private String status;

	@Id
	@Column(name = "phone", unique = true, nullable = false)
	public String getPhone() {
		return phone;
	}

	@Column(name = "device_id")
	public String getDeviceId() {
		return deviceId;
	}

	@Column(name = "seller_name")
	public String getSellerName() {
		return sellerName;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass()).add("sellerName", sellerName).add("phone", phone)
				.add("name", name).add("createDate", createDate).add("deviceId", deviceId).add("status", status)
				.toString();
	}

	public static SellerRegistered valueOf(SellerRegisteredVo vo) {
		SellerRegistered info = new SellerRegistered();
		info.sellerName = vo.getSeller_name();
		info.phone = vo.getPhone();
		info.address = vo.getAddress();
		info.name = vo.getName();
		info.createDate = vo.getCreate_date();
		info.deviceId = vo.getDevice_id();
		info.status = vo.getStatus();
		return info;
	}

}
