package org.darioaxel.mapper.source.walker;

import org.darioaxel.mapper.source.listener.ResourceDescriptionFileListener;
import org.darioaxel.mapper.source.listener.SourceFileListener;
import org.darioaxel.util.enums.EResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;

public abstract class InventoryModelWalker implements IInventoryModelWalker{

	protected final InventoryModel inventoryModel;
	protected EResourceDescription resource;

	protected SourceFileListener sourceFileVisitor;
	protected ResourceDescriptionFileListener resourceDescriptorFileVisitor;
	
	public InventoryModelWalker(InventoryModel inventoryModel) {
		this.inventoryModel = inventoryModel;
	}
	
	@Override
	public void walk(final Object listener) {
		// TODO Auto-generated method stub		
	}
	
	public void setSourceFileListener(final SourceFileListener sourceFileVisitor){
		this.sourceFileVisitor = sourceFileVisitor;
	}
	
	public void setResourceDescriptionFileListener(final ResourceDescriptionFileListener resourceDescriptorFileVisitor){
		this.resourceDescriptorFileVisitor = resourceDescriptorFileVisitor;
	}
}
