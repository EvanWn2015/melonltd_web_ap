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
@Table(name = "advertisement")
public class Advertisement implements Serializable {
	private static final long serialVersionUID = -8066578462454308401L;

	@Id
	@Column(name = "ad_uuid", unique = true, nullable = false)
	private String adUUID;
	@Column(name = "title")
	private String title;
	@Column(name = "content_text")
	private String contentText;
	@Column(name = "photo")
	private String photo;
	@Column(name = "pad_photo")
	private String padPhoto;
	@Column(name = "photo_type")
	private String photoType;
	@Column(name = "link_type")
	private String linkType;
	@Column(name = "link_to")
	private String linkTo;
	@Column(name = "enable")
	private String enable;
	@Column(name = "create_date")
	private String createDate;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
