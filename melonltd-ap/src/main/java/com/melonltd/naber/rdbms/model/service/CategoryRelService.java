package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.bean.CategoryRel;
import com.melonltd.naber.rdbms.model.dao.CategoryRelDao;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.CategoryRelVo;

@Service("categoryRelService")
public class CategoryRelService {

	@Autowired
	private CategoryRelDao categoryRelDao;

	public List<CategoryRelVo> findByRestaurantUUID(String restaurantUUID) {
		List<CategoryRel> list = categoryRelDao.findByRestaurantUUID(restaurantUUID);
		return CategoryRelVo.valueOfArray(list);
	}

	public List<CategoryRelVo> findAllByRestaurantUUID(String restaurantUUID) {
		List<CategoryRel> list = categoryRelDao.findAllByRestaurantUUID(restaurantUUID);
		return CategoryRelVo.valueOfArray(list);
	}

	public int getStatusByCategoryUUIDs(List<String> categoryUUIDs) {
		List<CategoryRel> list = categoryRelDao.findByCategoryUUIDs(categoryUUIDs);
		return list.size();
	}

	public CategoryRelVo saveCategoryRel(String restaurantUUID, String name) {
		CategoryRel info = new CategoryRel();
		info.setCategoryUUID(Tools.buildUUID(UUIDType.RESTAURANT_CATEGORY));
		info.setCategoryName(name);
		info.setRestaurantUUID(restaurantUUID);
		info.setCreateDate(Tools.getNowGMT());
		info.setEnable(Enable.Y.name());
		info.setStatus(SwitchStatus.OPEN.name());

		CategoryRel newInfo = categoryRelDao.save(info);

		if (ObjectUtils.allNotNull(newInfo)) {
			return CategoryRelVo.valueOf(newInfo);
		}
		return null;
	}

	public CategoryRelVo updateCategoryRelStatus(String restaurantUUID, ReqData req) {
		CategoryRel info = categoryRelDao.findByRestaurantAndCategoryRelUUID(restaurantUUID, req.getUuid());
		if (!ObjectUtils.allNotNull(info)) {
			return null;
		}
		if (info.getStatus().equals(req.getStatus().toUpperCase())) {
			return null;
		}
		info.setStatus(req.getStatus().toUpperCase());
		CategoryRel newInfo = categoryRelDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return CategoryRelVo.valueOf(newInfo);
		}
		return null;
	}

	public CategoryRel findByRestaurantAndCategoryRelUUID(String restaurantUUID, String categoryRelUUID) {
		CategoryRel info = categoryRelDao.findByRestaurantAndCategoryRelUUID(restaurantUUID,
				categoryRelUUID);
		if (ObjectUtils.allNotNull(info)) {
			return info;
		}
		return null;
	}
	
	public CategoryRelVo findByRestaurantUUIDAndCategoryRelUUID(String restaurantUUID, String categoryRelUUID) {
		CategoryRel info = categoryRelDao.findByRestaurantAndCategoryRelUUID(restaurantUUID,
				categoryRelUUID);
		if (ObjectUtils.allNotNull(info)) {
			return CategoryRelVo.valueOf(info);
		}
		return null;
	}

	public CategoryRelVo save(CategoryRel info) {
		CategoryRel newInfo = categoryRelDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return CategoryRelVo.valueOf(newInfo);
		}
		return null;
	}

}
