package com.melonltd.naber.rdbms.model.facade.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.CategoryFoodRel;
import com.melonltd.naber.rdbms.model.bean.RestaurantCategoryRel;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.CategoryFoodRelSerice;
import com.melonltd.naber.rdbms.model.service.RestaurantCategoryRelService;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.RestaurantCategoryRelVo;

@Service("deleteCategoryService")
public class DeleteCategoryService {

	@Autowired
	private RestaurantCategoryRelService restaurantCategoryRelService;

	@Autowired
	private CategoryFoodRelSerice categoryFoodRelSerice;

	public RestaurantCategoryRelVo deleteCategoryRelStatus(String restaurantUUID, ReqData req) {
		RestaurantCategoryRel info = restaurantCategoryRelService.findByRestaurantAndCategoryRelUUID(restaurantUUID, req.getUuid());
		if (!ObjectUtils.allNotNull(info)) {
			return null;
		}
		info.setStatus(SwitchStatus.CLOSE.name());
		info.setEnable(Enable.N.name());
		RestaurantCategoryRelVo vo = restaurantCategoryRelService.save(info);
		if (ObjectUtils.allNotNull(vo)) {
			List<CategoryFoodRel> foodInfos = categoryFoodRelSerice.findBycategoryUUID(vo.getCategory_uuid());
			List<CategoryFoodRel> cfoodInfos = foodInfos.stream().map(a -> {
				a.setEnable(Enable.N.name());
				a.setStatus(SwitchStatus.CLOSE.name());
				return a;
			}).collect(Collectors.toList());
			categoryFoodRelSerice.saves(cfoodInfos);
		} else {
			return null;
		}

		return  RestaurantCategoryRelVo.valueOf(info);
	}

}
