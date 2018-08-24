package com.melonltd.naber.rdbms.model.type;


/**
 * 
 * ORIGINAL("ORIGINAL") 原價
 * DISCOUNT("DISCOUNT") 折價
 * COUPON("COUPON") 兌換
 */
public enum BillingType {
	ORIGINAL("ORIGINAL"), 
	DISCOUNT("DISCOUNT"),
	COUPON("COUPON");

	private String name;
	
	BillingType(String name){
		this.name = name;
	}
}
