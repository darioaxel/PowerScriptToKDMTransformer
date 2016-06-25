package org.darioaxel.mapper.code;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//import mapping.KDMElementFactory;
//import mapping.KDMElementFactory.GlobalKind;
//import mapping.action.visitor.ActionSourceFileVisitor;
//import mapping.code.extern.ExternalDatatypeInfoRepository;
//import mapping.code.extern.loader.DatatypeInfoCacheLoader;
//import mapping.code.extern.loader.DatatypeInfoFileSystemLoader;
//import mapping.code.parser.CSharpMemberDeclarationParser;
//import mapping.code.parser.CSharpSourceFileParser;
//import mapping.code.parser.CSharpTypeParser;
//import mapping.code.visitor.LanguageUnitDetectorVisitor;
//import mapping.code.visitor.QueuingSourceFileVisitor;
//import mapping.code.visitor.SequentialParseVisitor;
//import mapping.source.InventoryModelWalker;
//import mapping.source.visitor.SourceFileCounter;
//import mapping.source.visitor.SourceFileVisitor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.LanguageUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Module;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class CodeModelCreator {

	private final String				rootDir;

	private CodeModel					internalCodeModel;
	private CodeModel					externalCodeModel;
	//private LanguageUnitDetectorVisitor	languageUnitDetectorVisitor;

	private Module						valueRepository;

	private final int					toPhase;

	public CodeModelCreator(final Properties prop, final int toPhase) {
		this.toPhase = toPhase;
		rootDir = prop.getProperty("ExternalDatatypeInfoRepository.rootDir");
	}

	public void create(final InventoryModel inventoryModel, IProgressMonitor monitor) {
		// ensure there is a monitor of some sort
		if (monitor == null) monitor = new NullProgressMonitor();

		final InventoryModelWalker walker = new InventoryModelWalker(inventoryModel);

//		SourceFileCounter sourceFileCounter = new SourceFileCounter();
//		walker.walk(sourceFileCounter);
//		final int numSourceFiles = sourceFileCounter.getAmount();

		// 3 passes + language detection
		monitor.beginTask("Extracting code model from inventory model...", 1 + toPhase * numSourceFiles);
		try {
			monitor.subTask("Searching for used programming languages...");
			languageUnitDetectorVisitor = new LanguageUnitDetectorVisitor();
			walker.walk(languageUnitDetectorVisitor);
			monitor.worked(1);

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
