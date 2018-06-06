package com.melonltd.naber.rdbms.model.service.facade;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.bean.AccountInfo;
import com.melonltd.naber.rdbms.model.dao.stored.procedure.LoginDao;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.type.DeviceCategory;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;

@Service("loginService")
public class LoginService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private LoginDao loginDao;

	@Autowired
	AccountInfoService accountInfoService;

	public AccountInfoVo checkLoginAndChangeStatusAndIntoDeviceToken(String phone, String password, String deviceToken,
			DeviceCategory category) {
		String deviceUUID = Tools.buildUUID(UUIDType.DEVICE);
		AccountInfo info = loginDao.checkLogin(phone, password, Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"),
				deviceUUID, deviceToken, category.name());
		if (ObjectUtils.anyNotNull(info)) {
			info.setIsLogin("1");
			info.setLoginDate(Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));
			accountInfoService.putCache(info);
			return AccountInfoVo.of(info);
		}
		return null;
	}

}
