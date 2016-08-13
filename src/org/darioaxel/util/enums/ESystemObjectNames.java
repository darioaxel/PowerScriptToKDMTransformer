package org.darioaxel.util.enums;

public enum ESystemObjectNames {

	SYSTEM_OBJECT_UNIT("SystemObjectsUnit"),
	ON_METHOD_ATTRIBUTE("onMethodObject"), 
	SYSTEM_SQL_SENTENCE_UNIT("SystemSQLSentenceUnit");
	
	private String description;
	
	private ESystemObjectNames( String description) {
		
		this.description = description;
	}
		
	public String Description() {
		return description;
	}
}

