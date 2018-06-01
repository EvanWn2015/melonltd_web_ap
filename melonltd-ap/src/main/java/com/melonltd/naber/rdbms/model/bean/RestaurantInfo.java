package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_info")
public class RestaurantInfo implements Serializable {
	private static final long serialVersionUID = -3292539927161071406L;

	private String restaurantUUID;
	private String accountUUID;
	private String name;
	private String address;
	private String storeStart;
	private String storeEnd;
	private String notBusiness;
	private String canStoreRange;
	private String latitude;
	private String longitude;
	private String bulletin;
	private String photo;
	private String photoType;
	private String enable;

	@Id
	@Column(name = "restaurant_uuid", unique = true, nullable = false)
	public String getRestaurantUUID() {
		return restaurantUUID;
	}

	@Column(name = "account_uuid")
	public String getAccountUUID() {
		return accountUUID;
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

	@Column(name = "photo_type")
	public String getPhotoType() {
		return photoType;
	}

	@Column(name = "enable")
	public String getEnable() {
		return enable;
	}

	public void setRestaurantUUID(String restaurantUUID) {
		this.restaurantUUID = restaurantUUID;
	}

	public void setAccountUUID(String accountUUID) {
		this.accountUUID = accountUUID;
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

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

}
