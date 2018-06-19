package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.RestaurantInfo;
import com.melonltd.naber.rdbms.model.bean.RestaurantLocationTemplate;

public class RestaurantInfoVo implements Serializable {
	private static final long serialVersionUID = -1332259920125957056L;

	private String restaurant_uuid;
	private String name;
	private String address;
	private String store_start;
	private String store_end;
	private String is_store_now_open;
	private List<String> not_business;
	private List<DateRangeVo> can_store_range;
	private String restaurant_category;
	private String latitude;
	private String longitude;
	private String bulletin;
	private String photo;
	private String background_photo;
	private String photo_type;
	private String create_date;
	private String enable;
	private String top;
	
	private String distance;

	
	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getIs_store_now_open() {
		return is_store_now_open;
	}

	public void setIs_store_now_open(String is_store_now_open) {
		this.is_store_now_open = is_store_now_open;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getRestaurant_uuid() {
		return restaurant_uuid;
	}

	public void setRestaurant_uuid(String restaurant_uuid) {
		this.restaurant_uuid = restaurant_uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStore_start() {
		return store_start;
	}

	public void setStore_start(String store_start) {
		this.store_start = store_start;
	}

	public String getStore_end() {
		return store_end;
	}

	public void setStore_end(String store_end) {
		this.store_end = store_end;
	}

	public List<String> getNot_business() {
		return not_business;
	}

	public void setNot_business(List<String> not_business) {
		this.not_business = not_business;
	}

	public List<DateRangeVo> getCan_store_range() {
		return can_store_range;
	}

	public void setCan_store_range(List<DateRangeVo> can_store_range) {
		this.can_store_range = can_store_range;
	}

	public String getRestaurant_category() {
		return restaurant_category;
	}

	public void setRestaurant_category(String restaurant_category) {
		this.restaurant_category = restaurant_category;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getBulletin() {
		return bulletin;
	}

	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBackground_photo() {
		return background_photo;
	}

	public void setBackground_photo(String background_photo) {
		this.background_photo = background_photo;
	}

	public String getPhoto_type() {
		return photo_type;
	}

	public void setPhoto_type(String photo_type) {
		this.photo_type = photo_type;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}
	
//	public static RestaurantInfoVo valueOf (RestaurantInfo info) {
//		RestaurantInfoVo vo = new RestaurantInfoVo(); 
//		vo.restaurant_uuid = info.getRestaurantUUID();
//		vo.name = info.getName();
//		vo.address = info.getAddress();
//		vo.store_start = info.getStoreStart();
//		vo.store_end = info.getStoreEnd();
//		vo.not_business = info.getNotBusiness();
//		vo.can_store_range = info.getCanStoreRange();
//		vo.restaurant_category = info.getRestaurantCategory();
//		vo.latitude = info.getLatitude();
//		vo.longitude = info.getLongitude();
//		vo.bulletin = info.getBulletin();
//		vo.photo = info.getPhoto();
//		vo.background_photo = info.getBackgroundPhoto();
//		vo.photo_type = info.getPhotoType();
//		vo.enable = info.getEnable();
//		vo.top = info.getTop();
//		return vo;
//	}

	public static RestaurantInfoVo valueOf(RestaurantInfo info) {
		RestaurantInfoVo vo = checkIsStoreOpen(info);
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.name = info.getName();
		vo.address = info.getAddress();
		vo.store_start = info.getStoreStart();
		vo.store_end = info.getStoreEnd();
//		vo.is_store_now_open = checkIsStoreOpen(info) + "";
//		vo.not_business = JsonHelper.jsonArray(info.getNotBusiness(), String[].class);
//		vo.can_store_range = JsonHelper.jsonArray(info.getCanStoreRange(),DateRangeVo[].class);
		vo.restaurant_category = info.getRestaurantCategory();
		vo.latitude = info.getLatitude();
		vo.longitude = info.getLongitude();
		vo.bulletin = info.getBulletin();
		vo.photo = info.getPhoto();
		vo.enable = info.getEnable();
		vo.background_photo = info.getBackgroundPhoto();
		vo.create_date = info.getCreateDate();
		vo.top = info.getTop();
//		if (ObjectUtils.allNotNull(start)) {
//			double distance = Tools.getGoogleDistance(start, LatLngVo.of(info.getLatitude(), info.getLongitude()));
//			vo.distance = conversionFrom(distance);
//		}
		return vo;
	}
	
	public static List<RestaurantInfoVo> valueOfArray(List<RestaurantInfo> infos) {
		List<RestaurantInfoVo> vos = Lists.<RestaurantInfoVo>newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}
	
	public static RestaurantInfoVo valueOfTemplate(RestaurantLocationTemplate info) {
		RestaurantInfoVo vo = new RestaurantInfoVo(); 
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.latitude = info.getLatitude();
		vo.longitude = info.getLongitude();
		return vo;
	}

	public static List<RestaurantInfoVo> valueOfTemplateArray(List<RestaurantLocationTemplate> infos) {
		List<RestaurantInfoVo> vos = Lists.<RestaurantInfoVo>newArrayList();
		vos.addAll(infos.stream().map(a -> valueOfTemplate(a)).collect(Collectors.toList()));
		return vos;
	}
	
	
	
	private static String conversionFrom (double distance) {
		double d = 0;
		d = distance/ 1000;
		if (d < 0) {
			return "";
		}
		String result = Tools.decimalFormat("0.0", d);
		return (result.equals("0.0") ? "0.1" : result) + "公里";
	}
	
	private static RestaurantInfoVo checkIsStoreOpen ( RestaurantInfo info) {
		RestaurantInfoVo vo = new RestaurantInfoVo ();
		String now = Tools.getGMTDate("HH:mm");
		boolean c1 =Tools.checkOpenStore(info.getStoreStart(), info.getStoreEnd(),now); 
		int nowUTC = Tools.getDayOfYear(Instant.now().toString(), "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		List<String> notBusiness = JsonHelper.jsonArray(info.getNotBusiness(), String[].class).stream().filter(a -> {
							return Tools.getDayOfYear(a, "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'") == nowUTC;
						}).collect(Collectors.toList());
		List<DateRangeVo> canStoreRange = Tools.checkOpenStoreByRanges(info.getCanStoreRange() ,now);
		vo.not_business = notBusiness;
		vo.can_store_range = canStoreRange;
		vo.is_store_now_open = (c1 && canStoreRange.size() == 0 && notBusiness.size() == 0) +"";
		return vo;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("restaurant_uuid", restaurant_uuid)
				.add("name", name)
				.add("address", address)
				.add("store_start", store_start)
				.add("store_end", store_end)
				.add("not_business", not_business)
				.add("can_store_range", can_store_range)
				.add("restaurant_category", restaurant_category)
				.add("latitude", latitude)
				.add("longitude", longitude)
				.add("bulletin", bulletin)
				.add("photo", photo)
				.add("background_photo", background_photo)
				.add("photo_type", photo_type)
				.add("enable", enable)
				.add("top", top)
				.toString();
	}

}
