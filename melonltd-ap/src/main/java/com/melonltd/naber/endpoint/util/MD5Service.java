package com.melonltd.naber.endpoint.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("md5Service")
public class MD5Service {
	private static final Logger LOGGER = LoggerFactory.getLogger(MD5Service.class);
	
	public static String encode(String str) {
		LOGGER.info("MD5 encode input: {}", str);
		String md5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] barr = md.digest(str.getBytes()); // 將 byte 陣列加密
			StringBuffer sb = new StringBuffer(); // 將 byte 陣列轉成 16 進制
			for (int i = 0; i < barr.length; i++) {
				sb.append(byte2Hex(barr[i]));
			}
			String hex = sb.toString();
			md5 = hex.toUpperCase(); // 一律轉成大寫
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("MD5 encode output: {}", md5);
		return md5;
	}
	
	// 產生 MD5 序號
	public static String buildSerialNumber(String str) {
		String md5 = encode(str);
		char[] chars = md5.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i< chars.length; i++) {
			sb.append(chars[i]);
			if ((i+1) % 4 == 0 && (i+1) < chars.length) {
				sb.append('-');	
			}
		}
		return sb.toString();
	}

	public static String byte2Hex(byte b) {
		String[] h = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
		int i = b;
		if (i < 0) {
			i += 256;
		}
		return h[i / 16] + h[i % 16];
	}
	
}
