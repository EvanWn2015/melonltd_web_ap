package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.Advertisement;
import com.melonltd.naber.rdbms.model.dao.AdvertisementDao;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.UUIDType;
import com.melonltd.naber.rdbms.model.vo.AdvertisementVo;

@Service("advertisementService")
public class AdvertisementService {

	@Autowired
	private AdvertisementDao advertisementDao;
	
	public List<AdvertisementVo> findAllByEnable (){
		List<Advertisement> list = advertisementDao.findAllByEnable();
		if (CollectionUtils.isNotEmpty(list)) {
			return AdvertisementVo.valueOfArray(list);
		}
		return Lists.<AdvertisementVo>newArrayList();
	}
	
	public List<AdvertisementVo> findAll (){
		List<Advertisement> list = advertisementDao.findAll();
		if (CollectionUtils.isNotEmpty(list)) {
			return AdvertisementVo.valueOfArray(list);
		}
		return Lists.<AdvertisementVo>newArrayList();
	}
	
	public AdvertisementVo save (AdvertisementVo vo){
		Advertisement entity = new Advertisement();
		entity.setAdUUID(Tools.buildUUID(UUIDType.AD));
		entity.setCreateDate(Tools.getNowGMT());
		entity.setEnable(Enable.N.name());
		entity.setTitle(vo.getTitle());
		entity.setContentText("text");
		entity.setPhoto("");
		entity.setPhotoType("jpg");
		entity.setPadPhoto("");
		entity.setLinkType(vo.getLink_type());
		entity.setLinkTo(vo.getLink_to());
		Advertisement info = advertisementDao.save(entity);
		if (ObjectUtils.allNotNull(info)) {
			return AdvertisementVo.valueOf(info);
		}
		return null;	
	}
	
	public AdvertisementVo update (AdvertisementVo vo){
		Advertisement entity = advertisementDao.findByUUID(vo.getAd_uuid());
		if (ObjectUtils.allNotNull(entity)) {
			entity.setEnable(vo.getEnable());
			entity.setTitle(vo.getTitle());
			entity.setContentText("text");
			entity.setPhoto(vo.getPhoto());
			entity.setPhotoType("jpg");
			entity.setPadPhoto(vo.getPad_photo());
			entity.setLinkType(vo.getLink_type());
			entity.setLinkTo(vo.getLink_to());
			Advertisement info = advertisementDao.save(entity);
			if (ObjectUtils.allNotNull(info)) {
				return AdvertisementVo.valueOf(info);
			}else {
				return null;
			}
		}
		return null;	
	}
}
