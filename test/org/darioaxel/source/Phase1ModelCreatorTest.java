package org.darioaxel.source;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.CodeModels;
import org.darioaxel.mapper.source.InventoryModels;
import org.darioaxel.util.FileUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.junit.Before;
import org.junit.Test;

public class Phase1ModelCreatorTest {
			
	protected Properties prop = new Properties();
	
	@Before
	public void before() {
		prop.setProperty("PhasesToGenerate", "1");
	}	
	
	@Test
	public void createPhase1ProyectTest() {
		Path root = Paths.get("/home/darioaxel/git/PowerScriptGrammar/resources/advanced/real/myproject");
		Path result = Paths.get("/home/darioaxel/git/PowerScriptGrammar/testing_results/createPhase1ModelTest.xmi");
		
		InventoryModel inventoryModel = InventoryModels.create(root.toFile());
		Segment segment = KDMElementFactory.createSegment();
		segment.getModel().add(inventoryModel);			
		
		CodeModel codeModel = CodeModels.create(inventoryModel,prop);
			
		segment.getModel().add(codeModel);
		FileUtils.saveEcoreToXMI(segment, result.toString(), new NullProgressMonitor());
		
		assertTrue(result.toFile().exists());
	}	
	
	@Test
	public void createPhase1FileTest() {
		Path root = Paths.get("/home/darioaxel/git/PowerScriptGrammar/resources/advanced/n_param_criterio");
		Path result = Paths.get("/home/darioaxel/git/PowerScriptGrammar/testing_results/createPhase1N_ParamModelFileTest.xmi");
		
		InventoryModel inventoryModel = InventoryModels.create(root.toFile());
		Segment segment = KDMElementFactory.createSegment();
		segment.getModel().add(inventoryModel);
		CodeModel codeModel = CodeModels.create(inventoryModel, prop);
			
		segment.getModel().add(codeModel);
		FileUtils.saveEcoreToXMI(segment, result.toString(), new NullProgressMonitor());
		
		assertTrue(result.toFile().exists());
	}	
}
