package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
@Entity
@Table(name = "activities")
public class Activities implements Serializable {
	private static final long serialVersionUID = -83755436543652291L;

	@Id
	@Column(name = "act_uuid", unique = true, nullable = false)
	private String actUUID;
	@Column(name = "serial")
	private String serial;
	@Column(name = "act_category")
	private String actCategory;
	@Column(name = "rel_uuid")
	private String relUUID;
	@Column(name = "title")
	private String title;
	@Column(name = "content_text")
	private String contentText;
	@Column(name = "photo")
	private String photo;
	@Column(name = "data")
	private String data;
	@Column(name = "restrict_func")
	private String restrictFunc;
	@Column(name = "need_bonus")
	private String needBonus;
	@Column(name = "restrict_limit_date")
	private String restrictLimitDate;
	@Column(name = "restrict_send_count")
	private String restrictSendCount;
	@Column(name = "restrict_get_count")
	private String restrictGetCount;
	@Column(name = "man_date")
	private String manDate;
	@Column(name = "exp_date")
	private String expDate;
	@Column(name = "status")
	private String status;
	@Column(name = "enable")
	private String enable;
	@Column(name = "create_date")
	private String createDate;
	@Column(name = "update_date")
	private String updateDate;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
