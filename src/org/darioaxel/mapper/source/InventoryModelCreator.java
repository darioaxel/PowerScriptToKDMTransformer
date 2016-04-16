package org.darioaxel.mapper.source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InventoryModelCreator {
	
	private static Path startDir;
	private InventoryFileVisitor inventoryFileVisitor;
		
	public InventoryModelCreator(Path startDir) {		
		InventoryModelCreator.setStartDir(startDir);		
	}
	
	public void create() {
		
		try {
			Files.walkFileTree( getStartDir(), inventoryFileVisitor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Path getStartDir() {
		return startDir;
	}

	public static void setStartDir(Path startDir) {
		InventoryModelCreator.startDir = startDir;
	}	
}

