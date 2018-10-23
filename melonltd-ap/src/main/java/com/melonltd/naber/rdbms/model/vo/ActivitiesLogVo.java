package com.melonltd.naber.rdbms.model.vo;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.ActivitiesLog;

public class ActivitiesLogVo {
	
	private String id;
	private String act_uuid;
	private String account_uuid;
	private String serial;
	private String act_category;
	private String restrict_func;
	private String data;
	private String create_date;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAct_uuid() {
		return act_uuid;
	}
	public void setAct_uuid(String act_uuid) {
		this.act_uuid = act_uuid;
	}
	public String getAccount_uuid() {
		return account_uuid;
	}
	public void setAccount_uuid(String account_uuid) {
		this.account_uuid = account_uuid;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getAct_category() {
		return act_category;
	}
	public void setAct_category(String act_category) {
		this.act_category = act_category;
	}
	public String getRestrict_func() {
		return restrict_func;
	}
	public void setRestrict_func(String restrict_func) {
		this.restrict_func = restrict_func;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	
	public static ActivitiesLogVo valueOf (ActivitiesLog info) {
		ActivitiesLogVo vo = new ActivitiesLogVo();
		vo.id = String.valueOf(info.getId());
		vo.act_uuid = info.getActUUID();
		vo.account_uuid = info.getAccountUUID();
		vo.serial = info.getSerial();
		vo.act_category = info.getActCategory();
		vo.restrict_func = info.getRestrictFunc();
		vo.data = info.getData();
		vo.create_date = info.getCreateDate();
		
		return vo;
	}
	
	public static List<ActivitiesLogVo> valueOfArray(List<ActivitiesLog> infos) {
		List<ActivitiesLogVo> list = Lists.newArrayList();
		list.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return list;
	}
	
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("id", id)
				.add("act_uuid", act_uuid)
				.add("serial", serial)
				.add("act_category", act_category)
				.add("account_uuid", account_uuid)
				.add("data", data)
				.add("restrict_func", restrict_func)
				.add("create_date", create_date)
				.toString();
	}
	
}
