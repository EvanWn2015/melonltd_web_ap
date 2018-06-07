package com.melonltd.naber.rdbms.model.vo;

public class ReqData {
	private String search_type;
	private String area;
	private int page;
	private int top;
	private String longitude;
	private String latitude;
	private String category;
	
	
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}
