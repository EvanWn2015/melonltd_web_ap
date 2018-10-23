package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;


@Entity
@Table(name = "activities_log")
public class ActivitiesLog implements Serializable {
	private static final long serialVersionUID = 7294609723830911896L;

	private Integer id;
	private String actUUID;
	private String accountUUID;
	private String serial;
	private String actCategory;
	private String restrictFunc;
	private String data;
	private String createDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(name = "act_uuid")
	public String getActUUID() {
		return actUUID;
	}

	@Column(name = "account_uuid")
	public String getAccountUUID() {
		return accountUUID;
	}

	@Column(name = "serial")
	public String getSerial() {
		return serial;
	}

	@Column(name = "act_category")
	public String getActCategory() {
		return actCategory;
	}

	@Column(name = "restrict_func")
	public String getRestrictFunc() {
		return restrictFunc;
	}

	@Column(name = "data")
	public String getData() {
		return data;
	}

	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setActUUID(String actUUID) {
		this.actUUID = actUUID;
	}

	public void setAccountUUID(String accountUUID) {
		this.accountUUID = accountUUID;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setActCategory(String actCategory) {
		this.actCategory = actCategory;
	}

	public void setRestrictFunc(String restrictFunc) {
		this.restrictFunc = restrictFunc;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("id", id)
				.add("actUUID", actUUID)
				.add("serial", serial)
				.add("actCategory", actCategory)
				.add("accountUUID", accountUUID)
				.add("data", data)
				.add("restrictFunc", restrictFunc)
				.add("createDate", createDate)
				.toString();
	}
}
