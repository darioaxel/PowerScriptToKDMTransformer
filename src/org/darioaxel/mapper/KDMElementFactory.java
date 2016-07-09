package org.darioaxel.mapper;

import org.darioaxel.mapper.code.MoDiscoKDM;
import org.darioaxel.util.enums.EPowerscriptFileTypes;
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
	
	private static final KdmFactory		KDM_FACTORY					= KdmFactory.eINSTANCE;
	private static final CodeFactory	CODE_FACTORY				= CodeFactory.eINSTANCE;
	
	public static final String			DATAWINDOW_ANNOTATION		= "Datawindow object";
	public static final String			STRUCT_ANNOTATION			= "Struct object";
	public static final String			MENU_ANNOTATION				= "Menu object";
	public static final String			MAINCLASS_ANNOTATION		= "MainClass object";
	public static final String			USERFUNCTION_ANNOTATION		= "UserFunction object";
	public static final String			NAMESPACES_MODULE			= "Namespaces";
	public static final String			GLOBAL_NAMESPACE_NAME		= "global";
		
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

	public static CodeAssembly createCodeAssembly(final String name) {
		CodeAssembly assembly = CODE_FACTORY.createCodeAssembly();
		assembly.setName(name);
				
		return assembly;
	}

	public static CompilationUnit createCompilationUnit() {
		return CODE_FACTORY.createCompilationUnit();
	}
}
