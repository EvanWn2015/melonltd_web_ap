package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seller_registered")
public class SellerRegistered implements Serializable {
	private static final long serialVersionUID = -7285367027000323478L;

	private Integer id;
	private String sellerName;
	private String phone;
	private String address;
	private String name;
	private String createDate;
	private String deviceId;
	private String status;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(name = "seller_name")
	public String getSellerName() {
		return sellerName;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
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

	@Column(name = "device_id")
	public String getDeviceId() {
		return deviceId;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

}
