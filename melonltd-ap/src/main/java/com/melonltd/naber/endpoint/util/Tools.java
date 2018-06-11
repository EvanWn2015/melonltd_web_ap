package com.melonltd.naber.endpoint.util;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;

public class Tools {

	private static DecimalFormat DF = new DecimalFormat();
	
	public static enum UUIDType {
		ADMIN, USER, SELLER, NABER_BULLETIN, RESTAURANT, RESTAURANT_CATEGORY, DEVICE, AD, FOOD,ORDER
	}

	/**
	 * pattern="yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"
	 */
	public static String getGMTDate(String pattern) {
		ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of("Asia/Kuala_Lumpur"));
		return DateTimeFormatter.ofPattern(pattern).format(zonedDateTime);
	}
	
	public static String fromatUTCToGMT(String pattern, String utcDate) {
		return DateTimeFormatter.ofPattern(pattern).format(Instant.parse(utcDate).atZone(ZoneId.of("Asia/Kuala_Lumpur")));
	}
	
	public static String getNowUTC () {
		return Instant.now().toString();
	}

	/**
	 * date ="2018-06-02T00:26:40.4510Z" pattern="yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"
	 */
	public static int getDayOfYear(String date, String pattern) {
		LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
		return localDate.getDayOfYear();
	}

	/**
	 * date ="2018-06-02T00:26:40.4510Z"
	 */
	public static long getMinutes(String date) {
		Instant instant = Instant.parse(date);
		return Date.from(instant).getTime();
	}
	
	public static String buildUUID(UUIDType type) {
		UUID uuid = UUID.randomUUID();
		String uu = type.name() + "_" + uuid.toString();
		return uu;
	}

	public static String buildAccountUUID(UUIDType type) {
		UUID uuid = UUID.randomUUID();
		String uu = type.name() + "_" + getGMTDate("yyyyMMdd") + "_" + uuid.toString();
		return uu;
	}
	
	
	public static String decimalFormat(String pattern, Object format) {
		if(NumberUtils.isCreatable(format.toString())) {
			DF.applyPattern(pattern);	 
			return DF.format(NumberUtils.createDouble(format.toString()));
		}
		return"";
	}	
	
	/**
	 * 檢查每日營業時間範圍
	 * @param start
	 * @param end
	 * @param now format GMT "HH:mm"
	 * @return
	 */
	public static boolean checkOpenStore(String start, String end, String now) {
		boolean status = false;
		if (start.compareTo(end) >= 1) {
			status = start.compareTo(now) < 1 || now.compareTo(end) < 1;
		}else {
			status = org.apache.commons.lang3.Range.<String>between(start, end).contains(now);
		}
		return status;
	}
	
	// 檢查接單開關時間範圍
	public static List<DateRangeVo> checkOpenStoreByRanges (String canStoreRange, String now){
		
		List<DateRangeVo> list = JsonHelper.jsonArray(canStoreRange, DateRangeVo[].class);
		return list.stream()
				.filter(f -> "N".equals(f.getStatus()) && org.apache.commons.lang3.Range.<String>between(f.getDate().substring(0, 5), f.getDate().substring(6, 11)).contains(now))
				.collect(Collectors.toList());
	}
	
	
	public static List<DateRangeVo> buildDateRange(Integer start , Integer end) {
		start++;
		Range<Integer> timeR = Range.open(start, start + 29);
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();

		boolean status = true;
		while (status) {
			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
			int dd = 30;
			if (timeR.upperEndpoint() % 100 == 0) {
				dd = 30;
			} else {
				dd = 70;
			}
			timeR = Range.open(timeR.upperEndpoint() + 1, timeR.upperEndpoint() + dd);
			status = timeR.upperEndpoint().intValue() < end;
			if (!status) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
			}
		}
		return list;
	}
	
	public static List<DateRangeVo> buildDateRangeReverse(Integer start , Integer end) {
		start++;
		Range<Integer> timeR = Range.open(start, start + 29);
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();
		boolean status = true;
		
		while (status) {
			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
			int dd = 30;
			if (timeR.upperEndpoint() % 100 == 0) {
				dd = 30;
			} else {
				dd = 70;
			}
			timeR = Range.open(timeR.upperEndpoint() + 1, timeR.upperEndpoint() + dd);
			
			if (timeR.upperEndpoint().intValue() == 2400) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), 2400, "Y"));
				timeR = Range.open(0 + 1,  30);
			}
			
			if(timeR.upperEndpoint().intValue() == end) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
				status = false;
			}
		}
		return list;
	}
	
	
	
}
