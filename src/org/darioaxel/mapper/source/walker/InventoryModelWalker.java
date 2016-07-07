package org.darioaxel.mapper.source.walker;

import org.darioaxel.mapper.source.listener.ResourceDescriptionFileListener;
import org.darioaxel.mapper.source.listener.SourceFileListener;
import org.darioaxel.util.enums.EResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;

public class InventoryModelWalker implements IInventoryModelWalker{

	protected final InventoryModel inventoryModel;
	protected EResourceDescription resource;

	protected SourceFileListener sourceFileListener;
	protected ResourceDescriptionFileListener resourceDescriptorFileVisitor;
	
	public InventoryModelWalker(InventoryModel inventoryModel) {
		this.inventoryModel = inventoryModel;
	}
	
	@Override
	public void walk() {
			
	}
	
	public void setSourceFileListener(final SourceFileListener sourceFileListener){
		this.sourceFileListener = sourceFileListener;
	}
	
	public void setResourceDescriptionFileListener(final ResourceDescriptionFileListener resourceDescriptorFileVisitor){
		this.resourceDescriptorFileVisitor = resourceDescriptorFileVisitor;
	}

	@Override
	public void beforeWalk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterWalk() {
		// TODO Auto-generated method stub
		
	}
}
