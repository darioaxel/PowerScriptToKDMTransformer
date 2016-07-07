package org.darioaxel.mapper.source;

import java.util.Collection;
import java.util.Properties;

import org.darioaxel.mapper.IMapperElementRepository;
import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.PowerscriptElementRepository;
import org.darioaxel.mapper.code.CodeModels;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.LanguageUnit;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;

public class Segments {

	private static final Logger LOGGER = LogManager.getLogger();

	public static Segment create(IMapperElementRepository elementRepository, final InventoryModel inventoryModel, final IProgressMonitor monitor, final Properties prop, Collection<LanguageUnit> languageUnits ) {

		Segment segment = KDMElementFactory.createSegment();
		
		try {
			CodeModel model = CodeModels.create(elementRepository, inventoryModel, prop, languageUnits, monitor);
			segment.getModel().add(model);

		} catch (StackOverflowError e) {
			LOGGER.error("Could not build model due to StackOverflowError. "
					+ "Increase the default stack size limit for threads by the JVM argument '-Xss'.", e);
			e.printStackTrace();
		}
	
		return segment;
	}
}
