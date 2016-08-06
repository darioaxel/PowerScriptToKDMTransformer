package org.darioaxel.mapper.code.listener;

import org.darioaxel.grammar.powerscript.powerscriptBaseListener;
import org.darioaxel.grammar.powerscript.powerscriptParser;
import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.language.LanguageUnits;
import org.darioaxel.util.enums.EPowerscriptFileTypes;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.CompilationUnit;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class Phase1SourceListener extends powerscriptBaseListener implements PowerscriptListener {
	
	private final CodeModel codeModel;
	private ClassUnit classUnit = null;
	private String compilationUnitName = "";
	private CompilationUnit compilationUnit = null;
	private boolean inForward = false;
	
	public Phase1SourceListener(CodeModel codeModel) {
		this.codeModel = codeModel;
	}
	
	@Override
	public void setClassUnitName(String name) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void setCompilationUnit(SourceFile source) {
		compilationUnitName = source.getName();
		compilationUnit = KDMElementFactory.createCompilationUnit(source);
		compilationUnit.getCodeElement().add(LanguageUnits.getLanguage("Powerscript"));
		codeModel.getCodeElement().add(compilationUnit);	
	}
	
	@Override
	public void enterForwardDeclaration(powerscriptParser.ForwardDeclarationContext ctx) { 
		inForward = true;		
	}
	
	@Override
	public void exitTypeDeclarationBegin(powerscriptParser.TypeDeclarationBeginContext ctx) { 
		if (inForward == true) {
			if(ctx.getChild(0).getText().equals("global")) {
				String className = ctx.getChild(1).getChild(1).toString();
				if (!compilationUnit.equals(null)) {
					classUnit = KDMElementFactory.createClass(className, typePowerscriptObjectIdentification(compilationUnitName));
					compilationUnit.getCodeElement().add(classUnit);
				}
				inForward = false;
			}			
		}		
	}	
	
	private EPowerscriptFileTypes typePowerscriptObjectIdentification(String fileName) {

		int lastIndexOf = fileName.lastIndexOf('.');
		String fileExt = fileName.substring(lastIndexOf + 1).toLowerCase();
		
		for(EPowerscriptFileTypes ep : EPowerscriptFileTypes.values()) {
			if (ep.extension().equals(fileExt)) {
				return ep;
			}
		}
		
		return EPowerscriptFileTypes.Unknown;
	}

	
}
