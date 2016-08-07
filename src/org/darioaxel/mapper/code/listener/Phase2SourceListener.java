package org.darioaxel.mapper.code.listener;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.darioaxel.grammar.powerscript.powerscriptBaseListener;
import org.darioaxel.grammar.powerscript.powerscriptParser;
import org.darioaxel.grammar.powerscript.powerscriptParser.ParameterDeclaratorContext;
import org.darioaxel.grammar.powerscript.powerscriptParser.ParametersListContext;
import org.darioaxel.grammar.powerscript.powerscriptParser.ScopeModificatorContext;
import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.language.LanguageUnitCache;
import org.darioaxel.mapper.code.language.PowerscriptLanguageUnitCache;
import org.darioaxel.util.CodeModelUtil;
import org.darioaxel.util.enums.EPowerscriptFileTypes;
import org.darioaxel.util.enums.ESystemObjectNames;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.Datatype;
import org.eclipse.gmt.modisco.omg.kdm.code.ExportKind;
import org.eclipse.gmt.modisco.omg.kdm.code.MemberUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodKind;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterKind;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.SharedUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class Phase2SourceListener extends powerscriptBaseListener implements PowerscriptListener{

	private final CodeModel codeModel;
	private String unitClassName;
	private LanguageUnitCache languageCache;
	
	private boolean insideForward = false;
	private boolean insideFunctionDeclarationBlock = false;
	private boolean functionDeclarationEnded = false;
	
	public Phase2SourceListener (final CodeModel codeModel) {
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
	public void exitTypeDeclarationBegin(powerscriptParser.TypeDeclarationBeginContext ctx) { 
		if (insideForward == true) {
			ScopeModificatorContext scopeModificator = ctx.scopeModificator();
			
			if(scopeModificator == null) {
				String memberName = ctx.typeDeclarationBeginIdentifier().typeIdentifier().getText();
				String parentClassName = ctx.typeDeclarationBeginIdentifier().typeParentIdentifier().getText();
				
				ClassUnit memberClass = CodeModelUtil.getClassByName(parentClassName, codeModel);	
				if (memberClass == null) {
					//If there is no parent class defined, I defined it as a system.object 
					memberClass = KDMElementFactory.createClass(parentClassName,EPowerscriptFileTypes.System_object);
					addNewSystemObject(memberClass);
				}
				MemberUnit member = KDMElementFactory.createMember(memberName, memberClass, ExportKind.PROTECTED);
								
				CodeModelUtil.addMemberUnitToClassUnit(member, unitClassName, codeModel);				
			}			
		}		
	}	
	
	private void addNewSystemObject(ClassUnit memberClass) {
		SharedUnit systemObjectsUnit = CodeModelUtil.getSystemObjectClass(codeModel);
		if (systemObjectsUnit == null) {
			systemObjectsUnit = KDMElementFactory.createSharedUnit(ESystemObjectNames.SYSTEM_OBJECT_UNIT.Description());						
		}
		systemObjectsUnit.getCodeElement().add(memberClass);
		CodeModelUtil.addSharedUnit(systemObjectsUnit, codeModel);
	}
	
	@Override 
	public void exitOnImplementationIdentifier(powerscriptParser.OnImplementationIdentifierContext ctx) {			
			String creatorType = ctx.creatorType().getText();			
			MethodUnit method= KDMElementFactory.createMethodUnit(getMethodKind(creatorType), KDMElementFactory.ON_METHOD);
			CodeModelUtil.addMethodToClassUnit(unitClassName, method, codeModel);
	}
	
	@Override 
	public void enterEventDeclarationWithCreator(powerscriptParser.EventDeclarationWithCreatorContext ctx) {
			String creatorType = ctx.getChild(1).getText();
			MethodUnit method = KDMElementFactory.createMethodUnit(getMethodKind(creatorType), KDMElementFactory.EVEN_METHOD);	
			CodeModelUtil.addMethodToClassUnit(unitClassName, method, codeModel);
	}	
	
	@Override 
	public void enterFunctionDeclarationBlock(powerscriptParser.FunctionDeclarationBlockContext ctx) { 
		insideFunctionDeclarationBlock = true;
	}
	
	@Override 
	public void exitFunctionDeclarationEnd(powerscriptParser.FunctionDeclarationEndContext ctx) {
		functionDeclarationEnded = true;
	}
	
	@Override 
	public void exitFunctionDeclaration(powerscriptParser.FunctionDeclarationContext ctx) { 
			
		if(!(insideFunctionDeclarationBlock 
				&& functionDeclarationEnded)) return;
		
		String paramTypeName = ctx.functionDeclarationHeader().functionHeaderIdentification().dataTypeName().getText();
		List<ParameterUnit> params = getParameters(ctx.parametersList());
		String functionName = ctx.functionDeclarationHeader().functionHeaderIdentification().functionIdentifier().getText();
			
		MethodUnit function = KDMElementFactory.createMethodUnit(getMethodKind("method"), KDMElementFactory.FUNCTION, functionName);			
		Datatype type = getDatatype(paramTypeName);

		ParameterUnit functionReturnType = KDMElementFactory.createParamUnit(type, ParameterKind.RETURN, "");
		params.add(functionReturnType);

		Signature signature = KDMElementFactory.createSignature(params);
		function.getCodeElement().add(signature);

		CodeModelUtil.addMethodToClassUnit(unitClassName, function, codeModel);

		functionDeclarationEnded = false;
	}

	private Datatype getDatatype(String paramTypeName) {
		Datatype type = languageCache.getDatatypeFromString(paramTypeName);
		
		if(type == null) {
			 type = CodeModelUtil.getDatatypeOfMember(paramTypeName, unitClassName, codeModel);				
		}
		return type;
	}
	
	private List<ParameterUnit> getParameters(ParametersListContext parameters) {
		
		List<ParameterUnit> params = new ArrayList<ParameterUnit>();
		
		if ( parameters.parametersDeclarators() != null) {
			for(ParameterDeclaratorContext p : parameters.parametersDeclarators().parameterDeclarator()) {
				String paramTypeName = p.primitiveType().getText();
				String paramId = p.parameterIdentifier().getText();
				
				Datatype type = getDatatype(paramTypeName);
				params.add(KDMElementFactory.createParamUnit(type, ParameterKind.BY_VALUE, paramId));
			}
		}	
		
		return params;
	}
	
	@Override 
	public void exitFunctionDeclarationBlock(powerscriptParser.FunctionDeclarationBlockContext ctx) { 
		insideFunctionDeclarationBlock = false;
	}
		
	private MethodKind getMethodKind(String creatorType) {
		
		switch(creatorType) {
		case "method": return MethodKind.METHOD;
		case "create": return MethodKind.CONSTRUCTOR;
		case "destroy": return MethodKind.DESTRUCTOR;
		case "open":
		case "close": return MethodKind.OPERATOR;
		default:  return MethodKind.UNKNOWN;
		}		
	}	
}
