package com.melonltd.naber.rdbms.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.RestaurantInfo;
import com.melonltd.naber.rdbms.model.dao.RestaurantInfoDao;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Service("restaurantInfoService")
public class RestaurantInfoService {
	
	@Autowired
	private RestaurantInfoDao restaurantInfoDao;
	
	
	public RestaurantInfoVo findByUUID (String uuid) {
		RestaurantInfo info = restaurantInfoDao.findUUID(uuid);
		System.out.println(info);
		return RestaurantInfoVo.valueOf(info);
	}

}
