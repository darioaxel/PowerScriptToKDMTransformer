package org.darioaxel.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.darioaxel.grammar.powerscript.powerscriptLexer;
import org.darioaxel.util.enums.EPowerscriptFileTypes;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public final class FileUtils {

	public static void saveEcoreToXMI(final EObject modelInstance, final String filename) {
		try {
			FileUtils.saveEcoreToXMIUnsafe(modelInstance, filename);
		} catch (StackOverflowError e) {
			System.err.println("Could not save model due to StackOverflowError. "
					+ "Increase the default stack size limit for threads by the JVM argument '-Xss'.");
			e.printStackTrace();
		} catch (IOException e ) {
			System.err.println("Could not save model due to IOException");
			e.printStackTrace();
		}
	}

	private static void saveEcoreToXMIUnsafe(final EObject modelInstance, final String filename) throws IOException {

		//monitor.subTask("Saving model to file..." + filename);		
		XMIResource resource;
		resource = new XMIResourceImpl();
		resource.setURI(URI.createFileURI(filename));
		resource.setEncoding("UTF-8");
		resource.getContents().add(modelInstance);
		resource.save(Collections.EMPTY_MAP);
	}

//	@SuppressWarnings("unchecked")
//	public static <T extends EPackage> T loadEcoreFromXMIFile(final T modelClass, final String filename) throws IOException {
//
//		ResourceSet resourceSet = new ResourceSetImpl();
//		resourceSet.getPackageRegistry().put(modelClass.getNsURI(), modelClass);
//
//		Resource resource = resourceSet.createResource(URI.createFileURI(filename));
//		resource.load(null);
//
//		EList<EObject> contents = resource.getContents();
//
//		if (contents.size() == 0) {
//			throw new IOException("File does not contain anything (" + filename + ")");
//		}
//
//		EObject eObject = contents.get(0);
//		if (!(eObject instanceof EPackage)) {
//			throw new ClassCastException("The file content is not of the type EPackage, but of "
//					+ eObject.getClass().getName());
//		}
//
//		return (T) eObject;
//	}

	public static TokenStream createPowerscriptInputTokenStream(File program) throws IOException {

		CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
		TokenSource tokenSource = new powerscriptLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}

	public static void saveTextFile(final String filename, final String text) throws IOException {

		FileWriter fileWriter = new FileWriter(filename);
		try {
			fileWriter.write(text);
		} finally {
			fileWriter.close();
		}
	}

	public static void walkDirectoryRecursively(final File dir, final IFileListener listener) {
		for (File file : dir.listFiles()) {			
			if (file.isDirectory()) {
				listener.enterDir(dir, file);
				walkDirectoryRecursively(file, listener);
				listener.exitDir(dir, file);
			} else {
				listener.updateFile(file);
			}
		}
	}

	public static String getFileExtension(final File file) {

		String name = file.getName();
		int lastIndexOf = name.lastIndexOf('.');
		if (lastIndexOf == -1) {
			return "";
		}
		String fileExt = name.substring(lastIndexOf + 1).toLowerCase();
		return fileExt;
	}	
	

	public static Path concatWithoutLast(Path root, Path ending) {
		Path rootPath = root.subpath(0, root.getNameCount() - 1);	
		return Paths.get("/", rootPath.toString(), ending.toString());
	}
	
	public static Path changeFileExtension(Path fileName, String extension) {
		
		String name = fileName.getFileName().toString();
		int length = name.length();
		String nameWithoutExt = name.substring(0, length - 3);
		
		return Paths.get(nameWithoutExt.concat(extension));
	}

	public static List<Path> getAllSubDirectories(Path path) {		
		List<Path> allDirectories = new ArrayList<Path>();
		
		try {
			Files.list(path).filter(p -> Files.isDirectory(p)).forEach(allDirectories :: add);			

		} catch (IOException e) {
			return null;
		}	
		return allDirectories;
	}	
	
	public static List<Path> getAllFilesOfType(Path path, EPowerscriptFileTypes epft ) {
		List<Path> allFiles = new ArrayList<Path>();
			
		try {
			Files.list(path).filter(p -> FileUtils.getFileExtension(p.toFile()).equals(epft.extension())).forEach(allFiles :: add);			

		} catch (IOException e) {
			return null;
		}	
		return allFiles;
	}	
	
	public static Optional<Path> getFirstFileOfType(Path path, EPowerscriptFileTypes epft ) {		
		try {
			return Files.list(path).filter(p -> FileUtils.getFileExtension(p.toFile()).equals(epft.extension())).findFirst();			
			
		} catch (IOException e) {
			return null;
		}		
	}	
}
