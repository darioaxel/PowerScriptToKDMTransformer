package org.darioaxel.mapper.source.visitors;

import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public interface ISourceFileVisitor {

	void beforeWalk();

	void visitSourceFile(SourceFile sourceFile);

	void afterWalk();

	CodeModel getInternalCodeModel();

	CodeModel getExternalCodeModel();
}

