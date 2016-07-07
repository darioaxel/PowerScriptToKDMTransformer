package org.darioaxel.transformator;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.gmt.modisco.omg.kdm.code.LanguageUnit;
import org.darioaxel.mapper.code.language.LanguageUnitDetector;
import org.darioaxel.mapper.source.InventoryModels;
import org.darioaxel.mapper.source.Segments;
import org.darioaxel.util.FileUtils;
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
		Collection<String> languagesUsed = new ArrayList<String>();		
		InventoryModel inventoryModel = InventoryModels.create(directory, languagesUsed, new NullProgressMonitor());
		Collection<LanguageUnit> languages = LanguageUnitDetector.getLanguages(languagesUsed);
		Segment segment = Segments.create(inventoryModel, null, null, languages);

		FileUtils.saveEcoreToXMI(segment, null, null);
	}
	
	public String getDirectory() {
		return this.directory;
	}	
	
	public String getOutputFileName() {
		return this.outputFileName;
	}
}
