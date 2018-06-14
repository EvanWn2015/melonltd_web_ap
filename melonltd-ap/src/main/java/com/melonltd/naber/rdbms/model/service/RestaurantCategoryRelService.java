package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.bean.RestaurantCategoryRel;
import com.melonltd.naber.rdbms.model.dao.RestaurantCategoryRelDao;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.RestaurantCategoryRelVo;

@Service("restaurantCategoryRelService")
public class RestaurantCategoryRelService {

	@Autowired
	private RestaurantCategoryRelDao restaurantCategoryRelDao;

	public List<RestaurantCategoryRelVo> findByRestaurantUUID(String restaurantUUID) {
		List<RestaurantCategoryRel> list = restaurantCategoryRelDao.findByRestaurantUUID(restaurantUUID);
		return RestaurantCategoryRelVo.valueOfArray(list);
	}

	public List<RestaurantCategoryRelVo> findAllByRestaurantUUID(String restaurantUUID) {
		List<RestaurantCategoryRel> list = restaurantCategoryRelDao.findAllByRestaurantUUID(restaurantUUID);
		return RestaurantCategoryRelVo.valueOfArray(list);
	}

	public int getStatusByCategoryUUIDs(List<String> categoryUUIDs) {
		List<RestaurantCategoryRel> list = restaurantCategoryRelDao.findByCategoryUUIDs(categoryUUIDs);
		return list.size();
	}

	public RestaurantCategoryRelVo saveCategoryRel(String restaurantUUID, String name) {
		RestaurantCategoryRel info = new RestaurantCategoryRel();
		info.setCategoryUUID(Tools.buildUUID(UUIDType.RESTAURANT_CATEGORY));
		info.setCategoryName(name);
		info.setRestaurantUUID(restaurantUUID);
		info.setCreateDate(Tools.getNowUTC());
		info.setEnable(Enable.Y.name());
		info.setStatus(SwitchStatus.OPEN.name());

		RestaurantCategoryRel newInfo = restaurantCategoryRelDao.save(info);

		if (ObjectUtils.allNotNull(newInfo)) {
			return RestaurantCategoryRelVo.valueOf(newInfo);
		}
		return null;
	}

	public RestaurantCategoryRelVo updateCategoryRelStatus(String restaurantUUID, ReqData req) {
		RestaurantCategoryRel info = restaurantCategoryRelDao.findByRestaurantAndCategoryRelUUID(restaurantUUID, req.getUuid());
		if (!ObjectUtils.allNotNull(info)) {
			return null;
		}
		if (info.getStatus().equals(req.getStatus().toUpperCase())) {
			return null;
		}
		info.setStatus(req.getStatus().toUpperCase());
		RestaurantCategoryRel newInfo = restaurantCategoryRelDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return RestaurantCategoryRelVo.valueOf(newInfo);
		}
		return null;
	}

	public RestaurantCategoryRel findByRestaurantAndCategoryRelUUID(String restaurantUUID, String categoryRelUUID) {
		RestaurantCategoryRel info = restaurantCategoryRelDao.findByRestaurantAndCategoryRelUUID(restaurantUUID,
				categoryRelUUID);
		if (ObjectUtils.allNotNull(info)) {
			return info;
		}
		return null;
	}

	public RestaurantCategoryRelVo save(RestaurantCategoryRel info) {
		RestaurantCategoryRel newInfo = restaurantCategoryRelDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return RestaurantCategoryRelVo.valueOf(newInfo);
		}
		return null;
	}

}
