package com.melonltd.naber.rdbms.model.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.AccountInfo;
import com.melonltd.naber.rdbms.model.dao.AccountInfoDao;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.UUIDType;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;

@Service("accountInfoService")
public class AccountInfoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountInfoService.class);

	@Autowired
	private AccountInfoDao accountInfoDao;

	LoadingCache<String, AccountInfo> cacheBuilder = CacheBuilder.newBuilder().expireAfterAccess(7, TimeUnit.DAYS)
			.maximumSize(1000).build(new CacheLoader<String, AccountInfo>() {
				@Override
				public AccountInfo load(String uuid) throws Exception {
					AccountInfo info = accountInfoDao.findByAccountUUID(uuid);
					return info;
				}
			});

	public AccountInfoVo findByAccount(String account) {
		AccountInfo info = accountInfoDao.findByAccount(account);
		if (ObjectUtils.allNotNull(info)) {
			cacheBuilder.invalidate(info.getAccountUUID());
			return AccountInfoVo.of(info, true);
		}
		return null;
	}

	public AccountInfoVo findByPhoneAndMail(String phone, String email) {
		AccountInfo info = accountInfoDao.findByPhoneAndMail(phone, email);
		if (ObjectUtils.allNotNull(info)) {
			cacheBuilder.invalidate(info.getAccountUUID());
			return AccountInfoVo.of(info, true);
		}
		return null;
	}
	
	public AccountInfoVo findByPhone(String phone) {
		AccountInfo info = accountInfoDao.findByPhone(phone);
		if (ObjectUtils.allNotNull(info)) {
			cacheBuilder.invalidate(info.getAccountUUID());
			return AccountInfoVo.of(info, true);
		}
		return null;
	}

	public AccountInfoVo findByPhoneAndAccount(String account, String password) {
		AccountInfo info = accountInfoDao.findByPhoneAndAccount(account, password);
		if (ObjectUtils.allNotNull(info)) {
			putCache(info);
			return AccountInfoVo.of(info, false);
		}
		return null;
	}

	public AccountInfoVo findByAccountUUID(String uuid, boolean hasPassword) {
		AccountInfo info = accountInfoDao.findByAccountUUID(uuid);
		if (ObjectUtils.allNotNull(info)) {
			cacheBuilder.invalidate(info.getAccountUUID());
			return AccountInfoVo.of(info, hasPassword);
		}
		return null;
	}
	
	public AccountInfo save(AccountInfo entity) {
		return accountInfoDao.save(entity);
	}

	public AccountInfo save(AccountInfoVo vo, UUIDType type, Enable enable) {
		AccountInfo oldInfo = accountInfoDao.findByAccount(vo.getAccount());
		if (ObjectUtils.allNotNull(oldInfo)) {
			return null;
		}

		AccountInfo info = new AccountInfo();
		info.setAccount(vo.getPhone());
		info.setAccountUUID(Tools.buildAccountUUID(type));
		info.setName(vo.getName());
		info.setPassword(vo.getPassword());
		info.setPhone(vo.getPhone());
		info.setEmail(StringUtils.isBlank(vo.getEmail()) ? "" : vo.getEmail());
		info.setBirthDay(StringUtils.isBlank(vo.getBirth_day()) ? "": vo.getBirth_day());
		info.setAddress(StringUtils.isBlank(vo.getAddress()) ? "":vo.getAddress());
		info.setIdentity(vo.getIdentity());
		info.setSchoolName(vo.getSchool_name());
		// 2018/09/27 新增性別需求
		info.setGender(StringUtils.isBlank(vo.getGender()) ? "" : vo.getGender());
		info.setLevel(vo.getLevel());
		info.setCreateDate(Tools.getNowGMT());
		info.setUseBonus("0");
		info.setBonus("0");
		info.setEnable(enable.name());
		info.setIsLogin("N");
		return accountInfoDao.save(info);
	}

	public boolean update(AccountInfoVo vo) {
		try {
			accountInfoDao.updatePasswordByPhoneAndUUID(vo.getPassword(), vo.getPhone(), vo.getAccount_uuid());
			clearCacheBuilderByKey(vo.getAccount_uuid());
			return true;
		} catch (Exception e) {
			LOGGER.error("update password fail account: {}, msg:{}", vo.getAccount_uuid(), e.getMessage());
			return false;
		}
	}

	public boolean updatePhoto(AccountInfoVo vo) {
		try {
			accountInfoDao.updatePhoto(vo.getPhoto(), vo.getPhone(), vo.getAccount_uuid());
			clearCacheBuilderByKey(vo.getAccount_uuid());
			return true;
		} catch (Exception e) {
			LOGGER.error("update password fail account: {}, msg:{}", vo.getAccount_uuid(), e.getMessage());
			return false;
		}
	}
	
	public boolean updateBonus(String bonus, String accountUUID) {
		try {
			accountInfoDao.updateBonus(bonus, accountUUID);
			clearCacheBuilderByKey(accountUUID);
			return true;
		} catch (Exception e) {
			LOGGER.error("update password fail account: {}, msg:{}",accountUUID, e.getMessage());
			return false;
		}
	}
	
	public boolean updateUseBonus(String bonus, String accountUUID) {
		try {
			accountInfoDao.updateUseBonus(bonus, accountUUID);
			clearCacheBuilderByKey(accountUUID);
			return true;
		} catch (Exception e) {
			LOGGER.error("update password fail account: {}, msg:{}",accountUUID, e.getMessage());
			return false;
		}
	}

	public void refreshLoginStatus(String uuid) {
		AccountInfo info = accountInfoDao.findByAccountUUID(uuid);
		if (ObjectUtils.anyNotNull(info)) {
			info.setLoginDate("");
			info.setIsLogin("N");
			accountInfoDao.save(info);
			clearCacheBuilderByKey(uuid);
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
		if (StringUtils.isBlank(uuid)) {
			return null;
		}
		try {
			info = cacheBuilder.get(uuid);
			return AccountInfoVo.of(info, hasPassword);
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
