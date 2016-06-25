package org.darioaxel.util.project.validator;

import java.io.File;

public interface IPowerbuilderProjectValidator {
	
	boolean isValid(File root);
	File getPBTFile(File root);	
}
