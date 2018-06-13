package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.UserOrderLog;
import com.melonltd.naber.rdbms.model.dao.UserOrderLogDao;
import com.melonltd.naber.rdbms.model.vo.OrderVo;

@Service("userOrderLogService")
public class UserOrderLogService {

	@Autowired
	private UserOrderLogDao userOrderLogDao;

	public OrderVo save(UserOrderLog info) {
		UserOrderLog u = userOrderLogDao.save(info);
		if (ObjectUtils.anyNotNull(u)) {
			return OrderVo.valueOf(u);
		}
		return null;
	}

	public List<OrderVo> findByAccountUUIDAndPage(String accountUUID, Pageable pageable) {
		Page<UserOrderLog> page = userOrderLogDao.findByAccountUUIDAndPage(accountUUID, pageable);
		if (page.hasContent()) {
			return OrderVo.valueOfArray(page.getContent());
		}
		return Lists.newArrayList();
	}
}
