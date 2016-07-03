package org.darioaxel.mapper.code;

import java.util.Properties;

import org.darioaxel.mapper.source.InventoryModelWalker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.Module;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class CodeModelCreator {

	private final String				rootDir;

	private CodeModel					internalCodeModel;
	private CodeModel					externalCodeModel;
	
	private Module						valueRepository;

	private final int					toPhase;

	public CodeModelCreator(final Properties prop, final int toPhase) {
		this.toPhase = toPhase;
		rootDir = prop.getProperty("ExternalDatatypeInfoRepository.rootDir");		
	}

	public void create(final InventoryModel inventoryModel, IProgressMonitor monitor) {
	
		if (monitor == null) monitor = new NullProgressMonitor();
		final InventoryModelWalker walker = new InventoryModelWalker(inventoryModel);
		
		monitor.beginTask("Extracting code model from inventory model...", 1);
		try {
			monitor.subTask("Searching for used programming languages...");
		

			if (monitor.isCanceled()) throw new OperationCanceledException();
			// process inventory model
			buildCodeModels(walker, monitor);
//
//			// internalCodeModel = sourceFileTransformer.getInternalCodeModel();
//			// internalCodeModel.getCodeElement().addAll(necessaryLanguageUnits);
//			//
//			// externalCodeModel = sourceFileTransformer.getExternalCodeModel();
		} finally {
			monitor.done();
		}
	}

	private void buildCodeModels(final InventoryModelWalker walker, final IProgressMonitor monitor) {
		// external datatypeinfo repository
//		DatatypeInfoCacheLoader datatypeInfoLoader = new DatatypeInfoCacheLoader(new DatatypeInfoFileSystemLoader());
//		ExternalDatatypeInfoRepository externalDatatypeInfoRepository = new ExternalDatatypeInfoRepository(rootDir,
//				datatypeInfoLoader);
//
//		// 1. transformation
//		if (toPhase < 1) return;
//		monitor.subTask("Transforming own types...");
//		CSharpTypeParser sourceFileTypeParser = new CSharpTypeParser();
//		CodeModel initialInternalCodeModel = buildTypes(sourceFileTypeParser, walker, monitor);
//
//		if (monitor.isCanceled()) throw new OperationCanceledException();
//
//		// 2. transformation
//		if (toPhase < 2) return;
//		monitor.subTask("Transforming own types' member declarations and method definitions...");
//		CSharpMemberDeclarationParser cSharpParser1 = new CSharpMemberDeclarationParser(languageUnitDetectorVisitor,
//				externalDatatypeInfoRepository);
//		buildMemberDeclarations(initialInternalCodeModel, cSharpParser1, walker, monitor);
//
//		if (monitor.isCanceled()) throw new OperationCanceledException();
//
//		// 3. transformation
//		if (toPhase < 3) return;
//		monitor.subTask("Transforming expressions...");
//		CSharpSourceFileParser cSharpParser2 = new CSharpSourceFileParser(languageUnitDetectorVisitor,
//				externalDatatypeInfoRepository);
//		buildSequentially(internalCodeModel, cSharpParser2, walker, monitor);

		// buildConcurrently(cSharpParser, walker, monitor);
	}

//	private CodeModel buildTypes(final CSharpTypeParser sourceFileTypeParser, final InventoryModelWalker walker,
//			final IProgressMonitor monitor) {
	
//		ActionSourceFileVisitor visitor = new ActionSourceFileVisitor(monitor);
//		visitor.addSourceFileParser(sourceFileTypeParser);
//		walker.walk(visitor);
//
//		// both only for test purposes
//		this.internalCodeModel = visitor.getInternalCodeModel();
//		this.externalCodeModel = KDMElementFactory.createGenericCodeModel("External CodeModel", GlobalKind.EXTERNAL);
//
//		return visitor.getInternalCodeModel();
//	}



	public CodeModel getInternalCodeModel() {
		return this.internalCodeModel;
	}

	public CodeModel getExternalCodeModel() {
		return this.externalCodeModel;
	}
}
