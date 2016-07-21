package org.darioaxel.mapper.code.parser;

import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public interface ITypeParser {

	void parse(Object object);
	Object getListener();
	void addListener(Object listener);
	void parse(SourceFile e, CodeModel codeModel);
}
