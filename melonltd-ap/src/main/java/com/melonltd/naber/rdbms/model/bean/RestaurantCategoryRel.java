package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_category_rel")
public class RestaurantCategoryRel implements Serializable {
	private static final long serialVersionUID = 6997247221789078230L;

	private String categorUUID;
	private String restaurantUUID;
	private String categoryName;
	private String status;
	private String enable;

	@Id
	@Column(name = "category_uui", unique = true, nullable = false)
	public String getCategorUUID() {
		return categorUUID;
	}

	@Column(name = "restaurant_uuid")
	public String getRestaurantUUID() {
		return restaurantUUID;
	}

	@Column(name = "category_name")
	public String getCategoryName() {
		return categoryName;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	@Column(name = "enable")
	public String getEnable() {
		return enable;
	}

	public void setCategorUUID(String categorUUID) {
		this.categorUUID = categorUUID;
	}

	public void setRestaurantUUID(String restaurantUUID) {
		this.restaurantUUID = restaurantUUID;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

}
