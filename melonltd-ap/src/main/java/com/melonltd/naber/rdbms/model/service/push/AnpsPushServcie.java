package com.melonltd.naber.rdbms.model.service.push;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.ApnsService;

@Service("anpsPushServcie")
@PropertySource("classpath:/config.properties")
public class AnpsPushServcie {

	// private static String DEVICE_TOKEN = "YOUR_DEVICE_TOKEN";
	@Value("${apns.certificate.file.path}")
	private  String PATH_TO_P12_CERT;

	@Value("${apns.certificate.password}")
	private  String CERT_PASSWORD;

	private static ApnsService SERVICE = null;

	public  ApnsService getInstance() {
		if (SERVICE == null) {
			ClassLoader classLoader = getClass().getClassLoader();
			String certFile = classLoader.getResource(PATH_TO_P12_CERT).getFile();
			SERVICE = APNS.newService().withCert(certFile, CERT_PASSWORD).withSandboxDestination().build();
		}
		return SERVICE;
	}

	public void push(String device_token, String title, String message) {
		
		String payload = APNS.newPayload().alertBody("My first notification\nHello, I'm push notification"+ Instant.now())
				.sound("default").build();
		getInstance().push(device_token, payload);
		ApnsNotification notify = getInstance().push(device_token.getBytes(), payload.getBytes());
		System.out.println("The message has been hopefully sent...");
		
	}
}
