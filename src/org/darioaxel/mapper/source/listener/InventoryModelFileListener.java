package org.darioaxel.mapper.source.listener;

import java.io.File;
import java.util.Collection;

import org.darioaxel.util.IFileListener;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;

public abstract class InventoryModelFileListener implements IFileListener{
	
	protected InventoryContainer			parentContainer;
	protected Collection<String>			languagesUsed;
	protected SourceFactory				    sourceFactory;

	@Override
	public void updateFile(File dir, File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDir(File parent, File dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDir(File parent, File dir) {
		// TODO Auto-generated method stub
		
	}
	
	public Collection<String> getLanguagesUsed() {
		return languagesUsed;
	}
}
