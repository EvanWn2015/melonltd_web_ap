package com.melonltd.naber.rdbms.model.facade.service;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.bean.MobileDevice;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.MobileDeviceService;
import com.melonltd.naber.rdbms.model.type.DeviceCategory;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;

@Service("loginService")
public class LoginService {

	@Autowired
	private AccountInfoService accountInfoService;

	@Autowired
	private MobileDeviceService mobileDeviceService;

	public AccountInfoVo checkLoginAndChangeStatusAndIntoDeviceToken(String account, String password, String deviceToken,DeviceCategory category) {
		AccountInfoVo vo = accountInfoService.findByPhoneAndAccount(account, password);
		if (ObjectUtils.allNotNull(vo)) {
			if (!StringUtils.isBlank(deviceToken)) {
				MobileDevice m = new MobileDevice();
				m.setAccountUUID(vo.getAccount_uuid());
				m.setCreateDate(Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));
				m.setDeviceCategory(category.name());
				m.setDeviceToken(deviceToken);
				m.setDeviceUUID(Tools.buildUUID(UUIDType.DEVICE));
				mobileDeviceService.save(m);
			}
			accountInfoService.changeLoginStatus(vo.getAccount_uuid());
			return vo;
		}
		return null;
	}

}
