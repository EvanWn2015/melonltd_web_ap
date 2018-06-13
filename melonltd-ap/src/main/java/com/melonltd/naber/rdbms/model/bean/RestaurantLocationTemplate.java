package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_location_template")
public class RestaurantLocationTemplate implements Serializable{
	private static final long serialVersionUID = -3761800405209681381L;
	
	private String restaurantUUID;
	private String latitude;
	private String longitude;
	private String enable;
	
	@Id
	@Column(name = "restaurant_uuid", unique = true, nullable = false)
	public String getRestaurantUUID() {
		return restaurantUUID;
	}
	
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}
	
	@Column(name = "enable")
	public String getEnable() {
		return enable;
	}
	
	public void setRestaurantUUID(String restaurantUUID) {
		this.restaurantUUID = restaurantUUID;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

}
