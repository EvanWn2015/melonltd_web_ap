package com.melonltd.naber.rdbms.model.vo;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.ActivitiesLog;

import lombok.Data;

@Data
public class ActivitiesLogVo {

	private String id;
	private String act_uuid;
	private String account_uuid;
	private String serial;
	private String act_category;
	private String restrict_func;
	private String data;
	private String create_date;

	public static ActivitiesLogVo valueOf(ActivitiesLog info) {
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
		return JsonHelper.toJson(this);
	}

}
