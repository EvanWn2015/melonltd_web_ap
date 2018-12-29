package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.RestaurantLocationTemplate;
import com.melonltd.naber.rdbms.model.dao.RestaurantLocationTemplateDao;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Service("restaurantLocationTemplateService")
public class RestaurantLocationTemplateService {
	
	@Autowired
	private RestaurantLocationTemplateDao restaurantLocationTemplateDao;
	
	public RestaurantLocationTemplate save(RestaurantLocationTemplate entity) {
		return restaurantLocationTemplateDao.save(entity);
	}
	
	public void updateEnable(String enable, String restaurantUUID) {
		restaurantLocationTemplateDao.updateEnable(enable, restaurantUUID);
	}
	
	public List<RestaurantInfoVo> findAll() {
		 List<RestaurantLocationTemplate> list = restaurantLocationTemplateDao.findAllByEnable();
		 return RestaurantInfoVo.valueOfTemplateArray(list);
	}

}
