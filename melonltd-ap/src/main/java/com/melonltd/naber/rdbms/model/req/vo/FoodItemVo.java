package com.melonltd.naber.rdbms.model.req.vo;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
public class FoodItemVo implements Serializable {
	private static final long serialVersionUID = 8854246756125375923L;

	private String food_uuid;
	private String category_name;
	private String food_name;
	private String food_photo;
	private String price;

	private List<ItemVo> scopes = Lists.<ItemVo>newArrayList();
	private List<ItemVo> opts = Lists.<ItemVo>newArrayList();
	private List<DemandsItemVo> demands = Lists.<DemandsItemVo>newArrayList();

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
