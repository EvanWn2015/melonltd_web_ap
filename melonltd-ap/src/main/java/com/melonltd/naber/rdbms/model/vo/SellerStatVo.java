package com.melonltd.naber.rdbms.model.vo;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
public class SellerStatVo {

	private String year_income;
	private String month_income;
	private String day_income;
	private String finish_count;

	private String[] status_dates;
	private String unfinish_count;
	private String processing_count;
	private String can_fetch_count;
	private String cancel_count;

	private String year_use_bonus;
	private String month_use_bonus;
	private String day_use_bonus;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	public static SellerStatVo of(long yearIncome, long monthIncome, long dayIncome, long finishCount) {
		SellerStatVo vo = new SellerStatVo();
		vo.year_income = String.valueOf(yearIncome);
		vo.month_income = String.valueOf(monthIncome);
		vo.day_income = String.valueOf(dayIncome);
		vo.finish_count = String.valueOf(finishCount);
		return vo;
	}

	public SellerStatVo ofUseBonus(long yearUseBonus, long monthUseBonus, long dayUseBonus) {
		this.year_use_bonus = String.valueOf(yearUseBonus);
		this.month_use_bonus = String.valueOf(monthUseBonus);
		this.day_use_bonus = String.valueOf(dayUseBonus);
		return this;
	}

	public SellerStatVo ofStatus(String[] statusDates, long unFinishCount, long processingCount, long canFetchCount,
			long cancelCount) {
		this.status_dates = statusDates;
		this.unfinish_count = String.valueOf(unFinishCount);
		this.processing_count = String.valueOf(processingCount);
		this.can_fetch_count = String.valueOf(canFetchCount);
		this.cancel_count = String.valueOf(cancelCount);
		return this;
	}

}
