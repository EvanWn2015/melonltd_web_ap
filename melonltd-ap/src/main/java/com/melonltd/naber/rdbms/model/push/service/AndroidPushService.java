package com.melonltd.naber.rdbms.model.push.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service("androidPushService")
@PropertySource("classpath:/config.properties")
public class AndroidPushService {

	@Value("${google.fcm.server.key}")
	private static String SERVER_KEY;
    
    public static void push(String title, String message, String device_token) throws IOException {
        String pushMessage = "{\"data\":{\"title\":\"" + title + "\",\"message\":\"" + message + "\"},\"to\":\"" + device_token + "\"}";
        
        // Create connection to send FCM Message request.
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
    }
    
}
