package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.UserOrderInfo;
import com.melonltd.naber.rdbms.model.dao.UserOrderInfoDao;
import com.melonltd.naber.rdbms.model.vo.OrderVo;

@Service("userOrderInfoService")
public class UserOrderInfoService {

	@Autowired
	private UserOrderInfoDao userOrderInfoDao;

	public OrderVo save(UserOrderInfo info) {
		UserOrderInfo u = userOrderInfoDao.save(info);
		if (ObjectUtils.anyNotNull(u)) {
			return OrderVo.valueOf(u);
		}
		return null;
	}
	
	public OrderVo findOne(String uuid) {
		UserOrderInfo o = userOrderInfoDao.findOne(uuid);
		if (ObjectUtils.anyNotNull(o)) {
			if (o.getEnable().equals("N")) {
				return null;
			}else {
				return OrderVo.valueOf(o);	
			}
		}
		return null;
	}

	public List<OrderVo> findByAccountUUIDAndPage(String accountUUID, int page) {
		if (page > 0) {
			page = page - 1;
		}
		Sort sort = new Sort(Direction.DESC, "fetchDate");
		Pageable pageable = new PageRequest(page, 10, sort);
		Page<UserOrderInfo> pages = userOrderInfoDao.findByAccountUUIDAndPage(accountUUID, pageable);
		if (pages.hasContent()) {
			return OrderVo.valueOfArray(pages.getContent());
		}
		return Lists.newArrayList();
	}
	
	public int findByOrderStatusAccountUUID(String accountUUID) {
		List<UserOrderInfo> list = userOrderInfoDao.findByOrderStatusAccountUUID(accountUUID);
		return list.size();
	}
	
	public int findByOrderFailByAccountUUID(String accountUUID) {
		List<UserOrderInfo> list = userOrderInfoDao.findByOrderFailByAccountUUID(accountUUID);
		return list.size();
	}
}
