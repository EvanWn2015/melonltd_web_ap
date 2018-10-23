package com.melonltd.naber.rdbms.model.service;


import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.ActivitiesLog;
import com.melonltd.naber.rdbms.model.dao.ActivitiesLogDao;
import com.melonltd.naber.rdbms.model.vo.ActivitiesLogVo;
import com.melonltd.naber.rdbms.model.vo.ActivitiesVo;

@Service("activitiesLogService")
public class ActivitiesLogService {

	@Autowired
	private ActivitiesLogDao activitiesLogDao;
	
	
	public ActivitiesLog save(String accountUUID, ActivitiesVo act, String data) {
		ActivitiesLog entity = new ActivitiesLog();
		entity.setActUUID(act.getAct_uuid());
		entity.setAccountUUID(accountUUID);
		entity.setActCategory(act.getAct_category());
		entity.setSerial(act.getSerial());
		entity.setRestrictFunc(act.getRestrict_func());
		entity.setData(data);
		entity.setCreateDate(Tools.getNowGMT());
		return activitiesLogDao.save(entity);
	}
	
	public List<ActivitiesLogVo> findByActUUID(String actUUID) {
		List<ActivitiesLog> list = activitiesLogDao.findByActUUID(actUUID);
		if (CollectionUtils.isNotEmpty(list)){
			return ActivitiesLogVo.valueOfArray(list);
		}
		return Lists.newArrayList();
	}
	
	public List<ActivitiesLogVo> findByAccountUUIDAndActUUID(String accountUUID, String actUUID){
		List<ActivitiesLog> list = activitiesLogDao.findByAccountUUIDAndActUUID(accountUUID, actUUID);
		if (CollectionUtils.isNotEmpty(list)){
			return ActivitiesLogVo.valueOfArray(list);
		}
		return Lists.newArrayList();
	}
}
