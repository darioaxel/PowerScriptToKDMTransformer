package org.darioaxel.util.project.validator;

import java.io.File;

public class PowerbuilderProjectValidator implements IPowerbuilderProjectValidator {

	public PowerbuilderProjectValidator() {		
	}

	@Override
	public boolean isValid(File root) {
		// 1º Busco un pbt y que sólo exista uno en el árbol
		// 2º Bucle que busca cada pbg que tiene el pbt
		//		De cada pbg reviso el contenido, y busco cada fichero de código que referencia para ver que existe
		
		return false;
	}

	@Override
	public File getPBTFile(File root) {
		// TODO Auto-generated method stub
		return null;
	}
}
