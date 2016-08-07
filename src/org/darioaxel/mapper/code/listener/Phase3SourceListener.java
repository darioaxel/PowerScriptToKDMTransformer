package org.darioaxel.mapper.code.listener;

import java.util.ArrayList;
import java.util.List;

import org.darioaxel.grammar.powerscript.powerscriptBaseListener;
import org.darioaxel.grammar.powerscript.powerscriptParser;
import org.darioaxel.grammar.powerscript.powerscriptParser.TypeDeclarationBodyContext;
import org.darioaxel.grammar.powerscript.powerscriptParser.VariableDeclarationContext;
import org.darioaxel.mapper.code.language.LanguageUnitCache;
import org.darioaxel.util.CodeModelUtil;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class Phase3SourceListener extends powerscriptBaseListener implements PowerscriptListener{

	private final CodeModel codeModel;
	private String unitClassName;
	private LanguageUnitCache languageCache;
	private CodeElement mainCodeElement;
	
	private boolean insideForward = false;
	
	public Phase3SourceListener (final CodeModel codeModel) {
		this.codeModel = codeModel;
	}	
	
	@Override
	public void setClassUnitName(String name) {
		unitClassName = name;		
	}

	@Override
	public void setCompilationUnit(SourceFile source) {
		languageCache = new LanguageUnitCache(CodeModelUtil.getLanguageUnit(codeModel, source));
		unitClassName = CodeModelUtil.getClassUnitName(codeModel, source);		
	}
	
	@Override
	public void enterForwardDeclaration(powerscriptParser.ForwardDeclarationContext ctx) { 
		insideForward = true;
	}
	
	@Override
	public void exitForwardDeclaration(powerscriptParser.ForwardDeclarationContext ctx) { 
		insideForward = false;
	}
	@Override 
	public void exitOnImplementation(powerscriptParser.OnImplementationContext ctx) {
		
	}
	
/*	
	@Override 
	public void exitTypeDeclaration(powerscriptParser.TypeDeclarationContext ctx) { 
		
		if (insideForward) return;
		
		if (!ctx.typeDeclarationBody().isEmpty()) {
			List<VariableDeclarationContext> variables = new ArrayList<VariableDeclarationContext>();
			for(TypeDeclarationBodyContext tdbc : ctx.typeDeclarationBody()) {
				VariableDeclarationContext var = tdbc.variableDeclaration();
				if (var != null) variables.add(var);
			}
			List<ActionElement> variablesActionElements = getVariable(variables); 			
		}		
	}

	private List<ActionElement> getVariable(List<VariableDeclarationContext> variables) {
		
		return null;
	}
*/
	

}
