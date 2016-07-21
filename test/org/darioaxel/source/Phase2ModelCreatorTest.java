package org.darioaxel.source;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.PowerscriptElementRepository;
import org.darioaxel.mapper.code.NewCodeModels;
import org.darioaxel.mapper.source.InventoryModels;
import org.darioaxel.util.FileUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.junit.Test;

public class Phase2ModelCreatorTest {
	
	private PowerscriptElementRepository elements = new PowerscriptElementRepository();	
	Path root = Paths.get("/home/darioaxel/git/PowerScriptGrammar/resources/advanced/real/myproject");
	Path result = Paths.get("/home/darioaxel/git/PowerScriptGrammar/testing_results/createPhase2ModelTestNew.xmi");

	@Test
	public void createPhase1Test() {
		
		InventoryModel inventoryModel = InventoryModels.create(elements, root.toFile(), new NullProgressMonitor());
		Segment segment = KDMElementFactory.createSegment();
		segment.getModel().add(inventoryModel);
		CodeModel codeModel = NewCodeModels.create(inventoryModel);
			
		segment.getModel().add(codeModel);
		FileUtils.saveEcoreToXMI(segment, result.toString(), new NullProgressMonitor());
		
		assertTrue(result.toFile().exists());
	}	
}
