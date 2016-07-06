package org.darioaxel.mapper.source.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.darioaxel.util.FileAccess;
import org.darioaxel.util.IFileListener;
import org.darioaxel.util.enums.ResourceDescriptionEnum;
import org.eclipse.gmt.modisco.omg.kdm.source.BinaryFile;
import org.eclipse.gmt.modisco.omg.kdm.source.Configuration;
import org.eclipse.gmt.modisco.omg.kdm.source.Directory;
import org.eclipse.gmt.modisco.omg.kdm.source.ExecutableFile;
import org.eclipse.gmt.modisco.omg.kdm.source.Image;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryItem;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class PowerscriptInventoryModelFileListener extends InventoryModelFileListener {

	private InventoryContainer			parentContainer;
	private Collection<String>			languagesUsed;
	private final SourceFactory			sourceFactory;
	private static final List<String>	IMAGE_EXTENSIONS		= new ArrayList<String>();
	private static final List<String>	SOURCECODE_EXTENSIONS	= new ArrayList<String>();
	private static final List<String>	CONFIGURATIONS			= new ArrayList<String>();
	private static final List<String>	RESOURCE_DESCRIPTIONS	= new ArrayList<String>();
	
	static {
		IMAGE_EXTENSIONS.addAll(Arrays.asList("jpg", "jpeg", "png", "bmp", "tif", "gif", "svg"));
		SOURCECODE_EXTENSIONS.addAll(Arrays.asList("srw", "srm", "srd", "sra", "sru", "srf", "srs",
												    "sql"));
		CONFIGURATIONS.addAll(Arrays.asList("cfg", "ini", "xml", "prefs", "properties"));
		RESOURCE_DESCRIPTIONS.addAll(Arrays.asList("pbt", "pbg"));
	}

	public PowerscriptInventoryModelFileListener(final InventoryContainer root, final Collection<String> languagesUsed) {
		this.parentContainer = root;
		this.sourceFactory = SourceFactory.eINSTANCE;
		this.languagesUsed = languagesUsed;
	}
	
	public Collection<String> getLanguagesUsed() {
		return languagesUsed;
	}

	@Override
	public void updateFile(final File dir, final File file) {

		InventoryItem inventoryItem;
		// TODO for content type, see IContentTypeManager
		if (isImageFile(file)) {

			Image image = sourceFactory.createImage();
			inventoryItem = image;
			inventoryItem.setVersion(Long.toString(file.lastModified()));

		} else if (isConfigurationFile(file)) {

			Configuration configuration = sourceFactory.createConfiguration();
			inventoryItem = configuration;
			inventoryItem.setVersion(Long.toString(file.lastModified()));

		} else if (isExecutableFile(file)) {
			
			ExecutableFile executableFile = sourceFactory.createExecutableFile();
			inventoryItem = executableFile;
			inventoryItem.setVersion(Long.toString(file.lastModified()));

		} else if (isResourceDescriptionFile(file)) {
			
			ResourceDescription resourceDescription = sourceFactory.createResourceDescription();
						
			if (getLanguageFromFile(file).equals("Pbt")) {
				resourceDescription.setVersion(ResourceDescriptionEnum.PROJECT.Description());
			}
			else if (getLanguageFromFile(file).equals("Pbg")) {
				resourceDescription.setVersion(ResourceDescriptionEnum.LIBRARY.Description());
			}
			inventoryItem = resourceDescription;			
			
		} else if (isSourceFile(file)) {
			
			SourceFile sourceFile = sourceFactory.createSourceFile();
			sourceFile.setEncoding("not checked");
			String language = getLanguageFromFile(file);
			sourceFile.setLanguage(language);
			this.languagesUsed.add(language);
			inventoryItem = sourceFile;
			inventoryItem.setVersion(Long.toString(file.lastModified()));
			
		} else { // fall back case: it's at least a binary file
			
			BinaryFile binaryFile = sourceFactory.createBinaryFile();
			inventoryItem = binaryFile;
			inventoryItem.setVersion(Long.toString(file.lastModified()));
		}

		// set common attributes
		inventoryItem.setName(file.getName());
		inventoryItem.setPath(file.getAbsolutePath());		

		parentContainer.getInventoryElement().add(inventoryItem);	
	}

	@Override
	public void enterDir(final File parent, final File dir) {
	
		Directory directory = sourceFactory.createDirectory();

		directory.setName(dir.getName());
		directory.setPath(dir.getAbsolutePath());

		parentContainer.getInventoryElement().add(directory);
		parentContainer = directory;
	}

	@Override
	public void exitDir(final File parent, final File dir) {
		parentContainer = (InventoryContainer) parentContainer.eContainer();		
	}

	private boolean isImageFile(final File file) {
		String fileExt = FileAccess.getFileExtension(file);
		return IMAGE_EXTENSIONS.contains(fileExt);
	}

	private boolean isConfigurationFile(final File file) {
		String fileExt = FileAccess.getFileExtension(file);
		return CONFIGURATIONS.contains(fileExt);
	}

	private boolean isExecutableFile(final File file) {
		return false;
	}

	private boolean isResourceDescriptionFile(final File file) {
		String fileExt = FileAccess.getFileExtension(file);
		return RESOURCE_DESCRIPTIONS.contains(fileExt);
	}

	private boolean isSourceFile(final File file) {
		String fileExt = FileAccess.getFileExtension(file);
		return SOURCECODE_EXTENSIONS.contains(fileExt);
	}

	private String getLanguageFromFile(final File file) {

		String fileExt = FileAccess.getFileExtension(file);
	
		if (fileExt.equals("sql")) {
			return "SQL";
		} 
		else if (fileExt.equals("pbt")) {
			return "Pbt";
		}
		else if (fileExt.equals("pbg")) {
			return "Pbg";
		}
		
		return "Powerscript";
	}

}
