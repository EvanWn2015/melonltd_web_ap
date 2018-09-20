package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "advertisement")
public class Advertisement implements Serializable { 
	private static final long serialVersionUID = -8066578462454308401L;
	
	private String adUUID;
	private String title;
	private String contentText;
	private String photo;
	private String padPhoto;
	private String photoType;
	private String linkType;
	private String linkTo;
	private String enable;
	private String createDate;

	@Id
	@Column(name = "ad_uuid", unique = true, nullable = false)
	public String getAdUUID() {
		return adUUID;
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
	
	@Column(name = "pad_photo")
	public String getPadPhoto() {
		return padPhoto;
	}
	
	@Column(name = "photo_type")
	public String getPhotoType() {
		return photoType;
	}
	
	@Column(name = "link_type")
	public String getLinkType() {
		return linkType;
	}
	
	@Column(name = "link_to")
	public String getLinkTo() {
		return linkTo;
	}
	@Column(name = "enable")
	public String getEnable() {
		return enable;
	}
	
	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}
	
	public void setAdUUID(String adUUID) {
		this.adUUID = adUUID;
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

	public void setPadPhoto(String padPhoto) {
		this.padPhoto = padPhoto;
	}
	
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("adUUID", adUUID)
				.add("title", title)
				.add("contentText", contentText)
				.add("photo", photo)
				.add("padPhoto", padPhoto)
				.add("photoType", photoType)
				.add("linkType", linkType)
				.add("linkTo", linkTo)
				.add("createDate", createDate)
				.add("enable", enable)
				.toString();
	}
	
}
