package org.darioaxel.mapper.source.listener;

import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.data.DataModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class SourceFileListener implements ISourceFileListener {

	@Override
	public void beforeWalk() {
		// default implementation: empty
	}

	@Override
	public void visitSourceFile(final SourceFile sourceFile) {
		// default implementation: empty
	}

	@Override
	public void afterWalk() {
		// default implementation: empty
	}

	@Override
	public CodeModel getCodeModel() {
		// default implementation: return null
		return null;
	}

	@Override
	public DataModel getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}