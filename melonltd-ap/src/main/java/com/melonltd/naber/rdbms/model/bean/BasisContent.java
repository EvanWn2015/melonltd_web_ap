package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.type.Enable;

import lombok.Data;

@Data
@Entity
@Table(name = "basis_content")
public class BasisContent implements Serializable {
	private static final long serialVersionUID = -2573458244510261118L;

	@Id
	@Column(name = "type")
	private String type;
	@Column(name = "function")
	private String function;
	@Column(name = "content")
	private String content;
	@Enumerated(EnumType.STRING)
	@Column(name = "enable")
	private Enable enable;
	@Column(name = "create_date")
	private String createDate;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
