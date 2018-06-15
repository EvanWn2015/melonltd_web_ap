package com.melonltd.naber.endpoint.controller.seller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;
@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class SellerSettingController {
	
	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
	private RestaurantInfoService restaurantInfoService;
	
	@ResponseBody
	@PostMapping(value = "seller/setting")
	public ResponseEntity<String> setting(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		RestaurantInfoVo req = JsonHelper.json(request, RestaurantInfoVo.class);

		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		LinkedHashMap<String, Object> map = null;

		ErrorType errorType = checkReqData(req, account);
		
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			RestaurantInfoVo restaurant = restaurantInfoService.findByAccountUUID(accountUUID);
			if (ObjectUtils.allNotNull(restaurant)) {
				RestaurantInfoVo newVo  = null;
				if (!StringUtils.isAnyBlank(req.getStore_start(), req.getStore_end()) && !StringUtils.equals(req.getStore_start(), req.getStore_end()) && (!StringUtils.equals(req.getStore_start(), restaurant.getStore_start()) || !StringUtils.equals(req.getStore_end(), restaurant.getStore_end()))) {
					// TODO update Bulletin --> Store start & end --> build  can_store_range
					Integer start = Integer.parseInt(new StringBuffer(req.getStore_start()).deleteCharAt(2).toString());
					Integer end = Integer.parseInt(new StringBuffer(req.getStore_end()).deleteCharAt(2).toString());
					List<DateRangeVo> canStoreRanges = checkCanStoreRanges(start, end, restaurant.getCan_store_range());
					restaurant.setCan_store_range(canStoreRanges);
					if (StringUtils.isNotBlank(req.getBulletin())) {
						restaurant.setBulletin(req.getBulletin());
					}
					newVo = restaurantInfoService.update(restaurant);
					map = RespData.of(Status.TRUE, null, newVo);
				}else if (StringUtils.isNotBlank(req.getBulletin())) {
					// TODO update Bulletin
					restaurant.setBulletin(req.getBulletin());
					newVo = restaurantInfoService.update(restaurant);
					map = RespData.of(Status.TRUE, null, newVo);
				} else {
					map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
				}
			}else {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "seller/setting/business")
	public ResponseEntity<String> notBusiness(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		RestaurantInfoVo req = JsonHelper.json(request, RestaurantInfoVo.class);

		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		LinkedHashMap<String, Object> map = null;

		ErrorType errorType = checkNotBusinessReqData(req, account);
		
		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		}else {
			RestaurantInfoVo restaurant = restaurantInfoService.findByAccountUUID(accountUUID);
			if (ObjectUtils.allNotNull(restaurant)) {
				String start = Tools.getNowStartOfDayUTC();
				String end = Tools.getEndOfPlusDayUTC(2);
				Range<String> check3day = Range.<String>between(start, end);
				long check = req.getNot_business().stream().filter(b -> !check3day.contains(b)).count();
				if (check > 0L || req.getNot_business().size() == 0 || req.getNot_business().size() > 3) {
					map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
				}else {
					restaurant.setNot_business(req.getNot_business());
					RestaurantInfoVo newVo = restaurantInfoService.update(restaurant);
					if (ObjectUtils.allNotNull(newVo)) {
						map = RespData.of(Status.TRUE, null, "");	
					}else {
						map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
					}
				}
			}else {
				map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	private static List<DateRangeVo> checkCanStoreRanges(Integer start, Integer end, List<DateRangeVo> oldRanges) {
		List<DateRangeVo> ranges = Tools.buildCanStoreRange(start, end);
		List<DateRangeVo> newRanges  = Lists.<DateRangeVo>newArrayList();
		for (DateRangeVo r: ranges) {
			DateRangeVo vo = r;
			for (DateRangeVo o: oldRanges) {
				if (StringUtils.equals(r.getDate(), o.getDate())) {
					vo = o;
					break;
				}
			}
			newRanges.add(vo);
		}
		return newRanges;
	}
	
	private static ErrorType checkReqData (RestaurantInfoVo req, AccountInfoVo account) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(account)) {
			return ErrorType.DATABASE_NULL;
		}
		if (StringUtils.isBlank(account.getRestaurant_uuid())) {
			return ErrorType.DATABASE_NULL;
		}
		return null;
	}
	
	private static ErrorType checkNotBusinessReqData (RestaurantInfoVo req, AccountInfoVo account) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(account)) {
			return ErrorType.DATABASE_NULL;
		}
		if (StringUtils.isAnyBlank(account.getRestaurant_uuid())) {
			return ErrorType.DATABASE_NULL;
		}
		
		if (!ObjectUtils.allNotNull(req.getNot_business())) {
			return ErrorType.INVALID;
		}
		return null;
	}
	
	

}
