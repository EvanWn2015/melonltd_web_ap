package com.melonltd.naber.rdbms.model.push.service;

import java.util.LinkedHashMap;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.stored.bean.MobileDeviceStordVo;
import com.melonltd.naber.rdbms.model.stored.service.MobileTokenStoredService;
import com.melonltd.naber.rdbms.model.type.DeviceCategory;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.vo.json.data.OredeSubimtReq;

@Service("pudhSellerService")
public class PudhSellerService {
	
	@Autowired
	private MobileTokenStoredService mobileTokenStoredService;
	
	@Autowired
	private AndroidPushService androidPushService;
	
	@Autowired
	private AnpsPushServcie anpsPushServcie;
	
	public enum PushType {
		NEW_ORDER
	}
	
	public void pushOrderToSeller(String restaurantUUID , OredeSubimtReq data, OrderStatus status ) {
		MobileDeviceStordVo mobile =  mobileTokenStoredService.findByRestaurantUUID(restaurantUUID);
		LinkedHashMap<String, Object> map = Maps.newLinkedHashMap();
		map.put("notify_type", PushType.NEW_ORDER.name());
		map.put("data",JsonHelper.toJson(data));
		
		if (!ObjectUtils.anyNotNull(mobile)) {
			switch (DeviceCategory.of(mobile.getDevice_category())) {
			case IOS:
				anpsPushServcie.push(mobile.getDevice_token(), "", JsonHelper.toJson(map));
				break;
			case ANDROID:
				androidPushService.push(mobile.getDevice_token(), "", JsonHelper.toJson(map));
				break;
			default:
				break;
			}
		}
		
	}
	

}
