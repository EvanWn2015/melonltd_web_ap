package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.MobileDevice;

import lombok.Data;

@Data
public class MobileDeviceVo implements Serializable {
	private static final long serialVersionUID = 7942166100146484192L;

	private String device_uuid;
	private String device_token;
	private String account_uuid;
	private String device_category;
	private String create_date;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	public static MobileDeviceVo valueOf(MobileDevice info) {
		MobileDeviceVo vo = new MobileDeviceVo();
		vo.device_uuid = info.getDeviceUUID();
		vo.device_token = info.getDeviceToken();
		vo.account_uuid = info.getAccountUUID();
		vo.device_category = info.getDeviceCategory();
		vo.create_date = info.getCreateDate();
		return vo;
	}

	public static List<MobileDeviceVo> valueOfArray(List<MobileDevice> infos) {
		List<MobileDeviceVo> list = Lists.newArrayList();
		list = infos.stream().map(a -> valueOf(a)).collect(Collectors.toList());
		return list;
	}

}
