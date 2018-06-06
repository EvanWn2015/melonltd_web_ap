package com.melonltd.naber.rdbms.model.service.facade;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.MobileDevice;
import com.melonltd.naber.rdbms.model.dao.stored.procedure.DeviceTokenDao;
import com.melonltd.naber.rdbms.model.vo.MobileDeviceVo;


@Service("deviceTokenService")
public class DeviceTokenService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceTokenService.class);
	
	@Autowired
	private DeviceTokenDao deviceTokenDao;
	
	
	public MobileDeviceVo findByAccountUUID (String accountUUID) {
		MobileDevice info =  deviceTokenDao.findLimtOneByAccountUUID(accountUUID);
		if (ObjectUtils.anyNotNull(info)) {
			return MobileDeviceVo.valueOf(info);
		}
		return null;
	}
}
