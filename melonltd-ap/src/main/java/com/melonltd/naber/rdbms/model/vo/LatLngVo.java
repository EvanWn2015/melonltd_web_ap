package com.melonltd.naber.rdbms.model.vo;

public class LatLngVo {
	private double latitude;
	private double longitude;
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public static LatLngVo of (String latitude, String longitude) {
		LatLngVo vo = new LatLngVo();
		vo.latitude = Double.parseDouble(latitude);
		vo.longitude = Double.parseDouble(longitude);
		return vo;
	}
	
}
