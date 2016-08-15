package org.darioaxel.util.enums;

public enum EActionElementTypes {
	
	ON_METHOD_CALL("on method invocation"), 
	METHOD_INVOCATION("method invocation"), 
	ASSIGN("Assign"),
	EXPRESSION_STATEMENT("expression statement"),
	FIELD_ACCESS("field access"),
	ADDRESSES("Addresses");
	
	private String description;
	
	private EActionElementTypes(String description) {
		
		this.description = description;
	}
		
	public String Description() {
		return description;
	}
}