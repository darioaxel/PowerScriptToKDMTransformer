package org.darioaxel.mapper.source;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import org.darioaxel.mapper.code.MoDiscoKDM;
import org.darioaxel.mapper.source.listener.PowerscriptInventoryModelFileListener;
import org.darioaxel.util.FileUtils;
import org.eclipse.gmt.modisco.omg.kdm.source.Directory;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;

public class InventoryModels {

	private final static SourceFactory sourceFactory = SourceFactory.eINSTANCE;
		
	public static InventoryModel create(File directory, final Collection<String> languagesUsed) {
	
		InventoryModel inventoryModel = sourceFactory.createInventoryModel();
		inventoryModel.setName(MoDiscoKDM.INVENTORYMODEL_NAME);

		final Directory root = sourceFactory.createDirectory();
		root.setName(directory.getName());
		root.setPath(directory.getAbsolutePath());

		inventoryModel.getInventoryElement().add(root);

		FileUtils.walkDirectoryRecursively(directory,  new PowerscriptInventoryModelFileListener(root,languagesUsed));
		

		return inventoryModel;
	}
	
	public static InventoryModel create(File directory) {
		
		return create(directory, new ArrayList<String>());
	}
}
