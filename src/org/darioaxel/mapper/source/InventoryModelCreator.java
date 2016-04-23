package org.darioaxel.mapper.source;

import java.io.File;
import org.darioaxel.mapper.code.MoDiscoKDM;
import org.darioaxel.util.FileAccess;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.source.Directory;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;

public final class InventoryModelCreator implements ModelCreator<InventoryModel> {

	private final SourceFactory sourceFactory;

	public InventoryModelCreator() {
		this.sourceFactory = SourceFactory.eINSTANCE;
	}

	@Override
	public InventoryModel create(final File directory, IProgressMonitor monitor) {
	
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
			FileAccess.walkDirectoryRecursively(directory, new InventoryModelFileListener(root));
		} finally {
			monitor.done();
		}

		return inventoryModel;
	}

	@Override
	public InventoryModel create(final String dirname, final IProgressMonitor monitor) {
		return create(new File(dirname), monitor);
	}

}
