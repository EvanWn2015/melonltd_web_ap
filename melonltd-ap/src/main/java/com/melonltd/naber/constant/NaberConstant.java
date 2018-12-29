package com.melonltd.naber.constant;

import java.util.Map;

import com.google.common.collect.Maps;

public class NaberConstant {
	public static boolean IS_DEBUG = true;
	
	public static int ORDER_PRICE_MAX = 5000;
	public static int ORDER_PRICE_COUNT_MAX = 3;
	
	public static int SMS_CODE_LENGTH = 6;
	public static int SMS_DAILY_SEND_MAX = 3;
	
	
	public static Map<String, String> TOP_MAPPING = getTopMapping();
	
	private static Map<String, String> getTopMapping (){
		Map<String, String> map = Maps.newHashMap();
		map.put("開南大學", "-1");
		map.put("長庚大學", "-2");		
		return map;
	}
}
