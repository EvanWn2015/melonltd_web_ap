package com.melonltd.naber.endpoint.interceptor;

import java.util.LinkedHashMap;
import java.util.List;

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
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;

public class CheckAccountHandlerInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckAccountHandlerInterceptor.class);
	
	private static List<Identity> userIdentitys = Identity.getUserEnumValues();
	
	@Autowired
	private AccountInfoService accountInfoService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String uuid = request.getHeader("Authorization");
		if (StringUtils.isAllBlank(uuid)) {
			sendError(response);
			return false;
		}
		
		AccountInfoVo infoVo = accountInfoService.getCacheBuilderByKey(uuid,true);

		if (!ObjectUtils.allNotNull(infoVo)) {
			sendError(response);
			return false;
		}

		if ("1".equals(infoVo.getIs_login()) && userIdentitys.contains(Identity.of(infoVo.getIdentity()))) {
			sendError(response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public void sendError(HttpServletResponse response) throws Exception {
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		LinkedHashMap<String, Object> map = null;
		map = RespData.of(Status.FALSE, ErrorType.HEADESR_ERROR, null);
		String result = Base64Service.encode(JsonHelper.toJson(map));
		response.getWriter().write(result);
	}

}
