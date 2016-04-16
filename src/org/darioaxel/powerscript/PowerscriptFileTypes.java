package org.darioaxel.powerscript;

public enum PowerscriptFileTypes {
	
    Menu("srm"),
    Window("srw"),
	DataWindow("srd"), 
	Functions("sru"),	
    Structure("srs"),
    GlobalFunctions("srf"),
    Libraries("pbt"),
    Language("lng"),
    ImageJPG("jpg");
    
    private String extension;
    
    PowerscriptFileTypes (String extension) {
    	this.extension = extension;
    }
   
    public String extension() {
    	return this.extension;
    }   
}