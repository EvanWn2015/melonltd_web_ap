package com.melonltd.naber.endpoint.controller.admin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.CategoryRel;
import com.melonltd.naber.rdbms.model.bean.FoodInfo;
import com.melonltd.naber.rdbms.model.bean.OrderInfo;
import com.melonltd.naber.rdbms.model.bean.RestaurantInfo;
import com.melonltd.naber.rdbms.model.bean.SellerRegistered;
import com.melonltd.naber.rdbms.model.dao.AccountInfoDao;
import com.melonltd.naber.rdbms.model.dao.CategoryRelDao;
import com.melonltd.naber.rdbms.model.dao.FoodInfoDao;
import com.melonltd.naber.rdbms.model.dao.MobileDeviceDao;
import com.melonltd.naber.rdbms.model.dao.OrderInfoDao;
import com.melonltd.naber.rdbms.model.dao.RestaurantInfoDao;
import com.melonltd.naber.rdbms.model.dao.RestaurantLocationTemplateDao;
import com.melonltd.naber.rdbms.model.dao.SellerRegisteredDao;
import com.melonltd.naber.rdbms.model.facade.service.ScheduleOrderService;
import com.melonltd.naber.rdbms.model.push.service.AndroidPushService;
import com.melonltd.naber.rdbms.model.req.vo.FoodItemVo;
import com.melonltd.naber.rdbms.model.req.vo.ItemVo;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.type.Delivery;
import com.melonltd.naber.rdbms.model.type.DeviceCategory;
import com.melonltd.naber.rdbms.model.type.Identity;
import com.melonltd.naber.rdbms.model.type.UUIDType;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.PushFCMVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class AdminController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private ScheduleOrderService scheduleOrderService;

	@Autowired
	private AccountInfoService accountInfoService;

	@Autowired
	private AndroidPushService androidPushService;
	
	@Autowired
	private AccountInfoDao accountInfoDao;
	@Autowired
	private RestaurantInfoDao restaurantInfoDao;
	@Autowired
	private FoodInfoDao foodInfoDao;
	@Autowired
	private RestaurantLocationTemplateDao restaurantLocationTemplateDao;
	@Autowired
	private CategoryRelDao categoryRelDao;
	@Autowired
	private SellerRegisteredDao sellerRegisteredDao;
	@Autowired
	private MobileDeviceDao mobileDeviceDao;
	@Autowired
	private OrderInfoDao orderInfoDao;

	
	
//	private static List<DateRangeVo> checkCanStoreRanges(Integer start, Integer end, List<DateRangeVo> oldRanges) {
//		List<DateRangeVo> ranges = Tools.buildCanStoreRange(start, end);
//		List<DateRangeVo> newRanges = Lists.<DateRangeVo>newArrayList();
//		for (DateRangeVo r : ranges) {
//			DateRangeVo vo = r;
//			for (DateRangeVo o : oldRanges) {
//				if (StringUtils.equals(r.getDate(), o.getDate())) {
//					vo = o;
//					break;
//				}
//			}
//			newRanges.add(vo);
//		}
//		return newRanges;
//	}
	
	
	@ResponseBody
	@PostMapping(value = "admin/processing/demand/datas")
	public ResponseEntity<String> processingDemandDatas(@RequestParam(value = "data", required = false) String data,
			HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
				List<FoodInfo> list = foodInfoDao.findByEnableANDStatuss("Y", Lists.newArrayList("OPEN", "CLOSE"));
				list.parallelStream().forEach(a -> {
					FoodItemVo itemVo = JsonHelper.json(a.getFoodData(), FoodItemVo.class);
					itemVo.getDemands().stream().forEach(d ->{
						d.getDatas().stream().forEach(ddd -> {
							if (StringUtils.isBlank(ddd.getPrice())) {
								ddd.setPrice("0");
							}
							if ("clean".equals(data)) {
								ddd.setPrice("");
							}
						});
					});
					a.setFoodData(JsonHelper.toJson(itemVo));
				});
				foodInfoDao.save(list);
			}
			
		}
		return new ResponseEntity<String>("AAA", HttpStatus.OK);
	}
	

	// 複製內容到另一家餐館
//	@ResponseBody
//	@PostMapping(value = "admin/copy/restaurant/detail")
//	public ResponseEntity<String> copyRestaurantDetail(@RequestParam(value = "copy", required = false) String copy,
//			@RequestParam(value = "to", required = false) String to,
//			HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//				LOGGER.info(" copy: {}, to: {}" ,copy, to);
//				
//				// 先刪除複製到達對象下所有種類與品項資料
//				List<CategoryRel> toCatList = categoryRelDao.findAllByRestaurantUUID(to);
//				List<String> toCatUUIDs = toCatList.stream().map(c -> c.getCategoryUUID()).collect(Collectors.toList());
//				List<FoodInfo> toFoodList = foodInfoDao.findBycategoryUUIDs(toCatUUIDs.isEmpty() ? Arrays.asList("") : toCatUUIDs );
//				LOGGER.info("delete to CategoryRels: {}, FoodInfos :{}", toCatList.size(), toFoodList.size());
//				foodInfoDao.delete(toFoodList);
//				categoryRelDao.delete(toCatList);
//
//				
//				List<RestaurantInfo> resList = restaurantInfoDao.findUUIDs(Arrays.asList(copy, to));
//				RestaurantInfo copyRestaurant = resList.stream().filter(r -> r.getRestaurantUUID().equals(copy)).findAny().get();
//				RestaurantInfo toRestaurant = resList.stream().filter(r -> r.getRestaurantUUID().equals(to)).findAny().get();
//				
//				List<CategoryRel> copyCatList = categoryRelDao.findAllByRestaurantUUID(copy);
//				List<String> copyCatUUIDs = copyCatList.stream().map(c -> c.getCategoryUUID()).collect(Collectors.toList());
//				List<FoodInfo> copyFoodList = foodInfoDao.findBycategoryUUIDs(copyCatUUIDs.isEmpty() ? Arrays.asList("") : copyCatUUIDs);
//				
//				LOGGER.info("change copy data CategoryRels: {}, FoodInfos :{} ", copyCatList.size(), copyFoodList.size());
//				
//				copyCatList.stream()
//					.sorted((CategoryRel o1, CategoryRel o2) -> StringUtils.compare(o1.getCreateDate(), o2.getCreateDate()))
//					.peek(c -> System.out.println("\ncp: " + c.getRestaurantUUID() + " : " + c.getCategoryUUID()))
//					.forEach(c -> {
//						String newUUID = Tools.buildUUID(UUIDType.RESTAURANT_CATEGORY);
//						copyFoodList.stream()
////							.sorted((FoodInfo o1, FoodInfo o2) -> StringUtils.compare(o1.getCreateDate(), o2.getCreateDate()))
//							.peek(e -> System.out.println("cp: " + e.getCategoryUUID() + " : " + e.getFoodUUID()))
//							.filter(f -> f.getCategoryUUID().equals(c.getCategoryUUID()))
//							.forEach(f -> {
//								f.setCategoryUUID(newUUID);
//								f.setFoodUUID(Tools.buildUUID(UUIDType.FOOD));
////								f.setCreateDate(Tools.getNowGMT());
//								f.setPhoto(null);
//							});
//						c.setCategoryUUID(newUUID);
//						c.setRestaurantUUID(toRestaurant.getRestaurantUUID());
//						c.setCreateDate(Tools.getNowGMT());
//					});
//				
//				
//				List<FoodInfo> tonewFoodList = foodInfoDao.save(copyFoodList);
//				List<CategoryRel> tonewCatList = categoryRelDao.save(copyCatList);
//				
//				LOGGER.info("to data CategoryRels: {}, FoodInfos :{} ", tonewCatList.size(), tonewFoodList.size());
//				
//			}
//		}
//
//		// System.out.println(smsHttpService.getCreditValue() + "");
//		return new ResponseEntity<String>("AAA", HttpStatus.OK);
//	}
	
//	@ResponseBody
//	@PostMapping(value = "admin/process/restaurant/and/order/delivery/types")
//	public ResponseEntity<String> processRestaurantAndOrderDeliveryTypes(HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			String d = JsonHelper.toJson(Arrays.asList(Delivery.OUT.name()));
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//				List<RestaurantInfo> restaurantInfos = restaurantInfoDao.findAll();
//				restaurantInfos.stream().forEach(r -> r.setDeliveryTypes(d));
//				System.out.println(restaurantInfos.toString());
//				restaurantInfoDao.save(restaurantInfos);
//			}
//		}
//
//		// System.out.println(smsHttpService.getCreditValue() + "");
//		return new ResponseEntity<String>("AAA", HttpStatus.OK);
//	}
	
	
//	@ResponseBody
//	@GetMapping(value = "admin/order/job")
//	public ResponseEntity<String> textOrderJob(HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//				
//				long now = System.currentTimeMillis();
//				LOGGER.info("Do Order Job start: --> {} , dataTime:{}", now, Tools.getNowGMT());
//
//				String start = Tools.getStartOfDayGMT(5, 0);
//				String end = Tools.getEndOfPlusDayGMT(-2);
//				LOGGER.info("處理資料時段開始:{} , 結束:{}", start, end);
//				scheduleOrderService.doOrderJob(start, end);
//
//				System.out.println();
//				LOGGER.info("Do Order Job end: --> {} ", (System.currentTimeMillis() - now) / 1000d);
//			}
//		}
//		return new ResponseEntity<String>("AAA", HttpStatus.OK);
//	}

//	@ResponseBody
//	@PostMapping(value = "admin/push")
//	public ResponseEntity<String> textPush(@RequestParam(value = "data", required = false) String data,
//			HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//				String[] tokens = JsonHelper.jsonArray(data, String[].class).stream().toArray(String[] :: new);
////				List<String> tokenDatas = JsonHelper.jsonArray(data, String[].class);
//				PushFCMVo pushFCMVo = new PushFCMVo();
//				pushFCMVo.setNotification(new PushFCMVo.Notify("ttt title","mmm mseage"));
//				pushFCMVo.setRegistration_ids(tokens);
//				
//				Map<String, Object> datas = Maps.newHashMap();
//				datas.put("identity", Identity.NON_STUDENT.name());
//				datas.put("title", "訂單信息");
//				datas.put("message", "測試中文內容");
//				pushFCMVo.setData(datas);
//				androidPushService.push(pushFCMVo, DeviceCategory.ANDROID);
//			}
//		}
//		return new ResponseEntity<String>("AAA", HttpStatus.OK);
//	}
	
//	@ResponseBody
//	@PostMapping(value = "admin/pushAPNS")
//	public ResponseEntity<String> textPushAPNS(@RequestParam(value = "data", required = false) String data,
//			HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//				
//				List<String> devidTokens = JsonHelper.jsonArray(data, String[].class);
//				PushFCMVo pushFCMVo = new PushFCMVo();
//				pushFCMVo.setTo(data);
//				pushFCMVo.setRegistration_ids(devidTokens.stream().map(t -> t).toArray(String[] :: new));
//				Map<String, Object> datas = Maps.newHashMap();
//				datas.put("identity", Identity.SELLERS.name());
//				datas.put("title", "訂單信息");
//				datas.put("message", "測試中文內容");
//				pushFCMVo.setData(datas);
//				androidPushService.push(pushFCMVo,DeviceCategory.IOS);
//			}
//			
//		}
//		return new ResponseEntity<String>("AAA", HttpStatus.OK);
//	}
//	

//	@ResponseBody
//	@PostMapping(value = "admin/add/restaurant")
//	public ResponseEntity<String> textAddRestaurant(@RequestParam(value = "data", required = false) String data,
//			@RequestParam(value = "array", required = false) String array,
//			@RequestParam(value = "account", required = false) String accountInfo,
//			HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//
//		String resp = "";
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//				RestaurantInfoVo req = JsonHelper.json(data, RestaurantInfoVo.class);
//
//				String restaurantUUID = Tools.buildUUID(UUIDType.RESTAURANT);
//				String now = Tools.getNowGMT();
//				req.setRestaurant_uuid(restaurantUUID);
//				req.setCreate_date(now);
//				req.setEnable(Enable.N.name());
//				req.setTop("0");
//				req.setDelivery_types(Arrays.asList("OUT"));
//				Integer start = Integer.parseInt(new StringBuffer(req.getStore_start()).deleteCharAt(2).toString());
//				Integer end = Integer.parseInt(new StringBuffer(req.getStore_end()).deleteCharAt(2).toString());
//				List<DateRangeVo> dataRange = Tools.buildCanStoreRange(start, end);
//				req.setCan_store_range(dataRange);
//
//				RestaurantInfo newInfo = restaurantInfoDao.save(newRestaurantInfo(req));
//				LOGGER.info("save RestaurantInfoVo : --> {}", newInfo.toString());
//
//				RestaurantLocationTemplate template = new RestaurantLocationTemplate();
//				template.setRestaurantUUID(restaurantUUID);
//				template.setLatitude(req.getLatitude());
//				template.setLongitude(req.getLongitude());
//				template.setEnable(Enable.N.name());
//				RestaurantLocationTemplate newTemplate = restaurantLocationTemplateDao.save(template);
//				LOGGER.info("new Template : --> {}", newTemplate.toString());
//
//				List<String> categoryNames = JsonHelper.jsonArray(array, String[].class);
//				List<CategoryRel> categoryRels = categoryNames.stream().map(a -> {
//					CategoryRel rel = new CategoryRel();
//					rel.setRestaurantUUID(restaurantUUID);
//					rel.setCategoryName(a);
//					rel.setCategoryUUID(Tools.buildUUID(UUIDType.RESTAURANT_CATEGORY));
//					rel.setEnable(Enable.Y.name());
//					rel.setStatus(SwitchStatus.CLOSE.name());
//					rel.setCreateDate(now);
//					return rel;
//				}).collect(Collectors.toList());
//
//				List<CategoryRel> newCategoryRels = categoryRelDao.save(categoryRels);
//				LOGGER.info("new CategoryRels : --> {}", newCategoryRels.toString());
//
//				
//				AccountInfoVo aVo = JsonHelper.json(accountInfo, AccountInfoVo.class);
//				AccountInfo info = new AccountInfo();
//				info.setAccount(aVo.getAccount());
//				info.setPassword("a123456");
//				info.setAccountUUID(Tools.buildUUID(UUIDType.SELLER));
//				info.setRestaurantUUID(restaurantUUID);
//				info.setName(newInfo.getName());
//				info.setEmail(aVo.getAccount() + "@gmail.com");
//				info.setAddress(newInfo.getAddress());
//				info.setPhone("1");
//				info.setIdentity(Identity.SELLERS.name());
//				info.setLevel(Level.MANAGE.name());
//				info.setBonus("0");
//				info.setEnable(Enable.Y.name());
//				info.setIsLogin(Enable.N.name());
//				info.setUseBonus("0");
//				info.setCreateDate(now);
//				AccountInfo newAccount = accountInfoDao.save(info);
//				
//				
//				Map<String, Object> map = Maps.newHashMap();
//				map.put("帳號", newAccount);
//				map.put("餐館", newInfo);
//				map.put("餐館模板", newTemplate);
//				map.put("餐館系列", newCategoryRels);
//
//				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
//				LOGGER.info("RestaurantInfoVo : --> {}", req.toString());
//				resp = JsonHelper.toJson(maps);
//			}
//		}
//		return new ResponseEntity<String>(resp, HttpStatus.OK);
//	}

//	@ResponseBody
//	@PostMapping(value = "admin/add/categorys")
//	public ResponseEntity<String> textAddCategorys(@RequestParam(value = "id", required = false) String id,
//			@RequestParam(value = "array", required = false) String array, HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		String resp = "";
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//				String now = Tools.getNowGMT();
//				List<String> categoryNames = JsonHelper.jsonArray(array, String[].class);
//				List<CategoryRel> categoryRels = categoryNames.stream().map(a -> {
//					CategoryRel rel = new CategoryRel();
//					rel.setRestaurantUUID(id);
//					rel.setCategoryName(a);
//					rel.setCategoryUUID(Tools.buildUUID(UUIDType.RESTAURANT_CATEGORY));
//					rel.setEnable(Enable.Y.name());
//					rel.setStatus(SwitchStatus.CLOSE.name());
//					rel.setCreateDate(now);
//					return rel;
//				}).collect(Collectors.toList());
//				List<CategoryRel> newCategoryRels = categoryRelDao.save(categoryRels);
//				LOGGER.info("new CategoryRels : --> {}", newCategoryRels.toString());
//				Map<String, Object> map = Maps.newHashMap();
//				map.put("餐館系列", newCategoryRels);
//				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
//				resp = JsonHelper.toJson(maps);
//			}
//		}
//
//		return new ResponseEntity<String>(resp, HttpStatus.OK);
//	}

//	@ResponseBody
//	@PostMapping(value = "admin/add/food/names")
//	public ResponseEntity<String> textAddFoodNames(@RequestParam(value = "id", required = false) String id,
//			@RequestParam(value = "data", required = false) String data, HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		String resp = "";
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//				String now = Tools.getNowGMT();
//				List<FoodInfoVo> foods = JsonHelper.jsonArray(data, FoodInfoVo[].class);
//				List<FoodInfo> entities = foods.stream().map(a -> {
//					a.setCategory_uuid(id);
//					FoodItemVo foodData = new FoodItemVo();
//					ItemVo item = new ItemVo();
//					item.setName("統一價格");
//					item.setPrice(a.getDefault_price());
//					foodData.getScopes().add(item);
//					a.setFood_data(foodData);
//					return newFoodInfo(a, now);
//				}).collect(Collectors.toList());
//
//				List<FoodInfo> newFoodInfos = foodInfoDao.save(entities);
//
//				Map<String, Object> map = Maps.newHashMap();
//				map.put("系列ID", id);
//				map.put("品項", newFoodInfos);
//				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
//				resp = JsonHelper.toJson(maps);
//			}
//		}
//
//		return new ResponseEntity<String>(resp, HttpStatus.OK);
//	}

//	@ResponseBody
//	@PostMapping(value = "admin/update/food")
//	public ResponseEntity<String> textUpdateFoodNames(@RequestParam(value = "id", required = false) String id,
//			@RequestParam(value = "data", required = false) String data, HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		String resp = "";
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//
//				FoodInfo entitie = foodInfoDao.findByFoodUUID(id);
//				FoodItemVo itemVo = JsonHelper.json(data, FoodItemVo.class);
//				Optional<ItemVo> minPriceItem = itemVo.getScopes().stream()
//						.collect(Collectors.minBy(Comparator.comparingInt(a -> Integer.parseInt(a.getPrice()))));
//				entitie.setDefaultPrice(
//						minPriceItem.isPresent() ? minPriceItem.get().getPrice() : entitie.getDefaultPrice());
//				entitie.setFoodData(JsonHelper.toJson(itemVo));
//				FoodInfo newFoodInfos = foodInfoDao.save(entitie);
//
//				Map<String, Object> map = Maps.newHashMap();
//				map.put("品項ID", id);
//				map.put("品項內容", newFoodInfos);
//				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
//				resp = JsonHelper.toJson(maps);
//			}
//		}
//
//		return new ResponseEntity<String>(resp, HttpStatus.OK);
//	}

//	@ResponseBody
//	@PostMapping(value = "admin/find/all/restaurant")
//	public ResponseEntity<String> findAllRestaurant(HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		String resp = "";
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//
//				List<RestaurantInfo> restaurantInfoList = restaurantInfoDao.findAll();
//				List<String> enableList = restaurantInfoList.stream().filter(a -> a.getEnable().equals("Y")).map(a -> {
//					return "[" + a.getName() + "] " + a.getRestaurantUUID();
//				}).collect(Collectors.toList());
//
//				List<String> disableList = restaurantInfoList.stream().filter(a -> a.getEnable().equals("N")).map(a -> {
//					return "[" + a.getName() + "] " + a.getRestaurantUUID();
//				}).collect(Collectors.toList());
//
//				Map<String, Object> map = Maps.newHashMap();
//				map.put("可用餐館", enableList);
//				map.put("不可用餐館", disableList);
//				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
//				resp = JsonHelper.toJson(maps);
//			}
//		}
//		return new ResponseEntity<String>(resp, HttpStatus.OK);
//	}

//	@ResponseBody
//	@PostMapping(value = "admin/find/all/categorys")
//	public ResponseEntity<String> findAllCategorys(@RequestParam(value = "id", required = false) String id,
//			HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		String resp = "";
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//
//				List<CategoryRel> categoryRels = categoryRelDao.findAllByRestaurantUUID(id);
//				List<String> categorys = categoryRels.stream().map(a -> {
//					return "[" + a.getCategoryName() + "]" + a.getCategoryUUID();
//				}).collect(Collectors.toList());
//				Map<String, Object> map = Maps.newHashMap();
//				map.put("餐館ID", id);
//				map.put("可用系列", categorys);
//				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
//				resp = JsonHelper.toJson(maps);
//			}
//		}
//		return new ResponseEntity<String>(resp, HttpStatus.OK);
//	}

//	@ResponseBody
//	@PostMapping(value = "admin/find/all/foods")
//	public ResponseEntity<String> findAllFoods(@RequestParam(value = "id", required = false) String id,
//			HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		String resp = "";
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//				List<FoodInfo> foodInfos = foodInfoDao.findBycategoryUUID(id);
//				List<String> enableList = foodInfos.stream().filter(a -> a.getEnable().equals("Y")).map(a -> {
//					return "[" + a.getFoodName() + "]" + a.getFoodUUID();
//				}).collect(Collectors.toList());
//				List<String> disableList = foodInfos.stream().filter(a -> a.getEnable().equals("N")).map(a -> {
//					return "[" + a.getFoodName() + "]" + a.getFoodUUID();
//				}).collect(Collectors.toList());
//
//				Map<String, Object> map = Maps.newHashMap();
//				map.put("系列ID", id);
//				map.put("可用品項", enableList);
//				map.put("不可用品項", disableList);
//
//				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
//				resp = JsonHelper.toJson(maps);
//			}
//		}
//		return new ResponseEntity<String>(resp, HttpStatus.OK);
//	}

//	@ResponseBody
//	@PostMapping(value = "admin/find/all/registereds")
//	public ResponseEntity<String> findAllSelletRegistered(HttpServletRequest httpRequest) {
//		String accountUUID = httpRequest.getHeader("Authorization");
//		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
//		String resp = "";
//		if (ObjectUtils.allNotNull(accountInfoVo)) {
//			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
//				List<SellerRegistered> sellerRegistereds = sellerRegisteredDao.findAll();
//
//				Map<String, Object> map = Maps.newHashMap();
//				map.put("待處理註冊信息", sellerRegistereds);
//
//				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
//				resp = JsonHelper.toJson(maps);
//			}
//		}
//		return new ResponseEntity<String>(resp, HttpStatus.OK);
//	}
	
	@ResponseBody
	@PostMapping(value = "admin/find/all/orders")
	public ResponseEntity<String> findAllOrders(HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String resp = "";
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
				String startDate = Tools.getNowStartOfDayGMT();
				String endDate = Tools.getNowEndOfDayGMT();
				List<OrderInfo> orders = orderInfoDao.findByBetweenDate(startDate, endDate);
				Map<String, Object> map = Maps.newHashMap();
				
				map.put("開始", startDate);
				map.put("結束", endDate);
				map.put("訂單", orders);

				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
				resp = JsonHelper.toJson(maps);
			}
		}
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

//	private static FoodInfo newFoodInfo(FoodInfoVo vo, String now) {
//		FoodInfo info = new FoodInfo();
//		info.setCategoryUUID(vo.getCategory_uuid());
//		info.setFoodUUID(Tools.buildUUID(UUIDType.FOOD));
//		info.setFoodName(vo.getFood_name());
//		info.setDefaultPrice(vo.getDefault_price());
//		info.setFoodData(JsonHelper.toJson(vo.getFood_data()));
//		info.setEnable(Enable.Y.name());
//		info.setStatus(SwitchStatus.CLOSE.name());
//		info.setCreateDate(now);
//		return info;
//	}
//
//	private RestaurantInfo newRestaurantInfo(RestaurantInfoVo vo) {
//		RestaurantInfo info = new RestaurantInfo();
//		info.setRestaurantUUID(vo.getRestaurant_uuid());
//		info.setName(vo.getName());
//		info.setAddress(vo.getAddress());
//		info.setStoreStart(vo.getStore_start());
//		info.setStoreEnd(vo.getStore_end());
//		info.setNotBusiness("[]");
//		info.setDeliveryTypes(JsonHelper.toJson(vo.getDelivery_types()));
//		info.setCanStoreRange(JsonHelper.toJson(vo.getCan_store_range()));
//		info.setRestaurantCategory(vo.getRestaurant_category());
//		info.setLatitude(vo.getLatitude());
//		info.setLongitude(vo.getLongitude());
//		info.setBulletin(vo.getBulletin());
//		info.setPhoto(vo.getPhoto());
//		info.setCreateDate(vo.getCreate_date());
//		info.setBackgroundPhoto(vo.getBackground_photo());
//		info.setPhotoType(vo.getPhoto_type());
//		info.setEnable(vo.getEnable());
//		info.setTop(vo.getTop());
//		return info;
//	}

}
