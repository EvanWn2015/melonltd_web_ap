package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.CategoryRel;

import lombok.Data;

@Data
public class CategoryRelVo implements Serializable {
	private static final long serialVersionUID = 1978459295957706702L;

	private String category_uuid;
	private String restaurant_uuid;
	private String category_name;
	private String top;
	private String status;
	private String enable;
	private String create_date;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	public static CategoryRelVo valueOf(CategoryRel info) {
		CategoryRelVo vo = new CategoryRelVo();
		vo.category_uuid = info.getCategoryUUID();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.category_name = info.getCategoryName();
		vo.top = info.getTop();
		vo.status = info.getStatus();
		vo.enable = info.getEnable();
		vo.create_date = info.getCreateDate();
		return vo;
	}

	public static List<CategoryRelVo> valueOfArray(List<CategoryRel> infos) {
		List<CategoryRelVo> vos = Lists.<CategoryRelVo>newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}

}
