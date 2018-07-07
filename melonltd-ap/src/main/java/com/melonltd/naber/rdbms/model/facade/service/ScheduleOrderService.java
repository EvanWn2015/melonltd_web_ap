package com.melonltd.naber.rdbms.model.facade.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mchange.lang.IntegerUtils;
import com.melonltd.naber.background.job.OrderJob;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.AccountInfo;
import com.melonltd.naber.rdbms.model.bean.OrderInfo;
import com.melonltd.naber.rdbms.model.bean.OrderLog;
import com.melonltd.naber.rdbms.model.bean.SellerOrderFinish;
import com.melonltd.naber.rdbms.model.dao.AccountInfoDao;
import com.melonltd.naber.rdbms.model.dao.OrderInfoDao;
import com.melonltd.naber.rdbms.model.dao.OrderLogDao;
import com.melonltd.naber.rdbms.model.dao.SellerOrderFinishDao;
import com.melonltd.naber.rdbms.model.dao.UserOrderInfoDao;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.OrderStatus;

@Service("scheduleOrderService")
public class ScheduleOrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleOrderService.class);

	@Autowired
	private AccountInfoDao accountInfoDao;
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private UserOrderInfoDao userOrderInfoDao;
	@Autowired
	private OrderLogDao orderLogDao;
	@Autowired
	private SellerOrderFinishDao sellerOrderFinishDao;

	public void doOrderJob(String startDate, String endDate) {
		LOGGER.info("處理訂單時間紀錄: now: {}, start: {}, end: {}", Tools.getNowGMT(), startDate, endDate);
		
		List<OrderInfo> list = orderInfoDao.findUnfinishProcessingCanFetchByBetweenDate(startDate, endDate);
		if (CollectionUtils.isNotEmpty(list)) {
			String now = Tools.getNowGMT();
			List<String> orderUUIDs = list.stream().map(a -> a.getOrderUUID()).collect(Collectors.toList());
			List<String> accountUUIDs = list.stream().map(a -> a.getAccountUUID()).collect(Collectors.toList());
			List<AccountInfo> accounts = accountInfoDao.findByAccountUUIDs(accountUUIDs);
			
			List<AccountInfo> accountList = accounts.stream().map(a -> {
				int bonu = list.stream().filter(f -> f.getAccountUUID().equals(a.getAccountUUID())).mapToInt(b -> IntegerUtils.parseInt(b.getOrderBonus(), 0)).sum();
				a.setBonus(IntegerUtils.parseInt(a.getBonus(), 0) + bonu + "");
				return a;
			}).collect(Collectors.toList());

			List<OrderInfo> orderList = list.stream().map(a -> {
				a.setStatus(OrderStatus.FINISH.name());
				a.setUpdateDate(now);
				a.setEnable(Enable.N.name());
				return a;
			}).collect(Collectors.toList());
			
			List<OrderLog> logList = list.stream().map(a -> newOrderLog(a, now)).collect(Collectors.toList());
			List<SellerOrderFinish> finishList = list.stream().map(a -> newOrderFinish(a, OrderStatus.FINISH, now, Enable.Y)).collect(Collectors.toList());
			
			accountInfoDao.save(accountList);
			userOrderInfoDao.updateOrderStatusByorderUUIDs(orderUUIDs, OrderStatus.FINISH.name(), now);
			orderInfoDao.save(orderList);
			orderLogDao.save(logList);
			sellerOrderFinishDao.save(finishList);
		}
		
		LOGGER.info("處理訂單筆數: {}", list.size());
	}

//	private static OrderInfo newOrderInfo(OrderVo vo, OrderStatus status, String date, Enable enable) {
//		OrderInfo info = OrderInfo.of(vo);
//		info.setUpdateDate(date);
//		info.setStatus(status.name());
//		info.setEnable(enable.name());
//		return info;
//	}

	private static OrderLog newOrderLog(OrderInfo vo, String now) {
		OrderLog info = OrderLog.of(vo);
		info.setUpdateDate(now);
		return info;
	}

	// private static UserOrderLog newUserOrderLog(OrderVo vo, OrderStatus status,
	// String date, Enable enable) {
	// UserOrderLog info = UserOrderLog.of(vo);
	// info.setUpdateDate(date);
	// info.setStatus(status.name());
	// info.setEnable(enable.name());
	// return info;
	// }

	private static SellerOrderFinish newOrderFinish(OrderInfo vo, OrderStatus status, String date, Enable enable) {
		SellerOrderFinish info = new SellerOrderFinish();
		info.setOrderUUID(vo.getOrderUUID());
		info.setAccountUUID(vo.getAccountUUID());
		info.setRestaurantUUID(vo.getRestaurantUUID());
		info.setUserMessage(vo.getUserMessage());
		info.setCreateDate(date);
		info.setUpdateDate(date);
		info.setOrderPrice(vo.getOrderPrice());
		info.setOrderBonus(vo.getOrderBonus());
		info.setFetchDate(vo.getFetchDate());
		info.setOrderData(vo.getOrderData());
		info.setStatus(status.name());
		info.setEnable(enable.name());
		return info;
	}

}
