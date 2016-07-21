package org.darioaxel.util;

import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.CompilationUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public final class CodeModelUtil {

	public static void addMethodToClassUnit(String unitClassName, MethodUnit method, CodeModel codeModel) {
		
		for(AbstractCodeElement e : codeModel.getCodeElement()) {
			if (e instanceof CompilationUnit ) {
				CompilationUnit cu = (CompilationUnit) e;
				for(AbstractCodeElement ee : cu.getCodeElement()) {
					if ( ee.getName() == unitClassName && ee instanceof ClassUnit) {
						((ClassUnit) ee).getCodeElement().add(method);
					}
				}
			}			
		}		
	}
	
	public static void addExtendsRelationshipToClassUnit(String fromClassName, String toClassName ,CodeModel codeModel) {
		
			
	}

	public static String getClassUnitName(CodeModel codeModel, SourceFile source) {
		return source.getName();
	}
}
