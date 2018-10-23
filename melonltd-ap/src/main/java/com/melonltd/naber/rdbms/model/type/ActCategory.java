package com.melonltd.naber.rdbms.model.type;

/**
 * POINT = 點數 
 * TICKET = 兌換券 // 充值 user bouns  
 * RES_EVENT = 商家活動 // 換品項 
 *
 */
public enum ActCategory {
	POINT("POINT"), 
	TICKET("TICKET"), 
	RES_EVENT("RES_EVENT");
	
	private String name;
	
	ActCategory(String name){
		this.name = name;
	}

}
