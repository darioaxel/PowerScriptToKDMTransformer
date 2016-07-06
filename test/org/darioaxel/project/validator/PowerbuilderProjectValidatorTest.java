package org.darioaxel.project.validator;

import static org.junit.Assert.*;

import java.io.File;

import org.darioaxel.project.validator.PowerbuilderProjectValidator;
import org.junit.Test;

public class PowerbuilderProjectValidatorTest {

	@Test
	public void createInventoryModelCreatorFromShouldWork() {

		File directory = new File("../PowerScriptGrammar/resources/advanced/real/myproject");
		PowerbuilderProjectValidator validator = new PowerbuilderProjectValidator();
		File f = validator.getPBTFile(directory);
		assertNotNull(validator.getPBTFile(directory));		
		assertEquals(f.getPath(), "../PowerScriptGrammar/resources/advanced/real/myproject/myproject.pbt");	
	  }
}
