package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "category_food_rel")
public class CategoryFoodRel implements Serializable {
	private static final long serialVersionUID = -5381913190890717792L;

	private String foodUUID;
	private String categoryUUID;
	private String foodName;
	private String photo;
	private String photoType;
	private String foodData;
	private String status;
	private String enable;

	@Id
	@Column(name = "food_uuid", unique = true, nullable = false)
	public String getFoodUUID() {
		return foodUUID;
	}

	@Column(name = "category_uuid")
	public String getCategoryUUID() {
		return categoryUUID;
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

	public void setFoodUUID(String foodUUID) {
		this.foodUUID = foodUUID;
	}

	public void setCategoryUUID(String categoryUUID) {
		this.categoryUUID = categoryUUID;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
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
	
	@Override
	public String toString() {
		return MoreObjects
				.toStringHelper(this.getClass())
				.add("foodUUID",foodUUID)
				.add("categoryUUID",categoryUUID)
				.add("foodName",foodName)
				.add("photo",photo)
				.add("photoType",photoType)
				.add("foodData",foodData)
				.add("status",status)
				.add("enable",enable)
				.toString();
	}

}
