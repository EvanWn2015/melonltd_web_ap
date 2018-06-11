package com.melonltd.naber.rdbms.model.stored.service;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.stored.bean.MobileDeviceStordVo;
import com.melonltd.naber.rdbms.model.stored.dao.MobileDeviceStord;
import com.melonltd.naber.rdbms.model.stored.dao.MobileTokenStoredDao;


@Service("mobileTokenStoredService")
public class MobileTokenStoredService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MobileTokenStoredService.class);
	
	@Autowired
	private MobileTokenStoredDao mobileTokenStoredDao;
	
	public MobileDeviceStordVo findByAccountUUID (String accountUUID) {
		MobileDeviceStord info =  mobileTokenStoredDao.findByAccountUUID(accountUUID);
		if (ObjectUtils.anyNotNull(info)) {
			return MobileDeviceStordVo.valueOf(info);
		}
		return null;
	}
	
	
	public MobileDeviceStordVo findByRestaurantUUID (String restaurantUUID) {
		MobileDeviceStord info =  mobileTokenStoredDao.findByRestaurantUUID(restaurantUUID);
		if (ObjectUtils.anyNotNull(info)) {
			return MobileDeviceStordVo.valueOf(info);
		}
		return null;
	}
}
