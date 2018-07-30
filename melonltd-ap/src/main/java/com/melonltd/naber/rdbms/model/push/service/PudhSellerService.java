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
	private AnpsPushServcie anpsPushServcie;

	public enum PushType {
		NEW_ORDER
	}

	public void pushOrderToUser(String accountUUID, NotificationVo notificationVo) {
		List<MobileDeviceVo> mobiles = mobileDeviceService.findByAccountUUID(accountUUID);
		if (CollectionUtils.isNotEmpty(mobiles)) {
			for (MobileDeviceVo m : mobiles) {
				switch (DeviceCategory.of(m.getDevice_category())) {
				case IOS:
					// anpsPushServcie.push(m.getDevice_token(), "", "");
					break;
				case ANDROID:
					// List<NotificationVo> notificationVos = Lists.newArrayList();
					List<String> tokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
					List<NotificationVo> notificationVos = tokens.stream().map(t -> {
						return NotificationVo.newInstance(t ,notificationVo);
					}).collect(Collectors.toList());
					androidPushService.pushs(notificationVos);
					break;
				default:
					break;
				}
			}
//			
//			mobiles.forEach(m -> {
//				switch (DeviceCategory.of(m.getDevice_category())) {
//				case IOS:
//					// anpsPushServcie.push(m.getDevice_token(), "", "");
//					break;
//				case ANDROID:
//					// List<NotificationVo> notificationVos = Lists.newArrayList();
//					List<String> tokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
//					List<NotificationVo> notificationVos = tokens.stream().map(t -> {
//						return NotificationVo.newInstance(t ,notificationVo);
//					}).collect(Collectors.toList());
//					androidPushService.pushs(notificationVos);
//					break;
//				default:
//					break;
//				}
//			});
		}
	}
	

	public void pushOrderToSeller(String restaurantUUID, NotificationVo notificationVo) {
		List<MobileDeviceVo> mobiles = mobileDeviceService.findByRestaurantUUID(restaurantUUID);
		if (CollectionUtils.isNotEmpty(mobiles)) {
			
			for (MobileDeviceVo m : mobiles) {
				switch (DeviceCategory.of(m.getDevice_category())) {
				case IOS:
//					anpsPushServcie.push(m.getDevice_token(), "", "");
					break;
				case ANDROID:
					List<String> tokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
					List<NotificationVo> notificationVos = tokens.stream().map(t -> {
						return NotificationVo.newInstance(t ,notificationVo);
					}).collect(Collectors.toList());
					androidPushService.pushs(notificationVos);
					break;
				default:
					break;
				}
			}
			
//			mobiles.forEach(m -> {
//				switch (DeviceCategory.of(m.getDevice_category())) {
//				case IOS:
////					anpsPushServcie.push(m.getDevice_token(), "", "");
//					break;
//				case ANDROID:
//					List<String> tokens = JsonHelper.jsonArray(m.getDevice_token(), String[].class);
//					List<NotificationVo> notificationVos = tokens.stream().map(t -> {
//						return NotificationVo.newInstance(t ,notificationVo);
//					}).collect(Collectors.toList());
//					androidPushService.pushs(notificationVos);
//					break;
//				default:
//					break;
//				}
//			});
		}
	}

	// public void pushOrderToUser(String accountUUID, String mssage) {
	// MobileDeviceVo mobile = mobileDeviceService.findByAccountUUID(accountUUID);
	// if (ObjectUtils.anyNotNull(mobile)) {
	// switch (DeviceCategory.of(mobile.getDevice_category())) {
	// case IOS:
	//// anpsPushServcie.push(mobile.getDevice_token(), "", JsonHelper.toJson(map));
	// NotificationVo notificationVo = new NotificationVo ();
	// notificationVo.setTo(mobile.getDevice_token());
	// Map<String, String> map = Maps.newHashMap();
	// map.put("identity", Identity.SELLERS.name());
	// map.put("title", "訂單信息");
	// map.put("message", "你的訂單");
	//// map.put("picture",
	// "https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Fbackground%2FRESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267.jpg?alt=media&token=25502bb8-e397-4541-9d86-7cd304b53e58");
	//// map.put("icon",
	// "https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Flogo%2FRESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267.jpg?alt=media&token=a443d757-f8a9-400e-9012-171e669d981c");
	// notificationVo.setData(map);
	// anpsPushServcie.push(mobile.getDevice_token(), "", mssage);
	// break;
	// case ANDROID:
	//// androidPushService.push(mobile.getDevice_token(), "",
	// JsonHelper.toJson(map));
	// androidPushService.push(mobile.getDevice_token(), "", mssage);
	// break;
	// default:
	// break;
	// }
	// }
	// }

	// public void pushOrderToSeller(String restaurantUUID , OredeSubimtReq data,
	// OrderStatus status ) {
	// MobileDeviceVo mobile =
	// mobileDeviceService.findByRestaurantUUID(restaurantUUID);
	// LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
	// map.put("notify_type", PushType.NEW_ORDER.name());
	// map.put("data",JsonHelper.toJson(data));
	//
	// if (ObjectUtils.anyNotNull(mobile)) {
	// switch (DeviceCategory.of(mobile.getDevice_category())) {
	// case IOS:
	// anpsPushServcie.push(mobile.getDevice_token(), "", JsonHelper.toJson(map));
	// break;
	// case ANDROID:
	// androidPushService.push(mobile.getDevice_token(), "",
	// JsonHelper.toJson(map));
	// break;
	// default:
	// break;
	// }
	// }
	// }


}
