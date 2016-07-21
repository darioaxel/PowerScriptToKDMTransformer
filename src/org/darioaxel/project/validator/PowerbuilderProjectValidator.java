package org.darioaxel.project.validator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import org.darioaxel.mapper.code.parser.LibraryDescriptorTypeParser;
import org.darioaxel.mapper.code.parser.ProjectDescriptorTypeParser;
import org.darioaxel.project.validator.pbg.Phase1LibraryListener;
import org.darioaxel.project.validator.pbt.Phase1ProjectListener;
import org.darioaxel.util.FileUtils;
import org.darioaxel.util.enums.EPowerscriptFileTypes;

public class PowerbuilderProjectValidator implements IProjectValidator {
	
	private final Path root;
	
	public PowerbuilderProjectValidator(final Path root) {
		this.root = root;
	}

	@Override
	public boolean isValid() {

		if (Files.isDirectory(root)) {
			Optional<Path> pbt = FileUtils.getFirstFileOfType(root, EPowerscriptFileTypes.ProjectDefinition);

			if (pbt.isPresent()) {			
				
				Phase1ProjectListener listenerPBT = new Phase1ProjectListener();
				ProjectDescriptorTypeParser projectParser = new ProjectDescriptorTypeParser();
				projectParser.addListener(listenerPBT);
				projectParser.parse(pbt.get());				
				listenerPBT = projectParser.getListener();			
				
				for(Path lib : listenerPBT.getLiblist()) {
					boolean valid = isLibraryValid(lib, pbt.get());
					if (valid) return false;					
				}
				return true;
			}
		}		

		return false;
	}	
	
	static boolean isLibraryValid(Path pathToLib, final Path pbt ) {
		
		Path pbl = FileUtils.changeFileExtension(pathToLib.getFileName(), EPowerscriptFileTypes.Libraries.extension());		
		Path pbg = pathToLib.resolveSibling(pbl.toString());
			
		pbg = FileUtils.concatWithoutLast(pbt, pbg); 		
						
		if( pbg.toFile().exists()) {
			
			Phase1LibraryListener listenerPBG = new Phase1LibraryListener();
			LibraryDescriptorTypeParser libraryParser = new LibraryDescriptorTypeParser(pbg, listenerPBG);
			libraryParser.parse();
			
			for(Path f : listenerPBG.getFileNames()) {
				Path file = FileUtils.concatWithoutLast(pbt, f);
				if (file.toFile().exists() == false) return false;
			}
			return true;				
		}		
		
		return false;
	}	
}
