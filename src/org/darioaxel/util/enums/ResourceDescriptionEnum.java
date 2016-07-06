package org.darioaxel.util.enums;

public enum ResourceDescriptionEnum { 
	PROJECT(0, "ProjectDescriptor"), LIBRARY(1, "");
	
	private int type;
	private String description;
	
	private ResourceDescriptionEnum(int type, String description) {
		this.type = type;
		this.description = description;
	}
	
	public int Type() {
		return type;
	}
	
	public String Description() {
		return description;
	}
}
