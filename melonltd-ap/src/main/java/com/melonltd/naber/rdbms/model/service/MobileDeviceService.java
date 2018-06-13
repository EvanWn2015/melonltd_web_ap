package com.melonltd.naber.rdbms.model.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.MobileDevice;
import com.melonltd.naber.rdbms.model.dao.MobileDeviceDao;
import com.melonltd.naber.rdbms.model.vo.MobileDeviceVo;

@Service("mobileDeviceService")
public class MobileDeviceService {
	
	@Autowired
	private MobileDeviceDao mobileDeviceDao;
	
	public MobileDeviceVo findByRestaurantUUID(String restaurantUUID) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(0, 1, sort);
		Page<MobileDevice> page = mobileDeviceDao.findByRestaurantUUID(restaurantUUID, pageable);
		if (page.hasContent()) {
			return MobileDeviceVo.valueOf(page.getContent().get(0));
		}
		return null;
	}
	
	
	public MobileDeviceVo save(MobileDevice info) {
		MobileDevice m = mobileDeviceDao.save(info);
		if (ObjectUtils.anyNotNull(m)) {
			return MobileDeviceVo.valueOf(m);
		}
		return null;
	}

}
