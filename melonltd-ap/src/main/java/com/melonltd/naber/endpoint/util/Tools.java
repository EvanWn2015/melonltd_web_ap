package com.melonltd.naber.endpoint.util;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;
import com.melonltd.naber.rdbms.model.vo.LatLngVo;

public class Tools {

	private static DecimalFormat DF = new DecimalFormat();
	private static final ZoneId ZONEID_GMT = ZoneId.of("Asia/Kuala_Lumpur");
	private static final DateTimeFormatter yyyy_MM_dd_T_HH_mm_ss_SSSS_Z = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");

	public static enum UUIDType {
		ADMIN, USER, SELLER, NABER_BULLETIN, RESTAURANT, RESTAURANT_CATEGORY, DEVICE, AD, FOOD, ORDER
	}

	/**
	 * pattern="yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"
	 */
	public static String getGMTDate(String pattern) {
		return LocalDateTime.now(ZONEID_GMT).format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String getNowGMT() {
		return LocalDateTime.now(ZONEID_GMT).format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}
	
	public static String getNowGMT(int hours,int minutes) {
		return LocalDateTime.now(ZONEID_GMT).plusHours(hours).plusMinutes(minutes).format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String getNowStartOfDayGMT() {
		return LocalDateTime.now(ZONEID_GMT).with(LocalTime.MIN).format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String getNowEndOfDayGMT() {
		return LocalDateTime.now(ZONEID_GMT).with(LocalTime.MAX).format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String getEndOfPlusDayGMT(int plus) {
		return LocalDateTime.now(ZONEID_GMT).plusDays(plus).with(LocalTime.MAX).format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String fromatGMT(String pattern, String date) {
		return LocalDateTime.parse(date, yyyy_MM_dd_T_HH_mm_ss_SSSS_Z).format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static String fromatStartOfDayGMT(String date) {
		return LocalDateTime.parse(date, yyyy_MM_dd_T_HH_mm_ss_SSSS_Z).with(LocalTime.MIN).format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}
	
	public static String fromatEndOfDayGMT(String date) {
		return LocalDateTime.parse(date, yyyy_MM_dd_T_HH_mm_ss_SSSS_Z).with(LocalTime.MAX).format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String getPlusDayGMT(int days) {
		return LocalDateTime.now(ZONEID_GMT).plusDays(days).format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String getStartOfDayGMT(int minusDay, int plusHours) {
		return LocalDateTime.now(ZONEID_GMT).with(LocalTime.MIN).minusDays(minusDay).plusHours(plusHours)
				.format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String getNowEndOfDayGMT(int minusDay, int plusHours) {
		return LocalDateTime.now(ZONEID_GMT).with(LocalTime.MAX).minusDays(minusDay).plusHours(plusHours)
				.format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String getNowStartOfDay(String date) {
		return LocalDateTime.parse(date, yyyy_MM_dd_T_HH_mm_ss_SSSS_Z).with(LocalTime.MIN)
				.format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String getNowEndOfDay(String date) {
		return LocalDateTime.parse(date, yyyy_MM_dd_T_HH_mm_ss_SSSS_Z).with(LocalTime.MAX)
				.format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String getStartOfYearGMT(int minusDay, int plusHours) {
		return LocalDateTime.now(ZONEID_GMT).withDayOfYear(1).with(LocalTime.MIN).minusDays(minusDay)
				.plusHours(plusHours).format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String getNowEndOfMonthGMT(int minus) {
		LocalDateTime localDateTime = LocalDateTime.now(ZONEID_GMT);
		return localDateTime.withDayOfMonth(localDateTime.getMonth().maxLength()).plusMonths(minus).with(LocalTime.MAX)
				.format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
	}

	public static String getNowStartOfMonthGMT(int minus) {
		return LocalDateTime.now(ZONEID_GMT).withDayOfMonth(1).plusMonths(minus).with(LocalTime.MIN)
				.format(yyyy_MM_dd_T_HH_mm_ss_SSSS_Z);
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
		return Date.from(Instant.parse(date)).getTime();
	}

	public static String buildUUID(UUIDType type) {
		UUID uuid = UUID.randomUUID();
		String uu = type.name() + "_" + getGMTDate("yyyyMMdd_HHmmss_SSS") + "_" + uuid.toString();
		return uu;
	}

	public static String buildAccountUUID(UUIDType type) {
		UUID uuid = UUID.randomUUID();
		String uu = type.name() + "_" + getGMTDate("yyyyMMdd_HHmmss_SSS") + "_" + uuid.toString();
		return uu;
	}

	public static String decimalFormat(String pattern, Object format) {
		if (NumberUtils.isCreatable(format.toString())) {
			DF.applyPattern(pattern);
			return DF.format(NumberUtils.createDouble(format.toString()));
		}
		return "";
	}

	/**
	 * 檢查每日營業時間範圍
	 * 
	 * @param start
	 * @param end
	 * @param now
	 *            format GMT "HH:mm"
	 * @return
	 */
	public static boolean checkOpenStore(String start, String end, String now) {
		boolean status = false;
		if (start.compareTo(end) >= 1) {
			status = start.compareTo(now) < 1 || now.compareTo(end) < 1;
		} else {
			status = org.apache.commons.lang3.Range.<String>between(start, end).contains(now);
		}
		return status;
	}

	// 檢查接單開關時間範圍
	public static List<DateRangeVo> checkOpenStoreByRanges(String canStoreRange, String now) {
		
		List<DateRangeVo> list = JsonHelper.jsonArray(canStoreRange, DateRangeVo[].class);
		return list.stream()
				.filter(f -> SwitchStatus.CLOSE.name().equals(f.getStatus()) && org.apache.commons.lang3.Range
						.<String>between(f.getDate().substring(0, 5), f.getDate().substring(6, 11)).contains(now))
				.collect(Collectors.toList());
	}

	public static List<DateRangeVo> checkOpenStoreByRanges(List<DateRangeVo> list, String now) {
		return list.stream()
				.filter(f -> SwitchStatus.CLOSE.name().equals(f.getStatus()) && org.apache.commons.lang3.Range
						.<String>between(f.getDate().substring(0, 5), f.getDate().substring(6, 11)).contains(now))
				.collect(Collectors.toList());
	}

	public static List<DateRangeVo> buildCanStoreRange(Integer start, Integer end) {
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();
		if (end == 0) {
			end = 2400;
		}
		
		if (start < end) {
			list = buildDateRange(start, end);
		} else {
			list = buildDateRangeReverse(start, end);
		}
		return list;
	}

	public static List<DateRangeVo> buildDateRange(Integer start, Integer end) {
		int tmpEnd = 0;
		if (start % 100 == 0) {
			tmpEnd = start + 30;
		} else {
			tmpEnd =  start + 70;
		}
		
		start++;
		Range<Integer> timeR = Range.open(start, tmpEnd);
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();

		boolean status = true;
		while (status) {
			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(),
					SwitchStatus.OPEN));
			int dd = 30;
			if (timeR.upperEndpoint() % 100 == 0) {
				dd = 30;
			} else {
				dd = 70;
			}

			// if (!status) {
			// list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(),
			// timeR.upperEndpoint().intValue(), SwitchStatus.OPEN));
			// }
			timeR = Range.open(timeR.upperEndpoint() + 1, timeR.upperEndpoint() + dd);
			status = timeR.upperEndpoint().intValue() <= end;
		}
		return list;
	}

	public static List<DateRangeVo> buildDateRangeReverse(Integer start, Integer end) {
		int tmpEnd = 0;
		if (start % 100 == 0) {
			tmpEnd = start + 30;
		} else {
			tmpEnd =  start + 70;
		}
		
		start++;
		Range<Integer> timeR = Range.open(start, tmpEnd);
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();
		boolean status = true;

		while (status) {
			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(),
					SwitchStatus.OPEN));
			int dd = 30;
			if (timeR.upperEndpoint() % 100 == 0) {
				dd = 30;
			} else {
				dd = 70;
			}
			timeR = Range.open(timeR.upperEndpoint() + 1, timeR.upperEndpoint() + dd);

			if (timeR.upperEndpoint().intValue() == 2400) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), 2400, SwitchStatus.OPEN));
				timeR = Range.open(0 + 1, 30);
				if (end == 0) {
					status = false;
				}
			}

			if (timeR.upperEndpoint().intValue() == end) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(),
						SwitchStatus.OPEN));
				status = false;
			}
		}
		return list;
	}

	/**
	 * 隨機亂數密碼產生
	 * 
	 * @param len
	 * 所需產生長度
	 */
	public static String getRandomPassword(int len) {
		String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int idx = (int) (Math.random() * str.length());
			sb.append(str.charAt(idx));
		}
		return sb.toString();
	}

	public static double getGoogleDistance(LatLngVo start, LatLngVo end) {
		double lat1 = (Math.PI / 180) * start.getLatitude();
		double lat2 = (Math.PI / 180) * end.getLatitude();
		double lon1 = (Math.PI / 180) * start.getLongitude();
		double lon2 = (Math.PI / 180) * end.getLongitude();

		double R = 6371;// 地球半径
		double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
		return d * 1000;
	}
}
