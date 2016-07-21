package org.darioaxel.mapper.code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.darioaxel.mapper.IMapperElementRepository;
import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.listener.Phase1LibraryListener;
import org.darioaxel.mapper.code.listener.Phase1ProjectListener;
import org.darioaxel.mapper.code.listener.Phase1SourceListener;
import org.darioaxel.mapper.code.listener.Phase2SourceListener;
import org.darioaxel.mapper.code.parser.LibraryDescriptorTypeParser;
import org.darioaxel.mapper.code.parser.ProjectDescriptorTypeParser;
import org.darioaxel.mapper.code.parser.SourceFileTypeParserNew;
import org.darioaxel.mapper.code.parser.TypeParser;
import org.darioaxel.mapper.source.listener.SourceFileListener;
import org.darioaxel.mapper.source.walker.InventoryModelWalker;
import org.darioaxel.mapper.source.walker.InventoryModelWalkerNew;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.LanguageUnit;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;

public class NewCodeModels {	
				
	public static CodeModel create(final InventoryModel inventoryModel) {
		
	
		final CodeModel codeModel = KDMElementFactory.createCodeModel("");
		final List<LanguageUnit> languagesUsed = new ArrayList<LanguageUnit>();
		
	//	if (monitor == null) monitor = new NullProgressMonitor();
	
//		monitor.beginTask("Extracting code model from inventory model...", 1);
		try {
	//		if (monitor.isCanceled()) throw new OperationCanceledException();
			phase1(inventoryModel, codeModel);
			phase2(inventoryModel,codeModel);
		} finally {
//			monitor.done();
		}
		
		return codeModel;
	}	
	public static CodeModel create(final InventoryModel inventoryModel, Properties prop) {
		
		int toPhase = Integer.valueOf(prop.getProperty("PhasesToGenerate"));
		final CodeModel codeModel = KDMElementFactory.createCodeModel("");
		final List<LanguageUnit> languagesUsed = new ArrayList<LanguageUnit>();
		
	//	if (monitor == null) monitor = new NullProgressMonitor();
	
//		monitor.beginTask("Extracting code model from inventory model...", 1);
		try {
	//		if (monitor.isCanceled()) throw new OperationCanceledException();
			
		if (toPhase > 1)
				phase1(inventoryModel, codeModel);
		if (toPhase > 2)
				phase2(inventoryModel,codeModel);
		if (toPhase == 3)			
			phase3(inventoryModel,codeModel);
		} finally {
//			monitor.done();
		}
		
		return codeModel;
	}

	private static void phase1(final InventoryModel inventoryModel, final CodeModel codeModel) {
		
		final InventoryModelWalkerNew walker = new InventoryModelWalkerNew(inventoryModel);
		SourceFileTypeParserNew sourceParser = new SourceFileTypeParserNew();
		LibraryDescriptorTypeParser libraryParser = new LibraryDescriptorTypeParser();
		ProjectDescriptorTypeParser projectParser = new ProjectDescriptorTypeParser();
		
		Phase1SourceListener sourceListener = new Phase1SourceListener(codeModel);
		Phase1ProjectListener projectListener = new Phase1ProjectListener(codeModel);
		Phase1LibraryListener libraryListener = new Phase1LibraryListener(codeModel);
		
		sourceParser.addListener(sourceListener);
		projectParser.addListener(projectListener);
		libraryParser.addListener(libraryListener);
		
		
		walker.setSourceFileParser(sourceParser);
		walker.addResourceDescriptionParser(libraryParser);
		walker.addResourceDescriptionParser(projectParser);
			
		walker.walk();	
	}
	
	private static void phase2(InventoryModel inventoryModel, final CodeModel codeModel) {
		
		final InventoryModelWalkerNew walker = new InventoryModelWalkerNew(inventoryModel);
		Phase2SourceListener sourceListener = new Phase2SourceListener(codeModel);
		SourceFileTypeParserNew sourceParser = new SourceFileTypeParserNew();
		
		sourceParser.addListener(sourceListener);
		walker.setSourceFileParser(sourceParser);	
		
		walker.walk();
	}
	

	private static void phase3(InventoryModel inventoryModel,
			CodeModel codeModel) {
		// TODO Auto-generated method stub
		
	}
}
