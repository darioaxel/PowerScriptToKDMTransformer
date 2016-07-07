package org.darioaxel.source;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.darioaxel.mapper.source.InventoryModelCreator;
import org.darioaxel.mapper.source.SegmentCreator;
import org.darioaxel.util.FileUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.junit.Test;

public class InventoryModelCreatorTest {

	@Test
	public void createInventoryModelTest() {
		Path root = Paths.get("/home/darioaxel/git/PowerScriptGrammar/resources/advanced/real/myproject");
		Path result = Paths.get("/home/darioaxel/git/PowerScriptGrammar/testing_results/createInventoryModelTest.xmi");
		
		InventoryModelCreator imc = new InventoryModelCreator();
		InventoryModel inventoryModel = imc.create(root.toFile(), new NullProgressMonitor());
		Segment segment = SegmentCreator.create(inventoryModel);
		FileUtils.saveEcoreToXMI(segment, result.toString(), new NullProgressMonitor());
		
		assertTrue(result.toFile().exists());
	}
}
