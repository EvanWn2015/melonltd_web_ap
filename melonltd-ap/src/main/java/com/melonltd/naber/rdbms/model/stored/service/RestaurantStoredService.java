package com.melonltd.naber.rdbms.model.stored.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.stored.bean.RestaurantStored;
import com.melonltd.naber.rdbms.model.stored.dao.RestaurantStoredDao;
import com.melonltd.naber.rdbms.model.stored.vo.RestaurantStoredVo;

@Service("restaurantStoredService")
public class RestaurantStoredService {

	@Autowired
	private RestaurantStoredDao restaurantStoredDao;

	public List<RestaurantStoredVo> findByLatlng(String lat, String lng, int page) {
		int inPage = page * 10 - 10;
		List<RestaurantStored> list = restaurantStoredDao.getRestaurantDistanceByLatlng(lat, lng, inPage);
		return RestaurantStoredVo.valueOfArray(list);
	}

	public List<RestaurantStoredVo> findByTop(String lat, String lng, int top) {
		List<RestaurantStored> list = restaurantStoredDao.getRestaurantTopByLatlng(lat, lng, top);
		return RestaurantStoredVo.valueOfArray(list);
	}

	public List<RestaurantStoredVo> findByArea(String area, int page) {
		int inPage = page * 10 - 10;
		List<RestaurantStored> list = restaurantStoredDao.getRestaurantByArea(area, inPage);
		return RestaurantStoredVo.valueOfArray(list);
	}

	public List<RestaurantStoredVo> findByCategory(String category, int page) {
		int inPage = page * 10 - 10;
		List<RestaurantStored> list = restaurantStoredDao.getRestaurantByCategory(category, inPage);
		return RestaurantStoredVo.valueOfArray(list);
	}

}
