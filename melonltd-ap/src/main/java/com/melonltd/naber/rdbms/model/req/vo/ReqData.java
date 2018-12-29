package com.melonltd.naber.rdbms.model.req.vo;

import java.util.List;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
public class ReqData {

	private String id;
	private String uuid;
	private List<String> uuids;
	private String search_type;
	private String area;
	private int page;
	private int top;
	private String longitude;
	private String latitude;
	private String category;
	private String json_data;
	private String date;
	private String data;
	// private List<String> datas;
	private String start_date;
	private String end_date;
	private String message;
	private String type;
	private String name;
	private String status;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

}
