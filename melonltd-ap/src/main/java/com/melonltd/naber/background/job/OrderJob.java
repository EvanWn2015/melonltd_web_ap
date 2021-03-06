package com.melonltd.naber.background.job;

import javax.inject.Named;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.facade.service.ScheduleOrderService;

@Named("OrderJob")
public class OrderJob implements Job {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderJob.class);

	@Autowired
	private ScheduleOrderService scheduleOrderService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		long now = System.currentTimeMillis();
		LOGGER.info("Do Order Job start: --> {} , dataTime:{}", now, Tools.getNowGMT());
		String start = Tools.getStartOfDayGMT(2, 0);
		String end = Tools.getEndOfPlusDayGMT(-2);
		LOGGER.info("Processing interval,  start:{} , end:{}", start, end);
		scheduleOrderService.doOrderJob(start, end);
		LOGGER.info("Do Order Job end: --> {} ", (System.currentTimeMillis() - now) / 1000d);
	}
}
