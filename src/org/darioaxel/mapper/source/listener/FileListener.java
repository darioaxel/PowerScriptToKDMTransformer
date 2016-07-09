package org.darioaxel.mapper.source.listener;

import java.util.Collection;

import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.AbstractInventoryElement;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;

public abstract class FileListener implements IFileListener{
	
	@Override
	public void beforeWalk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterWalk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public abstract void visit(AbstractInventoryElement element); 

	public CodeModel getCodeModel() {
		// default implementation: return null
		return null;
	}
}
