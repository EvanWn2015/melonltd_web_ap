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
@Table(name = "restaurant_info")
public class RestaurantInfo implements Serializable {
	private static final long serialVersionUID = -3292539927161071406L;

	@Id
	@Column(name = "restaurant_uuid", unique = true, nullable = false)
	private String restaurantUUID;
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
	@Column(name = "store_start")
	private String storeStart;
	@Column(name = "store_end")
	private String storeEnd;
	@Column(name = "not_business")
	private String notBusiness;
	@Column(name = "can_store_range")
	private String canStoreRange;
	@Column(name = "restaurant_category")
	private String restaurantCategory;
	@Column(name = "latitude")
	private String latitude;
	@Column(name = "longitude")
	private String longitude;
	@Column(name = "bulletin")
	private String bulletin;
	@Column(name = "photo")
	private String photo;
	@Column(name = "background_photo")
	private String backgroundPhoto;
	@Column(name = "photo_type")
	private String photoType;
	@Column(name = "create_date")
	private String createDate;
	@Column(name = "can_discount")
	private String canDiscount;
	@Column(name = "delivery_types")
	private String deliveryTypes;
	@Column(name = "enable")
	private String enable;
	@Column(name = "top")
	private String top;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
