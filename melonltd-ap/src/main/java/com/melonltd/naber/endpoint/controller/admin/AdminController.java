package com.melonltd.naber.endpoint.controller.admin;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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

import com.google.api.client.util.Lists;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.bean.CategoryRel;
import com.melonltd.naber.rdbms.model.bean.FoodInfo;
import com.melonltd.naber.rdbms.model.bean.MobileDevice;
import com.melonltd.naber.rdbms.model.bean.OrderInfo;
import com.melonltd.naber.rdbms.model.bean.RestaurantInfo;
import com.melonltd.naber.rdbms.model.bean.RestaurantLocationTemplate;
import com.melonltd.naber.rdbms.model.bean.SellerRegistered;
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
import com.melonltd.naber.rdbms.model.type.DeviceCategory;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.Identity;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;
import com.melonltd.naber.rdbms.model.vo.FoodInfoVo;
import com.melonltd.naber.rdbms.model.vo.NotificationVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

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

	@ResponseBody
	@PostMapping(value = "admin/processing/dsevice")
	public ResponseEntity<String> processingDevice(HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String resp = "";
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {

				List<MobileDevice> list = mobileDeviceDao.findAll();
				HashMultimap<String, String> multimap = HashMultimap.create();
				Comparator<MobileDevice> comparator = Comparator.comparing(MobileDevice ::getCreateDate, (d1, d2) -> d2.compareTo(d1));
				List<MobileDevice> userlist = list.stream().filter(m -> StringUtils.contains(m.getAccountUUID(),"USER")).sorted(comparator).collect(Collectors.toList());
				
				userlist.forEach(u ->{
					if (multimap.get(u.getAccountUUID()).isEmpty()) {
						System.out.println("set : "+ u.getAccountUUID() + ":"+ u.getCreateDate());
						multimap.put(u.getAccountUUID(), u.getDeviceToken());
					}else {
						System.out.println(u.getAccountUUID() + ":"+ u.getCreateDate());
					}
				});

				List<String> accounts = list.stream().map(a -> a.getAccountUUID()).distinct().collect(Collectors.toList());
				List<MobileDevice> entities = accounts.stream().map(a -> {
					MobileDevice device = new MobileDevice();
					Set<String> tokens = multimap.get(a);
					device.setAccountUUID(a);
					device.setDeviceCategory(DeviceCategory.ANDROID.name());
					device.setCreateDate(Tools.getNowGMT());
					device.setDeviceUUID(Tools.buildUUID(UUIDType.DEVICE));
					device.setDeviceToken(JsonHelper.toJson(tokens));
					System.out.println("account: " + a + "  ->>> " + tokens.toString());
					return device;
				}).collect(Collectors.toList());
				List<MobileDevice> r_entities = mobileDeviceDao.save(entities);
				mobileDeviceDao.delete(list);
				Map<String, Object> map = Maps.newHashMap();
				 map.put("old", list);
				 map.put("new", r_entities);
				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
				resp = JsonHelper.toJson(maps);
			}
		}
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "admin/order/job")
	public ResponseEntity<String> textOrderJob(HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {

				long now = System.currentTimeMillis();
				LOGGER.info("Do Order Job start: --> {} , dataTime:{}", now, Tools.getNowGMT());

				String start = Tools.getStartOfDayGMT(5, 0);
				String end = Tools.getEndOfPlusDayGMT(-2);
				LOGGER.info("處理資料時段開始:{} , 結束:{}", start, end);
				scheduleOrderService.doOrderJob(start, end);

				System.out.println();
				LOGGER.info("Do Order Job end: --> {} ", (System.currentTimeMillis() - now) / 1000d);

			}
		}

		// System.out.println(smsHttpService.getCreditValue() + "");
		return new ResponseEntity<String>("AAA", HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "admin/push")
	public ResponseEntity<String> textPush(@RequestParam(value = "data", required = false) String data,
			HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {

				List<NotificationVo> notificationVos = Lists.newArrayList();
				for (int i = 0; i < 10; i++) {
					NotificationVo notify = new NotificationVo();
					notify.setTo(data);
					Map<String, String> datas = Maps.newHashMap();
					datas.put("identity", Identity.NON_STUDENT.name());
					datas.put("title", "訂單信息" + i);
					datas.put("message", "測試中文內容" + i);
					notify.setData(datas);
					notificationVos.add(notify);
				}

				androidPushService.pushs(notificationVos);
			}
		}
		return new ResponseEntity<String>("AAA", HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "admin/add/restaurant")
	public ResponseEntity<String> textAddRestaurant(@RequestParam(value = "data", required = false) String data,
			@RequestParam(value = "array", required = false) String array, HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);

		String resp = "";
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
				RestaurantInfoVo req = JsonHelper.json(data, RestaurantInfoVo.class);

				String restaurantUUID = Tools.buildUUID(UUIDType.RESTAURANT);
				String now = Tools.getNowGMT();
				req.setRestaurant_uuid(restaurantUUID);
				req.setCreate_date(now);
				req.setEnable(Enable.Y.name());
				req.setTop("0");
				Integer start = Integer.parseInt(new StringBuffer(req.getStore_start()).deleteCharAt(2).toString());
				Integer end = Integer.parseInt(new StringBuffer(req.getStore_end()).deleteCharAt(2).toString());
				List<DateRangeVo> dataRange = Tools.buildCanStoreRange(start, end);
				req.setCan_store_range(dataRange);

				RestaurantInfo newInfo = restaurantInfoDao.save(newRestaurantInfo(req));
				LOGGER.info("save RestaurantInfoVo : --> {}", newInfo.toString());

				RestaurantLocationTemplate template = new RestaurantLocationTemplate();
				template.setRestaurantUUID(restaurantUUID);
				template.setLatitude(req.getLatitude());
				template.setLongitude(req.getLongitude());
				template.setEnable(Enable.Y.name());
				RestaurantLocationTemplate newTemplate = restaurantLocationTemplateDao.save(template);
				LOGGER.info("new Template : --> {}", newTemplate.toString());

				List<String> categoryNames = JsonHelper.jsonArray(array, String[].class);
				List<CategoryRel> categoryRels = categoryNames.stream().map(a -> {
					CategoryRel rel = new CategoryRel();
					rel.setRestaurantUUID(restaurantUUID);
					rel.setCategoryName(a);
					rel.setCategoryUUID(Tools.buildUUID(UUIDType.RESTAURANT_CATEGORY));
					rel.setEnable(Enable.Y.name());
					rel.setStatus(SwitchStatus.CLOSE.name());
					rel.setCreateDate(now);
					return rel;
				}).collect(Collectors.toList());

				List<CategoryRel> newCategoryRels = categoryRelDao.save(categoryRels);
				LOGGER.info("new CategoryRels : --> {}", newCategoryRels.toString());

				Map<String, Object> map = Maps.newHashMap();
				map.put("餐館", newInfo);
				map.put("餐館模板", newTemplate);
				map.put("餐館系列", newCategoryRels);

				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
				LOGGER.info("RestaurantInfoVo : --> {}", req.toString());
				resp = JsonHelper.toJson(maps);
			}
		}
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "admin/add/categorys")
	public ResponseEntity<String> textAddCategorys(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "array", required = false) String array, HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String resp = "";
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
				String now = Tools.getNowGMT();
				List<String> categoryNames = JsonHelper.jsonArray(array, String[].class);
				List<CategoryRel> categoryRels = categoryNames.stream().map(a -> {
					CategoryRel rel = new CategoryRel();
					rel.setRestaurantUUID(id);
					rel.setCategoryName(a);
					rel.setCategoryUUID(Tools.buildUUID(UUIDType.RESTAURANT_CATEGORY));
					rel.setEnable(Enable.Y.name());
					rel.setStatus(SwitchStatus.CLOSE.name());
					rel.setCreateDate(now);
					return rel;
				}).collect(Collectors.toList());
				List<CategoryRel> newCategoryRels = categoryRelDao.save(categoryRels);
				LOGGER.info("new CategoryRels : --> {}", newCategoryRels.toString());
				Map<String, Object> map = Maps.newHashMap();
				map.put("餐館系列", newCategoryRels);
				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
				resp = JsonHelper.toJson(maps);
			}
		}

		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "admin/add/food/names")
	public ResponseEntity<String> textAddFoodNames(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "data", required = false) String data, HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String resp = "";
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
				String now = Tools.getNowGMT();
				List<FoodInfoVo> foods = JsonHelper.jsonArray(data, FoodInfoVo[].class);
				List<FoodInfo> entities = foods.stream().map(a -> {
					a.setCategory_uuid(id);
					FoodItemVo foodData = new FoodItemVo();
					ItemVo item = new ItemVo();
					item.setName("統一價格");
					item.setPrice(a.getDefault_price());
					foodData.getScopes().add(item);
					a.setFood_data(foodData);
					return newFoodInfo(a, now);
				}).collect(Collectors.toList());

				List<FoodInfo> newFoodInfos = foodInfoDao.save(entities);

				Map<String, Object> map = Maps.newHashMap();
				map.put("系列ID", id);
				map.put("品項", newFoodInfos);
				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
				resp = JsonHelper.toJson(maps);
			}
		}

		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "admin/update/food")
	public ResponseEntity<String> textUpdateFoodNames(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "data", required = false) String data, HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String resp = "";
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {

				FoodInfo entitie = foodInfoDao.findByFoodUUID(id);
				FoodItemVo itemVo = JsonHelper.json(data, FoodItemVo.class);
				Optional<ItemVo> minPriceItem = itemVo.getScopes().stream()
						.collect(Collectors.minBy(Comparator.comparingInt(a -> Integer.parseInt(a.getPrice()))));
				entitie.setDefaultPrice(
						minPriceItem.isPresent() ? minPriceItem.get().getPrice() : entitie.getDefaultPrice());
				entitie.setFoodData(JsonHelper.toJson(itemVo));
				FoodInfo newFoodInfos = foodInfoDao.save(entitie);

				Map<String, Object> map = Maps.newHashMap();
				map.put("品項ID", id);
				map.put("品項內容", newFoodInfos);
				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
				resp = JsonHelper.toJson(maps);
			}
		}

		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "admin/find/all/restaurant")
	public ResponseEntity<String> findAllRestaurant(HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String resp = "";
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {

				List<RestaurantInfo> restaurantInfoList = restaurantInfoDao.findAll();
				List<String> enableList = restaurantInfoList.stream().filter(a -> a.getEnable().equals("Y")).map(a -> {
					return "[" + a.getName() + "] " + a.getRestaurantUUID();
				}).collect(Collectors.toList());

				List<String> disableList = restaurantInfoList.stream().filter(a -> a.getEnable().equals("N")).map(a -> {
					return "[" + a.getName() + "] " + a.getRestaurantUUID();
				}).collect(Collectors.toList());

				Map<String, Object> map = Maps.newHashMap();
				map.put("可用餐館", enableList);
				map.put("不可用餐館", disableList);
				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
				resp = JsonHelper.toJson(maps);
			}
		}
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "admin/find/all/categorys")
	public ResponseEntity<String> findAllCategorys(@RequestParam(value = "id", required = false) String id,
			HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String resp = "";
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {

				List<CategoryRel> categoryRels = categoryRelDao.findAllByRestaurantUUID(id);
				List<String> categorys = categoryRels.stream().map(a -> {
					return "[" + a.getCategoryName() + "]" + a.getCategoryUUID();
				}).collect(Collectors.toList());
				Map<String, Object> map = Maps.newHashMap();
				map.put("餐館ID", id);
				map.put("可用系列", categorys);
				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
				resp = JsonHelper.toJson(maps);
			}
		}
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "admin/find/all/foods")
	public ResponseEntity<String> findAllFoods(@RequestParam(value = "id", required = false) String id,
			HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String resp = "";
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
				List<FoodInfo> foodInfos = foodInfoDao.findBycategoryUUID(id);
				List<String> enableList = foodInfos.stream().filter(a -> a.getEnable().equals("Y")).map(a -> {
					return "[" + a.getFoodName() + "]" + a.getFoodUUID();
				}).collect(Collectors.toList());
				List<String> disableList = foodInfos.stream().filter(a -> a.getEnable().equals("N")).map(a -> {
					return "[" + a.getFoodName() + "]" + a.getFoodUUID();
				}).collect(Collectors.toList());

				Map<String, Object> map = Maps.newHashMap();
				map.put("系列ID", id);
				map.put("可用品項", enableList);
				map.put("不可用品項", disableList);

				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
				resp = JsonHelper.toJson(maps);
			}
		}
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "admin/find/all/registereds")
	public ResponseEntity<String> findAllSelletRegistered(HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		String resp = "";
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
				List<SellerRegistered> sellerRegistereds = sellerRegisteredDao.findAll();

				Map<String, Object> map = Maps.newHashMap();
				map.put("待處理註冊信息", sellerRegistereds);

				LinkedHashMap<String, Object> maps = RespData.of(Status.TRUE, null, map);
				resp = JsonHelper.toJson(maps);
			}
		}
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}
	
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

	private static FoodInfo newFoodInfo(FoodInfoVo vo, String now) {
		FoodInfo info = new FoodInfo();
		info.setCategoryUUID(vo.getCategory_uuid());
		info.setFoodUUID(Tools.buildUUID(UUIDType.FOOD));
		info.setFoodName(vo.getFood_name());
		info.setDefaultPrice(vo.getDefault_price());
		info.setFoodData(JsonHelper.toJson(vo.getFood_data()));
		info.setEnable(Enable.Y.name());
		info.setStatus(SwitchStatus.CLOSE.name());
		info.setCreateDate(now);
		return info;
	}

	private RestaurantInfo newRestaurantInfo(RestaurantInfoVo vo) {
		RestaurantInfo info = new RestaurantInfo();
		info.setRestaurantUUID(vo.getRestaurant_uuid());
		info.setName(vo.getName());
		info.setAddress(vo.getAddress());
		info.setStoreStart(vo.getStore_start());
		info.setStoreEnd(vo.getStore_end());
		info.setNotBusiness("[]");
		info.setCanStoreRange(JsonHelper.toJson(vo.getCan_store_range()));
		info.setRestaurantCategory(vo.getRestaurant_category());
		info.setLatitude(vo.getLatitude());
		info.setLongitude(vo.getLongitude());
		info.setBulletin(vo.getBulletin());
		info.setPhoto(vo.getPhoto());
		info.setCreateDate(vo.getCreate_date());
		info.setBackgroundPhoto(vo.getBackground_photo());
		info.setPhotoType(vo.getPhoto_type());
		info.setEnable(vo.getEnable());
		info.setTop(vo.getTop());
		return info;
	}

}
