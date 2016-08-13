package org.darioaxel.util.enums;

public enum EActionElementTypes {
	
	ON_METHOD_CALL("on method invocation"), 
	METHOD_INVOCATION("method invocation"), 
	ASSIGN("Assign");
	
	private String description;
	
	private EActionElementTypes(String description) {
		
		this.description = description;
	}
		
	public String Description() {
		return description;
	}
}