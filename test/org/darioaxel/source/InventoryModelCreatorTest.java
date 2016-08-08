package org.darioaxel.source;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.source.InventoryModels;
import org.darioaxel.mapper.source.Segments;
import org.darioaxel.util.FileUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.junit.Test;

public class InventoryModelCreatorTest {


	
	Path root = Paths.get("/home/darioaxel/git/PowerScriptGrammar/resources/advanced/real/myproject");
	Path result = Paths.get("/home/darioaxel/git/PowerScriptGrammar/testing_results/createInventoryModelTest.xmi");

	@Test
	public void createInventoryModelTest() {
		
		InventoryModel inventoryModel = InventoryModels.create(root.toFile());
		Segment segment = KDMElementFactory.createSegment();
		segment.getModel().add(inventoryModel);
		FileUtils.saveEcoreToXMI(segment, result.toString(), new NullProgressMonitor());
		
		assertTrue(result.toFile().exists());
	}
	
	@Test
	public void createInventoryModelLanguagesTest() {
		
		Collection<String> languages = new ArrayList<String>();
		InventoryModel inventoryModel = InventoryModels.create(root.toFile(), languages);
			
		assertTrue(languages.size() > 0);
	}
}
