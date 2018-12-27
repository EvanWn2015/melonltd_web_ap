package com.melonltd.naber.rdbms.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.IdentityTable;

import com.melonltd.naber.rdbms.model.dao.IdentityTableDao;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.Status;
import com.melonltd.naber.rdbms.model.vo.IdentityTableVo;

@Service
public class IdentityTableService {

	@Autowired
	private IdentityTableDao identityTableDao;

	public  List<IdentityTableVo>  findAll() {
		List<IdentityTable> list = identityTableDao.findByEnableAndStatus(Enable.Y, Status.OPEN);
		return combination(list);
	}

//	public  List<IdentityTableVo> update(Map<String, List<String>> data) {
//		List<String> areas = Lists.newArrayList(data.keySet());
//		List<IdentityTable> infos = identityTableDao.findByAreas(areas);
//
//		if (infos.isEmpty()) {
//			List<IdentityTable> entities = data.keySet().stream().map(a -> {
//				IdentityTable table = new IdentityTable();
//				table.setArea(a);
//				table.setIdentityList(JsonHelper.toJson(data.get(a)));
//				table.setCreateDate(Tools.getNowGMT());
//				table.setUpdateDate(Tools.getNowGMT());
//				table.setEnable(Enable.Y);
//				table.setStatus(Status.OPEN);
//				return table;
//			}).collect(Collectors.toList());
//			return combination(identityTableDao.save(entities));
//		} else {
//			List<IdentityTable> entities = infos.stream().map(a -> {
//				System.out.println(data.get(a.getArea()));
//				a.setIdentityList(JsonHelper.toJson(data.get(a.getArea())));
//				return a;
//			}).collect(Collectors.toList());
//			return combination(identityTableDao.save(entities));
//		}
//	}

	private List<IdentityTableVo> combination(List<IdentityTable> list) {
		List<IdentityTableVo> datas = Lists.newArrayList(); 
		datas.addAll(list.stream().map(a -> IdentityTableVo.of(a)).collect(Collectors.toList()));
		return datas;
	}

}
