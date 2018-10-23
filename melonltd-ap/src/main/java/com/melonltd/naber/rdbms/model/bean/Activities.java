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
	private String serial;
	private String actCategory;
	private String relUUID;
	private String title;
	private String contentText;
	private String photo;
	private String data;
	private String restrictFunc;
	private String needBonus;
	private String restrictLimitDate;
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
	
	@Column(name = "serial")
	public String getSerial() {
		return serial;
	}

	@Column(name = "act_category")
	public String getActCategory() {
		return actCategory;
	}

	@Column(name = "rel_uuid")
	public String getRelUUID() {
		return relUUID;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	@Column(name = "content_text")
	public String getContentText() {
		return contentText;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return photo;
	}

	@Column(name = "data")
	public String getData() {
		return data;
	}

	@Column(name = "restrict_func")
	public String getRestrictFunc() {
		return restrictFunc;
	}

	@Column(name = "need_bonus")
	public String getNeedBonus() {
		return needBonus;
	}
	
	@Column(name = "restrict_limit_date")
	public String getRestrictLimitDate() {
		return restrictLimitDate;
	}

	@Column(name = "restrict_send_count")
	public String getRestrictSendCount() {
		return restrictSendCount;
	}

	@Column(name = "restrict_get_count")
	public String getRestrictGetCount() {
		return restrictGetCount;
	}

	@Column(name = "man_date")
	public String getManDate() {
		return manDate;
	}

	@Column(name = "exp_date")
	public String getExpDate() {
		return expDate;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	@Column(name = "enable")
	public String getEnable() {
		return enable;
	}

	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}

	@Column(name = "update_date")
	public String getUpdateDate() {
		return updateDate;
	}

	public void setActUUID(String actUUID) {
		this.actUUID = actUUID;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setActCategory(String actCategory) {
		this.actCategory = actCategory;
	}

	public void setRelUUID(String relUUID) {
		this.relUUID = relUUID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setRestrictFunc(String restrictFunc) {
		this.restrictFunc = restrictFunc;
	}

	public void setNeedBonus(String needBonus) {
		this.needBonus = needBonus;
	}

	public void setRestrictLimitDate(String restrictLimitDate) {
		this.restrictLimitDate = restrictLimitDate;
	}
	
	public void setRestrictSendCount(String restrictSendCount) {
		this.restrictSendCount = restrictSendCount;
	}

	public void setRestrictGetCount(String restrictGetCount) {
		this.restrictGetCount = restrictGetCount;
	}

	public void setManDate(String manDate) {
		this.manDate = manDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("actUUID", actUUID)
				.add("serial", serial)
				.add("actCategory", actCategory)
				.add("relUUID", relUUID)
				.add("title", title)
				.add("contentText", contentText)
				.add("photo", photo)
				.add("data", data)
				.add("restrictFunc", restrictFunc)
				.add("needBonus", needBonus)
				.add("restrictLimitDate", restrictLimitDate)
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
