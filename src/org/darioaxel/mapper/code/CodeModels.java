package org.darioaxel.mapper.code;

import java.util.Properties;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.listener.Phase1LibraryListener;
import org.darioaxel.mapper.code.listener.Phase1ProjectListener;
import org.darioaxel.mapper.code.listener.Phase1SourceListener;
import org.darioaxel.mapper.code.listener.Phase2SourceListener;
import org.darioaxel.mapper.code.listener.Phase3SourceListener;
import org.darioaxel.mapper.code.parser.LibraryDescriptorTypeParser;
import org.darioaxel.mapper.code.parser.ProjectDescriptorTypeParser;
import org.darioaxel.mapper.code.parser.SourceFileTypeParserNew;
import org.darioaxel.mapper.source.walker.InventoryModelWalkerNew;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;

public class CodeModels {	
				
	public static CodeModel create(final InventoryModel inventoryModel) {		
	
		final CodeModel codeModel = KDMElementFactory.createCodeModel("");
		
		phase1(inventoryModel, codeModel);
		phase2(inventoryModel,codeModel);
		phase3(inventoryModel,codeModel);		
		return codeModel;
	}
	
	public static CodeModel create(final InventoryModel inventoryModel, Properties prop) {
		
		int toPhase = Integer.valueOf(prop.getProperty("PhasesToGenerate"));
		final CodeModel codeModel = KDMElementFactory.createCodeModel("");
				
		if (toPhase >= 1)
				phase1(inventoryModel, codeModel);
		if (toPhase > 2)
				phase2(inventoryModel,codeModel);
		if (toPhase == 3)			
			phase3(inventoryModel,codeModel);		
		
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

	private static void phase3(InventoryModel inventoryModel, CodeModel codeModel) {
		
		final InventoryModelWalkerNew walker = new InventoryModelWalkerNew(inventoryModel);
		Phase3SourceListener sourceListener = new Phase3SourceListener(codeModel);
		SourceFileTypeParserNew sourceParser = new SourceFileTypeParserNew();
		
		sourceParser.addListener(sourceListener);
		walker.setSourceFileParser(sourceParser);	
		
		walker.walk();		
	}
}
