package org.darioaxel.transformator;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.CodeModels;
import org.darioaxel.mapper.source.InventoryModels;
import org.darioaxel.mapper.source.Segments;
import org.darioaxel.util.FileUtils;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;


public class Transformator {

	private Path root = Paths.get("/home/darioaxel/git/PowerScriptGrammar/resources/advanced/real/myproject");
	private Path result = Paths.get("/home/darioaxel/git/PowerScriptGrammar/testing_results/createPhase3ModelTest.xmi");
	
	public Transformator(String directory, String outputFileName){
		this.root = Paths.get(directory);
		this.result = Paths.get(outputFileName);
	}
	
	public Transformator(Path directory, Path outputFileName){
		this.root = directory;
		this.result = outputFileName;
	}
	
	public void Transform() {		
				
		InventoryModel inventoryModel = InventoryModels.create(root.toFile());
		Segment segment = KDMElementFactory.createSegment();
		segment.getModel().add(inventoryModel);

		
		CodeModel codeModel = CodeModels.create(inventoryModel);
		segment.getModel().add(codeModel);
		
		FileUtils.saveEcoreToXMI(segment, result.toString());
	}
	
	public String getDirectory() {
		return this.root.toString();
	}	
	
	public String getOutputFileName() {
		return this.result.toString();
	}
}
