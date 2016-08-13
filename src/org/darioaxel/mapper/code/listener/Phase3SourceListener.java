package org.darioaxel.mapper.code.listener;

import java.util.ArrayList;
import java.util.List;

import org.darioaxel.grammar.powerscript.powerscriptBaseListener;
import org.darioaxel.grammar.powerscript.powerscriptParser;
import org.darioaxel.grammar.powerscript.powerscriptParser.CallStatementContext;
import org.darioaxel.grammar.powerscript.powerscriptParser.ObjectDeclarationContext;
import org.darioaxel.grammar.powerscript.powerscriptParser.OnImplementationBodyStatementContext;
import org.darioaxel.grammar.powerscript.powerscriptParser.OnImplementationIdentifierContext;
import org.darioaxel.grammar.powerscript.powerscriptParser.TypeDeclarationBodyContext;
import org.darioaxel.grammar.powerscript.powerscriptParser.VariableDeclarationContext;
import org.darioaxel.grammar.powerscript.powerscriptParser.VariableSelectedContext;
import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.language.LanguageUnitCache;
import org.darioaxel.util.CodeModelUtil;
import org.darioaxel.util.enums.EActionElementTypes;
import org.darioaxel.util.enums.EPowerscriptFileTypes;
import org.darioaxel.util.enums.ESQLSentenceType;
import org.darioaxel.util.enums.ESystemObjectNames;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.action.Calls;
import org.eclipse.gmt.modisco.omg.kdm.action.Reads;
import org.eclipse.gmt.modisco.omg.kdm.action.Writes;
import org.eclipse.gmt.modisco.omg.kdm.code.CallableUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeItem;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.Datatype;
import org.eclipse.gmt.modisco.omg.kdm.code.Extends;
import org.eclipse.gmt.modisco.omg.kdm.code.MemberUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodKind;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterKind;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.SharedUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Value;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class Phase3SourceListener extends powerscriptBaseListener implements PowerscriptListener{

	private final CodeModel codeModel;
	private String unitClassName;
	private LanguageUnitCache languageCache;
	private CodeElement mainCodeElement;
	private ClassUnit superClass;
	private String functionName = null;
	private List<ParameterUnit> variablesSelected = new ArrayList<ParameterUnit>();
	private boolean endSelectVariablesToReturn = false;
	
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
	public void exitTypeDeclarationBegin(powerscriptParser.TypeDeclarationBeginContext ctx) { 
		if (insideForward == true && ctx.scopeModificator() != null) {
			ClassUnit a = CodeModelUtil.getClassByName(unitClassName, codeModel);
			superClass = CodeModelUtil.getClassByName(ctx.typeDeclarationBeginIdentifier().typeParentIdentifier().getText(), codeModel);
			if (superClass == null) {
				//If there is no parent class defined, I defined it as a system.object 
				superClass = KDMElementFactory.createClass(ctx.typeDeclarationBeginIdentifier().typeParentIdentifier().getText(),
														EPowerscriptFileTypes.System_object);
				addNewSystemObject(superClass);
			}
			Extends ext = KDMElementFactory.createExtendsRelation((Datatype) a,(Datatype) superClass);
			CodeModelUtil.addCodeRelationship(ext, unitClassName, codeModel);			
		}		
	}
		
	@Override 
	public void exitOnImplementation(powerscriptParser.OnImplementationContext ctx) {
		String onMethodObjectName = ctx.onImplementationHead().onImplementationIdentifier().expression().getText();
		
		MethodUnit method = CodeModelUtil.getMethodUnitByAttributeValue(onMethodObjectName, unitClassName, codeModel);
		if (method == null) return;
		
		if (ctx.onImplementationBody() == null) return;
		List<ActionElement> actions = new ArrayList<ActionElement>();		
		
		for(OnImplementationBodyStatementContext st: ctx.onImplementationBody().onImplementationBodyStatement()) {
			ActionElement ae = null;
			if (st.statementBlock().objectDeclaration() != null) {
				ae = objectDeclarationAction(st.statementBlock().objectDeclaration());
			}
			if (st.statementBlock().variableDeclaration() != null) {
				ae = variableDeclarationAction(st.statementBlock().variableDeclaration());				
			}
			if (st.statementBlock().statement() != null) {
				if (st.statementBlock().statement().callStatement() != null) {
					ae = callStatementAction (st.statementBlock().statement().callStatement(), method);					
				}
			}
			if (ae != null) actions.add(ae);
		}
	
	
		BlockUnit block = KDMElementFactory.createBlockUnit(actions);
		CodeModelUtil.addBlockUnitToOnMethod(block, onMethodObjectName, unitClassName, codeModel);
		
	}

	private ActionElement callStatementAction(CallStatementContext callStatement, MethodUnit method ) {
		ActionElement ae = null;
		
		if (callStatement.callStatementIdentifier().getText().equals("super")) {
			
			String creatorTypeId ="";
			MethodUnit methodCalled = null;
						
			if (callStatement.creatorType() != null) {
			//Exp: call super :: creatorType	
				creatorTypeId = callStatement.creatorType().getText();
								
				methodCalled = CodeModelUtil.getOnMethodFromClass(superClass.getName(), getMethodKind(creatorTypeId), superClass);
				if (methodCalled == null) {
					methodCalled = createOnMethod(creatorTypeId, superClass.getName(), superClass.getName());
				}
			}			
			
			ae = KDMElementFactory.createActionElement(EActionElementTypes.METHOD_INVOCATION.Description(), "On Method Called");
			Calls ar = KDMElementFactory.createCalls(ae, methodCalled);
			ae.getActionRelation().add(ar);
		}
		
		return ae == null ? null : ae;
	}
	
	private MethodUnit createOnMethod(String type, String parent, String className) {
			
		MethodUnit method= KDMElementFactory.createMethodUnit(getMethodKind(type), KDMElementFactory.ON_METHOD);
		KDMElementFactory.createAttributeOnMethod(method, parent);
		
		CodeModelUtil.addMethodToClassUnit(className, method, codeModel);
		
		return method;
	}

	private ActionElement variableDeclarationAction(VariableDeclarationContext variableDeclaration) {
		
		return null;
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
	
	private ActionElement objectDeclarationAction(ObjectDeclarationContext objectDeclaration) {
		String storeName = objectDeclaration.objectDeclarationIdentifier().getText();
		String type = "unknown";
		ActionElement ae = null;
		
		if (objectDeclaration.objectDeclarationTypeIdentifier() == null) {
		//'this' '.' objectDeclarationIdentifier 
			StorableUnit st = CodeModelUtil.getStorableUnitFromClass(storeName, unitClassName, codeModel);
			if (st == null) {
				st = KDMElementFactory.createVariable(storeName, null, null);
				CodeModelUtil.addStorableUnitToClass(st, unitClassName, codeModel);
			}
			ae = KDMElementFactory.createActionElement(EActionElementTypes.ASSIGN.Description(), null);
			Reads reads = null;
			if (objectDeclaration.objectValueInstantiation().literal() != null) {
				Value val = KDMElementFactory.createValue(objectDeclaration.objectValueInstantiation().literal().getText(), null);
				reads = KDMElementFactory.createReads(ae, val);
				ae.getActionRelation().add(reads);
			}
			else {
				MemberUnit member = CodeModelUtil.getMemberUnit(objectDeclaration.objectValueInstantiation().Identifier().getText(), codeModel);
				if ( member == null) {
					return null;
				}
				reads = KDMElementFactory.createReads(ae, member);
				ae.getActionRelation().add(reads);
			}
			
			if  (reads != null) {
				ae.getActionRelation().add(reads);
			}
			Writes writes = KDMElementFactory.createWrites(ae, st);
			ae.getActionRelation().add(writes);
			
		}
		return ae;
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
	private void addNewSystemObject(ClassUnit memberClass) {
		SharedUnit systemObjectsUnit = CodeModelUtil.getSystemObjectClass(codeModel);
		if (systemObjectsUnit == null) {
			systemObjectsUnit = KDMElementFactory.createSharedUnit(ESystemObjectNames.SYSTEM_OBJECT_UNIT.Description());						
		}
		systemObjectsUnit.getCodeElement().add(memberClass);
		CodeModelUtil.addSharedUnit(systemObjectsUnit, codeModel);
	}
	
	@Override 
	public void exitFunctionHeaderIdentification(powerscriptParser.FunctionHeaderIdentificationContext ctx) {
		functionName = ctx.functionIdentifier().getText();
	}
	
	@Override 
	public void exitSelectStatement(powerscriptParser.SelectStatementContext ctx) { 
		if (functionName == null) return;
		
		CallableUnit select = KDMElementFactory.createCallableUnit(ctx.getText(), ESQLSentenceType.SELECT.Description(), functionName);
		if (CodeModelUtil.getSystemSQLSentenceClass(codeModel) == null) {
			SharedUnit sqlUnit = KDMElementFactory.createSharedUnit(ESystemObjectNames.SYSTEM_SQL_SENTENCE_UNIT.Description());
			CodeModelUtil.addSharedUnit(sqlUnit, codeModel);
		}
		for(ParameterUnit param : variablesSelected) {
			select.getCodeElement().add(param);
		}
		CodeModelUtil.addSQLSentence(select, codeModel);	
		variablesSelected = new ArrayList<ParameterUnit>();
		endSelectVariablesToReturn = false;
	}
	
	@Override 
	public void exitVariablesSelected(powerscriptParser.VariablesSelectedContext ctx) { 
		for(VariableSelectedContext v  : ctx.variableSelected()) {			
			ParameterUnit parameter = KDMElementFactory.createParamUnit(null, ParameterKind.RETURN, v.Identifier().getText());
			variablesSelected.add(parameter);
		}		
		endSelectVariablesToReturn = true;
	}	
	@Override 
	public void exitVariableSelected(powerscriptParser.VariableSelectedContext ctx) {
		if (endSelectVariablesToReturn) {
			ParameterUnit parameter = KDMElementFactory.createParamUnit(null, ParameterKind.UNKNOWN, ctx.Identifier().getText());
			variablesSelected.add(parameter);
		}
	}
}
