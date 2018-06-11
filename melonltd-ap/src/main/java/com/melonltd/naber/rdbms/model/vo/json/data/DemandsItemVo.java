package com.melonltd.naber.rdbms.model.vo.json.data;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

public class DemandsItemVo implements Serializable {
	private static final long serialVersionUID = 8573962792580515438L;

	private String name;
	private List<ItemVo> datas = Lists.<ItemVo>newLinkedList();

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
