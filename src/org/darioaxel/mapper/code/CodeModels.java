package org.darioaxel.mapper.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Properties;

import org.darioaxel.mapper.source.walker.IInventoryModelWalker;
import org.darioaxel.mapper.source.walker.PowerscriptInventoryModelWalker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.LanguageUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Module;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class CodeModels {
	
	public static CodeModel create(final InventoryModel inventoryModel, final Properties prop, Collection<LanguageUnit> languageUnits, IProgressMonitor monitor) {
		
		Path rootDir = Paths.get(prop.getProperty("ExternalDatatypeInfoRepository.rootDir"));
		int toPhase = Integer.valueOf(prop.getProperty("PhasesToGenerate"));
		
		if (monitor == null) monitor = new NullProgressMonitor();
		final IInventoryModelWalker walker = new PowerscriptInventoryModelWalker(inventoryModel);
		
		monitor.beginTask("Extracting code model from inventory model...", 1);
		try {
			monitor.subTask("Searching for used programming languages...");

			if (monitor.isCanceled()) throw new OperationCanceledException();

		} finally {
			monitor.done();
		}
		
		return null;
	}	
}
