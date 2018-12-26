package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
@Entity
@Table(name = "activities_log")
public class ActivitiesLog implements Serializable {
	private static final long serialVersionUID = 7294609723830911896L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "act_uuid")
	private String actUUID;
	@Column(name = "account_uuid")
	private String accountUUID;
	@Column(name = "serial")
	private String serial;
	@Column(name = "act_category")
	private String actCategory;
	@Column(name = "restrict_func")
	private String restrictFunc;
	@Column(name = "data")
	private String data;
	@Column(name = "create_date")
	private String createDate;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
}
