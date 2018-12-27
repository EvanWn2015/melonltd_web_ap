package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.FoodInfo;
import com.melonltd.naber.rdbms.model.req.vo.FoodItemVo;

import lombok.Data;

@Data
public class FoodInfoVo implements Serializable {
	private static final long serialVersionUID = -1682202184306433042L;

	private String food_uuid;
	private String category_uuid;
	private String food_name;
	private String food_content;
	private String default_price;
	private String photo;
	private String photo_type;
	private FoodItemVo food_data;
	private String top;
	private String status;
	private String enable;
	private String create_date;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	public static FoodInfoVo valueOf(FoodInfo info, boolean isDetail) {
		FoodInfoVo vo = new FoodInfoVo();
		vo.food_uuid = info.getFoodUUID();
		vo.category_uuid = info.getCategoryUUID();
		vo.food_name = info.getFoodName();
		vo.food_content = info.getFoodContent();
		vo.default_price = info.getDefaultPrice();
		vo.photo = info.getPhoto();
		vo.top = info.getTop();

		if (isDetail) {
			vo.food_data = JsonHelper.json(info.getFoodData(), FoodItemVo.class);
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
		vo.food_content = info.getFoodContent();
		vo.default_price = info.getDefaultPrice();
		vo.photo = info.getPhoto();
		vo.food_data = JsonHelper.json(info.getFoodData(), FoodItemVo.class);
		vo.top = info.getTop();
		vo.status = info.getStatus();
<<<<<<< HEAD
=======
		// vo.enable = info.getEnable();
>>>>>>> feature/v1.0.2_developer_evan
		return vo;
	}

	public static List<FoodInfoVo> valueOfArray(List<FoodInfo> infos, boolean isDetail) {
		List<FoodInfoVo> vos = Lists.<FoodInfoVo>newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a, isDetail)).collect(Collectors.toList()));
		return vos;
	}
}
