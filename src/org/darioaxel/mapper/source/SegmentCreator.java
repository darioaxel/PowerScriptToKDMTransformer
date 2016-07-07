package org.darioaxel.mapper.source;

import java.util.Collection;
import java.util.Properties;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.CodeModelCreator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.LanguageUnit;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;

public class SegmentCreator {

	private static final Logger LOGGER = LogManager.getLogger(ModelCreator.class);

	public static Segment create(final InventoryModel inventoryModel, final IProgressMonitor monitor, final Properties prop, final int toPhase, Collection<LanguageUnit> languageUnits ) {

		CodeModelCreator codeModelCreator = new CodeModelCreator(prop, toPhase);

		try {
			codeModelCreator.create(inventoryModel, monitor);

		} catch (StackOverflowError e) {
			LOGGER.error("Could not build model due to StackOverflowError. "
					+ "Increase the default stack size limit for threads by the JVM argument '-Xss'.", e);
			e.printStackTrace();
		}

		CodeModel internalCodeModel = codeModelCreator.getInternalCodeModel();
		CodeModel externalCodeModel = codeModelCreator.getExternalCodeModel();
		Collection<LanguageUnit> neccessaryLanguageUnits = languageUnits;

		internalCodeModel.getCodeElement().addAll(neccessaryLanguageUnits);

		Segment segment = KDMElementFactory.createSegment();
		segment.getModel().add(inventoryModel);
		segment.getModel().add(internalCodeModel);
		segment.getModel().add(externalCodeModel);

		return segment;
	}
	
	public static Segment create(final InventoryModel inventoryModel) {
		
		Segment segment = KDMElementFactory.createSegment();
		segment.getModel().add(inventoryModel);
		return segment;
	}
}
