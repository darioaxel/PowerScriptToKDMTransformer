package org.darioaxel.project.validator;

import java.io.File;

public interface PowerbuilderProjectValidator {
	
	boolean isValid(File root);
	File getPBTFile(File root);	
}
