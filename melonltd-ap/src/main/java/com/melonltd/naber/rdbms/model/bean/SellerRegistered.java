package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.vo.SellerRegisteredVo;

import lombok.Data;

@Data
@Entity
@Table(name = "seller_registered")
public class SellerRegistered implements Serializable {
	private static final long serialVersionUID = -7285367027000323478L;

	@Id
	@Column(name = "phone", unique = true, nullable = false)
	private String phone;
	@Column(name = "seller_name")
	private String sellerName;
	@Column(name = "address")
	private String address;
	@Column(name = "name")
	private String name;
	@Column(name = "create_date")
	private String createDate;
	@Column(name = "device_id")
	private String deviceId;
	@Column(name = "status")
	private String status;

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

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
