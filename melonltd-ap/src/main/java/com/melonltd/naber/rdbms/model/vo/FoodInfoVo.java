package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.FoodInfo;
import com.melonltd.naber.rdbms.model.req.vo.FoodItemVo;

public class FoodInfoVo implements Serializable{
	private static final long serialVersionUID = -1682202184306433042L;
	
	private String food_uuid;
	private String category_uuid;
	private String food_name;
	private String default_price;
	private String photo;
	private String photo_type;
	private FoodItemVo food_data;
	private String top;
	private String status;
	private String enable;
	private String create_date;

	public String getFood_uuid() {
		return food_uuid;
	}

	public void setFood_uuid(String food_uuid) {
		this.food_uuid = food_uuid;
	}

	public String getCategory_uuid() {
		return category_uuid;
	}

	public void setCategory_uuid(String category_uuid) {
		this.category_uuid = category_uuid;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getDefault_price() {
		return default_price;
	}

	public void setDefault_price(String default_price) {
		this.default_price = default_price;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoto_type() {
		return photo_type;
	}

	public void setPhoto_type(String photo_type) {
		this.photo_type = photo_type;
	}

	public FoodItemVo getFood_data() {
		return food_data;
	}

	public void setFood_data(FoodItemVo food_data) {
		this.food_data = food_data;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	@Override
	public String toString() {
		return MoreObjects
				.toStringHelper(this.getClass())
				.add("food_uuid",food_uuid)
				.add("category_uuid",category_uuid)
				.add("food_name",food_name)
				.add("default_price",default_price)
				.add("photo",photo)
				.add("photo_type",photo_type)
				.add("food_data",food_data)
				.add("top",top)
				.add("status",status)
				.add("enable",enable)
				.add("create_date", create_date)
				.toString();
	}
	
	public static FoodInfoVo valueOf(FoodInfo info, boolean isDetail ) {
		FoodInfoVo vo = new FoodInfoVo();
		vo.food_uuid = info.getFoodUUID();
		vo.category_uuid = info.getCategoryUUID();
		vo.food_name = info.getFoodName();
		vo.default_price = info.getDefaultPrice();
		vo.photo = info.getPhoto();
		vo.top = info.getTop();
		
		if (isDetail) {
			vo.food_data =JsonHelper.json(info.getFoodData(), FoodItemVo.class);
			vo.photo_type = info.getPhotoType();
			vo.status = info.getStatus();
			vo.enable = info.getEnable();
			vo.create_date = info.getCreateDate();
		}
		return vo;
	}
	
	public static FoodInfoVo valueOf(FoodInfo info) {
		FoodInfoVo vo = new FoodInfoVo();
		vo.food_uuid = info.getFoodUUID();
		vo.category_uuid = info.getCategoryUUID();
		vo.food_name = info.getFoodName();
		vo.default_price = info.getDefaultPrice();
		vo.photo = info.getPhoto();
		vo.food_data =JsonHelper.json(info.getFoodData(), FoodItemVo.class);
		vo.top = info.getTop();
		vo.status = info.getStatus();
		return vo;
	}
	
	public static List<FoodInfoVo> valueOfArray(List<FoodInfo> infos, boolean isDetail) {
		List<FoodInfoVo> vos = Lists.<FoodInfoVo>newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a,isDetail)).collect(Collectors.toList()));
		return vos;
	}
}
