package org.darioaxel.mapper.source.listener;

import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.data.DataModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public abstract class SourceFileListener extends FileListener {

	
	public void beforeWalk() {
		// default implementation: empty
	}
	
	public void afterWalk() {
		// default implementation: empty
	}
		
	public DataModel getDataModel() {
		// TODO Auto-generated method stub
		return null;
	}

}