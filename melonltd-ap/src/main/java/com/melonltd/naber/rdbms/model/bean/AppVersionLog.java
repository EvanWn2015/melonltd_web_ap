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

import com.google.common.base.MoreObjects;
import com.melonltd.naber.rdbms.model.type.DeviceCategory;
import com.melonltd.naber.rdbms.model.type.Enable;


@Entity
@Table(name = "app_version_log")
public class AppVersionLog implements Serializable {
	private static final long serialVersionUID = 227628387369633268L;
	
	
	private Long id;
	private String version;
	private DeviceCategory category;
	private Enable needUpgrade;
	private String createDate;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	
	@Column(name = "version")
	public String getVersion() {
		return version;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	public DeviceCategory getCategory() {
		return category;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "need_upgrade")
	public Enable getNeedUpgrade() {
		return needUpgrade;
	}

	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}

	
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setCategory(DeviceCategory category) {
		this.category = category;
	}

	public void setNeedUpgrade(Enable needUpgrade) {
		this.needUpgrade = needUpgrade;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("id", id)
				.add("version", version)
				.add("category", category)
				.add("needUpgrade", needUpgrade)
				.add("createDate", createDate)
				.toString();	
	}
	
}
