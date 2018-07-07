package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.melonltd.naber.rdbms.model.bean.CategoryRel;

public class CategoryRelVo implements Serializable{
	private static final long serialVersionUID = 1978459295957706702L;
	
	private String category_uuid;
	private String restaurant_uuid;
	private String category_name;
	private String status;
	private String enable;
	private String create_date;
	
	public String getCategory_uuid() {
		return category_uuid;
	}
	public void setCategory_uuid(String category_uuid) {
		this.category_uuid = category_uuid;
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
				.add("category_uuid", category_uuid)
				.add("restaurant_uuid", restaurant_uuid)
				.add("category_name", category_name)
				.add("status", status)
				.add("enable", enable)
				.add("create_date", create_date)
				.toString();
	}

	
	public static CategoryRelVo valueOf(CategoryRel info) {
		CategoryRelVo vo = new CategoryRelVo ();
		vo.category_uuid = info.getCategoryUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.category_name = info.getCategoryName();
		vo.status = info.getStatus();
//		vo.enable = info.getEnable();
//		vo.create_date = info.getCreateDate();
		return vo;
	}
	
	public static List<CategoryRelVo> valueOfArray(List<CategoryRel> infos) {
		List<CategoryRelVo> vos = Lists.<CategoryRelVo>newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}
	
}
