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

@Entity
@Table(name = "account_info")
public class AccountInfo implements Serializable {
	private static final long serialVersionUID = 1002107104893197472L;

	public enum IDENTITY {
		ELEMENTARY, SENOR, JUNOR, UNIVERSITY, NON_STUDENT, SELLERS
	}

	public enum LEVEL {
		MANAGE, EMPLOYEE
	}

	public enum ENABLE {
		Y, N
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "password", length = 50)
	private String password;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "phone", length = 20)
	private String phone;

	@Column(name = "address", length = 250)
	private String address;

	@Enumerated(EnumType.STRING)
	private IDENTITY identity;

	@Column(name = "school_name", length = 250)
	private String schoolName;

	@Enumerated(EnumType.STRING)
	private LEVEL level;

	@Enumerated(EnumType.STRING)
	private ENABLE enable;

	@Column(name = "is_login", length = 1)
	private Integer isLogin;

}
