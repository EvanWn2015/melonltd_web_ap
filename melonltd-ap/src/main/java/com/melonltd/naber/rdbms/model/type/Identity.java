package com.melonltd.naber.rdbms.model.type;

import java.util.List;

import com.google.common.collect.Lists;

public enum Identity {
	ADMIN("ADMIN"), 
	ELEMENTARY("ELEMENTARY"), 
	SENOR("SENOR"), 
	JUNOR("JUNOR"), 
	UNIVERSITY("UNIVERSITY"), 
	NON_STUDENT("NON_STUDENT"),
	SELLERS("SELLERS");
	
	private String name;
	
	Identity(String name) {
		this.name = name;
	}
	
	
	public static Identity of(String name) {
		for (Identity type : getEnumValues()) {
			if (type.name.equals(name.toUpperCase())) {
				return type;
			}
		}
		return null;
	}
	
	public static List<Identity> getEnumValues() {
		return Lists.newArrayList(ADMIN, ELEMENTARY, SENOR, JUNOR, UNIVERSITY, NON_STUDENT,SELLERS);
	}
	
	public static List<Identity> getNeedSchoolEnumValues() {
		return Lists.newArrayList(SENOR, JUNOR, UNIVERSITY);
	}

}
