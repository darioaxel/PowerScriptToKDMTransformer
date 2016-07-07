package org.darioaxel.mapper.source.listener;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.parser.SourceFileTypeParser;
import org.darioaxel.util.Logger;

public class InternalTypeTransformatorSourceFileListener extends FileListener{

	private static final org.darioaxel.util.Logger				LOGGER	= new Logger(InternalTypeTransformatorSourceFileListener.class);
	private final IProgressMonitor								monitor;
	private final CodeModel										codeModel;
	private final ConcurrentMap<String, SourceFileTypeParser>	sourceFileParsers;

	public InternalTypeTransformatorSourceFileListener(final IProgressMonitor monitor) {
		this.monitor = monitor;
		this.codeModel = KDMElementFactory.createCodeModel("Internal CodeModel");
		this.sourceFileParsers = new ConcurrentHashMap<String, SourceFileTypeParser>();		
	}

	public CodeModel geCodeModel() {
		return this.codeModel;
	}

	public void addSourceFileParser(final SourceFileTypeParser sourceFileParser) {
		sourceFileParsers.put(sourceFileParser.getLanguageString(), sourceFileParser);
	}

	public void processSourceFile(final SourceFile sourceFile) {
		SourceFileTypeParser sourceFileParser = sourceFileParsers.get(sourceFile.getLanguage());
		if (sourceFileParser == null) {
			LOGGER.warning("No appropriate source file parser found for language '" + sourceFile.getPath() + "'.");
			return;
		}

		try {
			sourceFileParser.readInto(sourceFile, codeModel, monitor);
		} catch (RuntimeException e) {
			LOGGER.warning("Exception while parsing " + sourceFile.getPath());
			throw e;
		}
	}

}
