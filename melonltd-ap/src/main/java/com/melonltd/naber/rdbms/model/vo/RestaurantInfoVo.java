package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.RestaurantInfo;
import com.melonltd.naber.rdbms.model.bean.RestaurantLocationTemplate;

import lombok.Data;

@Data
public class RestaurantInfoVo implements Serializable {
	private static final long serialVersionUID = -1332259920125957056L;

	private String restaurant_uuid;
	private String name;
	private String address;
	private String store_start;
	private String store_end;
	private String is_store_now_open;
	private String can_discount;
	private List<String> delivery_types;
	private List<String> not_business;
	private List<DateRangeVo> can_store_range;
	private String restaurant_category;
	private String latitude;
	private String longitude;
	private String bulletin;
	private String photo;
	private String background_photo;
	private String photo_type;
	private String create_date;
	private String enable;
	private String top;

	private String distance;

	public static RestaurantInfoVo valueOf(RestaurantInfo info, boolean hasStoreRange) {
		RestaurantInfoVo vo = checkIsStoreOpen(info);
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.name = info.getName();
		vo.address = info.getAddress();
		vo.store_start = info.getStoreStart();
		vo.store_end = info.getStoreEnd();
		vo.can_discount = info.getCanDiscount();

		vo.delivery_types = JsonHelper.jsonArray(info.getDeliveryTypes(), String[].class);
		// vo.is_store_now_open = checkIsStoreOpen(info) + "";
		// vo.not_business = JsonHelper.jsonArray(info.getNotBusiness(),
		// String[].class);
		if (hasStoreRange) {
			vo.can_store_range = JsonHelper.jsonArray(info.getCanStoreRange(), DateRangeVo[].class);
			vo.not_business = JsonHelper.jsonArray(info.getNotBusiness(), String[].class);
		}

		vo.restaurant_category = info.getRestaurantCategory();
		vo.latitude = info.getLatitude();
		vo.longitude = info.getLongitude();
		vo.bulletin = info.getBulletin();
		vo.photo = info.getPhoto();
		vo.enable = info.getEnable();
		vo.background_photo = info.getBackgroundPhoto();
		vo.create_date = info.getCreateDate();
		vo.top = info.getTop();
		return vo;
	}

	public static List<RestaurantInfoVo> valueOfArray(List<RestaurantInfo> infos) {
		List<RestaurantInfoVo> vos = Lists.<RestaurantInfoVo>newArrayList();
		vos.addAll(infos.stream().map(a -> valueOf(a, false)).collect(Collectors.toList()));
		return vos;
	}

	public static RestaurantInfoVo valueOfTemplate(RestaurantLocationTemplate info) {
		RestaurantInfoVo vo = new RestaurantInfoVo();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.latitude = info.getLatitude();
		vo.longitude = info.getLongitude();
		return vo;
	}

	public static List<RestaurantInfoVo> valueOfTemplateArray(List<RestaurantLocationTemplate> infos) {
		List<RestaurantInfoVo> vos = Lists.<RestaurantInfoVo>newArrayList();
		vos.addAll(infos.stream().map(a -> valueOfTemplate(a)).collect(Collectors.toList()));
		return vos;
	}

	// private static String conversionFrom (double distance) {
	// double d = 0;
	// d = distance/ 1000;
	// if (d < 0) {
	// return "";
	// }
	// String result = Tools.decimalFormat("0.0", d);
	// return (result.equals("0.0") ? "0.1" : result) + "公里";
	// }

	private static RestaurantInfoVo checkIsStoreOpen(RestaurantInfo info) {
		RestaurantInfoVo vo = new RestaurantInfoVo();
		String now = Tools.getGMTDate("HH:mm");
		boolean c1 = Tools.checkOpenStore(info.getStoreStart(), info.getStoreEnd(), now);
		int nowGMT = Tools.getDayOfYear(Tools.getNowGMT(), "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		List<String> notBusiness = JsonHelper.jsonArray(info.getNotBusiness(), String[].class).stream().filter(a -> {
			return Tools.getDayOfYear(a, "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'") == nowGMT;
		}).collect(Collectors.toList());
		List<DateRangeVo> canStoreRange = Tools.checkOpenStoreByRanges(info.getCanStoreRange(), now);
		vo.not_business = notBusiness;
		vo.can_store_range = canStoreRange;
		// vo.is_store_now_open = (c1 && canStoreRange.size() == 0 && notBusiness.size()
		// == 0) +"";
		vo.is_store_now_open = c1 + "";
		return vo;
	}

	@Override
	public String toString() {
		return JsonHelper.toJson(this);

	}

}
