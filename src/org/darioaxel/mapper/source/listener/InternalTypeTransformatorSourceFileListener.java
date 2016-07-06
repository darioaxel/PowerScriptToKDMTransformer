package org.darioaxel.mapper.source.listener;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.KDMElementFactory.GlobalKind;
import org.darioaxel.mapper.code.parser.SourceFileTypeParser;
import org.darioaxel.util.Logger;

public class InternalTypeTransformatorSourceFileListener extends SourceFileListener{

	private static final org.darioaxel.util.Logger				LOGGER	= new Logger(InternalTypeTransformatorSourceFileListener.class);
	private final IProgressMonitor								monitor;
	private final CodeModel										internalCodeModel;
	private final ConcurrentMap<String, SourceFileTypeParser>	sourceFileParsers;

	public InternalTypeTransformatorSourceFileListener(final IProgressMonitor monitor) {
		this.monitor = monitor;
		this.internalCodeModel = KDMElementFactory.createGenericCodeModel("Internal CodeModel", GlobalKind.INTERNAL);
		this.sourceFileParsers = new ConcurrentHashMap<String, SourceFileTypeParser>();		
	}

	@Override
	public void visitSourceFile(final SourceFile sourceFile) {
		if (monitor.isCanceled()) throw new OperationCanceledException();

		processSourceFile(sourceFile);
	}

	@Override
	public void beforeWalk() {

	}

	@Override
	public void afterWalk() {

	}

	@Override
	public CodeModel getInternalCodeModel() {
		return this.internalCodeModel;
	}

	public void addSourceFileParser(final SourceFileTypeParser sourceFileParser) {
		sourceFileParsers.put(sourceFileParser.getLanguageString(), sourceFileParser);
	}

	private void processSourceFile(final SourceFile sourceFile) {
		SourceFileTypeParser sourceFileParser = sourceFileParsers.get(sourceFile.getLanguage());
		if (sourceFileParser == null) {
			LOGGER.warning("No appropriate source file parser found for language '" + sourceFile.getPath() + "'.");
			return;
		}

		try {
			sourceFileParser.readInto(sourceFile, internalCodeModel, monitor);
		} catch (RuntimeException e) {
			LOGGER.warning("Exception while parsing " + sourceFile.getPath());
			throw e;
		}
	}

}
