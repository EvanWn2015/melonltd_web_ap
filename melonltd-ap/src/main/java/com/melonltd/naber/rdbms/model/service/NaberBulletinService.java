package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.NaberBulletin;
import com.melonltd.naber.rdbms.model.dao.NaberBulletinDao;
import com.melonltd.naber.rdbms.model.vo.NaberBulletinVo;

@Service("naberBulletinService")
public class NaberBulletinService {
	
	@Autowired
	private NaberBulletinDao naberBulletinDao;
	
	public List<NaberBulletinVo> findAll(){
		List<NaberBulletin> list = naberBulletinDao.findAllByEnable();
		if (CollectionUtils.isNotEmpty(list)) {
			return NaberBulletinVo.valueOfArray(list);
		}
		return Lists.<NaberBulletinVo>newArrayList();
	}
}
