package org.darioaxel.mapper;

import java.util.List;

import org.darioaxel.util.enums.EActionElementTypes;
import org.darioaxel.util.enums.EPowerscriptFileTypes;
import org.darioaxel.util.enums.ESystemObjectNames;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionFactory;
import org.eclipse.gmt.modisco.omg.kdm.action.Addresses;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.action.Calls;
import org.eclipse.gmt.modisco.omg.kdm.action.Reads;
import org.eclipse.gmt.modisco.omg.kdm.action.Writes;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.CallableKind;
import org.eclipse.gmt.modisco.omg.kdm.code.CallableUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeAssembly;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeFactory;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.CompilationUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ControlElement;
import org.eclipse.gmt.modisco.omg.kdm.code.DataElement;
import org.eclipse.gmt.modisco.omg.kdm.code.Datatype;
import org.eclipse.gmt.modisco.omg.kdm.code.ExportKind;
import org.eclipse.gmt.modisco.omg.kdm.code.Extends;
import org.eclipse.gmt.modisco.omg.kdm.code.MemberUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodKind;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterKind;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.SharedUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableKind;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Value;
import org.eclipse.gmt.modisco.omg.kdm.core.CoreFactory;
import org.eclipse.gmt.modisco.omg.kdm.core.KDMEntity;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Annotation;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Attribute;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KdmFactory;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceRef;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceRegion;


public final class KDMElementFactory {
	
	private static final KdmFactory		KDM_FACTORY					= KdmFactory.eINSTANCE;
	private static final CodeFactory	CODE_FACTORY				= CodeFactory.eINSTANCE;
	private static final ActionFactory  ACTION_FACTORY				= ActionFactory.eINSTANCE;
	private static final CoreFactory	CORE_FACTORY				= CoreFactory.eINSTANCE;
	
	public static final String			DATAWINDOW_ANNOTATION		= "Datawindow object";
	public static final String			STRUCT_ANNOTATION			= "Struct object";
	public static final String			MENU_ANNOTATION				= "Menu object";
	public static final String			MAINCLASS_ANNOTATION		= "MainClass object";
	public static final String			USERFUNCTION_ANNOTATION		= "UserFunction object";
	public static final String			NAMESPACES_MODULE			= "Namespaces";
	public static final String			GLOBAL_NAMESPACE_NAME		= "global";
	public static final String			ON_METHOD					= "On method";
	public static final String			EVEN_METHOD					= "Event method";
	public static final String			FUNCTION					= "Function method";
		
	// SourcePackage Objects
	public static Segment createSegment() {
		Segment segment = KDM_FACTORY.createSegment();
		return segment;
	}
	
	// CodePackage Objects
	public static CodeModel createCodeModel(final String name) {
			
		CodeModel codeModel = CODE_FACTORY.createCodeModel();
		codeModel.setName(name);
		return codeModel;
	}
	
	public static CompilationUnit createCompilationUnit(final SourceFile sourceFile) {
		
		SourceRegion sourceRegion = SourceFactory.eINSTANCE.createSourceRegion();
		sourceRegion.setFile(sourceFile);
		sourceRegion.setPath(sourceFile.getPath());

		SourceRef sourceRef = SourceFactory.eINSTANCE.createSourceRef();
		sourceRef.getRegion().add(sourceRegion);

		CompilationUnit compilationUnit = CODE_FACTORY.createCompilationUnit();
		compilationUnit.setName(sourceFile.getName());
		compilationUnit.getSource().add(sourceRef);
				
		return compilationUnit;
	}
	
	public static Package createPackageUnit(final ResourceDescription resourceDescriptor) {
		
		Package packageElement = CODE_FACTORY.createPackage();
		packageElement.setName(resourceDescriptor.getName());
		
			
		return packageElement;
	}
	
	// Powerbuilder type classes
	
	public static ClassUnit createClass(String name, EPowerscriptFileTypes type) {

		ClassUnit classUnit = CODE_FACTORY.createClassUnit();
		classUnit.setName(name);
		addAnnotation(type.description(), classUnit);
		return classUnit;
	}

	private static void addAnnotation(final String text, final KDMEntity kdmEntity) {
		Annotation anno = KDM_FACTORY.createAnnotation();
		anno.setText(text);
		kdmEntity.getAnnotation().add(anno);
	}
	
	private static void addAttribute(String tag, String value, final MethodUnit method) {
		Attribute anno = KDM_FACTORY.createAttribute();
		anno.setTag(tag);
		anno.setValue(value);
		method.getAttribute().add(anno);
	}
	
	public static CodeAssembly createCodeAssembly(final String name) {
		CodeAssembly assembly = CODE_FACTORY.createCodeAssembly();
		assembly.setName(name);
				
		return assembly;
	}

	public static CompilationUnit createCompilationUnit() {
		return CODE_FACTORY.createCompilationUnit();
	}

	public static MethodUnit createMethodUnit(MethodKind methodKind, String annotation) {
		MethodUnit method = CODE_FACTORY.createMethodUnit();
		method.setKind(methodKind);
		addAnnotation(annotation, method);
		return method;
	}
	
	public static MethodUnit createMethodUnit(MethodKind methodKind, String annotation, String methodName) {
		MethodUnit method = createMethodUnit(methodKind, annotation);
		method.setName(methodName);
		
		return method;
	}

	public static ParameterUnit createParamUnit(Datatype type, ParameterKind kind, String paramName) {
		ParameterUnit param = CODE_FACTORY.createParameterUnit();
		param.setName(paramName);
		param.setKind(kind);
		if(type != null) {
			param.setType(type);
		}
				
		return param;
	}
	
	public static MemberUnit createMember(String memberName, ClassUnit memberClass, ExportKind exportKind) {
		
		MemberUnit member = CODE_FACTORY.createMemberUnit();
		member.setExport(exportKind);
		member.setName(memberName);
		member.setType(memberClass);
				
		return member;
	}

	public static Signature createSignature(List<ParameterUnit> params) {
		
		Signature sign = CODE_FACTORY.createSignature();
		sign.getParameterUnit().addAll(params);
		return sign;
	}

	public static SharedUnit createSharedUnit(String description) {
		 SharedUnit sharedUnit = CODE_FACTORY.createSharedUnit();
		 sharedUnit.setName(description);
		 
		return sharedUnit;
	}

	public static ActionElement createActionElementOnMethodCall(AbstractCodeElement member) {
		ActionElement action = ACTION_FACTORY.createActionElement();
		action.setKind(EActionElementTypes.ON_METHOD_CALL.Description());
		
		Calls call = ACTION_FACTORY.createCalls();
		call.setTo((ControlElement) member);
		
		action.getActionRelation().add(call);		
		return action;
	}
	
	public static Calls createCalls(final ActionElement expression, final ControlElement method) {
		Calls calls = ACTION_FACTORY.createCalls();
		calls.setFrom(expression);
		calls.setTo(method);
		return calls;
	}

	public static void createAttributeOnMethod(final MethodUnit method, String objectParent) {
		addAttribute(ESystemObjectNames.ON_METHOD_ATTRIBUTE.Description(), objectParent, method);
	}

	public static BlockUnit createBlockUnit(List<ActionElement> actions) {
		BlockUnit block = ACTION_FACTORY.createBlockUnit();
		for(ActionElement ae: actions) {
			block.getCodeElement().add(ae);
		}
		return block;
	}

	public static StorableUnit createLocalVariable(String id) {
		StorableUnit storableUnit = CODE_FACTORY.createStorableUnit();
		storableUnit.setKind(StorableKind.LOCAL);
		storableUnit.setName(id);
		return storableUnit;
	}

	public static ActionElement createActionElement(String description) {
		ActionElement action = ACTION_FACTORY.createActionElement();
		action.setName(description);
		action.setKind(description);
		return action;
	}

	public static Extends createExtendsRelation(Datatype from, Datatype to) {
		Extends ext = CODE_FACTORY.createExtends();
		ext.setFrom(from);
		ext.setTo(to);
		return ext;
	}

	public static ActionElement createActionElement(String description,	String annotation) {
		ActionElement ae = createActionElement(description);
		Annotation ann = KDM_FACTORY.createAnnotation();
		
		if (annotation != null) {
			ann.setText(annotation);
			ae.getAnnotation().add(ann);
		}
			
		return ae;
	}
	
	public static StorableUnit createVariable(String variableName, String storableKind, Datatype type ) {
		StorableUnit storableUnit = CODE_FACTORY.createStorableUnit();
		storableUnit.setKind(getStorableKind(storableKind));
		storableUnit.setType(type);
		storableUnit.setName(variableName);
		return storableUnit;
	}
	
	private static StorableKind getStorableKind(String kind) {
		if (kind == null) {
			return StorableKind.UNKNOWN;
		}
		return StorableKind.UNKNOWN;
	}

	public static Value createValue(String text, Datatype type) {
		Value val = CODE_FACTORY.createValue();
		val.setName(text);
		if (type == null) {
			val.setType(CODE_FACTORY.createDatatype());
		}
		val.setType(type);
		return val;
	}

	public static Writes createWrites(final ActionElement statement, final DataElement itemToBeWritten) {
		
		Writes writeAccess = ACTION_FACTORY.createWrites();
		writeAccess.setFrom(statement);
		writeAccess.setTo(itemToBeWritten);

		return writeAccess;
	}
	
	public static Reads createReads(final ActionElement statement, final DataElement itemToBeRead) {
		
		Reads readAccess = ACTION_FACTORY.createReads();
		readAccess.setFrom(statement);
		readAccess.setTo(itemToBeRead);
		return readAccess;
	}

	public static CallableUnit createCallableUnit(String text, String SQLType, String functionName) {
		CallableUnit call = CODE_FACTORY.createCallableUnit();
		addAnnotation(SQLType, call);
		addAnnotation(functionName, call);
		call.setName(text);
		call.setKind(CallableKind.STORED);
		return call;
	}

	public static Addresses createAddresses(ActionElement thisElement, StorableUnit st) {
		Addresses add = ACTION_FACTORY.createAddresses();
		add.setFrom(thisElement);
		add.setTo(st);		
		return add;
	}
}
