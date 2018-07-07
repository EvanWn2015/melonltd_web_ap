package com.melonltd.naber.background.job;

import java.time.ZoneId;
import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.melonltd.naber.endpoint.util.Tools;

public class ScheduleListener implements ServletContextListener{
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleListener.class);
	
	private Scheduler scheduler;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.info("start Order Job Schedule --> " + Tools.getNowGMT());
		JobDetail job = JobBuilder.newJob(OrderJob.class)
						.withIdentity("OrderJob", "group1")
						.build();
		
		// 每月一號 上午 00:00:00 開始 [0 0 0 1 1/1 ? *]
		// 0 0 12 * * ? 每日12點執行
//		.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
		Trigger trigger = TriggerBuilder.newTrigger()
						.withIdentity("OrderJob", "group1")
//						.withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?"))
						.withSchedule(CronScheduleBuilder
								.dailyAtHourAndMinute(0,0)
								.inTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Kuala_Lumpur"))))
						.build();
		try {
			scheduler = ((StdSchedulerFactory) sce.getServletContext()
					.getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY))
					.getScheduler();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			LOGGER.error("start Order Schedule fail", e.getMessage());
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
	
}
