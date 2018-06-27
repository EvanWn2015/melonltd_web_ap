package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.RestaurantInfo;
import com.melonltd.naber.rdbms.model.dao.RestaurantInfoDao;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Service("restaurantInfoService")
public class RestaurantInfoService {

	@Autowired
	private RestaurantInfoDao restaurantInfoDao;

	
//	public RestaurantInfoVo save(RestaurantInfo info) {
//		restaurantInfoDao.save(info);
//		return null;
//	}
	
	public RestaurantInfoVo changeCanStoreRange(RestaurantInfoVo vo, List<DateRangeVo> canStoreRange) {
		RestaurantInfo info = restaurantInfoDao.save(newRestaurantInfo(vo, canStoreRange));
		if (ObjectUtils.allNotNull(info)) {
			return RestaurantInfoVo.valueOf(info, true);
		}
		return null;
	}
	
	public RestaurantInfoVo findByUUID(String uuid) {
		RestaurantInfo info = restaurantInfoDao.findUUID(uuid);
		if (ObjectUtils.anyNotNull(info)) {
			return RestaurantInfoVo.valueOf(info, true);
		}
		return null;
	}

	public List<RestaurantInfoVo> findByTop(int top) {
		Sort sort = new Sort(Direction.ASC, "top");
		Pageable pageable = new PageRequest(0, top, sort);
		Page<RestaurantInfo> page = restaurantInfoDao.findByTop(pageable);
		if (page.hasContent()) {
			return RestaurantInfoVo.valueOfArray(page.getContent());
		}
		return Lists.<RestaurantInfoVo>newArrayList();
	}

	public List<RestaurantInfoVo> findByArea(String area, int page) {
		if (page > 0) {
			page --;
		}
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(page, 10, sort);
		Page<RestaurantInfo> pages = restaurantInfoDao.findByArea(area, pageable);
		if (pages.hasContent()) {
			return RestaurantInfoVo.valueOfArray(pages.getContent());
		}
		return Lists.<RestaurantInfoVo>newArrayList();
	}

	public List<RestaurantInfoVo> findByCategory(String category, int page) {
		if (page > 0) {
			page --;
		}
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(page, 10, sort);
		Page<RestaurantInfo> pages = restaurantInfoDao.findByCategory(category, pageable);
		if (pages.hasContent()) {
			return RestaurantInfoVo.valueOfArray(pages.getContent());
		}
		return Lists.<RestaurantInfoVo>newArrayList();
	}
	
	
	public List<RestaurantInfoVo> findByUUIDs(List<String> uuids) {
		if (CollectionUtils.isNotEmpty(uuids)) {
			List<RestaurantInfo> list = restaurantInfoDao.findUUIDs(uuids);
			return RestaurantInfoVo.valueOfArray(list);
		}else {
			return Lists.<RestaurantInfoVo>newArrayList();
		}
	}
	
	public RestaurantInfoVo findByAccountUUID(String accountUUID) {
		RestaurantInfo info = restaurantInfoDao.findByAccountUUID(accountUUID);
		if (ObjectUtils.allNotNull(info)) {
			return RestaurantInfoVo.valueOf(info, true);
		}
		return null;
	}
	
	public RestaurantInfoVo update(RestaurantInfoVo vo) {
		RestaurantInfo info = restaurantInfoDao.save(newRestaurantInfo(vo));
		if (ObjectUtils.allNotNull(info)) {
			return RestaurantInfoVo.valueOf(info, false);
		}
		return null;
	}
	
//	public RestaurantInfo findNativeByAccountUUID(String accountUUID) {
//		RestaurantInfo info = restaurantInfoDao.findByAccountUUID(accountUUID);
//		if (ObjectUtils.allNotNull(info)) {
//			return info;
//		}
//		return null;
//	}
	
	private static RestaurantInfo newRestaurantInfo(RestaurantInfoVo vo,List<DateRangeVo> canStoreRange) {
		RestaurantInfo info = new RestaurantInfo();
		info.setRestaurantUUID(vo.getRestaurant_uuid());
		info.setName(vo.getName());
		info.setAddress(vo.getAddress());
		info.setStoreStart(vo.getStore_start());
		info.setStoreEnd(vo.getStore_end());
		info.setNotBusiness(JsonHelper.toJson(vo.getNot_business()));
		info.setCanStoreRange(JsonHelper.toJson(canStoreRange));
		info.setRestaurantCategory(vo.getRestaurant_category());
		info.setLatitude(vo.getLatitude());
		info.setLongitude(vo.getLongitude());
		info.setBulletin(vo.getBulletin());
		info.setPhoto(vo.getPhoto());
		info.setCreateDate(vo.getCreate_date());
		info.setBackgroundPhoto(vo.getBackground_photo());
		info.setPhotoType(vo.getPhoto_type());
		info.setEnable(vo.getEnable());
		info.setTop(vo.getTop());
		return info;
	}
	
	
	private static RestaurantInfo newRestaurantInfo(RestaurantInfoVo vo) {
		RestaurantInfo info = new RestaurantInfo();
		info.setRestaurantUUID(vo.getRestaurant_uuid());
		info.setName(vo.getName());
		info.setAddress(vo.getAddress());
		info.setStoreStart(vo.getStore_start());
		info.setStoreEnd(vo.getStore_end());
		info.setNotBusiness(JsonHelper.toJson(vo.getNot_business()));
		info.setCanStoreRange(JsonHelper.toJson(vo.getCan_store_range()));
		info.setRestaurantCategory(vo.getRestaurant_category());
		info.setLatitude(vo.getLatitude());
		info.setLongitude(vo.getLongitude());
		info.setBulletin(vo.getBulletin());
		info.setPhoto(vo.getPhoto());
		info.setCreateDate(vo.getCreate_date());
		info.setBackgroundPhoto(vo.getBackground_photo());
		info.setPhotoType(vo.getPhoto_type());
		info.setEnable(vo.getEnable());
		info.setTop(vo.getTop());
		return info;
	}

}
