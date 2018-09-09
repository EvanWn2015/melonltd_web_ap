package com.melonltd.naber.background.job;

import javax.inject.Named;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.melonltd.naber.endpoint.util.Tools;

@Named("OrderNotifyJob")
public class OrderNotifyJob implements Job {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderNotifyJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		long now = System.currentTimeMillis();
		LOGGER.info("Do Order Notify Job start: --> {} , dataTime:{}", now, Tools.getNowGMT());
		LOGGER.info("Do Order Notify Job end: --> {} ", (System.currentTimeMillis() - now) / 1000d);
	}


}
