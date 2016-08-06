package org.darioaxel.mapper.code.listener;

import org.darioaxel.grammar.powerscript.powerscriptBaseListener;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class Phase3SourceListener extends powerscriptBaseListener implements PowerscriptListener{

	private final CodeModel codeModel;
	
	public Phase3SourceListener (final CodeModel codeModel) {
		this.codeModel = codeModel;
	}	
	
	@Override
	public void setClassUnitName(String name) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void setCompilationUnit(SourceFile source) {
		// TODO Auto-generated method stub		
	}
}
