package com.melonltd.naber.rdbms.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.AppVersionLog;
import com.melonltd.naber.rdbms.model.dao.AppVersionLogDao;
import com.melonltd.naber.rdbms.model.type.DeviceCategory;
import com.melonltd.naber.rdbms.model.vo.AppVersionLogVo;

@Service("appVersionLogService")
public class AppVersionLogService {

	@Autowired
	private AppVersionLogDao appVersionLogDao;
	
	public AppVersionLogVo findOneByCategory(String category) {
		
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(0, 1, sort);
		Page<AppVersionLog> infos = appVersionLogDao.findOneByCategory(JsonHelper.json(category, DeviceCategory.class), pageable);

		if (infos.hasContent()) {
			return AppVersionLogVo.valueOf(infos.getContent().get(0));
		}
		return null;
	}
	
}
