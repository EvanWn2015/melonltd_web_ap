package com.melonltd.naber.rdbms.model.service;

import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mchange.lang.IntegerUtils;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.OrderInfo;
import com.melonltd.naber.rdbms.model.bean.SellerOrderFinish;
import com.melonltd.naber.rdbms.model.dao.OrderInfoDao;
import com.melonltd.naber.rdbms.model.dao.SellerOrderFinishDao;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.vo.OrderVo;
import com.melonltd.naber.rdbms.model.vo.SellerStatVo;

@Service("sellerOrderFinishService")
public class SellerOrderFinishService {

	@Autowired
	private SellerOrderFinishDao sellerOrderFinishDao;

	@Autowired
	private OrderInfoDao orderInfoDao;

	// @Autowired
	// private SellerOrderFinishDao sellerOrderFinishDao;

	public OrderVo save(SellerOrderFinish info) {
		SellerOrderFinish o = sellerOrderFinishDao.save(info);
		if (ObjectUtils.anyNotNull(o)) {
			return OrderVo.valueOf(o);
		}
		return null;
	}
	
	// TODO 
	public List<OrderVo> findFinishByBetweenDate(String restaurantUUID, String statStart, String statEnd, int page){
		if (page > 0) {
			page --;
		}
		Sort sort = new Sort(Direction.DESC, "updateDate");
		Pageable pageable = new PageRequest(page, 10, sort);
		Page<SellerOrderFinish> pages = sellerOrderFinishDao.findFinishCancelByBetweenDateAndPage(restaurantUUID, statStart, statEnd,pageable);
		if (pages.hasContent()) {
			return OrderVo.valueOrderFinishOfArray(pages.getContent());
		}
		return Lists.newArrayList();
	} 

	public SellerStatVo findSellerStat(String restaurantUUID) {
		long now = System.currentTimeMillis();
//		System.out.println(now);
		String statStart = Tools.getStartOfYearGMT(0, 0);
		String statEnd = Tools.getNowGMT();
		// TODO 起迄時間改變
		String statusStart = Tools.getStartOfDayGMT(1, 0);
		String statusEnd = Tools.getEndOfPlusDayGMT(0);

		List<SellerOrderFinish> list = sellerOrderFinishDao.findFinishByBetweenDate(restaurantUUID, statStart, statEnd);
		List<OrderInfo> orderLive = orderInfoDao.findUnfinishProcessingCanFetchByBetweenDate(restaurantUUID, statusStart, statusEnd);

		Range<String> year = Range.between(statStart, Tools.getNowEndOfMonthGMT(-1));
		Range<String> month = Range.between(Tools.getNowStartOfMonthGMT(-1), Tools.getNowEndOfMonthGMT(-1));
		Range<String> day = Range.between(Tools.getNowStartOfDayGMT(), statEnd);

		Predicate<SellerOrderFinish> isNumber = (a) -> NumberUtils.isCreatable(a.getOrderPrice());
		Predicate<SellerOrderFinish> containsYear = (a) -> year.contains(a.getUpdateDate());
		Predicate<SellerOrderFinish> containsMonth = (a) -> month.contains(a.getUpdateDate());
		Predicate<SellerOrderFinish> containsDay = (a) -> day.contains(a.getUpdateDate());
		Predicate<SellerOrderFinish> finishPred = (a) -> OrderStatus.of(a.getStatus()).equals(OrderStatus.FINISH);
		
		Predicate<OrderInfo> equalUnFinish = (a) -> OrderStatus.of(a.getStatus()).equals(OrderStatus.UNFINISH);
		Predicate<OrderInfo> equalProcessing = (a) -> OrderStatus.of(a.getStatus()).equals(OrderStatus.PROCESSING);
		Predicate<OrderInfo> equalCanFetch = (a) -> OrderStatus.of(a.getStatus()).equals(OrderStatus.CAN_FETCH);
		Predicate<OrderInfo> equalCancel = (a) -> OrderStatus.of(a.getStatus()).equals(OrderStatus.CANCEL);

		long finishCount = list.stream().filter(o -> OrderStatus.of(o.getStatus()).equals(OrderStatus.FINISH)).count();
		long yearIncome = list.parallelStream().filter(isNumber.and(containsYear).and(finishPred)).mapToInt(a -> IntegerUtils.parseInt(a.getOrderPrice(), 0)).sum();
		long monthIncome = list.parallelStream().filter(isNumber.and(containsMonth).and(finishPred)).mapToInt(a -> IntegerUtils.parseInt(a.getOrderPrice(), 0)).sum();
		long dayIncome = list.parallelStream().filter(isNumber.and(containsDay).and(finishPred)).mapToInt(a -> IntegerUtils.parseInt(a.getOrderPrice(), 0)).sum();

		long unFinishCount = orderLive.parallelStream().filter(equalUnFinish).count();
		long processingCount = orderLive.parallelStream().filter(equalProcessing).count();
		long canFetchCount = orderLive.parallelStream().filter(equalCanFetch).count();
		long cancelCount = orderLive.parallelStream().filter(equalCancel).count();
		
		// 收入統計需減去被使用紅利折抵
		long yearUseBonus = list.parallelStream().filter(isNumber.and(containsYear).and(finishPred)).mapToInt(a -> IntegerUtils.parseInt(a.getUseBonus(), 0)).sum();
		long monthUseBonus = list.parallelStream().filter(isNumber.and(containsMonth).and(finishPred)).mapToInt(a -> IntegerUtils.parseInt(a.getUseBonus(), 0)).sum();
		long dayUseBonus = list.parallelStream().filter(isNumber.and(containsDay).and(finishPred)).mapToInt(a -> IntegerUtils.parseInt(a.getUseBonus(), 0)).sum();

		long yI = yearIncome - (yearUseBonus / 10 * 3);
		long mI = monthIncome - (monthUseBonus / 10 * 3);
		long dI = dayIncome - (dayUseBonus / 10 * 3);
		
//		System.out.println("Y: " + yearIncome + ", " + yearUseBonus + ", " + yI);
//		System.out.println("M: " + monthIncome + ", " + monthUseBonus + ", " + mI);
//		System.out.println("D: " + dayIncome + ", " + dayUseBonus + ", " + dI);
//		System.out.println();
		
		// TODO 要使用格用計算還是日計算
//		long bonus = list.parallelStream().filter(isNumber.and(containsDay).and(finishPred)).mapToInt(a -> IntegerUtils.parseInt(a.getOrderBonus(), 0)).sum();
//		long useBonus = list.parallelStream().filter(isNumber.and(containsDay).and(finishPred)).mapToInt(a -> IntegerUtils.parseInt(a.getUseBonus(), 0)).sum();
//		System.out.println(bonus + ": " + useBonus);
		
		System.out.println((System.currentTimeMillis() - now) / 1000d);
		
		return SellerStatVo
				.of(yI, mI, dI, finishCount)
				.ofUseBonus(yearUseBonus, monthUseBonus, dayUseBonus)
				.ofStatus(new String[]{statusStart, statusEnd} , unFinishCount, processingCount, canFetchCount, cancelCount);
	}
	
	
	public List<OrderVo> findFinishByBetweenDate(String restaurantUUID, String month){
		String start = Tools.getDateStartOfMonthGMT(month);
		String end = Tools.getDateEndOfMonthGMT(month);
		
		List<SellerOrderFinish> list = sellerOrderFinishDao.findFinishByBetweenDate(restaurantUUID, start, end);
		if(CollectionUtils.isNotEmpty(list)) {
			return OrderVo.valueOrderFinishOfArray(list);
		}
		return Lists.newArrayList();
		
	}

}
