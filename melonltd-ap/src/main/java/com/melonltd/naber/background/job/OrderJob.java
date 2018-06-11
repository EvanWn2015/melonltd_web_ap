package com.melonltd.naber.background.job;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.melonltd.naber.rdbms.model.push.service.AndroidPushService;

public class OrderJob implements Job {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderJob.class);

	@Autowired
	private AndroidPushService androidPushService;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		LOGGER.info("Do Order Job : --> " + Instant.now(Clock.system(ZoneId.of("Asia/Kuala_Lumpur"))));
		androidPushService.push("fG2lkHazsrc:APA91bHEmarRP8uUT7ibkFaUwvsTXjLjlx2BYoO9cHnXdhFo4GKwPgaTYZfBUVAZNIBU1aU-TrFUrduZU222_w0asfx7SlQ6NZ2IH3SejYXwDRRQOcSgHmMYHDGuhv0e9f-6U3GDVJyJ", "執行背景程式", "時間" + Instant.now());
		// TODO 更新訂單狀態
	}
}
