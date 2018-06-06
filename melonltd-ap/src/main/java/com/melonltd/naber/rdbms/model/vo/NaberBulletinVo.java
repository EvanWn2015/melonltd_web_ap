package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.NaberBulletin;

public class NaberBulletinVo implements Serializable{
	private static final long serialVersionUID = 4818192795467064021L;
	
	
	private String bulletin_uuid;
	private String title;
	private String content_text;
	private String bulletin_category;
	private String enable;
	private String create_date;
	
	
	public String getBulletin_uuid() {
		return bulletin_uuid;
	}
	public void setBulletin_uuid(String bulletin_uuid) {
		this.bulletin_uuid = bulletin_uuid;
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
	public String getBulletin_category() {
		return bulletin_category;
	}
	public void setBulletin_category(String bulletin_category) {
		this.bulletin_category = bulletin_category;
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
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("bulletin_uuid",bulletin_uuid)
				.add("title",title)
				.add("content_text",content_text)
				.add("bulletin_category",bulletin_category)
				.add("enable",enable)
				.add("create_date",create_date)
				.toString();
	}
	
	public static NaberBulletinVo valueOf (NaberBulletin info) {
		NaberBulletinVo vo = new NaberBulletinVo();
//		vo.bulletin_uuid = info.getBulletinUUID();
		vo.title = info.getTitle();
		vo.content_text = info.getContentText();
		vo.bulletin_category = info.getBulletinCategory();
//		vo.enable = info.getEnable();
//		vo.create_date = info.getCreateDate();
		return vo;
	}
	
	public static List<NaberBulletinVo> valueOfArray (List<NaberBulletin> infos) {
		List<NaberBulletinVo> vos = Lists.<NaberBulletinVo>newArrayList();
		vos.addAll(infos.stream().map(i ->valueOf(i) ).collect(Collectors.toList()));
		return vos;
	}
}
