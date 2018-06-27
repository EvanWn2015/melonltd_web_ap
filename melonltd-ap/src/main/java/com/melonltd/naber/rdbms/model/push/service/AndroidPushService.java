package com.melonltd.naber.rdbms.model.push.service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.vo.NotificationVo;

@Service("androidPushService")
@PropertySource("classpath:/config.properties")
public class AndroidPushService {

	@Value("${google.fcm.server.key}")
	private String SERVER_KEY;

	public void push(String device_token, String title, String message) {
		// Create connection to send FCM Message request.
		try {
			String pushMessage = "{\"data\":{\"title\":\"" + title + "\",\"message\":\"" + message + "\"},\"to\":\"" + device_token + "\"}";
			URL url = new URL("https://fcm.googleapis.com/fcm/send");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// Send FCM message content.
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(pushMessage.getBytes());
			System.out.println(conn.getResponseCode());
			System.out.println(conn.getResponseMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		// System.out.println(conn.getResponseCode());
		// System.out.println(conn.getResponseMessage());
	}
	
	public void push(String device_token, NotificationVo notificationVo) {
		// Create connection to send FCM Message request.
		try {
			String pushMessage = JsonHelper.toJson(notificationVo);
			URL url = new URL("https://fcm.googleapis.com/fcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// Send FCM message content.
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(pushMessage.getBytes());
			System.out.println(conn.getResponseCode());
			System.out.println(conn.getResponseMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		// System.out.println(conn.getResponseCode());
		// System.out.println(conn.getResponseMessage());
	}

}
