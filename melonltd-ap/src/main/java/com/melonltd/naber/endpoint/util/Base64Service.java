package com.melonltd.naber.endpoint.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Base64Service {
	private static final Logger LOGGER = LoggerFactory.getLogger(Base64Service.class);

	public static String encode(String key) {
		try {
			return Base64.getEncoder().encodeToString(URLEncoder.encode(key, "UTF-8").getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("", e);
			throw new Base64Exception("Encryption error !", e);
		}
	}

	public static String decode(@NotNull String key) {
		try {
			String decode = new String(Base64.getDecoder().decode(key));
			return URLDecoder.decode(new String(Base64.getDecoder().decode(key), "UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("", e);
			throw new Base64Exception("Decryption error or The Cookie was tampered with !", e);
		}
	}

	public static String urlEncode(String key) {
		try {
			return new String(Base64.getUrlEncoder().encode(key.getBytes("UTF-8")), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("", e);
			throw new Base64Exception("Decryption error or The Cookie was tampered with !", e);
		}
	}

	public static String urlDecode(String key) {
		try {
			return new String(Base64.getUrlDecoder().decode(key), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("", e);
			throw new Base64Exception("Decryption error or The Cookie was tampered with !", e);
		}
	}
}
