package org.darioaxel.project.validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import org.darioaxel.mapper.code.parser.ProjectDescriptorTypeParser;
import org.darioaxel.project.validator.pbt.PowerbuilderProjectPBTListener;
import org.darioaxel.util.FileAccess;

public class PowerbuilderProjectValidator implements IProjectValidator {
	
	private final File root;
	private boolean valid = false;
	
	public PowerbuilderProjectValidator(final File root) {
		this.root = root;
	}

	@Override
	public boolean isValid() {
		
		if (!Files.isDirectory(root.toPath())) {
			Path pbt = getProjectDefinitionFile(root.toPath());
			
			if(pbt.equals(null)) return valid;
			
			ProjectDescriptorTypeParser projectParser = new ProjectDescriptorTypeParser(pbt, new PowerbuilderProjectPBTListener());
			projectParser.parse();
			PowerbuilderProjectPBTListener listener = projectParser.getListener();
			
		}			
		
		return false;
	}	
	
	private Path getProjectDefinitionFile(Path path) {		
		
		Optional<Path> pbtPath = null; 
		try {
			pbtPath = Files.list(path).filter(p -> FileAccess.getFileExtension(p.toFile()).equals("pbt")).findFirst();			
			
		} catch (IOException e) {
			return null;
		}	
		return pbtPath.get();
	}
	
	static Stream<Path> walkDirectoryRecursive (Path path) {
		if (Files.isDirectory(path)) {
			try {
				return Files.list(path).flatMap(PowerbuilderProjectValidator :: walkDirectoryRecursive );
			} catch (Exception e) {
				return Stream.empty();
			}
		}
		else {
			return Stream.of(path);
		}
	}
}
