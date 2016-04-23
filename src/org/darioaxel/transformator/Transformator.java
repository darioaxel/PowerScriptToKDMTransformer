package org.darioaxel.transformator;

import java.io.File;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;


public class Transformator {

	private String directory;
	private String outputFileName;
	
	public Transformator(String directory, String outputFileName){
		this.directory = directory;
		this.outputFileName = outputFileName;
	}
	
	public void Transform() {
		
		File directory = new File(this.directory);
		
		InventoryModel inventoryModel = new InventoryModelCreator().create();//new InventoryModelCreator().openDirectory(directory, new NullProgressMonitor());

		Segment segment = ModelCreationHelper.buildKDMInstance(inventoryModel, null, new MyProperties(), 3);

		//FileAccess.saveEcoreToXMI(segment, outputFilename, monitor);
	}
	
	public String getDirectory() {
		return this.directory;
	}
	
}
