package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.AccountInfo;
import com.melonltd.naber.rdbms.model.dao.AccountInfoDao;

@Service("accountInfoService")
public class AccountInfoService {
	
	@Autowired
	AccountInfoDao accountInfoDao;
	
	public AccountInfo checkLogin (String phone, String password) {
		List<AccountInfo> accountInfos = accountInfoDao.findByPhoneAndPassword(phone, password);
		// equalsIgnoreCase
//		StringUtils.isNotBlank("");
		if (CollectionUtils.isNotEmpty(accountInfos)) {
			return null;
		}
		return accountInfos.get(0);
	}
	
}
