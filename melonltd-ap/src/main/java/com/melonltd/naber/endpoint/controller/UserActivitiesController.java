package com.melonltd.naber.endpoint.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.core.Is;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.tags.form.AbstractDataBoundFormElementTag;

import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.mchange.lang.IntegerUtils;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.OrderCheckHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.ActivitiesLog;
import com.melonltd.naber.rdbms.model.facade.service.SubmitOrderService;
import com.melonltd.naber.rdbms.model.push.service.PushService;
import com.melonltd.naber.rdbms.model.req.vo.OredeSubimtReq;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.ActivitiesLogService;
import com.melonltd.naber.rdbms.model.service.ActivitiesService;
import com.melonltd.naber.rdbms.model.service.CategoryRelService;
import com.melonltd.naber.rdbms.model.service.FoodInfoSerice;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.Identity;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.type.UUIDType;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.ActivitiesLogVo;
import com.melonltd.naber.rdbms.model.vo.ActivitiesVo;
import com.melonltd.naber.rdbms.model.vo.FoodInfoVo;
import com.melonltd.naber.rdbms.model.vo.OrderVo;
import com.melonltd.naber.rdbms.model.vo.PushFCMVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class UserActivitiesController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserActivitiesController.class);

	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
	private ActivitiesService activitiesService;
	
	@Autowired
	private ActivitiesLogService activitiesLogService;
	@Autowired
	private FoodInfoSerice foodInfoSerice;
	@Autowired
	private RestaurantInfoService restaurantInfoService;
	@Autowired
	private PushService pushService;
	@Autowired
	private SubmitOrderService submitOrderService;
	@Autowired
	private CategoryRelService restaurantCategoryRelService;
	
//	@Autowired
//	private UserOrderInfoService userOrderInfoService;
	
	// 提交兌換信息
	@ResponseBody
	@PostMapping(value = "activities/submit")
	public ResponseEntity<String> submitActivities(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false); 
		ActivitiesVo vo =  activitiesService.findByUUID(req.getUuid());
		LinkedHashMap<String, Object> map = null;
		
		int useBonus = IntegerUtils.parseInt(account.getUse_bonus(), 0);
		int bonus = IntegerUtils.parseInt(account.getBonus(), 0);
		int needBonus = IntegerUtils.parseInt(vo.getNeed_bonus(), 0);
		
		if (!ObjectUtils.allNotNull(account, vo)) {
			map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, "兌換失敗",null);
		}else if (bonus - useBonus < needBonus) {
			map = RespData.of(Status.FALSE, ErrorType.INSUFFICIENT_BALANCE, "兌換失敗\t紅利點數不足，再多使用naber吧加油",null);
		}else {
			boolean status = accountInfoService.updateUseBonus((useBonus + needBonus) + "", accountUUID);
			if (status) {
				ActivitiesLog log = activitiesLogService.save(accountUUID, vo, req.getData());
				if (ObjectUtils.allNotNull(log)) {
					map = RespData.of(Status.TRUE, null, "兌換成功\tNaber專人將與您聯繫");	
				}else {
					accountInfoService.updateUseBonus(useBonus + "", accountUUID);
					map = RespData.of(Status.FALSE, ErrorType.SERVER_ERROR, null);	
				}
			}else {
				map = RespData.of(Status.FALSE, ErrorType.SERVER_ERROR, null);
			}
		}
		
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	
	
	// 提交序列號後繼續動作
	@ResponseBody
	@PostMapping(value = "serial/number/submit")
	public ResponseEntity<String> submitSerialNumber(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		ActivitiesVo activities = activitiesService.findBySerial(req.getData());
		
		LinkedHashMap<String, Object> map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, "查無兌換項目！",null);
		if (ObjectUtils.allNotNull(activities, req)) {
			if (StringUtils.isNoneBlank(req.getData())) {
				switch (activities.getAct_category()) {
				// 商家活動 
				case "RES_EVENT":
					map = resEvent(account, activities);
					break;
				// 使用者充值紅利
				case "TICKET":
					map = rechargeBouns(account, activities);
					break;
				case "POINT":
					break;
				default:
					break;
				}
			}
		}
		
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	

	// 商家兌換品項
	// 判斷每個帳戶只能兌換幾個
	private LinkedHashMap<String, Object> resEvent(AccountInfoVo account, ActivitiesVo activities) {
		int getCount = IntegerUtils.parseInt(activities.getRestrict_get_count(), 0);
		List<ActivitiesLogVo> logs = activitiesLogService.findByAccountUUIDAndActUUID(account.getAccount_uuid(), activities.getAct_uuid());
		if (getCount == 0 || logs.size() < getCount) {
			return RespData.of(Status.TRUE, null, activities);
		}else {
			return RespData.of(Status.FALSE, ErrorType.ACT_GET_MAX, null);
		}
	} 
	
	// 使用者充值紅利
	// 直接幫 User 充值
	private LinkedHashMap<String, Object> rechargeBouns(AccountInfoVo account, ActivitiesVo vo) {
		List<ActivitiesLogVo> logs = activitiesLogService.findByActUUID(vo.getAct_uuid());
		ErrorType error = checkActivitiesRestrict(vo, logs, account);
		if (ObjectUtils.anyNotNull(error)) {
			return RespData.of(Status.FALSE, error, null);
		}else {
			int actBount = IntegerUtils.parseInt(vo.getData(), 0);
			int userBount = IntegerUtils.parseInt(account.getBonus(), 0);
			String bonus = (actBount + userBount) + "";
			boolean status = accountInfoService.updateBonus(bonus, account.getAccount_uuid());
			if (status) {
				activitiesLogService.save(account.getAccount_uuid(), vo, "");
				return RespData.of(Status.TRUE, null, vo);
			}else {
				return RespData.of(Status.FALSE, ErrorType.ACT_EXCHANGE_ERROR, null);
			}
		}
	} 
	

	// 商家兌換品項提交
	// 判斷活動限制
	@ResponseBody
	@PostMapping(value = "serial/res/event/submit")
	public ResponseEntity<String> submitSerialResEvent(HttpServletRequest httpRequest,
			@RequestParam(value = "data", required = false) String data) {
		String request = Base64Service.decode(data);
		ReqData req = JsonHelper.json(request, ReqData.class);
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo account = accountInfoService.getCacheBuilderByKey(accountUUID, false);

		LinkedHashMap<String, Object> map = null;
		ErrorType errorType = checkRequest(req);
		if (ObjectUtils.anyNotNull(errorType)) {
			map = RespData.of(Status.FALSE, errorType, null);
		} else {
			OredeSubimtReq oredeData = JsonHelper.json(req.getData(), OredeSubimtReq.class);
			ActivitiesVo activitiesVo = activitiesService.findByUUID(req.getUuid());
			RestaurantInfoVo restaurantVo = restaurantInfoService.findByUUID(activitiesVo.getRel_uuid());
			List<ActivitiesLogVo> logs = activitiesLogService.findByActUUID(activitiesVo.getAct_uuid());
			if (!ObjectUtils.allNotNull(restaurantVo, account, activitiesVo)) {
				map = RespData.of(Status.FALSE, ErrorType.DATABASE_NULL, null);
			} else {
				ErrorType restrictError = checkActivitiesRestrict(activitiesVo, logs, account);
				if (ObjectUtils.anyNotNull(restrictError)) {
					map = RespData.of(Status.FALSE, restrictError, null);
				} else {
					String hasMsg = OrderCheckHelper.checkCanOrderTime(oredeData, restaurantVo);
//					List<String> foodUUIDs = oredeData.getOrders().stream().map(a -> a.getItem().getFood_uuid()).distinct().collect(Collectors.toList());
//					List<String> categoryUUIDs = oredeData.getOrders().stream().map(a -> a.getCategory_uuid()).distinct().collect(Collectors.toList());
//					List<FoodInfoVo> foodList = foodInfoSerice.getFoodStatusOpenByUUIDs(foodUUIDs);
//					boolean isItemChange = OrderCheckHelper.checkLiveItemData(oredeData.getOrders(), foodList);
//					int categoryOpens = restaurantCategoryRelService.getStatusByCategoryUUIDs(categoryUUIDs, Enable.Y, Arrays.asList(SwitchStatus.OPEN));
					if (StringUtils.isNoneBlank(hasMsg)) {
						LOGGER.info("Store is close account : {}, uuid:{} ", accountUUID, oredeData.getRestaurant_uuid());
						map = RespData.of(Status.FALSE, ErrorType.STORE_IS_CLOSE, hasMsg, null);
//					} else if (!isItemChange) {
//						LOGGER.info("Store item is change account : {}, uuid:{} ", accountUUID, oredeData.getRestaurant_uuid());
//						map = RespData.of(Status.FALSE, ErrorType.FOOD_ITEM_IS_CHANGE, null);
//					} else if (foodUUIDs.size() != foodList.size()) {
//						LOGGER.info("food item status is close account : {}, food_uuid:{} ", accountUUID, foodUUIDs);
//						map = RespData.of(Status.FALSE, ErrorType.FOOD_ITEM_CLOSE, null);
//					} else if (categoryUUIDs.size() != categoryOpens) {
//						LOGGER.info("category status is close account : {}, food_uuid:{} ", accountUUID, foodUUIDs);
//						map = RespData.of(Status.FALSE, ErrorType.CATEGORY_IS_CLOSE, null);
					} else {
						String orders = JsonHelper.toJson(oredeData);
						OrderVo result = submitOrderService.submitOrder(account, Tools.buildUUID(UUIDType.ORDER), restaurantVo, oredeData, "0", "0", orders, false);
						if (!ObjectUtils.anyNotNull(result)) {
							LOGGER.error("submit order save fail account : {}, uuid:{} ", accountUUID, oredeData.getRestaurant_uuid());
							map = RespData.of(Status.FALSE, ErrorType.ORDER_UNFINISH_MAX, null);
						} else {
							activitiesLogService.save(accountUUID, activitiesVo, orders);
							PushFCMVo notificationVo = new PushFCMVo();
							notificationVo.setNotification(new PushFCMVo.Notify("訂單信息", "您有新訂單！請前往訂單查看！"));
							Map<String, Object> datas = Maps.newHashMap();
							datas.put("identity", Identity.SELLERS.name());
							datas.put("title", "訂單信息");
							datas.put("message", "您有新訂單！請前往訂單查看！");
							notificationVo.setData(datas);
							pushService.pushRemoteMessage(result.getRestaurant_uuid(), notificationVo, false);
							map = RespData.of(Status.TRUE, null, result);
						}
					}
				}
			}
		}

		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	private static ErrorType checkActivitiesRestrict(ActivitiesVo vo, List<ActivitiesLogVo> logs, AccountInfoVo account) {
		
		ErrorType error = null;
		// 判斷 活動有效範圍
		if (!StringUtils.isAllBlank(vo.getMan_date(), vo.getExp_date())) {
			Range<String> range = Range.open(vo.getMan_date(), vo.getExp_date());
			error = range.contains(Tools.getNowGMT()) ? null : ErrorType.ACT_XPIRED;
		}
		
		// 判斷限制領取數量
		if (StringUtils.isNotBlank(vo.getRestrict_get_count())) { 
			int getCount = IntegerUtils.parseInt(vo.getRestrict_get_count(), 0);
			long accountUseCount = logs.stream().filter(a -> a.getAccount_uuid().equals(account.getAccount_uuid())).count();
			if (getCount != 0 && accountUseCount >= getCount) {
				error =  ErrorType.ACT_GET_MAX;
			}
		} 
		
		// 判斷發送數量
		if (StringUtils.isNotBlank(vo.getRestrict_send_count())) { 	
			int sendCount = IntegerUtils.parseInt(vo.getRestrict_send_count(), 0);
			if (sendCount != 0 && logs.size() >= sendCount) {
				error = ErrorType.ACT_SEND_MAX;
			}
		}
		
		return error;
	}
	
	private static ErrorType checkRequest(ReqData req) {
		ErrorType error = null;
		if (!ObjectUtils.anyNotNull(req)) {
			return ErrorType.INVALID;
		}
		if (StringUtils.isAllBlank(req.getUuid(), req.getData())) {
			error = ErrorType.INVALID;
		}
		return error;

	}
	
	
}
