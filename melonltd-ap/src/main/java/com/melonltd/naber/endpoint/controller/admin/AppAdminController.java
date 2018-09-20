package com.melonltd.naber.endpoint.controller.admin;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.AccountInfo;
import com.melonltd.naber.rdbms.model.bean.BasisContent;
import com.melonltd.naber.rdbms.model.bean.CategoryRel;
import com.melonltd.naber.rdbms.model.bean.RestaurantLocationTemplate;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.service.AccountInfoService;
import com.melonltd.naber.rdbms.model.service.AdvertisementService;
import com.melonltd.naber.rdbms.model.service.BasisContentService;
import com.melonltd.naber.rdbms.model.service.CategoryRelService;
import com.melonltd.naber.rdbms.model.service.RestaurantInfoService;
import com.melonltd.naber.rdbms.model.service.RestaurantLocationTemplateService;
import com.melonltd.naber.rdbms.model.service.SellerOrderFinishService;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.Identity;
import com.melonltd.naber.rdbms.model.type.Level;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.type.UUIDType;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.AdvertisementVo;
import com.melonltd.naber.rdbms.model.vo.CategoryRelVo;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;
import com.melonltd.naber.rdbms.model.vo.OrderVo;
import com.melonltd.naber.rdbms.model.vo.RespData;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;
import com.melonltd.naber.rdbms.model.vo.RespData.Status;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

/**
 * @author evan 
 * 取得全部商家列表
 * 初始化店家模板 
 * 商家隱藏開關 
 * 商家可紅利開關 
 * 刪除 DEMO 訂單 
 * 分析月單位 
 * 每店家訂單狀況資訊 
 * 使用者列表
 */

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class AppAdminController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppAdminController.class);
	
	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private RestaurantInfoService restaurantInfoService;
	
	@Autowired
	private RestaurantLocationTemplateService  restaurantLocationTemplateService;
	
	@Autowired
	private CategoryRelService categoryRelService;
	
	@Autowired
	private BasisContentService basisContentService;
	@Autowired
	private SellerOrderFinishService sellerOrderFinishService;
	
	/**
	 * 取得全部商家列表 
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/restaurant/list")
	public ResponseEntity<String> getRestaurantList(HttpServletRequest httpRequest) {
		String result = ""; 
		if (checkAdminAccount(httpRequest)) {
			List<RestaurantInfoVo> list = restaurantInfoService.findAll();
			LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, list);
			return new ResponseEntity<String>(Base64Service.encode(JsonHelper.toJson(map)), HttpStatus.OK);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	/**
	 * 商家隱藏開關
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/restaurant/hidden/switch")
	public ResponseEntity<String> setRestaurantHiddenSwitch (@RequestParam(value = "data", required = false) String data, HttpServletRequest httpRequest){
		String result = "";
		if (checkAdminAccount(httpRequest)) {
			LinkedHashMap<String, Object> map = null;
			try {
				String request = Base64Service.decode(data);
				ReqData req = JsonHelper.json(request, ReqData.class);
				restaurantLocationTemplateService.updateEnable(req.getStatus(), req.getUuid());
				restaurantInfoService.updateEnable(req.getStatus(), req.getUuid());
				map = RespData.of(Status.TRUE, null, "");
			}catch (Exception e) {
				map = RespData.of(Status.FALSE, ErrorType.UPDATE_ERROR, null);
			}
			result = Base64Service.encode(JsonHelper.toJson(map));
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	/**
	 * 商家可紅利開關 
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/restaurant/can/discount/switch")
	public ResponseEntity<String> setRestaurantCanDiscountSwitch (@RequestParam(value = "data", required = false) String data, HttpServletRequest httpRequest){
		String result = "";
		if (checkAdminAccount(httpRequest)) {
			LinkedHashMap<String, Object> map = null;
			try {
				String request = Base64Service.decode(data);
				ReqData req = JsonHelper.json(request, ReqData.class);
				restaurantInfoService.updateCanDiscount(req.getStatus(), req.getUuid());
				map = RespData.of(Status.TRUE, null, "");
			}catch (Exception e) {
				map = RespData.of(Status.FALSE,  ErrorType.UPDATE_ERROR, null);
			}
			result = Base64Service.encode(JsonHelper.toJson(map));
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	/**
	 * 初始化店家模板
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/restaurant/add")
	public ResponseEntity<String> addRestaurant(@RequestParam(value = "data", required = false) String data, HttpServletRequest httpRequest) {
		
		String result = "";
		if (checkAdminAccount(httpRequest)) {
			LinkedHashMap<String, Object> map = RespData.of(Status.FALSE, ErrorType.INVALID, null);
			String request = Base64Service.decode(data);
			RestaurantTemp req = JsonHelper.json(request, RestaurantTemp.class);
			
			AccountInfoVo oldInfo = accountInfoService.findByAccount(req.account.getAccount());
			if (!ObjectUtils.allNotNull(oldInfo)) {
				// 新增商家
				String restaurantUUID = Tools.buildUUID(UUIDType.RESTAURANT);
				String now = Tools.getNowGMT();
				req.restaurant.setRestaurant_uuid(restaurantUUID);
				req.restaurant.setCreate_date(now);
				req.restaurant.setEnable(Enable.N.name());
				req.restaurant.setTop("0");
				req.restaurant.setDelivery_types(Arrays.asList("OUT"));
				req.restaurant.setNot_business(Lists.<String>newArrayList());
				Integer start = Integer.parseInt(new StringBuffer(req.restaurant.getStore_start()).deleteCharAt(2).toString());
				Integer end = Integer.parseInt(new StringBuffer(req.restaurant.getStore_end()).deleteCharAt(2).toString());
				List<DateRangeVo> dataRange = Tools.buildCanStoreRange(start, end);
				req.restaurant.setCan_store_range(dataRange);

				RestaurantInfoVo newInfo = restaurantInfoService.save(req.restaurant);
				LOGGER.info("save RestaurantInfoVo : --> {}", newInfo.toString());

				
				// 新增商家地理位置模板
				RestaurantLocationTemplate template = new RestaurantLocationTemplate();
				template.setRestaurantUUID(restaurantUUID);
				template.setLatitude(newInfo.getLatitude());
				template.setLongitude(newInfo.getLongitude());
				template.setEnable(Enable.N.name());
				RestaurantLocationTemplate newTemplate = restaurantLocationTemplateService.save(template);
				LOGGER.info("new Template : --> {}", newTemplate.toString());
				
				// 新增商店內種類列表
				List<CategoryRel> categoryRels = req.categorys.stream().map(a -> {
					CategoryRel rel = new CategoryRel();
					rel.setRestaurantUUID(restaurantUUID);
					rel.setCategoryName(a);
					rel.setCategoryUUID(Tools.buildUUID(UUIDType.RESTAURANT_CATEGORY));
					rel.setEnable(Enable.Y.name());
					rel.setStatus(SwitchStatus.CLOSE.name());
					rel.setCreateDate(now);
					return rel;
				}).collect(Collectors.toList());
				
				List<CategoryRelVo> newCategoryRels = categoryRelService.save(categoryRels);
				LOGGER.info("new CategoryRels : --> {}", newCategoryRels.toString());
				
				req.account.setRestaurant_uuid(restaurantUUID);
				req.account.setName(newInfo.getName());
				req.account.setPassword("a123456");
				req.account.setEmail(req.account.getAccount() + "@gmail.com");
				req.account.setAddress(newInfo.getAddress());
				req.account.setPhone("1");
				req.account.setIdentity(Identity.SELLERS.name());
				req.account.setLevel(Level.MANAGE.name());
				req.account.setBonus("0");
				
				AccountInfoVo newAccount = save(req.account, UUIDType.SELLER, Enable.Y);
				LOGGER.info("new Account : --> {}", newAccount.toString());
				map = RespData.of(Status.TRUE, null, RestaurantTemp.of(newInfo, req.categorys, newAccount));
			}else {
				map = RespData.of(Status.FALSE, ErrorType.ACCOUNT_ERROR, null);
			}
			result = Base64Service.encode(JsonHelper.toJson(map));
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	/**
	 * 商家更新，種類、TOP、PHOTO、背景圖 
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/restaurant/update")
	public ResponseEntity<String> updateRestaurant (@RequestParam(value = "data", required = false) String data, HttpServletRequest httpRequest){
		String result = "";
		if (checkAdminAccount(httpRequest)) {
			String request = Base64Service.decode(data);
			RestaurantInfoVo req = JsonHelper.json(request, RestaurantInfoVo.class);
			RestaurantInfoVo vo = restaurantInfoService.findUUIDForAdmin(req.getRestaurant_uuid());
			LinkedHashMap<String, Object> map = null;
			if (ObjectUtils.allNotNull(vo)) {
				vo.setTop(req.getTop());
				vo.setPhoto(req.getPhoto());
				vo.setRestaurant_category(req.getRestaurant_category());
				vo.setBackground_photo(req.getBackground_photo());
				RestaurantInfoVo newVo = restaurantInfoService.update(vo);
				if (ObjectUtils.allNotNull(newVo)) {
					map = RespData.of(Status.TRUE, null, newVo);
				}else {
					map = RespData.of(Status.FALSE,  ErrorType.UPDATE_ERROR, null);	
				}
			}else {
				map = RespData.of(Status.FALSE,  ErrorType.UPDATE_ERROR, null);
			}
			result = Base64Service.encode(JsonHelper.toJson(map));
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	

	/**
	 * 取得餐館種類列表 
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/store/category/list")
	public ResponseEntity<String> getStoreCategorys(HttpServletRequest httpRequest) {
		String result = "";
		if (checkAdminAccount(httpRequest)) {
			BasisContent info = basisContentService.getStoreCategorys();
			List<String> categorys = JsonHelper.jsonArray(info.getContent(), String[].class);
			LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, categorys);
			result = Base64Service.encode(JsonHelper.toJson(map));
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	
	/**
	 *更新商店種類列表 
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/store/categorys/update")
	public ResponseEntity<String> updateStoreCategorys(HttpServletRequest httpRequest, @RequestParam(value = "data", required = false) String data){
		String result = "";
		if (checkAdminAccount(httpRequest)) {
			String request = Base64Service.decode(data);
			List<String> req = JsonHelper.jsonArray(request, String[].class);
			List<String> categorys = req.stream().filter(a -> !StringUtils.isBlank(a)).distinct().collect(Collectors.toList());
			LinkedHashMap<String, Object> map = null;
			BasisContent info = basisContentService.updateStoreCategorys(JsonHelper.toJson(categorys));
			if (ObjectUtils.allNotNull(info)) {
				List<String> resultArray  = JsonHelper.jsonArray(info.getContent(), String[].class);
				map = RespData.of(Status.TRUE, null, resultArray);
			}else {
				map = RespData.of(Status.FALSE,  ErrorType.UPDATE_ERROR, null);
			}
			result = Base64Service.encode(JsonHelper.toJson(map));
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	/**
	 *  取得餐館區域列表
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/store/area/list")
	public ResponseEntity<String> getStoreAreas(HttpServletRequest httpRequest) {
		String result = "";
		if (checkAdminAccount(httpRequest)) {
			BasisContent info = basisContentService.getStoreAreas();
			List<String> areas = JsonHelper.jsonArray(info.getContent(), String[].class);
			LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, areas);
			result = Base64Service.encode(JsonHelper.toJson(map));
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	/**
	 *更新商店區域列表 
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/store/areas/update")
	public ResponseEntity<String> updateStoreAreas(HttpServletRequest httpRequest, @RequestParam(value = "data", required = false) String data){
		String result = "";
		if (checkAdminAccount(httpRequest)) {
			String request = Base64Service.decode(data);
			List<String> req = JsonHelper.jsonArray(request, String[].class);
			List<String> areas = req.stream().filter(a -> !StringUtils.isBlank(a)).distinct().collect(Collectors.toList());
			LinkedHashMap<String, Object> map = null;
			BasisContent info = basisContentService.updateStoreAreas(JsonHelper.toJson(areas));
			if (ObjectUtils.allNotNull(info)) {
				List<String> resultArray  = JsonHelper.jsonArray(info.getContent(), String[].class);
				map = RespData.of(Status.TRUE, null, resultArray);
			}else {
				map = RespData.of(Status.FALSE,  ErrorType.UPDATE_ERROR, null);
			}
			result = Base64Service.encode(JsonHelper.toJson(map));
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	
	/**
	 * 取得全部輪播廣告列表 
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/ad/list")
	public ResponseEntity<String> getAllAds(HttpServletRequest httpRequest){
		String result = ""; 
		if (checkAdminAccount(httpRequest)) {
			List<AdvertisementVo> list = advertisementService.findAll();
			LinkedHashMap<String, Object> map = RespData.of(Status.TRUE, null, list);
			result = Base64Service.encode(JsonHelper.toJson(map));
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	/**
	 * 輪播廣告新增
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/ad/add")
	public ResponseEntity<String> saveAds(HttpServletRequest httpRequest, @RequestParam(value = "data", required = false) String data){
		String result = ""; 
		if (checkAdminAccount(httpRequest)) {
			String request = Base64Service.decode(data);
			AdvertisementVo req = JsonHelper.json(request, AdvertisementVo.class);
			AdvertisementVo vo = advertisementService.save(req);
			LinkedHashMap<String, Object> map = null;
			if (ObjectUtils.allNotNull(vo)) {
				map = RespData.of(Status.TRUE, null, vo);
			}else {
				map = RespData.of(Status.FALSE,  ErrorType.UPDATE_ERROR, null);
			}
			result = Base64Service.encode(JsonHelper.toJson(map));
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	/**
	 * 輪播廣告更新 
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/ad/update")
	public ResponseEntity<String> updateAds(HttpServletRequest httpRequest, @RequestParam(value = "data", required = false) String data){
		String result = ""; 
		if (checkAdminAccount(httpRequest)) {
			String request = Base64Service.decode(data);
			AdvertisementVo req = JsonHelper.json(request, AdvertisementVo.class);
			AdvertisementVo vo = advertisementService.update(req);
			LinkedHashMap<String, Object> map = null;
			if (ObjectUtils.allNotNull(vo)) {
				map = RespData.of(Status.TRUE, null, vo);
			}else {
				map = RespData.of(Status.FALSE,  ErrorType.UPDATE_ERROR, null);
			}
			result = Base64Service.encode(JsonHelper.toJson(map));
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	/**
	 *分析月單位 
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/statistics/by/month")
	public ResponseEntity<String> statisticsByMonth(HttpServletRequest httpRequest, @RequestParam(value = "data", required = false) String data){
		LinkedHashMap<String, Object> map = RespData.of(Status.FALSE,  ErrorType.SERVER_ERROR, null);
		if (checkAdminAccount(httpRequest)) {
			String request = Base64Service.decode(data);
			ReqData req = JsonHelper.json(request, ReqData.class);
			List<OrderVo> vos = sellerOrderFinishService.findFinishByBetweenDate(req.getUuid(), req.getDate());
			map = RespData.of(Status.TRUE, null, vos);
		}
		String result = Base64Service.encode(JsonHelper.toJson(map));
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	
	
	/**
	 * 刪除DEMO訂單 
	 */
	@ResponseBody
	@PostMapping(value = "app/admin/demo/order/data/delete")
	public ResponseEntity<String> deleteDemoOrderData (HttpServletRequest httpRequest){
		if (checkAdminAccount(httpRequest)) {
			
		}
		return new ResponseEntity<String>("AAA", HttpStatus.OK);
	}
	
	
	
	
	
	
	
	

	// 檢查Admin權限 
	private boolean checkAdminAccount(HttpServletRequest httpRequest) {
		String accountUUID = httpRequest.getHeader("Authorization");
		AccountInfoVo accountInfoVo = accountInfoService.getCacheBuilderByKey(accountUUID, false);
		if (ObjectUtils.allNotNull(accountInfoVo)) {
			if (Identity.ADMIN.equals(Identity.of(accountInfoVo.getIdentity()))) {
				return true; 
			}
		}
		return false;
	}
	
	
	public AccountInfoVo save(AccountInfoVo vo, UUIDType type, Enable enable) {
		AccountInfo info = new AccountInfo();
		info.setAccount(vo.getAccount());
		info.setAccountUUID(Tools.buildAccountUUID(type));
		info.setRestaurantUUID(vo.getRestaurant_uuid());
		info.setName(vo.getName());
		info.setPassword(vo.getPassword());
		info.setPhone(vo.getPhone());
		info.setEmail(StringUtils.isBlank(vo.getEmail()) ? "" : vo.getEmail());
		info.setBirthDay(StringUtils.isBlank(vo.getBirth_day()) ? "": vo.getBirth_day());
		info.setAddress(StringUtils.isBlank(vo.getAddress()) ? "":vo.getAddress());
		info.setIdentity(vo.getIdentity());
		info.setSchoolName(vo.getSchool_name());
		info.setLevel(vo.getLevel());
		info.setCreateDate(Tools.getNowGMT());
		info.setUseBonus("0");
		info.setBonus("0");
		info.setEnable(enable.name());
		info.setIsLogin("N");
		return AccountInfoVo.of(accountInfoService.save(info), false);
	}
	
	
	private static class RestaurantTemp{
		private RestaurantInfoVo  restaurant;
		private List<String> categorys;
		private AccountInfoVo account;
		
		 RestaurantTemp (RestaurantInfoVo  restaurant, List<String> categorys, AccountInfoVo account) {
			 this.restaurant = restaurant;
			 this.categorys = categorys;
			 this.account = account;
		 }
		
		private static RestaurantTemp of (RestaurantInfoVo  restaurant, List<String> categorys, AccountInfoVo account) {
			return new RestaurantTemp(restaurant, categorys, account);
		}
	}
	
}
