package com.melonltd.naber.rdbms.model.vo;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.RestaurantPhotoRel;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RestaurantPhotoRelVo {

	private String id;
	private String restaurant_uuid;
	private String photo;
	private String enable;
	private String status;
	private String create_date;
	
	
	public static RestaurantPhotoRelVo valueOf(RestaurantPhotoRel info) {
		return new RestaurantPhotoRelVo(info.getId().toString(), info.getRestaurantUUID(), info.getPhoto(), info.getEnable().name(), info.getStatus().name(), info.getCreateDate());
	}
	
	
	public static List<RestaurantPhotoRelVo> valueOfArray(List<RestaurantPhotoRel> infos) {
		List<RestaurantPhotoRelVo> list = Lists.newArrayList();
		list.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return list;
	}
	
	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
}
