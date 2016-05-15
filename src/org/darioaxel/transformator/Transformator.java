package org.darioaxel.transformator;

import java.io.File;
import java.nio.file.Path;

import org.darioaxel.mapper.source.InventoryModelCreator;
import org.darioaxel.mapper.source.SegmentCreator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;


public class Transformator {

	private String directory;
	private String outputFileName;
	
	public Transformator(String directory, String outputFileName){
		this.directory = directory;
		this.outputFileName = outputFileName;
	}
	
	public Transformator(Path directory, Path outputFileName){
		this.directory = directory.toString();
		this.outputFileName = outputFileName.toString();
	}
	
	public void Transform() {
		
		File directory = new File(this.directory);
		
		InventoryModel inventoryModel = new InventoryModelCreator().create(directory, new NullProgressMonitor());

		Segment segment = SegmentCreator.create(inventoryModel, null, new MyProperties(), 3);

		//FileAccess.saveEcoreToXMI(segment, outputFilename, monitor);
	}
	
	public String getDirectory() {
		return this.directory;
	}	
	
	public String getOutputFileName() {
		return this.outputFileName;
	}
}
