package com.melonltd.naber.rdbms.model.stored.service;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.MobileDevice;
import com.melonltd.naber.rdbms.model.stored.dao.MobileTokenStoredDao;
import com.melonltd.naber.rdbms.model.vo.MobileDeviceVo;


@Service("mobileTokenStoredService")
public class MobileTokenStoredService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MobileTokenStoredService.class);
	
	@Autowired
	private MobileTokenStoredDao mobileTokenStoredDao;
	
	public MobileDeviceVo findByAccountUUID (String accountUUID) {
		MobileDevice info =  mobileTokenStoredDao.findLimtOneByAccountUUID(accountUUID);
		if (ObjectUtils.anyNotNull(info)) {
			return MobileDeviceVo.valueOf(info);
		}
		return null;
	}
}
