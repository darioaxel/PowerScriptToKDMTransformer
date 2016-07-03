package org.darioaxel.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public final class FileAccess {

	private static final int BUFFER_SIZE	= 1024;

	private FileAccess() {
		// utility class
	}

	public static void saveEcoreToXMI(final EObject modelInstance, final String filename, final IProgressMonitor monitor) {
		try {
			FileAccess.saveEcoreToXMIUnsafe(modelInstance, filename, monitor);
		} catch (StackOverflowError e) {
			System.err.println("Could not save model due to StackOverflowError. "
					+ "Increase the default stack size limit for threads by the JVM argument '-Xss'.");
			e.printStackTrace();
		} catch (IOException e ) {
			System.err.println("Could not save model due to IOException");
			e.printStackTrace();
		}
	}

	private static void saveEcoreToXMIUnsafe(final EObject modelInstance, final String filename, final IProgressMonitor monitor) throws IOException {

		monitor.subTask("Saving model to file..." + filename);		
		XMIResource resource;
		resource = new XMIResourceImpl();
		resource.setURI(URI.createFileURI(filename));
		resource.setEncoding("UTF-8");
		resource.getContents().add(modelInstance);
		resource.save(Collections.EMPTY_MAP);
	}

	@SuppressWarnings("unchecked")
	public static <T extends EPackage> T loadEcoreFromXMIFile(final T modelClass, final String filename) throws IOException {

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(modelClass.getNsURI(), modelClass);

		Resource resource = resourceSet.createResource(URI.createFileURI(filename));
		resource.load(null);

		EList<EObject> contents = resource.getContents();

		if (contents.size() == 0) {
			throw new IOException("File does not contain anything (" + filename + ")");
		}

		EObject eObject = contents.get(0);
		if (!(eObject instanceof EPackage)) {
			throw new ClassCastException("The file content is not of the type EPackage, but of "
					+ eObject.getClass().getName());
		}

		return (T) eObject;
	}

	public static String loadTextFile(final String filename) throws IOException {

		byte[] buffer = new byte[BUFFER_SIZE];
		int read;
		StringBuilder stringBuilder = new StringBuilder();
		FileInputStream fileInputStream = new FileInputStream(filename);
		try {
			while ((read = fileInputStream.read(buffer)) != -1) {
				stringBuilder.append(new String(buffer, 0, read));
			}
		} finally {
			fileInputStream.close();
		}
		return stringBuilder.toString();
	}
	
	public static BufferedReader loadTextFileAsStream(final String filename) throws FileNotFoundException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));
	
		return reader;
	}

	public static void saveTextFile(final String filename, final String text) throws IOException {

		FileWriter fileWriter = new FileWriter(filename);
		try {
			fileWriter.write(text);
		} finally {
			fileWriter.close();
		}
	}

	public static void walkDirectoryRecursively(final File dir, final FileListener listener) {
		for (File file : dir.listFiles()) {			
			if (file.isDirectory()) {
				listener.enterDir(dir, file);
				walkDirectoryRecursively(file, listener);
				listener.exitDir(dir, file);
			} else {
				listener.updateFile(dir, file);
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
}
