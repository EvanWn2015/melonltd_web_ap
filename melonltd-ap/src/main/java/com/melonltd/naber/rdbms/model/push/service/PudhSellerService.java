package com.melonltd.naber.rdbms.model.push.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.service.MobileDeviceService;
import com.melonltd.naber.rdbms.model.type.DeviceCategory;
import com.melonltd.naber.rdbms.model.vo.MobileDeviceVo;
import com.melonltd.naber.rdbms.model.vo.NotificationVo;

@Service("pudhSellerService")
public class PudhSellerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PudhSellerService.class);
	@Autowired
	private MobileDeviceService mobileDeviceService;

	@Autowired
	private AndroidPushService androidPushService;

	@Autowired
	private APNSPushServcie apnsPushServcie;

	public enum PushType {
		NEW_ORDER
	}

	public void pushOrderToUser(String accountUUID, NotificationVo notificationVo) {
		List<MobileDeviceVo> mobiles = mobileDeviceService.findByAccountUUID(accountUUID);
		if (CollectionUtils.isNotEmpty(mobiles)) {
			for (MobileDeviceVo m : mobiles) {
				List<String> tokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
				List<NotificationVo> notifications = tokens.stream().map(t -> {
					return NotificationVo.newInstance(t ,notificationVo);
				}).collect(Collectors.toList());
				androidPushService.pushs(notifications, DeviceCategory.of(m.getDevice_category()));
				
//				switch (DeviceCategory.of(m.getDevice_category())) {
//				case IOS:
//					List<String> iosTokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
//					List<NotificationVo> notifications = iosTokens.stream().map(t -> {
//						return NotificationVo.newInstance(t ,notificationVo);
//					}).collect(Collectors.toList());
//					androidPushService.pushForIOSAPNs(notifications);
//					break;
//				case ANDROID:
//					List<String> tokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
//					List<NotificationVo> notificationVos = tokens.stream().map(t -> {
//						return NotificationVo.newInstance(t ,notificationVo);
//					}).collect(Collectors.toList());
//					androidPushService.pushForAndroids(notificationVos);
//					break;
//				default:
//					break;
//				}
			}
		}
	}
	

	public void pushOrderToSeller(String restaurantUUID, NotificationVo notificationVo) {
		List<MobileDeviceVo> mobiles = mobileDeviceService.findByRestaurantUUID(restaurantUUID);
		if (CollectionUtils.isNotEmpty(mobiles)) {
			
			for (MobileDeviceVo m : mobiles) {
				List<String> tokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
				List<NotificationVo> notifications = tokens.stream().map(t -> {
					return NotificationVo.newInstance(t ,notificationVo);
				}).collect(Collectors.toList());
				androidPushService.pushs(notifications, DeviceCategory.of(m.getDevice_category()));
				
//				
//				switch (DeviceCategory.of(m.getDevice_category())) {
//				case IOS:
//					List<String> iosTokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
//					List<NotificationVo> notifications = iosTokens.stream().map(t -> {
//						return NotificationVo.newInstance(t ,notificationVo);
//					}).collect(Collectors.toList());
//					androidPushService.pushForIOSAPNs(notifications);
//					break;
//				case ANDROID:
//					List<String> tokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
//					List<NotificationVo> notificationVos = tokens.stream().map(t -> {
//						return NotificationVo.newInstance(t ,notificationVo);
//					}).collect(Collectors.toList());
//					androidPushService.pushForAndroids(notificationVos);
//					break;
//				default:
//					break;
//				}
			}
		}
	}
}
