package org.darioaxel.mapper;

import org.darioaxel.mapper.code.MoDiscoKDM;
import org.eclipse.core.runtime.Assert;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionFactory;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.action.Calls;
import org.eclipse.gmt.modisco.omg.kdm.action.Creates;
import org.eclipse.gmt.modisco.omg.kdm.action.Reads;
import org.eclipse.gmt.modisco.omg.kdm.action.Writes;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeAssembly;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeFactory;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeItem;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.CompilationUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ControlElement;
import org.eclipse.gmt.modisco.omg.kdm.code.DataElement;
import org.eclipse.gmt.modisco.omg.kdm.code.Datatype;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.HasValue;
import org.eclipse.gmt.modisco.omg.kdm.code.Imports;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MemberUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Module;
import org.eclipse.gmt.modisco.omg.kdm.code.Namespace;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterKind;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableKind;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.TemplateParameter;
import org.eclipse.gmt.modisco.omg.kdm.code.TemplateUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Value;
import org.eclipse.gmt.modisco.omg.kdm.core.Element;
import org.eclipse.gmt.modisco.omg.kdm.core.KDMEntity;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Annotation;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Attribute;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KdmFactory;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Stereotype;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceRef;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceRegion;


public final class KDMElementFactory {

	public static enum GlobalKind {
		INTERNAL, EXTERNAL
	}

	private static final KdmFactory		KDM_FACTORY					= KdmFactory.eINSTANCE;
	private static final CodeFactory	CODE_FACTORY				= CodeFactory.eINSTANCE;
	
	public static final String			DATAWINDOW_ANNOTATION		= "Datawindow object";
	public static final String			STRUCT_ANNOTATION			= "Struct object";
	public static final String			MENU_ANNOTATION				= "Menu object";
	public static final String			MAINCLASS_ANNOTATION		= "MainClass object";
	public static final String			USERFUNCTION_ANNOTATION		= "UserFunction object";
	public static final String			NAMESPACES_MODULE			= "Namespaces";
	public static final String			GLOBAL_NAMESPACE_NAME		= "global";
	
	private KDMElementFactory() {
	}
	
	// SourcePackage Objects
	public static Segment createSegment() {
		Segment segment = KDM_FACTORY.createSegment();
		return segment;
	}
	
	// CodePackage Objects
	public static CodeModel createGenericCodeModel(final String name, final GlobalKind kind) {
			
		Module module = CODE_FACTORY.createModule();
		module.setName(NAMESPACES_MODULE);		

		CodeModel codeModel = CODE_FACTORY.createCodeModel();
		codeModel.setName(name);
		codeModel.getCodeElement().add(module);

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
	public static ClassUnit createDatawindow(final SourceFile sourceFile) {
		ClassUnit struct = CODE_FACTORY.createClassUnit();
		addAnnotation(DATAWINDOW_ANNOTATION, struct);
		return struct;
	}
	
	public static ClassUnit createMenu(final SourceFile sourceFile) {
		ClassUnit menu = CODE_FACTORY.createClassUnit();
		addAnnotation(MENU_ANNOTATION, menu);
		return menu;
	}
	
	public static ClassUnit createStructure(final SourceFile sourceFile) {
		ClassUnit struct = CODE_FACTORY.createClassUnit();
		addAnnotation(STRUCT_ANNOTATION, struct);
		return struct;
	}
	
	public static ClassUnit createUserFunctionClass(final SourceFile sourceFile) {
		ClassUnit functionClass = CODE_FACTORY.createClassUnit();
		addAnnotation(USERFUNCTION_ANNOTATION, functionClass);
		return functionClass;
	}
	
	public static ClassUnit createMainClass(final SourceFile sourceFile) {
		ClassUnit mainClass = CODE_FACTORY.createClassUnit();
		addAnnotation(MAINCLASS_ANNOTATION, mainClass);
		return mainClass;
	}
	
	private static void addAnnotation(final String text, final KDMEntity kdmEntity) {
		Annotation anno = KDM_FACTORY.createAnnotation();
		anno.setText(text);
		kdmEntity.getAnnotation().add(anno);
	}
}
