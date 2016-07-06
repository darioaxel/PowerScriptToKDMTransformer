package org.darioaxel.mapper.source.listener;

import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public interface ISourceFileListener {

	void beforeWalk();

	void visitSourceFile(SourceFile sourceFile);

	void afterWalk();

	CodeModel getInternalCodeModel();

	CodeModel getExternalCodeModel();
}

