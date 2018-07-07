package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;
import java.util.PrimitiveIterator.OfDouble;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.melonltd.naber.rdbms.model.vo.CategoryRelVo;

@Entity
@Table(name = "category_rel")
public class CategoryRel implements Serializable {
	private static final long serialVersionUID = 6997247221789078230L;

	private String categoryUUID;
	private String restaurantUUID;
	private String categoryName;
	private String status;
	private String enable;
	private String createDate;

	@Id
	@Column(name = "category_uuid", unique = true, nullable = false)
	public String getCategoryUUID() {
		return categoryUUID;
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

	@Column(name = "create_date")
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCategoryUUID(String categoryUUID) {
		this.categoryUUID = categoryUUID;
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
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("categoryUUID",categoryUUID)
				.add("restaurantUUID",restaurantUUID)
				.add("categoryName",categoryName)
				.add("status",status)
				.add("enable",enable)
				.add("createDate",createDate)
				.toString();
	}

}
