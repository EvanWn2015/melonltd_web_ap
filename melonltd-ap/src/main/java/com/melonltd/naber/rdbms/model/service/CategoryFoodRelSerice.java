package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.bean.CategoryFoodRel;
import com.melonltd.naber.rdbms.model.dao.CategoryFoodRelDao;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.CategoryFoodRelVo;

@Service("categoryFoodRelSerice")
public class CategoryFoodRelSerice {

	@Autowired
	private CategoryFoodRelDao categoryFoodRelDao;

	public List<CategoryFoodRelVo> findByStatusAndCategoryUUID(String status, String categoryUUID) {
		List<CategoryFoodRel> list = categoryFoodRelDao.findByStatusAndCategoryUUID(status, categoryUUID);
		return CategoryFoodRelVo.valueOfArray(list, false);
	}

	public CategoryFoodRelVo findByFoodUUID(String foodUUID) {
		CategoryFoodRel info = categoryFoodRelDao.findByFoodUUID(foodUUID);
		return CategoryFoodRelVo.valueOf(info, true);
	}

	public int getFoodStatusOpenByUUIDs(List<String> foodUUIDs) {
		List<CategoryFoodRel> list = categoryFoodRelDao.findStatusByFoodUUIDs(foodUUIDs);
		return list.size();
	}

	public List<CategoryFoodRel> findBycategoryUUID(String categoryUUID) {
		List<CategoryFoodRel> list = categoryFoodRelDao.findBycategoryUUID(categoryUUID);
		return list;
	}
	
	public void saves(List<CategoryFoodRel> infos) {
		categoryFoodRelDao.save(infos);
	}
	
	public CategoryFoodRelVo save(CategoryFoodRelVo vo) {
		CategoryFoodRel info = new CategoryFoodRel();
		info.setCategoryUUID(vo.getCategory_uuid());
		info.setFoodUUID(Tools.buildUUID(UUIDType.FOOD));
		info.setFoodName(vo.getFood_name());
		info.setDefaultPrice(vo.getDefault_price());
		info.setFoodData(JsonHelper.toJson(vo.getFood_data()));
		info.setStatus(SwitchStatus.OPEN.name());
		info.setEnable(Enable.Y.name());
		info.setCreateDate(Tools.getNowGMT());
		CategoryFoodRel newInfo = categoryFoodRelDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return CategoryFoodRelVo.valueOf(newInfo);	
		}
		return null;
	}
	
	public CategoryFoodRelVo delete(CategoryFoodRel info) {
		info.setEnable(Enable.N.name());
		info.setStatus(SwitchStatus.CLOSE.name());
		CategoryFoodRel newInfo = categoryFoodRelDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return CategoryFoodRelVo.valueOf(newInfo);	
		}
		return null;
	}
	
	public CategoryFoodRelVo update(CategoryFoodRelVo vo ,CategoryFoodRel info) {
//		info.setCategoryUUID(vo.getCategory_uuid());
		info.setFoodUUID(vo.getFood_uuid());
		info.setFoodName(vo.getFood_name());
		info.setDefaultPrice(vo.getDefault_price());
		info.setFoodData(JsonHelper.toJson(vo.getFood_data()));
		
		CategoryFoodRel newInfo = categoryFoodRelDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return CategoryFoodRelVo.valueOf(newInfo);	
		}
		return null;
	}
	
	public CategoryFoodRelVo changeStatus(CategoryFoodRel info, SwitchStatus status) {
		info.setStatus(status.name());
		CategoryFoodRel newInfo = categoryFoodRelDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return CategoryFoodRelVo.valueOf(newInfo);	
		}
		return null;
	}
	
	public List<CategoryFoodRelVo> findBycategoryUUIDAndRestaurantUUID (String categoryUUID, String restaurantUUID){
		List<CategoryFoodRel> list = categoryFoodRelDao.findBycategoryUUIDAndRestaurantUUID(categoryUUID, restaurantUUID);
		return CategoryFoodRelVo.valueOfArray(list, true);
	}
	
	public CategoryFoodRel findByFoodUUIDAndRestaurantUUID (String foodUUID, String restaurantUUID) {
		CategoryFoodRel info = categoryFoodRelDao.findByFoodUUIDAndRestaurantUUID(foodUUID, restaurantUUID);
		if (ObjectUtils.allNotNull(info)) {
			return info;
		}
		return null;
	}
}
