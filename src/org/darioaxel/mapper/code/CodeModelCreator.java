package org.darioaxel.mapper.code;

import java.util.Properties;

import org.darioaxel.mapper.source.InventoryModelWalker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.Module;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class CodeModelCreator {

	private final String				rootDir;

	private CodeModel					internalCodeModel;
	private CodeModel					externalCodeModel;
	
	private Module						valueRepository;

	private final int					toPhase;

	public CodeModelCreator(final Properties prop, final int toPhase) {
		this.toPhase = toPhase;
		rootDir = prop.getProperty("ExternalDatatypeInfoRepository.rootDir");		
	}

	public void create(final InventoryModel inventoryModel, IProgressMonitor monitor) {
	
		if (monitor == null) monitor = new NullProgressMonitor();
		final InventoryModelWalker walker = new InventoryModelWalker(inventoryModel);
		
		monitor.beginTask("Extracting code model from inventory model...", 1);
		try {
			monitor.subTask("Searching for used programming languages...");

			if (monitor.isCanceled()) throw new OperationCanceledException();
			
			buildCodeModels(walker, monitor);

		} finally {
			monitor.done();
		}
	}

	private void buildCodeModels(final InventoryModelWalker walker, final IProgressMonitor monitor) {
		
	}


	public CodeModel getInternalCodeModel() {
		return this.internalCodeModel;
	}

	public CodeModel getExternalCodeModel() {
		return this.externalCodeModel;
	}
}
