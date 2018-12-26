package com.melonltd.naber.rdbms.model.vo;

import lombok.Data;

@Data
public class LatLngVo {
	private double latitude;
	private double longitude;

	public static LatLngVo of(String latitude, String longitude) {
		LatLngVo vo = new LatLngVo();
		vo.latitude = Double.parseDouble(latitude);
		vo.longitude = Double.parseDouble(longitude);
		return vo;
	}

}
