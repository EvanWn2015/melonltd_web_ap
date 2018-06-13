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
import com.melonltd.naber.endpoint.util.Tools;
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
	
	public OrderInfo findOne(String uuid) {
		OrderInfo o = orderInfoDao.findOne(uuid);
		if (ObjectUtils.anyNotNull(o)) {
			return o;
		}
		return null;
	}
	
	public List<OrderVo> findQuickSearchByPhone(String phone){
		String startDate = Tools.getStartOfDayUTC(2,8);
		String endDate = Tools.getNowEndOfDayUTC(0,8);
		List<OrderInfo> list = orderInfoDao.findByPhoneAndBetweenDate(phone, startDate, endDate);
		return OrderVo.valueInfoOfArray(list);
	}
	
	
	public List<OrderVo> findByOrderStatusAndBetweenDate(String accountUUID, String status, String date, int page){
		if (page > 0) {
			page --;
		}
		String startDate = Tools.getNowStartOfDayUTC(date);
		String endDate = Tools.getNowEndOfDayUTC(date);
		Sort sort = new Sort(Direction.DESC, "fetchDate");
		Pageable pageable = new PageRequest(page, 10, sort);
		Page<OrderInfo> pages = orderInfoDao.findByOrderStatusAndBetweenDate(accountUUID, status, startDate, endDate, pageable);
		if(pages.hasContent()) {
			return OrderVo.valueInfoOfArray(pages.getContent());
		}
		return Lists.<OrderVo>newArrayList();
	}

}
