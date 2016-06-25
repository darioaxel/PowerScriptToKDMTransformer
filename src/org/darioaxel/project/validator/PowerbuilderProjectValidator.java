package org.darioaxel.project.validator;

import java.io.File;

import org.darioaxel.util.FileAccess;

public class PowerbuilderProjectValidator implements IPowerbuilderProjectValidator {

	public PowerbuilderProjectValidator() {		
	}

	@Override
	public boolean isValid(File root) {
		// 1º Busco un pbt y que sólo exista uno en el árbol
		File pbt = getPBTFile(root);
		if ( pbt != null) {
			
		}
		// 2º Bucle que busca cada pbg que tiene el pbt
		//		De cada pbg reviso el contenido, y busco cada fichero de código que referencia para ver que existe
		
		
		
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
