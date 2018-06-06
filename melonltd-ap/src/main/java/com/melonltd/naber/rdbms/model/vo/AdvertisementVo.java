package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.Advertisement;

public class AdvertisementVo implements Serializable{
	private static final long serialVersionUID = -3060248979663611209L;
	
	private String ad_uuid;
	private String title;
	private String content_text;
	private String photo;
	private String photo_type;
	private String enable;
	private String create_date;
	
	public String getAd_uuid() {
		return ad_uuid;
	}
	public void setAd_uuid(String ad_uuid) {
		this.ad_uuid = ad_uuid;
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
	public String getPhoto_type() {
		return photo_type;
	}
	public void setPhoto_type(String photo_type) {
		this.photo_type = photo_type;
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
				.add("ad_uuid", ad_uuid)
				.add("title", title)
				.add("content_text", content_text)
				.add("photo", photo)
				.add("photo_type", photo_type)
				.add("create_date", create_date)
				.add("enable", enable)
				.toString();
	}
	
	public static AdvertisementVo vslueOf (Advertisement info) {
		AdvertisementVo vo = new AdvertisementVo ();
//		vo.ad_uuid = info.getAdUUID();
		vo.title = info.getTitle();
		vo.content_text = info.getContentText();
		vo.photo = info.getPhoto();
//		vo.photo_type = info.getPhotoType();
//		vo.enable = info.getEnable();
//		vo.create_date = info.getCreateDate();
		return vo;
	}
	
	public static List<AdvertisementVo> vslueOfArray (List<Advertisement> infos) {
		List<AdvertisementVo> vos = Lists.<AdvertisementVo>newArrayList();
		vos.addAll(infos.stream().map(a -> vslueOf(a)).collect(Collectors.toList()));
		return vos;
	}
	
}
