package com.melonltd.naber.rdbms.model.type;

import java.util.List;

import com.google.common.collect.Lists;

public enum Identity {
	ADMIN("ADMIN"), 

	JUNIOR("JUNOR"), 
	SENIOR("SENOR"), 
	INTERMEDIATE("INTERMEDIATE"), 
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
		return Lists.newArrayList(ADMIN, INTERMEDIATE, SENIOR, JUNIOR, UNIVERSITY, NON_STUDENT,SELLERS);
	}
	
	public static List<Identity> getNeedSchoolEnumValues() {
		return Lists.newArrayList(SENIOR, JUNIOR, UNIVERSITY);
	}
	
	public static List<Identity> getUserEnumValues() {
		return Lists.newArrayList(INTERMEDIATE, SENIOR, JUNIOR,UNIVERSITY,NON_STUDENT);
	}

}
