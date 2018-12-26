package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
@Entity
@Table(name = "restaurant_location_template")
public class RestaurantLocationTemplate implements Serializable{
	private static final long serialVersionUID = -3761800405209681381L;
	
	@Id
	@Column(name = "restaurant_uuid", unique = true, nullable = false)
	private String restaurantUUID;
	@Column(name = "latitude")
	private String latitude;
	@Column(name = "longitude")
	private String longitude;
	@Column(name = "enable")
	private String enable;
	
	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
