package org.darioaxel.project.validator;

import java.io.File;

import org.darioaxel.util.FileAccess;

public class PowerbuilderProjectValidator implements IPowerbuilderProjectValidator {

	public PowerbuilderProjectValidator() {		
	}

	@Override
	public boolean isValid(File root) {
		// 1º Search for the one and only pbt existing file
		File pbt = getPBTFile(root);
		if ( pbt != null) {
			
		}
		// 2º For each pbg in the pbt file, test that sourcecode files exists 
		
		
		
		return false;
	}

	@Override
	public File getPBTFile(File root) {
		
		PowerbuilderProjectValidatorFileListener pbFileListener = new PowerbuilderProjectValidatorFileListener();
		try{
			FileAccess.walkDirectoryRecursively(root, pbFileListener);
		} 
		catch( Exception e) {
			System.out.println(e.getMessage());
		}
		return pbFileListener.getPbtFile();
	}
}
