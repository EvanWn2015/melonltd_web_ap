package com.melonltd.naber.rdbms.model.vo;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.Activities;

public class ActivitiesVo {

	private String act_uuid;
	private String serial;
	private String act_category;
	private String rel_uuid;
	private String title;
	private String content_text;
	private String photo;
	private String data;
	private String restrict_func;
	private String need_bonus;
	private String restrict_limit_date;
	private String restrict_send_count;
	private String restrict_get_count;
	private String man_date;
	private String exp_date;
	private String status;
	private String enable;
	private String create_date;
	private String update_date;


	public String getAct_uuid() {
		return act_uuid;
	}

	public void setAct_uuid(String act_uuid) {
		this.act_uuid = act_uuid;
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

	public String getRel_uuid() {
		return rel_uuid;
	}

	public void setRel_uuid(String rel_uuid) {
		this.rel_uuid = rel_uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent_text() {
		return content_text;
	}

	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getRestrict_func() {
		return restrict_func;
	}

	public void setRestrict_func(String restrict_func) {
		this.restrict_func = restrict_func;
	}

	public String getNeed_bonus() {
		return need_bonus;
	}

	public void setNeed_bonus(String need_bonus) {
		this.need_bonus = need_bonus;
	}

	public String getRestrict_limit_date() {
		return restrict_limit_date;
	}

	public void setRestrict_limit_date(String restrict_limit_date) {
		this.restrict_limit_date = restrict_limit_date;
	}

	public String getRestrict_send_count() {
		return restrict_send_count;
	}

	public void setRestrict_send_count(String restrict_send_count) {
		this.restrict_send_count = restrict_send_count;
	}

	public String getRestrict_get_count() {
		return restrict_get_count;
	}

	public void setRestrict_get_count(String restrict_get_count) {
		this.restrict_get_count = restrict_get_count;
	}

	public String getMan_date() {
		return man_date;
	}

	public void setMan_date(String man_date) {
		this.man_date = man_date;
	}

	public String getExp_date() {
		return exp_date;
	}

	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public static ActivitiesVo valueOf(Activities info) {
		ActivitiesVo vo = new ActivitiesVo();
		vo.setAct_uuid(info.getActUUID());
		vo.setSerial(info.getSerial());
		vo.setAct_category(info.getActCategory());
		vo.setRel_uuid(info.getRelUUID());
		vo.setTitle(info.getTitle());
		vo.setContent_text(info.getContentText());
		vo.setPhoto(info.getPhoto());
		vo.setData(info.getData());
		vo.setRestrict_func(info.getRestrictFunc());
		vo.setNeed_bonus(info.getNeedBonus());
		vo.setRestrict_limit_date(info.getRestrictLimitDate());
		vo.setRestrict_send_count(info.getRestrictSendCount());
		vo.setRestrict_get_count(info.getRestrictGetCount());
		vo.setMan_date(info.getManDate());
		vo.setExp_date(info.getExpDate());
		vo.setStatus(info.getStatus());
		vo.setEnable(info.getEnable());
		vo.setCreate_date(info.getCreateDate());
		vo.setUpdate_date(info.getUpdateDate());
		return vo;
	}
	
	public static List<ActivitiesVo> valueOfArray(List<Activities> infos) {
		List<ActivitiesVo> list = Lists.newArrayList();
		list.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return list;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("act_uuid", act_uuid)
				.add("serial", serial)
				.add("act_category", act_category)
				.add("rel_uuid", rel_uuid)
				.add("title", title)
				.add("content_text", content_text)
				.add("photo", photo)
				.add("data", data)
				.add("restrict_func", restrict_func)
				.add("need_bonus", need_bonus)
				.add("restrict_limit_date", restrict_limit_date)
				.add("restrict_send_count", restrict_send_count)
				.add("restrict_get_count", restrict_get_count)
				.add("man_date", man_date)
				.add("exp_date", exp_date)
				.add("status", status)
				.add("enable", enable)
				.add("create_date", create_date)
				.add("update_date", update_date)
				.toString();
	}
}
