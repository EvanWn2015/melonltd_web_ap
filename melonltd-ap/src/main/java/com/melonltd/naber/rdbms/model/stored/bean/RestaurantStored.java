package com.melonltd.naber.rdbms.model.stored.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.common.base.MoreObjects;

@Entity
//@Table(name = "RestaurantStored")
//@NamedStoredProcedureQueries({
//   @NamedStoredProcedureQuery(name = "getRestaurantDistanceByLatlng", 
//                              procedureName = "get_restaurant_distance_by_latlng",
//                              parameters = {
//                                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_latitude", type = String.class),
//                                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "", type = String.class)
////                                 @StoredProcedureParameter(mode = ParameterMode.OUT, name = "out_longitude", type = RestaurantInfoVo.class),
////                                 @StoredProcedureParameter(mode = ParameterMode.OUT, name = "in_longitude", type = String.class),
//                              },
//                              resultClasses = RestaurantStored.class)
//})

//@NamedStoredProcedureQuery(
//        name="get_restaurant_distance_by_latlng",
//        procedureName="get_restaurant_distance_by_latlng",
//        resultClasses = { RestaurantStored.class },
//        parameters={
//            @StoredProcedureParameter(name="in_latitude", type=String.class, mode=ParameterMode.IN),
//            @StoredProcedureParameter(name="in_longitude", type=String.class, mode=ParameterMode.IN)
//        }
//)
public class RestaurantStored implements Serializable{
	private static final long serialVersionUID = -3325633702432906952L;
	
	private String restaurantUUID;
	private String name;
	private String address;
	private String storeStart;
	private String storeEnd;
	private String notBusiness;
	private String canStoreRange;
	private String restaurantCategory;
	private String latitude;
	private String longitude;
	private String bulletin;
	private String photo;
	private String backgroundPhoto;
	private String photoType;
	private String enable;
	private String top;
	private String distance;

	@Id
	@Column(name = "restaurant_uuid", unique = true, nullable = false)
	public String getRestaurantUUID() {
		return restaurantUUID;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	@Column(name = "store_start")
	public String getStoreStart() {
		return storeStart;
	}

	@Column(name = "store_end")
	public String getStoreEnd() {
		return storeEnd;
	}

	@Column(name = "not_business")
	public String getNotBusiness() {
		return notBusiness;
	}

	@Column(name = "can_store_range")
	public String getCanStoreRange() {
		return canStoreRange;
	}
	

	@Column(name = "restaurant_category")
	public String getRestaurantCategory() {
		return restaurantCategory;
	}
	
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}

	@Column(name = "bulletin")
	public String getBulletin() {
		return bulletin;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return photo;
	}
	
	@Column(name = "background_photo")
	public String getBackgroundPhoto() {
		return backgroundPhoto;
	}

	@Column(name = "photo_type")
	public String getPhotoType() {
		return photoType;
	}

	@Column(name = "enable")
	public String getEnable() {
		return enable;
	}
	
	@Column(name = "top")
	public String getTop() {
		return top;
	}

	@Column(name = "distance")
	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public void setRestaurantUUID(String restaurantUUID) {
		this.restaurantUUID = restaurantUUID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setStoreStart(String storeStart) {
		this.storeStart = storeStart;
	}

	public void setStoreEnd(String storeEnd) {
		this.storeEnd = storeEnd;
	}

	public void setNotBusiness(String notBusiness) {
		this.notBusiness = notBusiness;
	}

	public void setCanStoreRange(String canStoreRange) {
		this.canStoreRange = canStoreRange;
	}
	
	public void setRestaurantCategory(String restaurantCategory) {
		this.restaurantCategory = restaurantCategory;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setBackgroundPhoto(String backgroundPhoto) {
		this.backgroundPhoto = backgroundPhoto;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	public void setTop(String top) {
		this.top = top;
	}
	
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("restaurantUUID",restaurantUUID)
				.add("name",name)
				.add("address",address)
				.add("storeStart",storeStart)
				.add("storeEnd",storeEnd)
				.add("notBusiness",notBusiness)
				.add("canStoreRange",canStoreRange)
				.add("restaurantCategory", restaurantCategory)
				.add("latitude",latitude)
				.add("longitude",longitude)
				.add("bulletin",bulletin)
				.add("photo",photo)
				.add("backgroundPhoto", backgroundPhoto)
				.add("photoType",photoType)
				.add("enable",enable)
				.add("top",top)
				.toString();
	}
	
	
	
}
