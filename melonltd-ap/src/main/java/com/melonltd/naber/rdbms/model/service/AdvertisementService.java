package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.Advertisement;
import com.melonltd.naber.rdbms.model.dao.AdvertisementDao;
import com.melonltd.naber.rdbms.model.vo.AdvertisementVo;

@Service("advertisementService")
public class AdvertisementService {

	@Autowired
	private AdvertisementDao advertisementDao;
	
	public List<AdvertisementVo> findAll (){
		List<Advertisement> list = advertisementDao.findAllByEnable();
		if (CollectionUtils.isNotEmpty(list)) {
			return AdvertisementVo.vslueOfArray(list);
		}
		return Lists.<AdvertisementVo>newArrayList();
	}
}
