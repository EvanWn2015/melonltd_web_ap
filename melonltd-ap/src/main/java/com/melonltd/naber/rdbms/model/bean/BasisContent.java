package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.melonltd.naber.rdbms.model.type.Enable;

@Entity
@Table(name = "basis_content")
public class BasisContent implements Serializable {
	private static final long serialVersionUID = -2573458244510261118L;

	private String type;
	private String function;
	private String content;
	private Enable enable;
	private String createDate;
	
	@Id
	@Column(name = "type")
	public String getType() {
		return type;
	}
	
	@Column(name = "function")
	public String getFunction() {
		return function;
	}
	
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "enable")
	public Enable getEnable() {
		return enable;
	}

	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setEnable(Enable enable) {
		this.enable = enable;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("type", type)
				.add("function", function)
				.add("content", content)
				.add("enable", enable)
				.add("createDate", createDate)
				.toString();
	}

}
