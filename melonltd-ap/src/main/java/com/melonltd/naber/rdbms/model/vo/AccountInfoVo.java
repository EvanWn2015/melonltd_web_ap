package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.AccountInfo;

import lombok.Data;

@Data
public class AccountInfoVo implements Serializable {
	private static final long serialVersionUID = 7659391411455993337L;

	private String account;
	private String account_uuid;
	private String restaurant_uuid;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String birth_day;
	private String gender;
	private String identity;
	private String school_name;
	private String bonus;
	private String level;
	private String enable;
	private String is_login;
	private String login_date;
	private String photo;
	private String photo_type;
	private String use_bonus;
	private String create_date;

	private String device_token;
	private String device_category;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	public static AccountInfoVo of(AccountInfo info, boolean hasPassword) {
		AccountInfoVo vo = new AccountInfoVo();

		vo.account = info.getAccount();
		vo.account_uuid = info.getAccountUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.name = info.getName();
		vo.email = info.getEmail();
		vo.phone = info.getPhone();
		vo.address = info.getAddress();
		vo.birth_day = info.getBirthDay();
		vo.gender = info.getGender();
		vo.identity = info.getIdentity();
		vo.school_name = info.getSchoolName();
		vo.bonus = info.getBonus();
		vo.level = info.getLevel();
		vo.enable = info.getEnable();
		vo.is_login = info.getIsLogin();
		vo.login_date = info.getLoginDate();
		vo.photo = info.getPhoto();
		vo.photo_type = info.getPhotoType();
		vo.use_bonus = info.getUseBonus();
		vo.create_date = info.getCreateDate();
		if (hasPassword) {
			vo.password = info.getPassword();
		}

		return vo;
	}
}
