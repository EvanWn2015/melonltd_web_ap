package com.melonltd.naber.rdbms.model.stored.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.stored.bean.RestaurantStored;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;





public class RestaurantStoredVo implements Serializable {
	private static final long serialVersionUID = 1892751420634749037L;

	private String restaurant_uuid;
	private String name;
	private String address;
	private String store_start;
	private String store_end;
	private String is_store_open;
	private List<DateRangeVo> not_business;
	private List<DateRangeVo> can_store_range;
	private String restaurant_category;
	private String latitude;
	private String longitude;
	private String bulletin;
	private String photo;
	private String background_photo;
	private String photo_type;
	private String top;
	private String distance;


	public String getRestaurant_uuid() {
		return restaurant_uuid;
	}

	public void setRestaurant_uuid(String restaurant_uuid) {
		this.restaurant_uuid = restaurant_uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStore_start() {
		return store_start;
	}

	public void setStore_start(String store_start) {
		this.store_start = store_start;
	}

	public String getStore_end() {
		return store_end;
	}

	public void setStore_end(String store_end) {
		this.store_end = store_end;
	}

	public String getIs_store_open() {
		return is_store_open;
	}

	public void setIs_store_open(String is_store_open) {
		this.is_store_open = is_store_open;
	}

	public List<DateRangeVo> getNot_business() {
		return not_business;
	}

	public void setNot_business(List<DateRangeVo> not_business) {
		this.not_business = not_business;
	}

	public List<DateRangeVo> getCan_store_range() {
		return can_store_range;
	}

	public void setCan_store_range(List<DateRangeVo> can_store_range) {
		this.can_store_range = can_store_range;
	}

	public String getRestaurant_category() {
		return restaurant_category;
	}

	public void setRestaurant_category(String restaurant_category) {
		this.restaurant_category = restaurant_category;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getBulletin() {
		return bulletin;
	}

	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBackground_photo() {
		return background_photo;
	}

	public void setBackground_photo(String background_photo) {
		this.background_photo = background_photo;
	}

	public String getPhoto_type() {
		return photo_type;
	}

	public void setPhoto_type(String photo_type) {
		this.photo_type = photo_type;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("restaurant_uuid", restaurant_uuid)
				.add("name", name)
				.add("distance", distance)
				.add("address", address)
				.add("store_start", store_start)
				.add("store_end", store_end)
				.add("not_business", not_business)
				.add("is_store_open", is_store_open)
				.add("can_store_range", can_store_range)
				.add("restaurant_category", restaurant_category)
				.add("latitude", latitude)
				.add("longitude", longitude)
				.add("bulletin", bulletin)
				.add("photo", photo)
				.add("background_photo", background_photo)
				.add("photo_type", photo_type)
				.add("top", top)	
				.toString();
	}

	public static RestaurantStoredVo valueOf(RestaurantStored info) {
		RestaurantStoredVo vo = new RestaurantStoredVo();
		vo.restaurant_uuid = info.getRestaurantUUID();
		vo.name = info.getName();
		vo.address = info.getAddress();
		vo.store_start = info.getStoreStart();
		vo.store_end = info.getStoreEnd();
		vo.is_store_open = Tools.checkOpenStore(vo.store_start, vo.store_end) + "";
		vo.not_business = JsonHelper.jsonArray(info.getNotBusiness(), DateRangeVo.class);
		vo.can_store_range = JsonHelper.jsonArray(info.getCanStoreRange(), DateRangeVo.class);
		vo.restaurant_category = info.getRestaurantCategory();
		vo.latitude = info.getLatitude();
		vo.longitude = info.getLongitude();
		vo.bulletin = info.getBulletin();
		vo.photo = info.getPhoto();
		vo.background_photo = info.getBackgroundPhoto();
		vo.top = info.getTop();
		vo.distance = conversionFrom(info.getDistance());
		return vo;
	}
	
	
	private static String conversionFrom (String distance) {
		double d = 0;
		if(NumberUtils.isCreatable(distance)) {
			d = NumberUtils.createDouble(distance)/ 1000;
		}
		if (d <0) {
			return "";
		}
		String result = Tools.decimalFormat("0.0", d);
		return (result.equals("0.0") ? "0.1" : result) + "公里";
	}
	
	public static List<RestaurantStoredVo> valueOfArray(List<RestaurantStored> infos) {
		List<RestaurantStoredVo> vos = Lists.<RestaurantStoredVo>newArrayList(); 
		vos.addAll(infos.stream().map(a -> valueOf(a)).collect(Collectors.toList()));
		return vos;
	}
	
	
//	public boolean buildRanges (List<DateRangeVo> list){
//		String now = Tools.getGMTDate("HH:mm");
//		long count = 0;
//		count = list.stream()
//				.filter(f -> "Y".equals(f.getStatus()) && org.apache.commons.lang3.Range.<String>between(f.getDate().substring(0, 5), f.getDate().substring(6, 11)).contains(now))
//				.count();
//		return count < 1 ? false : true ;
//	}
	
//	public List<DateRangeVo> buildDateRange(Integer start , Integer end) {
//		start++;
//		Range<Integer> timeR = Range.open(start, start + 29);
//		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();
//
//		boolean status = true;
//		while (status) {
//			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
//			int dd = 30;
//			if (timeR.upperEndpoint() % 100 == 0) {
//				dd = 30;
//			} else {
//				dd = 70;
//			}
//			timeR = Range.open(timeR.upperEndpoint() + 1, timeR.upperEndpoint() + dd);
//			status = timeR.upperEndpoint().intValue() < end;
//			if (!status) {
//				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
//			}
//		}
//		return list;
//	}
//	
//	public List<DateRangeVo> buildDateRangeReverse(Integer start , Integer end) {
//		start++;
//		Range<Integer> timeR = Range.open(start, start + 29);
//		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();
//		boolean status = true;
//		
//		while (status) {
//			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
//			int dd = 30;
//			if (timeR.upperEndpoint() % 100 == 0) {
//				dd = 30;
//			} else {
//				dd = 70;
//			}
//			timeR = Range.open(timeR.upperEndpoint() + 1, timeR.upperEndpoint() + dd);
//			
//			if (timeR.upperEndpoint().intValue() == 2400) {
//				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), 2400, "Y"));
//				timeR = Range.open(0 + 1,  30);
//			}
//			
//			if(timeR.upperEndpoint().intValue() == end) {
//				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
//				status = false;
//			}
//		}
//		return list;
//	}

	

}
