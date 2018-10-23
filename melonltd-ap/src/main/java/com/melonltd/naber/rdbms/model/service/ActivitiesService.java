package com.melonltd.naber.rdbms.model.service;

import static org.springframework.data.domain.Sort.Direction.ASC;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.Activities;
import com.melonltd.naber.rdbms.model.dao.ActivitiesDao;
import com.melonltd.naber.rdbms.model.vo.ActivitiesVo;

@Service("activitiesService")
public class ActivitiesService {
	
	@Autowired
	private ActivitiesDao activitiesDao;

//	public List<ActivitiesVo> findAll() {
//		List<Activities> infos = activitiesDao.findAll(new Sort(ASC, "needBonus"));
//		if (CollectionUtils.isNotEmpty(infos)) {
//			return ActivitiesVo.valueOfArray(infos);
//		}
//		return Lists.<ActivitiesVo>newArrayList();
//	}
//	
	
	public List<ActivitiesVo> findByActCategoryAnfFunc(String actCategory, String func){
		List<Activities> infos = activitiesDao.findByActCategoryAnfFunc(actCategory, func, new Sort(ASC, "needBonus"));
		if (CollectionUtils.isNotEmpty(infos)) {
			return ActivitiesVo.valueOfArray(infos);
		}
		return Lists.<ActivitiesVo>newArrayList();
	}
	
	public ActivitiesVo findByUUID (String actUUID) {
		Activities info = activitiesDao.findByActivitiesUUID(actUUID);
		if(ObjectUtils.anyNotNull(info)){
			return ActivitiesVo.valueOf(info);
		}
		return null;
	}
	
	public ActivitiesVo findBySerial( String serial) {
		String now = Tools.getNowGMT();
		Activities info = activitiesDao.findBySerial(serial, now);
		if(ObjectUtils.anyNotNull(info)){
			return ActivitiesVo.valueOf(info);
		}
		return null;
	}
}
