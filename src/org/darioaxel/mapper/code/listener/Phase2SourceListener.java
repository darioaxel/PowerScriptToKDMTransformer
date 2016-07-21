package org.darioaxel.mapper.code.listener;

import java.util.Stack;

import org.darioaxel.grammar.powerscript.powerscriptBaseListener;
import org.darioaxel.grammar.powerscript.powerscriptParser;
import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.util.CodeModelUtil;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.CompilationUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodKind;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class Phase2SourceListener extends powerscriptBaseListener implements PowerscriptListener{

	private CodeModel codeModel;
	private Stack<String> stack = new Stack<String>();
	private String unitClassName;
	
	public Phase2SourceListener (final CodeModel codeModel) {
		this.codeModel = codeModel;
	}	
	
	@Override
	public void setClassUnitName(String name) {
		unitClassName = name;
	}
	
	@Override
	public void setCompilationUnit(SourceFile source) {
		String compilationUnitName = source.getName();
		unitClassName = CodeModelUtil.getClassUnitName(codeModel, source);		
	}
	
	@Override
	public void enterForwardDeclaration(powerscriptParser.ForwardDeclarationContext ctx) { 
		System.out.println("enterForward");
	}	
		
	@Override 
	public void exitOnImplementationIdentifier(powerscriptParser.OnImplementationIdentifierContext ctx) {
			
			String creatorType = ctx.getChild(2).getText();			
			MethodUnit method= KDMElementFactory.createMethodUnit(getMethodKind(creatorType), KDMElementFactory.ON_METHOD);
	}
	
	@Override 
	public void enterEventDeclarationWithCreator(powerscriptParser.EventDeclarationWithCreatorContext ctx) {
			String creatorType = ctx.getChild(1).getText();
			MethodUnit method = KDMElementFactory.createMethodUnit(getMethodKind(creatorType), KDMElementFactory.EVEN_METHOD);	
			CodeModelUtil.addMethodToClassUnit(unitClassName, method, codeModel);
	}
		
	private MethodKind getMethodKind(String creatorType) {
		
		switch(creatorType) {
		case "create": return MethodKind.CONSTRUCTOR;
		case "destroy": return MethodKind.DESTRUCTOR;
		case "open":
		case "close": return MethodKind.OPERATOR;
		default:  return MethodKind.UNKNOWN;
		}
		
	}			
}
