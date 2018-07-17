package com.melonltd.naber.rdbms.model.push.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.vo.NotificationVo;

@Service("androidPushService")
@PropertySource("classpath:/config.properties")
public class AndroidPushService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AndroidPushService.class);

	@Value("${google.fcm.server.key}")
	private String SERVER_KEY;
	private static String serverKey;

//	private static URL url = new URL("https://fcm.googleapis.com/fcm/send");
	
	@PostConstruct
	public void init() {
		this.serverKey = this.SERVER_KEY;
	}

	private static HttpURLConnection getConnection() throws IOException {
		URL url = new URL("https://fcm.googleapis.com/fcm/send");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", "key=" + serverKey);
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		return conn;
	}

//	public void push(String device_token, NotificationVo notificationVo) {
//		try {
//			String pushMessage = JsonHelper.toJson(notificationVo);
//			HttpURLConnection conn = getConnection();
//			OutputStream outputStream = conn.getOutputStream();
//			outputStream.write(pushMessage.getBytes("UTF-8"));
//			LOGGER.info("pusp status ResponseCode: {}, ResponseMessage: {}", conn.getResponseCode(), conn.getResponseMessage());
//		} catch (Exception e) {
//			LOGGER.error("push error , error: {}", e.getMessage());
//		}
//	}

	public void pushs(List<NotificationVo> notificationVos) {
		notificationVos.stream().forEach(a -> {
			try {
				String pushMessage = JsonHelper.toJson(a);
				HttpURLConnection conn = getConnection();
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(pushMessage.getBytes("UTF-8"));
				LOGGER.info("pusp status ResponseCode: {}, ResponseMessage: {}", conn.getResponseCode(), conn.getResponseMessage());
			} catch (Exception e) {
				LOGGER.error("push error , error: {}", e.getMessage());
			}
		});
	}

}
