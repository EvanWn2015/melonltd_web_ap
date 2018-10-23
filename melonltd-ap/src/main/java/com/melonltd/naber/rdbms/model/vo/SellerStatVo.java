package com.melonltd.naber.rdbms.model.vo;

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

	public String getYear_income() {
		return year_income;
	}

	public void setYear_income(String year_income) {
		this.year_income = year_income;
	}

	public String getMonth_income() {
		return month_income;
	}

	public void setMonth_income(String month_income) {
		this.month_income = month_income;
	}

	public String getDay_income() {
		return day_income;
	}

	public void setDay_income(String day_income) {
		this.day_income = day_income;
	}

	public String getFinish_count() {
		return finish_count;
	}

	public void setFinish_count(String finish_count) {
		this.finish_count = finish_count;
	}

	public String[] getStatus_dates() {
		return status_dates;
	}

	public void setStatus_dates(String[] status_dates) {
		this.status_dates = status_dates;
	}

	public String getUnfinish_count() {
		return unfinish_count;
	}

	public void setUnfinish_count(String unfinish_count) {
		this.unfinish_count = unfinish_count;
	}

	public String getProcessing_count() {
		return processing_count;
	}

	public void setProcessing_count(String processing_count) {
		this.processing_count = processing_count;
	}

	public String getCan_fetch_count() {
		return can_fetch_count;
	}

	public void setCan_fetch_count(String can_fetch_count) {
		this.can_fetch_count = can_fetch_count;
	}

	public String getCancel_count() {
		return cancel_count;
	}

	public void setCancel_count(String cancel_count) {
		this.cancel_count = cancel_count;
	}


	public static SellerStatVo of(long yearIncome, long monthIncome, long dayIncome, long finishCount) {
		SellerStatVo vo = new SellerStatVo();
		vo.year_income = String.valueOf(yearIncome);
		vo.month_income = String.valueOf(monthIncome);
		vo.day_income = String.valueOf(dayIncome);
		vo.finish_count = String.valueOf(finishCount);
		return vo;
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
