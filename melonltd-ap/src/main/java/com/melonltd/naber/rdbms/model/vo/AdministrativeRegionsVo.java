package com.melonltd.naber.rdbms.model.vo;

import java.util.List;

import com.google.common.base.MoreObjects;

public class AdministrativeRegionsVo {

	private String city;
	private List<AreaVo> areas;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<AreaVo> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaVo> areas) {
		this.areas = areas;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass()).add("city", city).add("areas", areas).toString();
	}

	public static class AreaVo {

		private String area;
		private String postal_code;

		AreaVo(String area, String postal_code) {
			this.area = area;
			this.postal_code = postal_code;
		}

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getPostal_code() {
			return postal_code;
		}

		public void setPostal_code(String postal_code) {
			this.postal_code = postal_code;
		}

		public static AreaVo of(String area, String postal_code) {
			return new AreaVo(area, String.valueOf(postal_code));
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this.getClass()).add("area", area).add("postal_code", postal_code)
					.toString();
		}
	}

}
