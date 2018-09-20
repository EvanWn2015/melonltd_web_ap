package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "activities")
public class Activities implements Serializable { 
	private static final long serialVersionUID = -83755436543652291L;
	
	private String actUUID;
	private String actCategory;
	private String relUUID;
	private String title;
	private String contentText;
	private String photo;
	private String data;
	private String restrictFunc;
	private String needBonus;
	private String restrictSendCount;
	private String restrictGetCount;
	private String manDate;
	private String expDate;
	private String status;
	private String enable;
	private String createDate;
	private String updateDate;
	
	@Id
	@Column(name = "act_uuid", unique = true, nullable = false)
	public String getActUUID() {
		return actUUID;
	}




	public String getActCategory() {
		return actCategory;
	}




	public String getRelUUID() {
		return relUUID;
	}




	public String getTitle() {
		return title;
	}




	public String getContentText() {
		return contentText;
	}




	public String getPhoto() {
		return photo;
	}




	public String getData() {
		return data;
	}




	public String getRestrictFunc() {
		return restrictFunc;
	}




	public String getNeedBonus() {
		return needBonus;
	}




	public String getRestrictSendCount() {
		return restrictSendCount;
	}




	public String getRestrictGetCount() {
		return restrictGetCount;
	}




	public String getManDate() {
		return manDate;
	}




	public String getExpDate() {
		return expDate;
	}




	public String getStatus() {
		return status;
	}




	public String getEnable() {
		return enable;
	}




	public String getCreateDate() {
		return createDate;
	}




	public String getUpdateDate() {
		return updateDate;
	}




	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
			.add("actUUID", actUUID)
			.add("actCategory", actCategory)
			.add("relUUID", relUUID)
			.add("title", title)
			.add("contentText", contentText)
			.add("photo", photo)
			.add("data", data)
			.add("restrictFunc", restrictFunc)
			.add("needBonus", needBonus)
			.add("restrictSendCount", restrictSendCount)
			.add("restrictGetCount", restrictGetCount)
			.add("manDate", manDate)
			.add("expDate", expDate)
			.add("status", status)
			.add("enable", enable)
			.add("createDate", createDate)
			.add("updateDate", updateDate)
			.toString();
	}

}
