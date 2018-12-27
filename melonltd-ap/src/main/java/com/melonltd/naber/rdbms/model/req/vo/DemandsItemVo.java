package com.melonltd.naber.rdbms.model.req.vo;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class DemandsItemVo implements Serializable {
	private static final long serialVersionUID = 8573962792580515438L;

	private String name;
	private List<ItemVo> datas = Lists.<ItemVo>newLinkedList();

	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("name",name)
				.add("datas",datas)
				.toString();
	}

}
