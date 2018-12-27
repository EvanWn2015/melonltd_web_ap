package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.RestaurantPhotoRel;
import com.melonltd.naber.rdbms.model.dao.RestaurantPhotoRelDao;

@Service
public class RestaurantPhotoRelService {
	
	@Autowired
	private RestaurantPhotoRelDao restaurantPhotoRelDao;

	public List<RestaurantPhotoRel> findByRestaurantUUID(String restaurantUUID){
		
		List<RestaurantPhotoRel> list = restaurantPhotoRelDao.findByRestaurantUUID(restaurantUUID);
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return Lists.<RestaurantPhotoRel>newArrayList();
		
//		
//		return restaurantPhotoRelDao.findByRestaurantUUID(restaurantUUID);
	}
	
}
