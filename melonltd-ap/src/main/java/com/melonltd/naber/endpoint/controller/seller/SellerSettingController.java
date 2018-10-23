package com.melonltd.naber.endpoint.controller.seller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

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
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
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
	@PostMapping(value = "seller/setting/find/restaurant")
	public ResponseEntity<String> info(HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		LinkedHashMap<String, Object> map = null;
		if (!ObjectUtils.allNotNull(account)) {
			map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
		} else {
			RestaurantInfoVo vo = restaurantInfoService.findByAccountUUID(account.getAccount_uuid());
			if (!ObjectUtils.allNotNull(vo)) {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			} else {
				map = RespData.of(Status.TRUE, null, vo);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

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
		} else {
			RestaurantInfoVo restaurant = restaurantInfoService.findByAccountUUID(accountUUID);
			if (ObjectUtils.allNotNull(restaurant)) {
				RestaurantInfoVo newVo = null;
				if (!StringUtils.isAnyBlank(req.getStore_start(), req.getStore_end())
						&& !StringUtils.equals(req.getStore_start(), req.getStore_end())) {
					Integer start = Integer.parseInt(new StringBuffer(req.getStore_start()).deleteCharAt(2).toString());
					Integer end = Integer.parseInt(new StringBuffer(req.getStore_end()).deleteCharAt(2).toString());
					List<DateRangeVo> canStoreRanges = checkCanStoreRanges(start, end, restaurant.getCan_store_range());
					restaurant.setCan_store_range(canStoreRanges);
					restaurant.setStore_start(req.getStore_start());
					restaurant.setStore_end(req.getStore_end());
					if (StringUtils.isBlank(req.getBulletin())) {
						restaurant.setBulletin("");
					}else {
						restaurant.setBulletin(req.getBulletin());
					}
					newVo = restaurantInfoService.update(restaurant);
					map = RespData.of(Status.TRUE, null, newVo);
				} else {
					map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
				}
			} else {
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
		ReqData req = JsonHelper.json(request, ReqData.class);

		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		LinkedHashMap<String, Object> map = null;

		ErrorType errorType = checkNotBusinessReqData(req, account);

		if (ObjectUtils.allNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			RestaurantInfoVo restaurant = restaurantInfoService.findByAccountUUID(accountUUID);
			if (ObjectUtils.allNotNull(restaurant)) {
				String start = Tools.getNowStartOfDayGMT();
				String end = Tools.getEndOfPlusDayGMT(30);
				Range<String> check30day = Range.<String>between(start, end);
				boolean check = !check30day.contains(req.getDate());
				if (check) {
					map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
				} else {
					List<String> newNotBusiness = Lists.newArrayList();
					// 加入不接單時間
					if (SwitchStatus.CLOSE.equals(SwitchStatus.of(req.getStatus()))) {
						restaurant.getNot_business().add(req.getDate());
						newNotBusiness = restaurant.getNot_business().stream().filter(a -> check30day.contains(a)).distinct().collect(Collectors.toList());
						restaurant.setNot_business(newNotBusiness);
					// 刪除不接單時間
					}else if (SwitchStatus.OPEN.equals(SwitchStatus.of(req.getStatus()))) {
						newNotBusiness = restaurant.getNot_business().stream().filter(a -> !StringUtils.equals(req.getDate(), a)).collect(Collectors.toList());
					}
					restaurant.setNot_business(newNotBusiness);
					RestaurantInfoVo newVo = restaurantInfoService.update(restaurant);
					if (ObjectUtils.allNotNull(newVo)) {
						map = RespData.of(Status.TRUE, null, "");
					} else {
						map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
					}
				}
			} else {
				map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
			}
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	private static List<DateRangeVo> checkCanStoreRanges(Integer start, Integer end, List<DateRangeVo> oldRanges) {
		List<DateRangeVo> ranges = Tools.buildCanStoreRange(start, end);
		List<DateRangeVo> newRanges = Lists.<DateRangeVo>newArrayList();
		for (DateRangeVo r : ranges) {
			DateRangeVo vo = r;
			for (DateRangeVo o : oldRanges) {
				if (StringUtils.equals(r.getDate(), o.getDate())) {
					vo = o;
					break;
				}
			}
			newRanges.add(vo);
		}
		return newRanges;
	}

	private static ErrorType checkReqData(RestaurantInfoVo req, AccountInfoVo account) {
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

	private static ErrorType checkNotBusinessReqData(ReqData req, AccountInfoVo account) {
		if (!ObjectUtils.allNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (!ObjectUtils.allNotNull(account)) {
			return ErrorType.DATABASE_NULL;
		}
		if (StringUtils.isAnyBlank(account.getRestaurant_uuid(), req.getStatus())) {
			return ErrorType.DATABASE_NULL;
		}

		if (!ObjectUtils.allNotNull(req.getDate())) {
			return ErrorType.INVALID;
		}
		return null;
	}

}
