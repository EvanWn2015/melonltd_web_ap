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
import com.melonltd.naber.rdbms.model.vo.CategoryRelVo;

import lombok.Data;

@Data
@Entity
@Table(name = "category_rel")
public class CategoryRel implements Serializable {
	private static final long serialVersionUID = 6997247221789078230L;

	@Id
	@Column(name = "category_uuid", unique = true, nullable = false)
	private String categoryUUID;
	@Column(name = "restaurant_uuid")
	private String restaurantUUID;
	@Column(name = "category_name")
	private String categoryName;
	@Column(name = "top")
	private String top;
	@Column(name = "status")
	private String status;
	@Column(name = "enable")
	private String enable;
	@Column(name = "create_date")
	private String createDate;

	public static CategoryRel valueOf(CategoryRelVo vo) {
		CategoryRel info = new CategoryRel();
		info.categoryUUID = vo.getCategory_uuid();
		info.restaurantUUID = vo.getRestaurant_uuid();
		info.categoryName = vo.getCategory_name();
		info.top = vo.getTop();
		info.status = vo.getStatus();
		info.enable = vo.getEnable();
		info.createDate = vo.getCreate_date();
		return info;
	}

	public static List<CategoryRel> valueOfArray(List<CategoryRelVo> vos) {
		List<CategoryRel> infos = Lists.<CategoryRel>newArrayList();
		infos.addAll(vos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return infos;
	}

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
