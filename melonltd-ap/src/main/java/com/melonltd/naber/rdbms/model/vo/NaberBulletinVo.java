package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.NaberBulletin;

import lombok.Data;

@Data
public class NaberBulletinVo implements Serializable {
	private static final long serialVersionUID = 4818192795467064021L;

	private String bulletin_uuid;
	private String title;
	private String content_text;
	private String bulletin_category;
	private String enable;
	private String create_date;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	public static NaberBulletinVo valueOf(NaberBulletin info) {
		NaberBulletinVo vo = new NaberBulletinVo();
		// vo.bulletin_uuid = info.getBulletinUUID();
		vo.title = info.getTitle();
		vo.content_text = info.getContentText();
		vo.bulletin_category = info.getBulletinCategory();
		// vo.enable = info.getEnable();
		// vo.create_date = info.getCreateDate();
		return vo;
	}

	public static List<NaberBulletinVo> valueOfArray(List<NaberBulletin> infos) {
		List<NaberBulletinVo> vos = Lists.<NaberBulletinVo>newArrayList();
		vos.addAll(infos.stream().map(i -> valueOf(i)).collect(Collectors.toList()));
		return vos;
	}
}
