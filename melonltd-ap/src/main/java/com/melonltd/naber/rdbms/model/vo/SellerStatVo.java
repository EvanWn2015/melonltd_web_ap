package com.melonltd.naber.rdbms.model.vo;

public class SellerStatVo {

	private String year_income;
	private String month_income;
	private String day_income;
	private String order_count;

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

	public String getOrder_count() {
		return order_count;
	}

	public void setOrder_count(String order_count) {
		this.order_count = order_count;
	}
	
	public static SellerStatVo of(long yearIncome, long monthIncome, long dayIncome ,long finishCount) {
		SellerStatVo vo = new SellerStatVo();
		vo.year_income = String.valueOf(yearIncome);
		vo.month_income = String.valueOf(monthIncome);
		vo.day_income = String.valueOf(dayIncome);
		vo.order_count = String.valueOf(finishCount);
		return vo;
	}

}
