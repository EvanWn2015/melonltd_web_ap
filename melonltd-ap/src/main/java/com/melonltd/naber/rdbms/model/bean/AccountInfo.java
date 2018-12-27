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
@Table(name = "account_info")
public class AccountInfo implements Serializable {
	private static final long serialVersionUID = 1002107104893197472L;

	@Id
	@Column(name = "account", unique = true, nullable = false)
	private String account;
	@Column(name = "phone")
	private String phone;
	@Column(name = "account_uuid")
	private String accountUUID;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "address")
	private String address;
	@Column(name = "birth_day")
	private String birthDay;
	@Column(name = "gender")
	private String gender;
	@Column(name = "identity")
	private String identity;
	@Column(name = "restaurant_uuid")
	private String restaurantUUID;
	@Column(name = "school_name")
	private String schoolName;
	@Column(name = "bonus")
	private String bonus;
	@Column(name = "level")
	private String level;
	@Column(name = "enable")
	private String enable;
	@Column(name = "is_login")
	private String isLogin;
	@Column(name = "login_date")
	private String loginDate;
	@Column(name = "photo")
	private String photo;
	@Column(name = "photo_type")
	private String photoType;
	@Column(name = "use_bonus")
	private String useBonus;
	@Column(name = "create_date")
	private String createDate;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
}
