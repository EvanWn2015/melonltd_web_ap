package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "activities")
public class Activities implements Serializable { 
	private static final long serialVersionUID = -83755436543652291L;
	
	private String actUUID;
	private String actCategory;
	private String relUUID;
	private String title;
	private String contentText;
	private String photo;
	private String data;
	private String restrictFunc;
	private String needBonus;
	private String restrictSendCount;
	private String restrictGetCount;
	private String manDate;
	private String expDate;
	private String status;
	private String enable;
	private String createDate;
	private String updateDate;
	
	

}
