package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

//@Entity
//@Table(name = "naber_bulletin")
public class naberBulletin implements Serializable {
	private static final long serialVersionUID = 6082665371149846602L;

	private String bulletinUUID;
	private String title;
	private String contentText;
	private String bulletinCategory;
	private String enable;
	private String createDate;
	
	@Id
	@Column(name = "bulletin_uuid", unique = true, nullable = false)
	public String getBulletinUUID() {
		return bulletinUUID;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	
	@Column(name = "content_text")
	public String getContentText() {
		return contentText;
	}
	
	@Column(name = "bulletin_category")
	public String getBulletinCategory() {
		return bulletinCategory;
	}
	
	@Column(name = "enable")
	public String getEnable() {
		return enable;
	}
	
	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("bulletinUUID",bulletinUUID)
				.add("title",title)
				.add("contentText",contentText)
				.add("bulletinCategory",bulletinCategory)
				.add("enable",enable)
				.add("createDate",createDate)
				.toString();
	}
	

}
