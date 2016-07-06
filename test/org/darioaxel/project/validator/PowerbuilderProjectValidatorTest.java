package org.darioaxel.project.validator;

import static org.junit.Assert.*;
import java.nio.file.Paths;
import java.nio.file.Path;

import org.darioaxel.project.validator.PowerbuilderProjectValidator;
import org.junit.Test;

public class PowerbuilderProjectValidatorTest {

	@Test
	public void createInventoryModelCreatorFromShouldWork() {

		Path root = Paths.get("/home/darioaxel/git/PowerScriptGrammar/resources/advanced/real/myproject");
		PowerbuilderProjectValidator validator = new PowerbuilderProjectValidator(root);
		assertTrue(validator.isValid() == true);
	  }
}
