package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.RestaurantPhotoRel;
import com.melonltd.naber.rdbms.model.dao.RestaurantPhotoRelDao;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantPhotoRelVo;

@Service
public class RestaurantPhotoRelService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantPhotoRelService.class);
	
	@Autowired
	private RestaurantPhotoRelDao restaurantPhotoRelDao;

	public List<RestaurantPhotoRel> findByRestaurantUUID(String restaurantUUID){
		if (StringUtils.isBlank(restaurantUUID)) {
			return Lists.<RestaurantPhotoRel>newArrayList();	
		}
		List<RestaurantPhotoRel> list = restaurantPhotoRelDao.findByRestaurantUUID(restaurantUUID);
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return Lists.<RestaurantPhotoRel>newArrayList(); 
	}
	
	public RestaurantPhotoRel save(RestaurantPhotoRelVo vo) {
		RestaurantPhotoRel entity = new RestaurantPhotoRel();
		if (StringUtils.isBlank(vo.getId())) {
			entity.setCreateDate(Tools.getNowGMT());	
		}else {
			entity.setId(Long.valueOf(vo.getId()));
		}
		entity.setCreateDate(Tools.getNowGMT());
		entity.setPhoto(vo.getPhoto());
		entity.setRestaurantUUID(vo.getRestaurant_uuid());
		entity.setEnable(Enable.Y);
		entity.setStatus(Status.OPEN);
		
		return restaurantPhotoRelDao.save(entity);
	}
	
	public boolean delete(Long id) {
		try {
			restaurantPhotoRelDao.enablePhoto(Enable.N, id);
			return true;
		} catch (Exception e) {
			LOGGER.error("update photo fail RestaurantPhotoRel Id: {}", id);
			return false;
		}
		
	}
	
	
}
