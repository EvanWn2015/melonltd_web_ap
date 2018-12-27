package com.melonltd.naber.rdbms.model.vo;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.AppVersionLog;

import lombok.Data;

@Data
public class AppVersionLogVo {

	private String version;
	private String category;
	private String need_upgrade;
	private String create_date;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	public static AppVersionLogVo valueOf(AppVersionLog info) {
		AppVersionLogVo vo = new AppVersionLogVo();
		vo.version = info.getVersion();
		vo.category = info.getCategory().name();
		vo.need_upgrade = info.getNeedUpgrade().name();
		vo.create_date = info.getCreateDate();
		return vo;
	}

}
