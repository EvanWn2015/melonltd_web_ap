package com.melonltd.naber.rdbms.model.facade.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.RestaurantCategoryRel;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.RestaurantCategoryRelService;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.vo.RestaurantCategoryRelVo;

@Service("deleteCategoryService")
public class DeleteCategoryService {
	
	@Autowired
	private RestaurantCategoryRelService restaurantCategoryRelService;
	
	
	public RestaurantCategoryRelVo deleteCategoryRelStatus(String restaurantUUID , ReqData req) {
		RestaurantCategoryRel info = restaurantCategoryRelService.findByRestaurantAndCategoryRelUUID(restaurantUUID, req.getUuid());
		if (!ObjectUtils.allNotNull(info)) {
			return null;
		}
		info.setEnable(Enable.N.name());
		RestaurantCategoryRel newInfo = restaurantCategoryRelDao.save(info);
		
		
		if (ObjectUtils.allNotNull(newInfo)) {
			return RestaurantCategoryRelVo.valueOf(newInfo);
		}
		return null;
	}
	

}
