package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.RestaurantCategoryRel;

public class RestaurantCategoryRelVo implements Serializable{
	private static final long serialVersionUID = 1978459295957706702L;
	
	private String categor_uuid;
	private String restaurant_uuid;
	private String category_name;
	private String status;
	private String enable;
	private String create_date;
	
	public String getCategor_uuid() {
		return categor_uuid;
	}
	public void setCategor_uuid(String categor_uuid) {
		this.categor_uuid = categor_uuid;
	}
	public String getRestaurant_uuid() {
		return restaurant_uuid;
	}
	public void setRestaurant_uuid(String restaurant_uuid) {
		this.restaurant_uuid = restaurant_uuid;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
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
		return MoreObjects.toStringHelper(this.getClass())
				.add("categor_uuid", categor_uuid)
				.add("restaurant_uuid", restaurant_uuid)
				.add("category_name", category_name)
				.add("status", status)
				.add("enable", enable)
				.add("create_date", create_date)
				.toString();
	}

	
	public static RestaurantCategoryRelVo valueOf(RestaurantCategoryRel info) {
		RestaurantCategoryRelVo vo = new RestaurantCategoryRelVo ();
		vo.categor_uuid = info.getCategorUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.category_name = info.getCategoryName();
		vo.status = info.getStatus();
//		vo.enable = info.getEnable();
//		vo.create_date = info.getCreateDate();
		return vo;
	}
	
	public static List<RestaurantCategoryRelVo> valueOfArray(List<RestaurantCategoryRel> infos) {
		List<RestaurantCategoryRelVo> vos = Lists.<RestaurantCategoryRelVo>newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}
	
}
