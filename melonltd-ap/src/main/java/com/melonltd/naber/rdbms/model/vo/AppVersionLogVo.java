package com.melonltd.naber.rdbms.model.vo;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.Activities;
import com.melonltd.naber.rdbms.model.bean.AppVersionLog;

public class AppVersionLogVo {

	private String version;
	private String category;
	private String need_upgrade;
	private String create_date;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNeed_upgrade() {
		return need_upgrade;
	}

	public void setNeed_upgrade(String need_upgrade) {
		this.need_upgrade = need_upgrade;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("version", version)
				.add("category", category)
				.add("need_upgrade", need_upgrade)
				.add("create_date", create_date)
				.toString();	
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
