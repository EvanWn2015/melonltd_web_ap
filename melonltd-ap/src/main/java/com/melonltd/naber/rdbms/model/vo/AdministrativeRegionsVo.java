package com.melonltd.naber.rdbms.model.vo;

import java.util.List;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
public class AdministrativeRegionsVo {

	private String city;
	private List<AreaVo> areas;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	@Data
	public static class AreaVo {

		private String area;
		private String postal_code;

		AreaVo(String area, String postal_code) {
			this.area = area;
			this.postal_code = postal_code;
		}

		public static AreaVo of(String area, String postal_code) {
			return new AreaVo(area, String.valueOf(postal_code));
		}

		@Override
		public String toString() {
			return JsonHelper.toJson(this);
		}
	}

}
