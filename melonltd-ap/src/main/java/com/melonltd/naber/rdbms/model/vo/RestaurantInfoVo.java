package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class RestaurantInfoVo implements Serializable{
	private static final long serialVersionUID = -1332259920125957056L;
	
	
	private String search_type;
	private String area;
	private String page;
	private String restaurant_category;
	private String top_limit;
	
	private String restaurant_uuid;
	private String name;
	private String address;
	private String store_start;
	private String store_end;
	private String not_business;
	private String can_store_range;
	private String latitude;
	private String longitude;
	private String bulletin;
	private String photo;
	private String photo_type;
	private String enable;
	private String top;
	
	
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRestaurant_category() {
		return restaurant_category;
	}
	public void setRestaurant_category(String restaurant_category) {
		this.restaurant_category = restaurant_category;
	}
	public String getTop_limit() {
		return top_limit;
	}
	public void setTop_limit(String top_limit) {
		this.top_limit = top_limit;
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
	public String getNot_business() {
		return not_business;
	}
	public void setNot_business(String not_business) {
		this.not_business = not_business;
	}
	public String getCan_store_range() {
		return can_store_range;
	}
	public void setCan_store_range(String can_store_range) {
		this.can_store_range = can_store_range;
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
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("search_type", search_type)
				.add("area", area)
				.add("page", page)
				.add("restaurant_category", restaurant_category)
				.add("top_limit", top_limit)
				.add("restaurant_uuid", restaurant_uuid)
				.add("name", name)
				.add("address", address)
				.add("store_start", store_start)
				.add("store_end", store_end)
				.add("not_business", not_business)
				.add("can_store_range", can_store_range)
				.add("latitude", latitude)
				.add("longitude", longitude)
				.add("bulletin", bulletin)
				.add("photo", photo)
				.add("photo_type", photo_type)
				.add("enable", enable)
				.add("top", top)
				.toString();
	}
	
}
