package org.darioaxel.project.validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.darioaxel.mapper.code.parser.ProjectDescriptorTypeParser;
import org.darioaxel.project.validator.pbt.PowerbuilderProjectPBTListener;
import org.darioaxel.util.FileAccess;

public class PowerbuilderProjectValidator implements IProjectValidator {
	
	private final Path root;
	private boolean valid = false;
	
	public PowerbuilderProjectValidator(final Path root) {
		this.root = root;
	}

	@Override
	public boolean isValid() {

		boolean test = Files.isDirectory(root);

		Path pbta = getProjectDefinitionFile(root).get();

		if (Files.isDirectory(root)) {
			Optional<Path> pbt = getProjectDefinitionFile(root);

			if (pbt.isPresent()) {
				ProjectDescriptorTypeParser projectParser = new ProjectDescriptorTypeParser(pbt.get(), new PowerbuilderProjectPBTListener());
				projectParser.parse();
				PowerbuilderProjectPBTListener listener = projectParser.getListener();
				Path rootLib = listener.getApplib();
				rootLib = Paths.get(root.toString(), rootLib.getFileName().toString());
				
				boolean exits = rootLib.toFile().exists();
				
				if( exits == false) return valid;

				List<Path> pathsToLibs = listener.getLiblist();
				return true;
			}
		}			

		return false;
	}	
	
	private Optional<Path> getProjectDefinitionFile(Path path) {		
				 
		try {
			return Files.list(path).filter(p -> FileAccess.getFileExtension(p.toFile()).equals("pbt")).findFirst();			
			
		} catch (IOException e) {
			return null;
		}	
		//return pbtPath.get();
	}	
}
