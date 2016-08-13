package org.darioaxel.util.enums;

public enum ESQLSentenceType {


	SELECT("Select"),
	INSERT("Insert");
	
	private String description;
	
	private ESQLSentenceType( String description) {
		
		this.description = description;
	}
		
	public String Description() {
		return description;
	}
}
