package com.melonltd.naber.rdbms.model.vo.food;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.MoreObjects;

public class DemandsItemVo implements Serializable {
	private static final long serialVersionUID = 8573962792580515438L;

	private String name;
	private List<ItemVo> datas;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ItemVo> getDatas() {
		return datas;
	}

	public void setDatas(List<ItemVo> datas) {
		this.datas = datas;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("name",name)
				.add("datas",datas)
				.toString();
	}

}
