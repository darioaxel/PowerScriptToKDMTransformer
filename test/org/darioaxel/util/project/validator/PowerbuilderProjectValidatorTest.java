package org.darioaxel.util.project.validator;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

public class PowerbuilderProjectValidatorTest {

	@Test
	public void createInventoryModelCreatorFromShouldWork() {

		File directory = new File("../PowerScriptGrammar/advanced/real/myproject");
		PowerbuilderProjectValidator validator = new PowerbuilderProjectValidator();
		assertTrue(validator.isValid(directory));
		assertEquals(validator.getPBTFile(directory).getName(), "nombre del pbt");		
	  }
}
