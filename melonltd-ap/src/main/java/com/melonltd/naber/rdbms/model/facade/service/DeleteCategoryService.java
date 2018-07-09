package com.melonltd.naber.rdbms.model.facade.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.CategoryRel;
import com.melonltd.naber.rdbms.model.bean.FoodInfo;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.CategoryRelService;
import com.melonltd.naber.rdbms.model.service.FoodInfoSerice;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.CategoryRelVo;

@Service("deleteCategoryService")
public class DeleteCategoryService {

	@Autowired
	private CategoryRelService categoryRelService;

	@Autowired
	private FoodInfoSerice foodInfoSerice;

	public CategoryRelVo deleteCategoryRelStatus(String restaurantUUID, ReqData req) {
		CategoryRel info = categoryRelService.findByRestaurantAndCategoryRelUUID(restaurantUUID, req.getUuid());
		if (!ObjectUtils.allNotNull(info)) {
			return null;
		}
		info.setStatus(SwitchStatus.CLOSE.name());
		info.setEnable(Enable.N.name());
		CategoryRelVo vo = categoryRelService.save(info);
		if (ObjectUtils.allNotNull(vo)) {
			List<FoodInfo> foodInfos = foodInfoSerice.findBycategoryUUID(vo.getCategory_uuid());
			List<FoodInfo> cfoodInfos = foodInfos.stream().map(a -> {
				a.setEnable(Enable.N.name());
				a.setStatus(SwitchStatus.CLOSE.name());
				return a;
			}).collect(Collectors.toList());
			foodInfoSerice.saves(cfoodInfos);
		} else {
			return null;
		}

		return  CategoryRelVo.valueOf(info);
	}

}
