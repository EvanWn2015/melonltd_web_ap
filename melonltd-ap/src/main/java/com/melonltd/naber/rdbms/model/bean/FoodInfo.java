package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "food_info")
public class FoodInfo implements Serializable {
	private static final long serialVersionUID = -5381913190890717792L;

	private String foodUUID;
	private String categoryUUID;
	private String foodName;
	private String defaultPrice;
	private String photo;
	private String photoType;
	private String foodData;
	private String status;
	private String enable;
	private String createDate;

	@Id
	@Column(name = "food_uuid", unique = true, nullable = false)
	public String getFoodUUID() {
		return foodUUID;
	}
	
	@Column(name = "category_uuid")
	public String getCategoryUUID() {
		return categoryUUID;
	}

	@Column(name = "default_price")
	public String getDefaultPrice() {
		return defaultPrice;
	}

	@Column(name = "food_name")
	public String getFoodName() {
		return foodName;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return photo;
	}

	@Column(name = "photo_type")
	public String getPhotoType() {
		return photoType;
	}

	@Column(name = "food_data")
	public String getFoodData() {
		return foodData;
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
	
	
	public void setFoodUUID(String foodUUID) {
		this.foodUUID = foodUUID;
	}

	public void setCategoryUUID(String categoryUUID) {
		this.categoryUUID = categoryUUID;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public void setDefaultPrice(String defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public void setFoodData(String foodData) {
		this.foodData = foodData;
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
		return MoreObjects
				.toStringHelper(this.getClass())
				.add("foodUUID",foodUUID)
				.add("categoryUUID",categoryUUID)
				.add("foodName",foodName)
				.add("defaultPrice", defaultPrice)
				.add("photo",photo)
				.add("photoType",photoType)
				.add("foodData",foodData)
				.add("status",status)
				.add("enable",enable)
				.add("createDa", createDate)
				.toString();
	}

}
