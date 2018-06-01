package com.melonltd.naber.rdbms.model.vo;

import java.io.Serializable;

public class RequestData implements Serializable{
	private static final long serialVersionUID = -1416927200695229509L;
	
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
