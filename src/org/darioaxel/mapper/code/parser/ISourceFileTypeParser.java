package org.darioaxel.mapper.code.parser;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public interface ISourceFileTypeParser {

	void read();
	void readInto(SourceFile sourceFile, CodeModel internalCodeModel, IProgressMonitor monitor);
	String getLanguageString();
}
