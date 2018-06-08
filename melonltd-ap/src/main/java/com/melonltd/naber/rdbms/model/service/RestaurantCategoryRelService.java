package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.RestaurantCategoryRel;
import com.melonltd.naber.rdbms.model.dao.RestaurantCategoryRelDao;
import com.melonltd.naber.rdbms.model.vo.RestaurantCategoryRelVo;

@Service("restaurantCategoryRelService")
public class RestaurantCategoryRelService {

	@Autowired
	private RestaurantCategoryRelDao restaurantCategoryRelDao;

	public List<RestaurantCategoryRelVo> findByUUID(String uuid) {
		List<RestaurantCategoryRel> list = restaurantCategoryRelDao.findByRestaurantUUID(uuid);
		return RestaurantCategoryRelVo.valueOfArray(list);
	}
}
