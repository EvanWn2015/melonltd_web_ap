package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.MoreObjects;


public class DateRangeVo implements Serializable{
	private static final long serialVersionUID = -7509315033343182791L;
	
	private String status;
	private String date;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("status", status)
				.add("date", date)
				.toString();
	}
	
	public static DateRangeVo of(Integer start, Integer end, String status) {
		DateRangeVo vo = new DateRangeVo();
		vo.date = new StringBuffer(StringUtils.leftPad(start.toString(), 4, "0")).insert(2, ":").toString() + "-"
				+ new StringBuffer(StringUtils.leftPad(end.toString(), 4, "0")).insert(2, ":").toString();
		vo.status = status;
		return vo;
	}
}