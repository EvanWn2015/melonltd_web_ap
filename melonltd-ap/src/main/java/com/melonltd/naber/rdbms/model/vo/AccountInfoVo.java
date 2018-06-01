package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;

import com.google.common.base.MoreObjects;
import com.melonltd.naber.rdbms.model.bean.AccountInfo;

public class AccountInfoVo implements Serializable{
	private static final long serialVersionUID = 7659391411455993337L;
	
	private String accountUUID;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String identity;
	private String schoolName;
	private String bonus;
	private String level;
	private String enable;
	private String isLogin;
	private String loginDate;
	private String photo;
	private String photoType;

	public String getAccountUUID() {
		return accountUUID;
	}

	public void setAccountUUID(String accountUUID) {
		this.accountUUID = accountUUID;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhotoType() {
		return photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	
	@Override
	public String toString() {
		return MoreObjects
				.toStringHelper(this.getClass())
				.add("accountUUID",accountUUID)
				.add("name",name)
				.add("password",password)
				.add("email",email)
				.add("phone",phone)
				.add("address",address)
				.add("identity",identity)
				.add("schoolName",schoolName)
				.add("bonus",bonus)
				.add("level",level)
				.add("enable",enable)
				.add("isLogin",isLogin)
				.add("loginDate",loginDate)
				.add("photo",photo)
				.add("photoType",photoType)
				.toString();
	}

	public static AccountInfoVo of(AccountInfo info) {
		AccountInfoVo vo = new AccountInfoVo();
		vo.accountUUID = info.getAccountUUID();
		vo.name = info.getName();
		vo.email = info.getEmail();
		vo.phone = info.getPhone();
		vo.address = info.getAddress();
		vo.identity = info.getIdentity();
		vo.schoolName = info.getSchoolName();
		vo.bonus = info.getBonus();
		vo.level = info.getLevel();
		vo.enable = info.getEnable();
		vo.isLogin = info.getIsLogin();
		vo.loginDate = info.getLoginDate();
		vo.photo = info.getPhoto();
		vo.photoType = info.getPhotoType();
		return vo;
	}

}
