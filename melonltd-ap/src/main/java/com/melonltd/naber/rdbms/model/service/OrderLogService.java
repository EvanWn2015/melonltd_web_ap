package com.melonltd.naber.rdbms.model.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.OrderLog;
import com.melonltd.naber.rdbms.model.dao.OrderLogDao;
import com.melonltd.naber.rdbms.model.vo.OrderVo;

@Service("orderLogService")
public class OrderLogService {
	
	@Autowired
	private OrderLogDao orderLogDao;
	
	public OrderVo save(OrderLog info) {
		OrderLog o = orderLogDao.save(info);
		if (ObjectUtils.anyNotNull(o)) {
			return OrderVo.valueOf(o);
		}
		return null;
	}

}
