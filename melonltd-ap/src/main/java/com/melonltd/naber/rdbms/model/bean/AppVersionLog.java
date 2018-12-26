package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.type.DeviceCategory;
import com.melonltd.naber.rdbms.model.type.Enable;

import lombok.Data;

@Data
@Entity
@Table(name = "app_version_log")
public class AppVersionLog implements Serializable {
	private static final long serialVersionUID = 227628387369633268L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "version")
	private String version;
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private DeviceCategory category;
	@Enumerated(EnumType.STRING)
	@Column(name = "need_upgrade")
	private Enable needUpgrade;
	@Column(name = "create_date")
	private String createDate;
	
	
	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
	
}
