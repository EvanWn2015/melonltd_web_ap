package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.Advertisement;

import lombok.Data;

@Data
public class AdvertisementVo implements Serializable {
	private static final long serialVersionUID = -3060248979663611209L;

	private String ad_uuid;
	private String title;
	private String content_text;
	private String photo;
	private String pad_photo;
	private String photo_type;
	private String link_type;
	private String link_to;
	private String enable;
	private String create_date;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	public static AdvertisementVo valueOf(Advertisement info) {
		AdvertisementVo vo = new AdvertisementVo();
		vo.ad_uuid = info.getAdUUID();
		vo.title = info.getTitle();
		vo.content_text = info.getContentText();
		vo.photo = info.getPhoto();
		vo.pad_photo = info.getPadPhoto();
		vo.link_type = info.getLinkType();
		vo.link_to = info.getLinkTo();
		vo.photo_type = info.getPhotoType();
		vo.enable = info.getEnable();
		vo.create_date = info.getCreateDate();
		return vo;
	}

	public static List<AdvertisementVo> valueOfArray(List<Advertisement> infos) {
		List<AdvertisementVo> vos = Lists.<AdvertisementVo>newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}

}
