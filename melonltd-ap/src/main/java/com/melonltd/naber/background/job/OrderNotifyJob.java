package com.melonltd.naber.background.job;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.service.OrderInfoService;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.vo.OrderVo;

@Named("OrderNotifyJob")
public class OrderNotifyJob implements Job {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderNotifyJob.class);
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//		long now = System.currentTimeMillis();
//		System.out.println("now : " + Tools.getNowGMT());
//		System.out.println("range : " + Tools.getStartOfNowMinutes(-5) + " : " + Tools.getEndOfNowMinutes(-5));
		LOGGER.info("now : {}", Tools.getNowGMT());
		LOGGER.info("range : {} : {}", Tools.getStartOfNowMinutes(-5), Tools.getEndOfNowMinutes(-5));
		String start =  Tools.getStartOfNowMinutes(-5);
		String end = Tools.getEndOfNowMinutes(-5);
		OrderStatus.getSellerSearchType();
		List<String> statusIn = Lists.newArrayList();
		statusIn = OrderStatus.getSellerSearchType().stream().map(s -> s.name()).collect(Collectors.toList());
		
		List<OrderVo> list = orderInfoService.findByBetweenCreateDateAndStatusIn(start, end, statusIn);
		
		LOGGER.info("data : {}", JsonHelper.toJson(list));
		
//		LOGGER.info("Do Order Notify Job start: --> {} , dataTime:{}", now, Tools.getNowGMT());
//		LOGGER.info("Do Order Notify Job end: --> {} ", (System.currentTimeMillis() - now) / 1000d);
	}

}
