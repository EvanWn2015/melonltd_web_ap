package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "account_info")
public class AccountInfo implements Serializable {
	private static final long serialVersionUID = 1002107104893197472L;
	
	private String account;
	private String accountUUID;
	private String name;
	private String password;
	private String email;
	private String phone;
	private String address;
	private String birthDay;
	private String identity;
	private String restaurantUUID;
	private String schoolName;
	private String bonus;
	private String level;
	private String enable;
	private String isLogin;
	private String loginDate;
	private String photo;
	private String photoType;
	private String useDiscount;
	private String createDate;

	@Id
	@Column(name = "account", unique = true, nullable = false)
	public String getAccount() {
		return account;
	}
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	@Column(name = "account_uuid")
	public String getAccountUUID() {
		return accountUUID;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	@Column(name = "birth_day")
	public String getBirthDay() {
		return birthDay;
	}
	
	@Column(name = "identity")
	public String getIdentity() {
		return identity;
	}
	
	@Column(name = "restaurant_uuid")
	public String getRestaurantUUID() {
		return restaurantUUID;
	}
	
	@Column(name = "school_name")
	public String getSchoolName() {
		return schoolName;
	}

	@Column(name = "bonus")
	public String getBonus() {
		return bonus;
	}

	@Column(name = "level")
	public String getLevel() {
		return level;
	}

	@Column(name = "enable")
	public String getEnable() {
		return enable;
	}

	@Column(name = "is_login")
	public String getIsLogin() {
		return isLogin;
	}

	@Column(name = "login_date")
	public String getLoginDate() {
		return loginDate;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return photo;
	}

	@Column(name = "photo_type")
	public String getPhotoType() {
		return photoType;
	}
	
	@Column(name = "use_discount")
	public String getUseDiscount() {
		return useDiscount;
	}
	
	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}

	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public void setAccountUUID(String accountUUID) {
		this.accountUUID = accountUUID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public void setRestaurantUUID(String restaurantUUID) {
		this.restaurantUUID = restaurantUUID;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public void setUseDiscount(String useDiscount) {
		this.useDiscount = useDiscount;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("account", account)
				.add("accountUUID", accountUUID)
				.add("name", name)
				.add("password", password)
				.add("email", email)
				.add("createDate",createDate)
				.add("useDiscount", useDiscount)
				.add("phone", phone)
				.add("address", address)
				.add("identity", identity)
				.add("schoolName", schoolName)
				.add("bonus", bonus)
				.add("level", level)
				.add("enable", enable)
				.add("isLogin", isLogin)
				.add("loginDate", loginDate)
				.add("photo", photo)
				.add("photoType", photoType)
				.toString();
	}
}
