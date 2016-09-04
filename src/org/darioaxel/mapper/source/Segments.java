package org.darioaxel.mapper.source;

import java.util.Properties;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.CodeModels;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;

public class Segments {

	private static final Logger LOGGER = LogManager.getLogger();

	public static Segment create(final InventoryModel inventoryModel,final Properties prop ) {

		Segment segment = KDMElementFactory.createSegment();
		
		try {
			CodeModel model = CodeModels.create(inventoryModel, prop);
			segment.getModel().add(model);

		} catch (StackOverflowError e) {
			LOGGER.error("Could not build model due to StackOverflowError. "
					+ "Increase the default stack size limit for threads by the JVM argument '-Xss'.", e);
			e.printStackTrace();
		}
	
		return segment;
	}
}
