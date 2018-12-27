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
@Table(name = "naber_bulletin")
public class NaberBulletin implements Serializable {
	private static final long serialVersionUID = 6082665371149846602L;

	@Id
	@Column(name = "bulletin_uuid", unique = true, nullable = false)
	private String bulletinUUID;
	@Column(name = "title")
	private String title;
	@Column(name = "content_text")
	private String contentText;
	@Column(name = "bulletin_category")
	private String bulletinCategory;
	@Column(name = "enable")
	private String enable;
	@Column(name = "create_date")
	private String createDate;
	
	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
