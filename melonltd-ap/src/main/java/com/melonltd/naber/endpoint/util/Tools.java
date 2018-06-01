package com.melonltd.naber.endpoint.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Tools {

	public static enum AccountType {
		USER

	}

	public static String getGMTDate(String pattern) {
		ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of("Asia/Kuala_Lumpur"));
		return DateTimeFormatter.ofPattern(pattern).format(zonedDateTime);
	}

	public static String getUUID(AccountType type) {
		UUID uuid = UUID.randomUUID();
		String uu = type.name() + "_" + getGMTDate("yyyyMMdd") + "_" + uuid.toString();
		return uu;
	}
}
