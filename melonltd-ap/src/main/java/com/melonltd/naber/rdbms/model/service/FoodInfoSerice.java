package com.melonltd.naber.rdbms.model.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.CategoryRel;
import com.melonltd.naber.rdbms.model.bean.FoodInfo;
import com.melonltd.naber.rdbms.model.dao.FoodInfoDao;
import com.melonltd.naber.rdbms.model.req.vo.ItemVo;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.type.UUIDType;
import com.melonltd.naber.rdbms.model.vo.CategoryRelVo;
import com.melonltd.naber.rdbms.model.vo.FoodInfoVo;

@Service("foodInfoSerice")
public class FoodInfoSerice {
	private static final Logger LOGGER = LoggerFactory.getLogger(FoodInfoSerice.class);

	@Autowired
	private FoodInfoDao foodInfoDao;

	public List<FoodInfoVo> findByStatusAndCategoryUUID(String status, String categoryUUID) {
		List<FoodInfo> list = foodInfoDao.findByStatusAndCategoryUUID(status, categoryUUID);
		return FoodInfoVo.valueOfArray(list, false);
	}

	public FoodInfoVo findByFoodUUID(String foodUUID) {
		FoodInfo info = foodInfoDao.findByFoodUUID(foodUUID);
		return FoodInfoVo.valueOf(info, true);
	}
	
	public List<FoodInfoVo> findByFoodUUIDs(List<String> foodUUIDs) {
		List<FoodInfo> infos = foodInfoDao.findByFoodUUIDs(foodUUIDs);
		return FoodInfoVo.valueOfArray(infos, true);
	}

	public boolean updatePhoto(FoodInfoVo vo) {
		try {
			foodInfoDao.updatePhoto(vo.getPhoto(), vo.getFood_uuid());
			return true;
		} catch (Exception e) {
			LOGGER.error("update photo fail food: {}, msg:{}", vo.getFood_uuid(), e.getMessage());
			return false;
		}
	}

	public List<FoodInfoVo> getFoodStatusOpenByUUIDs(List<String> foodUUIDs) {
		List<FoodInfo> list = foodInfoDao.findStatusByFoodUUIDs(foodUUIDs);
		if (CollectionUtils.isNotEmpty(list)) {
			return FoodInfoVo.valueOfArray(list, true);
		}
		return Lists.<FoodInfoVo>newArrayList();
	}

	public List<FoodInfo> findBycategoryUUID(String categoryUUID) {
		List<FoodInfo> list = foodInfoDao.findBycategoryUUID(categoryUUID);
		return list;
	}

	
	public void save(FoodInfo info) {
		foodInfoDao.save(info);
	}
	
	public void save(List<FoodInfo> infos) {
		foodInfoDao.save(infos);
	}
	
	public List<FoodInfoVo> saves(List<FoodInfoVo> infoVos) {
		List<FoodInfo> entities = FoodInfo.valueOfArray(infoVos);
		List<FoodInfo> infos = foodInfoDao.save(entities);
		return FoodInfoVo.valueOfArray(infos,true);
	}

	public FoodInfoVo save(FoodInfoVo vo) {
		FoodInfo info = new FoodInfo();
		info.setCategoryUUID(vo.getCategory_uuid());
		info.setFoodUUID(Tools.buildUUID(UUIDType.FOOD));
		info.setFoodName(vo.getFood_name());
		info.setDefaultPrice(vo.getDefault_price());
		info.setFoodData(JsonHelper.toJson(vo.getFood_data()));
		info.setTop("0");
		info.setStatus(SwitchStatus.OPEN.name());
		info.setEnable(Enable.Y.name());
		info.setCreateDate(Tools.getNowGMT());
		FoodInfo newInfo = foodInfoDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return FoodInfoVo.valueOf(newInfo);
		}
		return null;
	}

	public FoodInfoVo delete(FoodInfo info) {
		info.setEnable(Enable.N.name());
		info.setStatus(SwitchStatus.CLOSE.name());
		FoodInfo newInfo = foodInfoDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return FoodInfoVo.valueOf(newInfo);
		}
		return null;
	}

	public FoodInfoVo update(FoodInfoVo vo, FoodInfo info) {
		// info.setCategoryUUID(vo.getCategory_uuid());
		Optional<ItemVo> minPriceItem = vo.getFood_data().getScopes().stream()
				.collect(Collectors.minBy(Comparator.comparingInt(a -> Integer.parseInt(a.getPrice()))));

		info.setDefaultPrice(minPriceItem.isPresent() ? minPriceItem.get().getPrice() : info.getDefaultPrice());
		info.setFoodUUID(vo.getFood_uuid());
		info.setFoodName(vo.getFood_name());
		info.setFoodData(JsonHelper.toJson(vo.getFood_data()));
		info.setStatus(vo.getStatus());
		info.setTop(StringUtils.isNotBlank(vo.getTop()) ? vo.getTop() : "0");
		
		FoodInfo newInfo = foodInfoDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return FoodInfoVo.valueOf(newInfo);
		}
		return null;
	}

	public FoodInfoVo changeStatus(FoodInfo info, SwitchStatus status) {
		info.setStatus(status.name());
		FoodInfo newInfo = foodInfoDao.save(info);
		if (ObjectUtils.allNotNull(newInfo)) {
			return FoodInfoVo.valueOf(newInfo);
		}
		return null;
	}

	public List<FoodInfoVo> findBycategoryUUIDAndRestaurantUUID(String categoryUUID, String restaurantUUID) {
		List<FoodInfo> list = foodInfoDao.findBycategoryUUIDAndRestaurantUUID(categoryUUID, restaurantUUID);
		return FoodInfoVo.valueOfArray(list, true);
	}

	public FoodInfo findByFoodUUIDAndRestaurantUUID(String foodUUID, String restaurantUUID) {
		FoodInfo info = foodInfoDao.findByFoodUUIDAndRestaurantUUID(foodUUID, restaurantUUID);
		if (ObjectUtils.allNotNull(info)) {
			return info;
		}
		return null;
	}
}
