package com.melonltd.naber.rdbms.model.push.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.vo.NotificationVo;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.ApnsService;

@Service("apnsPushServcie")
@PropertySource("classpath:/config.properties")
public class APNSPushServcie {
	private static final Logger LOGGER = LoggerFactory.getLogger(APNSPushServcie.class);

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
	
	public void push(String device_token, NotificationVo notificationVo) {
		Map<String, String> data = notificationVo.getData();
		String payload = APNS.newPayload()
							.sound("default")
							.alertTitle(data.get("title"))
							.alertBody(data.get("message"))
							.customFields(data)
							.build();
			ApnsNotification notify = getInstance().push(device_token, payload);
			LOGGER.info("The message has been hopefully sent..., notify:{}", notify);
	}
	
	public void pushs(List<NotificationVo> notificationVos) {
		for (NotificationVo a : notificationVos) {
			try {
				push(a.getTo(), a);
			}catch (RuntimeException e) {
				LOGGER.info("The message sent error... token:{}", a.getTo());
			}
		}		
	}
}
