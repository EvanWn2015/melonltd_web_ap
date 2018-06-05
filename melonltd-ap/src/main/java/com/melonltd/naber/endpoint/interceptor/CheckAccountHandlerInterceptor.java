package com.melonltd.naber.endpoint.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.type.Identity;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.ResponseData;
import com.melonltd.naber.rdbms.model.vo.ResponseData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.ResponseData.Status;

public class CheckAccountHandlerInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckAccountHandlerInterceptor.class);
	
	private static List<Identity> userIdentitys = Identity.getUserEnumValues();
	
	@Autowired
	private AccountInfoService accountInfoService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 如果限制的 RESTful API 沒帶入header
		String uuid = request.getHeader("Authorization");
		if (StringUtils.isAllBlank(uuid)) {
			return false;
		}
		
		AccountInfoVo infoVo = accountInfoService.getCacheBuilderByKey(uuid);

		// 如果沒有登入過使用API
		if (!ObjectUtils.allNotNull(infoVo)) {
			sendError(response);
			return false;
		}

		// 如果已經登入過未登出。
		if ("1".equals(infoVo.getIs_login()) && userIdentitys.contains(Identity.of(infoVo.getIdentity()))) {
			sendError(response);
			return false;
		}

		// 如果登入時間超過7天
//		long now = Tools.getMinutes(Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));
//		long loginDate = Tools.getMinutes(infoVo.getLogin_date());
//		long day_7 = 1000 * 60 * 60 * 24 * 7L;
//		if (now - loginDate > day_7) {
//			sendError(response);
//			return false;
//		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("");

	}

	public void sendError(HttpServletResponse response) throws Exception {
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		LinkedHashMap<String, Object> map = null;
		map = ResponseData.of(Status.FALSE, ErrorType.HEADESR_ERROR, "");
		String result = Base64Service.encode(JsonHelper.toJson(map));
		response.getWriter().write(result);
	}

	private static Map<String, String> getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

}
