package com.melonltd.naber.endpoint.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class Tools {

	public static enum AccountType {
		ADMIN, USER, SELLER

	}

	/**
	 * pattern="yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"
	 */
	public static String getGMTDate(String pattern) {
		ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of("Asia/Kuala_Lumpur"));
		return DateTimeFormatter.ofPattern(pattern).format(zonedDateTime);
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

	public static String buildAccountUUID(AccountType type) {
		UUID uuid = UUID.randomUUID();
		String uu = type.name() + "_" + getGMTDate("yyyyMMdd") + "_" + uuid.toString();
		return uu;
	}
}
