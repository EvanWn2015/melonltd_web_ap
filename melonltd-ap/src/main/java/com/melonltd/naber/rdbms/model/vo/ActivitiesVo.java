package com.melonltd.naber.rdbms.model.vo;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.Activities;

import lombok.Data;

@Data
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
		return JsonHelper.toJson(this);
	}
}
