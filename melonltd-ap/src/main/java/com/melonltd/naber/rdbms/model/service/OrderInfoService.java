package com.melonltd.naber.rdbms.model.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.OrderInfo;
import com.melonltd.naber.rdbms.model.dao.OrderInfoDao;
import com.melonltd.naber.rdbms.model.vo.OrderVo;

@Service("orderInfoService")
public class OrderInfoService {

	@Autowired
	private OrderInfoDao orderInfoDao;

	public OrderVo save(OrderInfo info) {
		OrderInfo o = orderInfoDao.save(info);
		if (ObjectUtils.anyNotNull(o)) {
			return OrderVo.valueOf(o);
		}
		return null;
	}

}
