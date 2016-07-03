package org.darioaxel.transformator;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

import org.eclipse.gmt.modisco.omg.kdm.code.LanguageUnit;
import org.darioaxel.mapper.code.language.LanguageUnitDetector;
import org.darioaxel.mapper.source.InventoryModelCreator;
import org.darioaxel.mapper.source.SegmentCreator;
import org.darioaxel.util.FileAccess;
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
		
		InventoryModelCreator imc = new InventoryModelCreator();
		InventoryModel inventoryModel = imc.create(directory, new NullProgressMonitor());
		Collection<LanguageUnit> languages = LanguageUnitDetector.getLanguages(imc.getLanguagesUsed());
		Segment segment = SegmentCreator.create(inventoryModel, null, null, 3, languages);

		FileAccess.saveEcoreToXMI(segment, null, null);
	}
	
	public String getDirectory() {
		return this.directory;
	}	
	
	public String getOutputFileName() {
		return this.outputFileName;
	}
}
