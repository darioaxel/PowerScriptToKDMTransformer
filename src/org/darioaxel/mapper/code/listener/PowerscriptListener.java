package org.darioaxel.mapper.code.listener;

import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public interface PowerscriptListener {
	
	public void setClassUnitName(String name);
	public void setCompilationUnit(SourceFile source);

}
