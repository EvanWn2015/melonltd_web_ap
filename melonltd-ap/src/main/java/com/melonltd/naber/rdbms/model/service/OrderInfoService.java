package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
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
	
	public OrderVo findOne(String uuid) {
		OrderInfo o = orderInfoDao.findOne(uuid);
		if (ObjectUtils.anyNotNull(o)) {
			if (o.getEnable().equals("N")) {
				return null;
			}else {
				return OrderVo.valueOf(o);	
			}
		}
		return null;
	}
	
	public List<OrderVo> findQuickSearchByPhone(String restaurantUUID ,String phone){
		String startDate = Tools.getStartOfDayGMT(2,8);
		String endDate = Tools.getNowEndOfDayGMT(0,8);
		List<OrderInfo> list = orderInfoDao.findByPhoneAndBetweenDate(phone, startDate, endDate, restaurantUUID);
		return OrderVo.valueInfoOfArray(list);
	}
	
	public List<OrderVo> findUnfinishProcessingCanFetchByBetweenDate(String status, String end){
		List<OrderInfo> list = orderInfoDao.findUnfinishProcessingCanFetchByBetweenDate(status, end);
		if (!CollectionUtils.isEmpty(list)) {
			return OrderVo.valueInfoOfArray(list);	
		}
		return Lists.newArrayList();
	}
	
	public List<OrderVo> findByOrderStatusAndBetweenDate(String accountUUID, String status, String date, int page){
		if (page > 0) {
			page --;
		}
		String startDate = Tools.getNowStartOfDay(date);
		String endDate = Tools.getNowEndOfDay(date);
		Sort sort = new Sort(Direction.ASC, "fetchDate");
		Pageable pageable = new PageRequest(page, 10,sort);
		Page<OrderInfo> pages = orderInfoDao.findByOrderStatusAndBetweenDate(accountUUID, status, startDate, endDate, pageable);
		if(pages.hasContent()) {
			return OrderVo.valueInfoOfArray(pages.getContent());
		}
		return Lists.<OrderVo>newArrayList();
	}

}
