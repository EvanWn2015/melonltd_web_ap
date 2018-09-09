package com.melonltd.naber.rdbms.model.push.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.service.MobileDeviceService;
import com.melonltd.naber.rdbms.model.vo.MobileDeviceVo;
import com.melonltd.naber.rdbms.model.vo.PushFCMVo;

@Service("pushService")
public class PushService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PushService.class);
	@Autowired
	private MobileDeviceService mobileDeviceService;

	@Autowired
	private AndroidPushService androidPushService;

//	@Autowired
//	private APNSPushServcie apnsPushServcie;

//	public enum PushType {
//		NEW_ORDER
//	}

//	public void pushOrderToUser(String accountUUID, NotificationVo notificationVo) {
//		List<MobileDeviceVo> mobiles = mobileDeviceService.findByAccountUUID(accountUUID);
//		if (CollectionUtils.isNotEmpty(mobiles)) {
//			for (MobileDeviceVo m : mobiles) {
//				List<String> tokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
//				List<NotificationVo> notifications = tokens.stream().map(t -> {
//					return NotificationVo.newInstance(t ,notificationVo);
//				}).collect(Collectors.toList());
//				androidPushService.pushs(notifications, DeviceCategory.of(m.getDevice_category()));
//			}
//		}
//	}
//	
//
//	public void pushOrderToSeller(String restaurantUUID, NotificationVo notificationVo) {
//		List<MobileDeviceVo> mobiles = mobileDeviceService.findByRestaurantUUID(restaurantUUID);
//		if (CollectionUtils.isNotEmpty(mobiles)) {
//			for (MobileDeviceVo m : mobiles) {
//				List<String> tokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
//				List<NotificationVo> notifications = tokens.stream().map(t -> {
//					return NotificationVo.newInstance(t ,notificationVo);
//				}).collect(Collectors.toList());
//				androidPushService.pushs(notifications, DeviceCategory.of(m.getDevice_category()));
//			}
//		}
//	}
	
	
	public void pushRemoteMessage(String uuid, PushFCMVo pushFCMVo, boolean isUser) {
		List<MobileDeviceVo> mobiles = mobileDeviceService.findByAccountOrRestaurantUUID(uuid, isUser);
		if (CollectionUtils.isNotEmpty(mobiles)) {
//			for (MobileDeviceVo m : mobiles) {
//				List<String> tokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
//				List<PushFCMVo> pushFCMVos = tokens.stream().map(to -> {
//					return PushFCMVo.newInstance(to ,pushFCMVo);
//				}).collect(Collectors.toList());
//				androidPushService.pushs(pushFCMVos, DeviceCategory.of(m.getDevice_category()));
//			}
			
			String[] tokens = mobiles.stream().flatMap(a -> JsonHelper.jsonArray(a.getDevice_token(), String[].class).stream()).toArray(String[] :: new);
			pushFCMVo.setRegistration_ids(tokens);
			androidPushService.push(pushFCMVo);
		}
	}
}
