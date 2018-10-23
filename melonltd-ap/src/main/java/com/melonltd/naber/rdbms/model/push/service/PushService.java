package com.melonltd.naber.rdbms.model.push.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.service.MobileDeviceService;
import com.melonltd.naber.rdbms.model.vo.MobileDeviceVo;
import com.melonltd.naber.rdbms.model.vo.PushFCMVo;

@Service("pushService")
public class PushService {
//	private static final Logger LOGGER = LoggerFactory.getLogger(PushService.class);
	@Autowired
	private MobileDeviceService mobileDeviceService;

	@Autowired
	private AndroidPushService androidPushService;
	
	public void pushRemoteMessage(String uuid, PushFCMVo pushFCMVo, boolean isUser) {
		List<MobileDeviceVo> mobiles = mobileDeviceService.findByAccountOrRestaurantUUID(uuid, isUser);
		if (CollectionUtils.isNotEmpty(mobiles)) {
			String[] tokens = mobiles.stream().flatMap(a -> JsonHelper.jsonArray(a.getDevice_token(), String[].class).stream()).toArray(String[] :: new);
			pushFCMVo.setRegistration_ids(tokens);
			androidPushService.push(pushFCMVo);
		}
	}
}
