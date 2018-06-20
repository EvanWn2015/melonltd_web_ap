package com.melonltd.naber.rdbms.model.service;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.SellerOrderFinish;
import com.melonltd.naber.rdbms.model.dao.SellerOrderFinishDao;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.vo.OrderVo;
import com.melonltd.naber.rdbms.model.vo.SellerStatVo;

@Service("sellerOrderFinishService")
public class SellerOrderFinishService {

	@Autowired
	private SellerOrderFinishDao sellerOrderFinishDao;

	public OrderVo save(SellerOrderFinish info) {
		SellerOrderFinish o = sellerOrderFinishDao.save(info);
		if (ObjectUtils.anyNotNull(o)) {
			return OrderVo.valueOf(o);
		}
		return null;
	}

	public SellerStatVo findSellerStat(String restaurantUUID) {
		String startDate = Tools.getStartOfYearGMT(0, 0);
		String endDate = Tools.getNowGMT();

		List<SellerOrderFinish> list = sellerOrderFinishDao.findFinishByBetweenDate(restaurantUUID, startDate, endDate);
		Range<String> year = Range.between(startDate, Tools.getNowEndOfMonthGMT(-1));
		Range<String> month = Range.between(Tools.getNowStartOfMonthGMT(-1), Tools.getNowEndOfMonthGMT(-1));
		Range<String> day = Range.between(Tools.getNowStartOfDayGMT(), endDate);

		long yearIncome = 0L;
		long monthIncome = 0L;
		long dayIncome = 0L;
		long finishCount = 0L;

		for (SellerOrderFinish o : list) {
			if (NumberUtils.isCreatable(o.getOrderPrice())) {
				long price = NumberUtils.toLong(o.getOrderPrice());
				if (year.contains(o.getUpdateDate())) {
					yearIncome += price;
				}

				if (month.contains(o.getUpdateDate())) {
					monthIncome += price;
				}

				if (day.contains(o.getUpdateDate())) {
					dayIncome += price;
				}
			}
			finishCount++;
		}
		return SellerStatVo.of(yearIncome, monthIncome, dayIncome, finishCount);
	}

}
