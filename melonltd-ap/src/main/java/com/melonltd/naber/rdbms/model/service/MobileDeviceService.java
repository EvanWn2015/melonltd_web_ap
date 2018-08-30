package com.melonltd.naber.rdbms.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.MobileDevice;
import com.melonltd.naber.rdbms.model.dao.MobileDeviceDao;
import com.melonltd.naber.rdbms.model.vo.MobileDeviceVo;

@Service("mobileDeviceService")
public class MobileDeviceService {

	@Autowired
	private MobileDeviceDao mobileDeviceDao;

//	public List<MobileDeviceVo> findByAccountUUID(String accountUUID) {
//		List<MobileDevice> list = mobileDeviceDao.findByAccountUUID(accountUUID);
//		if (CollectionUtils.isEmpty(list)) {
//			return Lists.newArrayList();
//		}
//		return MobileDeviceVo.valueOfArray(list);
//	}
//
//	public List<MobileDeviceVo> findByRestaurantUUID(String restaurantUUID) {
//		List<MobileDevice> list = mobileDeviceDao.findByRestaurantUUID(restaurantUUID);
//		if (CollectionUtils.isEmpty(list)) {
//			return Lists.newArrayList();
//		}
//		return MobileDeviceVo.valueOfArray(list);
//	}
	
	public List<MobileDeviceVo> findByAccountOrRestaurantUUID(String uuid, boolean isUser) {
		List<MobileDevice> list = Lists.<MobileDevice>newArrayList();
		if (isUser) {
			list = mobileDeviceDao.findByAccountUUID(uuid);
		}else {
			list = mobileDeviceDao.findByRestaurantUUID(uuid);
		}
		if (CollectionUtils.isEmpty(list)) {
			return Lists.newArrayList();
		}
		return MobileDeviceVo.valueOfArray(list);
	}

	public void save(MobileDevice info) {
		List<MobileDevice> list = mobileDeviceDao.findByAccountUUIDAndDeviceCategory(info.getAccountUUID(),info.getDeviceCategory());
		if (CollectionUtils.isEmpty(list)) {
			List<String> tokens = Lists.newArrayList();
			tokens.add(info.getDeviceToken());
			info.setDeviceToken(JsonHelper.toJson(tokens));
			list.add(info);
			mobileDeviceDao.save(info);
		}else {
			list.forEach(d -> {
				List<String> tokens = JsonHelper.jsonArray(d.getDeviceToken(), String[].class);
				tokens.add(info.getDeviceToken());
				tokens = tokens.stream().distinct().collect(Collectors.toList());
				d.setDeviceToken(JsonHelper.toJson(tokens));
				mobileDeviceDao.save(d);
			});
		}
	}

	public void remove(MobileDeviceVo vo) {
		List<MobileDevice> list = mobileDeviceDao.findByAccountUUIDAndDeviceCategory(vo.getAccount_uuid(), vo.getDevice_category());
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(d -> {
				List<String> tokens = JsonHelper.jsonArray(d.getDeviceToken(), String[].class);
				tokens = tokens.stream().filter(a -> !a.equals(vo.getDevice_token())).distinct().collect(Collectors.toList());
				d.setDeviceToken(JsonHelper.toJson(tokens));
				mobileDeviceDao.save(d);
			});
		}
	}

}
