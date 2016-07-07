package org.darioaxel.util.enums;

public enum EPowerscriptFileTypes {
	
    Menu("srm"),
    Window("srw"),
	DataWindow("srd"), 
	Functions("sru"),	
    Structure("srs"),
    GlobalFunctions("srf"),
    Libraries("pbg"),
    Language("lng"),
    ImageJPG("jpg"),
    ProjectDefinition("pbt");
    
    private final String extension;
    
    EPowerscriptFileTypes (String extension) {
    	this.extension = extension;
    }
   
    public String extension() {
    	return this.extension;
    }   
}