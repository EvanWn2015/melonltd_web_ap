package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

@Entity
@Table(name = "restaurant_info")
public class RestaurantInfo implements Serializable {
	private static final long serialVersionUID = -3292539927161071406L;

	private String restaurantUUID;
	private String name;
	private String address;
	private String storeStart;
	private String storeEnd;
	private String notBusiness;
	private String canStoreRange;
	private String restaurantCategory;
	private String canDiscount;
	private String deliveryTypes;
	private String latitude;
	private String longitude;
	private String bulletin;
	private String photo;
	private String createDate;
	private String backgroundPhoto;
	private String photoType;
	private String enable;
	private String top;

	@Id
	@Column(name = "restaurant_uuid", unique = true, nullable = false)
	public String getRestaurantUUID() {
		return restaurantUUID;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	@Column(name = "store_start")
	public String getStoreStart() {
		return storeStart;
	}

	@Column(name = "store_end")
	public String getStoreEnd() {
		return storeEnd;
	}

	@Column(name = "not_business")
	public String getNotBusiness() {
		return notBusiness;
	}

	@Column(name = "can_store_range")
	public String getCanStoreRange() {
		return canStoreRange;
	}

	@Column(name = "restaurant_category")
	public String getRestaurantCategory() {
		return restaurantCategory;
	}
	
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}

	@Column(name = "bulletin")
	public String getBulletin() {
		return bulletin;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return photo;
	}

	@Column(name = "background_photo")
	public String getBackgroundPhoto() {
		return backgroundPhoto;
	}

	@Column(name = "photo_type")
	public String getPhotoType() {
		return photoType;
	}

	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}

	@Column(name = "can_discount")
	public String getCanDiscount() {
		return canDiscount;
	}
	
	@Column(name = "delivery_types")
	public String getDeliveryTypes() {
		return deliveryTypes;
	}
	
	@Column(name = "enable")
	public String getEnable() {
		return enable;
	}
	
	@Column(name = "top")
	public String getTop() {
		return top;
	}

	public void setRestaurantUUID(String restaurantUUID) {
		this.restaurantUUID = restaurantUUID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setStoreStart(String storeStart) {
		this.storeStart = storeStart;
	}

	public void setStoreEnd(String storeEnd) {
		this.storeEnd = storeEnd;
	}

	public void setNotBusiness(String notBusiness) {
		this.notBusiness = notBusiness;
	}

	public void setCanStoreRange(String canStoreRange) {
		this.canStoreRange = canStoreRange;
	}

	public void setRestaurantCategory(String restaurantCategory) {
		this.restaurantCategory = restaurantCategory;
	}

	public void setCanDiscount(String canDiscount) {
		this.canDiscount = canDiscount;
	}
	
	public void setDeliveryTypes(String deliveryTypes) {
		this.deliveryTypes = deliveryTypes;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setBackgroundPhoto(String backgroundPhoto) {
		this.backgroundPhoto = backgroundPhoto;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public void setTop(String top) {
		this.top = top;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("restaurantUUID",restaurantUUID)
				.add("name",name)
				.add("address",address)
				.add("storeStart",storeStart)
				.add("storeEnd",storeEnd)
				.add("notBusiness",notBusiness)
				.add("canStoreRange",canStoreRange)
				.add("canDiscount", canDiscount)
				.add("deliveryTypes", deliveryTypes)
				.add("restaurantCategory", restaurantCategory)
				.add("latitude",latitude)
				.add("longitude",longitude)
				.add("bulletin",bulletin)
				.add("photo",photo)
				.add("createDate", createDate)
				.add("photoType",photoType)
				.add("enable",enable)
				.add("top",top)
				.toString();
	}

}
