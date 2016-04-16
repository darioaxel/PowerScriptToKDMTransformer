package org.darioaxel.mapper.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.darioaxel.util.PowerscriptFileUtil;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;

public class InventoryFileVisitor extends SimpleFileVisitor<Path> {
  	
	private final SourceFactory	sourceFactory;
	
	public InventoryFileVisitor() {
		this.sourceFactory = SourceFactory.eINSTANCE;
	}
	
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        
    	if (attr.isRegularFile()) {
           String fileName = file.toFile().getName();
           String extension = PowerscriptFileUtil.getExtension(file.toFile());
        } 
        return FileVisitResult.CONTINUE;
    }

    // Print each directory visited.
    public FileVisitResult preVisitDirectory(Path dir, IOException exc) {
        System.out.format("Directory: %s%n", dir);
        return FileVisitResult.CONTINUE;
    }
 
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }
    
   
}
