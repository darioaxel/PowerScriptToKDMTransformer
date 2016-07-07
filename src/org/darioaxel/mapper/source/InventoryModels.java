package org.darioaxel.mapper.source;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.darioaxel.mapper.code.MoDiscoKDM;
import org.darioaxel.mapper.source.listener.PowerscriptInventoryModelFileListener;
import org.darioaxel.util.FileUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.source.Directory;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;

public class InventoryModels {

	private final static SourceFactory sourceFactory = SourceFactory.eINSTANCE;
		
	public static InventoryModel create(final File directory, final Collection<String> languagesUsed, IProgressMonitor monitor) {
	
		if (monitor == null)
			monitor = new NullProgressMonitor();

		InventoryModel inventoryModel = sourceFactory.createInventoryModel();
		inventoryModel.setName(MoDiscoKDM.INVENTORYMODEL_NAME);

		final Directory root = sourceFactory.createDirectory();
		root.setName(directory.getName());
		root.setPath(directory.getAbsolutePath());

		inventoryModel.getInventoryElement().add(root);

		monitor.beginTask("Scanning directory..." + directory.getAbsolutePath(), IProgressMonitor.UNKNOWN);

		try {
			FileUtils.walkDirectoryRecursively(directory, new PowerscriptInventoryModelFileListener(root, languagesUsed));
		} finally {
			monitor.done();
		}

		return inventoryModel;
	}

	public static InventoryModel create(final String dirname, final IProgressMonitor monitor) {
		return create(new File(dirname), monitor);
	}
	
	public static InventoryModel create(File directory, IProgressMonitor progressMonitor) {
		
		return create(directory, new ArrayList<String>(), progressMonitor);
	}

}
