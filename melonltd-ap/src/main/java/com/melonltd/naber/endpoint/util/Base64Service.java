package com.melonltd.naber.endpoint.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Base64Service {
	private static final Logger LOGGER = LoggerFactory.getLogger(Base64Service.class);

	public static String encryptBASE64(String key) {
		try {
			return Base64.getEncoder().encodeToString(URLEncoder.encode(key, "utf-8").getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("", e);
			throw new Base64Exception("Encryption error !", e);
		}
	}

	public static String decryptBASE64(String key) {
		try {
			return URLDecoder.decode(new String(Base64.getDecoder().decode(key), "utf-8"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("", e);
			throw new Base64Exception("Decryption error or The Cookie was tampered with !", e);
		}
	}
}
