package org.darioaxel.util.enums;

public enum EPowerscriptFileTypes {
	
    Menu("srm", "Menu Type"),
    Window("srw", "Window Type"),
	DataWindow("srd","DataWindow Type"), 
	Functions("sru", "Functions Collection Type"),	
    Structure("srs", "User Defined Structure Type"),
    GlobalFunctions("srf", "Global User Defined Functions Collection Type"),
    Libraries("pbg", "Library Description Type"),
    Language("lng", "Language Type"),
    ImageJPG("jpg", "Image Type"),
    ProjectDefinition("pbt", "Project Definition Type"),
    Unknown("", "Unknown Type"),
    System_object("", "System Predefined Object");
    
    private final String extension;
    private final String description;
    
    EPowerscriptFileTypes (String extension, String description) {
  
    	this.extension = extension;
    	this.description = description;
    }
   
    public String extension() {
    	return this.extension;
    }   
    
    public String description() {
    	return this.description;
    }
}