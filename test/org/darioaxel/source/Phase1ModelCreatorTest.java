package org.darioaxel.source;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.PowerscriptElementRepository;
import org.darioaxel.mapper.code.listener.PowerscriptPhase1Listener;
import org.darioaxel.mapper.code.parser.PowerscriptSourceFileTypeParser;
import org.darioaxel.mapper.code.parser.TypeParser;
import org.darioaxel.mapper.source.InventoryModels;
import org.darioaxel.mapper.source.walker.PowerscriptPhase1InventoryModelWalker;
import org.darioaxel.util.FileUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.junit.Test;

public class Phase1ModelCreatorTest {
	
	private PowerscriptElementRepository elements = new PowerscriptElementRepository();	
	Path root = Paths.get("/home/darioaxel/git/PowerScriptGrammar/resources/advanced/real/myproject");
	Path result = Paths.get("/home/darioaxel/git/PowerScriptGrammar/testing_results/createPhase1ModelTest.xmi");

	@Test
	public void createPhase1Test() {
		
		InventoryModel inventoryModel = InventoryModels.create(elements, root.toFile(), new NullProgressMonitor());
		Segment segment = KDMElementFactory.createSegment();
		segment.getModel().add(inventoryModel);
		final CodeModel codeModel = KDMElementFactory.createCodeModel("test");
				
		TypeParser parser = new PowerscriptSourceFileTypeParser();
		PowerscriptPhase1Listener listener = new PowerscriptPhase1Listener();
		parser.addListener(listener);
		PowerscriptPhase1InventoryModelWalker walker = new PowerscriptPhase1InventoryModelWalker(inventoryModel, codeModel);
		walker.setSourceFileParser(parser);
		walker.walk();
		segment.getModel().add(codeModel);
		FileUtils.saveEcoreToXMI(segment, result.toString(), new NullProgressMonitor());
		
		assertTrue(result.toFile().exists());
	}
}
