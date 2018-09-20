package com.melonltd.naber.background.job;

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

public class OrderNotifyListener implements ServletContextListener{
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderNotifyListener.class);
	
	private Scheduler scheduler;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.info("start Order Notify Job Schedule --> " + Tools.getNowGMT());
		JobDetail job = JobBuilder.newJob(OrderNotifyJob.class)
						.withIdentity("OrderNotifyJob", "group2")
						.build();
		
		Trigger trigger = TriggerBuilder.newTrigger()
						.withIdentity("OrderNotifyJob", "group2")
						.withSchedule(CronScheduleBuilder.cronSchedule("1 * * * * ?"))
						.build();
		try {
			scheduler = ((StdSchedulerFactory) sce.getServletContext()
					.getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY))
					.getScheduler();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			LOGGER.error("start Order Notify Job Schedule fail", e.getMessage());
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
