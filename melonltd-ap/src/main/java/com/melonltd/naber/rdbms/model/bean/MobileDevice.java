package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mobile_device")
public class MobileDevice implements Serializable {
	private static final long serialVersionUID = 4703671514270687285L;

	public enum CATEGORY {
		IOS, ANDROID
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "account_id")
	private Integer accountId;

	@Enumerated(EnumType.STRING)
	private CATEGORY category;

	@Column(name = "push_token", length = 180)
	private String pushToken;

	@Column(name = "create_date")
	private Date createDate;

}
