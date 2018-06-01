package com.melonltd.naber.rdbms.model.service;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melonltd.naber.rdbms.model.bean.AccountInfo;
import com.melonltd.naber.rdbms.model.dao.AccountInfoDao;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;

@Service("accountInfoService")
@Transactional(readOnly = true)
public class AccountInfoService {

	@Autowired
	AccountInfoDao accountInfoDao;

	public AccountInfoVo findByPhoneAndPassword(String phone, String password) {
		AccountInfo info = accountInfoDao.findByPhoneAndPassword(phone, password);
		if (ObjectUtils.anyNotNull(info)) {
			return AccountInfoVo.of(info);
		}
		return null;
	}

	public AccountInfoVo findByAccountUUID(String uuid) {
		AccountInfo info = accountInfoDao.findByAccountUUID(uuid);
		if (ObjectUtils.anyNotNull(info)) {

			info.getIsLogin();
			return AccountInfoVo.of(info);
		}
		return null;
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public void refreshLoginStatus(String uuid) {
		AccountInfo info = accountInfoDao.findByAccountUUID(uuid);
		if (ObjectUtils.anyNotNull(info)) {
			info.setLoginDate("");
			info.setIsLogin("0");
			accountInfoDao.save(info);
		}
	}

}
