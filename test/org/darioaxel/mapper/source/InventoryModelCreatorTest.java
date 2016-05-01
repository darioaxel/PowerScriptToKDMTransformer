package org.darioaxel.mapper.source;

import static org.junit.Assert.*;

import java.io.File;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.junit.Test;

public class InventoryModelCreatorTest {

	
	@Test
	public void multiplicationOfZeroIntegersShouldReturnZero() {

		File directory = new File("/home/darioaxel/ProyectFinCarrera/G7Actualizador");
		InventoryModel inventoryModel = new InventoryModelCreator().create(directory, new NullProgressMonitor());
		assertEquals(inventoryModel.eContents().size(), 1);		
	  }
}
