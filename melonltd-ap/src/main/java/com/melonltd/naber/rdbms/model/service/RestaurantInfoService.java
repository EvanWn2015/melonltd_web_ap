package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.RestaurantInfo;
import com.melonltd.naber.rdbms.model.dao.RestaurantInfoDao;
import com.melonltd.naber.rdbms.model.vo.LatLngVo;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Service("restaurantInfoService")
public class RestaurantInfoService {

	@Autowired
	private RestaurantInfoDao restaurantInfoDao;

	public RestaurantInfoVo findByUUID(String uuid) {
		RestaurantInfo info = restaurantInfoDao.findUUID(uuid);
		if (ObjectUtils.anyNotNull(info)) {
			return RestaurantInfoVo.valueOf(info, null);
		}
		return null;
	}

	public List<RestaurantInfoVo> findByTop(LatLngVo start) {
		Sort sort = new Sort(Direction.DESC, "top");
		Pageable pageable = new PageRequest(0, 30, sort);
		Page<RestaurantInfo> page = restaurantInfoDao.findByTop(pageable);
		if (page.hasContent()) {
			return RestaurantInfoVo.valueOfArray(page.getContent(), start);
		}
		return Lists.<RestaurantInfoVo>newArrayList();
	}

	public List<RestaurantInfoVo> findByArea(String area, int page) {
		if (page < 0) {
			page --;
		}
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(page, 10, sort);
		Page<RestaurantInfo> pages = restaurantInfoDao.findByArea(area, pageable);
		return Lists.<RestaurantInfoVo>newArrayList();
	}

	public List<RestaurantInfoVo> findByCategory(String category, int page) {
		if (page < 0) {
			page --;
		}
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(page, 10, sort);
		Page<RestaurantInfo> pages = restaurantInfoDao.findByCategory(category, pageable);
		return Lists.<RestaurantInfoVo>newArrayList();
	}
	
	
	public List<RestaurantInfoVo> findByUUIDs(List<String> uuids, LatLngVo start) {
		List<RestaurantInfo> list = restaurantInfoDao.findUUIDs(uuids);
		return RestaurantInfoVo.valueOfArray(list, start);
	}

}
