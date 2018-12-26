package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.type.Enable;

import lombok.Data;

@Data
@Entity
@Table(name = "identity_table")
public class IdentityTable implements Serializable {
	private static final long serialVersionUID = 1262986052280602141L;

	public enum Status {
		OPEN, CLOSE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "area")
	private String area;
	@Column(name = "identity_list")
	private String identityList;
	@Column(name = "create_date")
	private String createDate;
	@Column(name = "update_date")
	private String updateDate;
	@Enumerated(EnumType.STRING)
	@Column(name = "enable")
	private Enable enable;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
}
