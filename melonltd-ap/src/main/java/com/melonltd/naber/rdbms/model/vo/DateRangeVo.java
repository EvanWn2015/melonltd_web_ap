package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;

import lombok.Data;

@Data
public class DateRangeVo implements Serializable {
	private static final long serialVersionUID = -7509315033343182791L;

	private String status;
	private String date;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	public static DateRangeVo of(Integer start, Integer end, SwitchStatus status) {
		DateRangeVo vo = new DateRangeVo();
		vo.date = new StringBuffer(StringUtils.leftPad(start.toString(), 4, "0")).insert(2, ":").toString() + "-"
				+ new StringBuffer(StringUtils.leftPad(end.toString(), 4, "0")).insert(2, ":").toString();
		vo.status = status.name();
		return vo;
	}
}