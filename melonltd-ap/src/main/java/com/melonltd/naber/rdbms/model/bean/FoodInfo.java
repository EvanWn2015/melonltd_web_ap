package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.vo.FoodInfoVo;

import lombok.Data;

@Data
@Entity
@Table(name = "food_info")
public class FoodInfo implements Serializable {
	private static final long serialVersionUID = -5381913190890717792L;

	@Id
	@Column(name = "food_uuid", unique = true, nullable = false)
	private String foodUUID;
	@Column(name = "category_uuid")
	private String categoryUUID;
	@Column(name = "food_name")
	private String foodName;
	@Column(name = "food_content")
	private String foodContent;
	@Column(name = "default_price")
	private String defaultPrice;
	@Column(name = "photo")
	private String photo;
	@Column(name = "photo_type")
	private String photoType;
	@Column(name = "food_data")
	private String foodData;
	@Column(name = "top")
	private String top;
	@Column(name = "status")
	private String status;
	@Column(name = "enable")
	private String enable;
	@Column(name = "create_date")
	private String createDate;

	public static FoodInfo valueOf(FoodInfoVo vo) {
		FoodInfo info = new FoodInfo();
		info.foodUUID = vo.getFood_uuid();
		info.categoryUUID = vo.getCategory_uuid();
		info.foodName = vo.getFood_name();
		info.foodContent = vo.getFood_content();
		info.defaultPrice = vo.getDefault_price();
		info.photo = vo.getPhoto();
		info.photoType = vo.getPhoto_type();
		info.foodData = JsonHelper.toJson(vo.getFood_data());
		info.top = vo.getTop();
		info.status = vo.getStatus();
		info.enable = vo.getEnable();
		info.createDate = vo.getCreate_date();
		return info;
	}

	public static List<FoodInfo> valueOfArray(List<FoodInfoVo> vos) {
		List<FoodInfo> infos = Lists.<FoodInfo>newArrayList();
		infos.addAll(vos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return infos;
	}

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
