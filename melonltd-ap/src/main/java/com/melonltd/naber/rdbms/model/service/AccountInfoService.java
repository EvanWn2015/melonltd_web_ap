package com.melonltd.naber.rdbms.model.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.bean.AccountInfo;
import com.melonltd.naber.rdbms.model.dao.AccountInfoDao;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;

@Service("accountInfoService")
public class AccountInfoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountInfoService.class);

	@Autowired
	AccountInfoDao accountInfoDao;

	LoadingCache<String, AccountInfo> cacheBuilder = CacheBuilder.newBuilder()
			.expireAfterAccess(7, TimeUnit.DAYS)
			.maximumSize(1000)
			.build(new CacheLoader<String, AccountInfo>() {
				@Override
				public AccountInfo load(String uuid) throws Exception {
					AccountInfo info = accountInfoDao.findByAccountUUID(uuid);
					return info;
				}
			});

	

	public AccountInfoVo findByPhone(String phone) {
		AccountInfo info = accountInfoDao.findByPhone(phone);
		if (!ObjectUtils.anyNotNull(info)) {
			return null;
		}
		return AccountInfoVo.of(info, true);
	}
	
	
	public AccountInfoVo findByPhoneAndPassword (String phone, String password) {
		AccountInfo info = accountInfoDao.findByPhoneAndPassword(phone, password);
		if (ObjectUtils.allNotNull(info)) {
			putCache(info);
			AccountInfoVo.of(info, false);
		}
		return null;
	}

	public AccountInfoVo findByAccountUUID(String uuid) {
		AccountInfo info = accountInfoDao.findByAccountUUID(uuid);
		if (ObjectUtils.anyNotNull(info)) {
			info.getIsLogin();
			return AccountInfoVo.of(info,true);
		}
		return null;
	}

	public AccountInfo save(AccountInfoVo vo, UUIDType type) {
		AccountInfo oldInfo = accountInfoDao.findByPhone(vo.getPhone());
		if (ObjectUtils.allNotNull(oldInfo)) {
			return null;
		}

		AccountInfo info = new AccountInfo();
		info.setAccountUUID(Tools.buildAccountUUID(type));
		info.setName(vo.getName());
		info.setPassword(vo.getPassword());
		info.setPhone(vo.getPhone());
		info.setEmail(vo.getEmail());
		info.setBirthDay(vo.getBirth_day());
		info.setAddress(vo.getAddress());
		info.setIdentity(vo.getIdentity());
		info.setSchoolName(vo.getSchool_name());
		info.setLevel(vo.getLevel());
		info.setBonus("0");
		info.setEnable("Y");
		info.setIsLogin("0");
		return accountInfoDao.save(info);
	}
	
	
	public boolean update(AccountInfoVo vo) {
		try {
			accountInfoDao.updatePasswordByPhoneAndUUID(vo.getPassword(), vo.getPhone(),vo.getAccount_uuid());
			clearCacheBuilderByKey(vo.getAccount_uuid());
			return true;
		}catch (Exception e) {
			LOGGER.error("update password fail account: {}, msg:{}",vo.getAccount_uuid(),e.getMessage());
			return false;
		}
	}

	public void refreshLoginStatus(String uuid) {
		AccountInfo info = accountInfoDao.findByAccountUUID(uuid);
		if (ObjectUtils.anyNotNull(info)) {
			info.setLoginDate(Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));
			info.setIsLogin("N");
			accountInfoDao.save(info);
		}
	}
	
	public void changeLoginStatus(String uuid) {
		try {
			AccountInfo info = cacheBuilder.get(uuid);
			if (ObjectUtils.anyNotNull(info)) {
				info.setLoginDate(Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));
				info.setIsLogin("Y");
				accountInfoDao.save(info);
				clearCacheBuilderByKey(info.getAccountUUID());
			}
		} catch (ExecutionException e) {
			LOGGER.error("The {} does not exist in the cache", uuid);
		}
	}

	
	public AccountInfoVo getCacheBuilderByKey(String uuid, boolean hasPassword) {
		AccountInfo info = null;
		try {
			info = cacheBuilder.get(uuid);
			return AccountInfoVo.of(info,hasPassword);
		} catch (Exception e) {
			LOGGER.error("The {} does not exist in the cache", uuid);
			return null;
		}
	}
	public void putCache(AccountInfo info) {
		cacheBuilder.put(info.getAccountUUID(), info);
	}

	public void clearCacheBuilderByKey(String uuid) {
		cacheBuilder.invalidate(uuid);
	}
}
