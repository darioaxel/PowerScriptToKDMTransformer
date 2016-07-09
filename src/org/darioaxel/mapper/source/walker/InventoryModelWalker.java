package org.darioaxel.mapper.source.walker;

import org.darioaxel.mapper.code.parser.PowerscriptSourceFileTypeParser;
import org.darioaxel.mapper.code.parser.TypeParser;
import org.darioaxel.mapper.source.listener.ResourceDescriptionFileListener;
import org.darioaxel.mapper.source.listener.SourceFileListener;
import org.darioaxel.util.enums.EResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;

public class InventoryModelWalker implements IInventoryModelWalker{

	protected final InventoryModel inventoryModel;
	protected EResourceDescription resource;

	protected TypeParser resourceDescriptorParser;
	protected TypeParser sourceParser;
	
	
	public InventoryModelWalker(InventoryModel inventoryModel) {
		this.inventoryModel = inventoryModel;
		this.sourceParser= new PowerscriptSourceFileTypeParser();
	}
	
	@Override
	public void walk() {
			
	}
	
	public void setSourceFileParser(final TypeParser sourceFileParser){
		sourceParser = sourceFileParser;
	}
	
	public void setResourceDescriptionParser(final TypeParser resourceDescriptorParser){
		this.resourceDescriptorParser = resourceDescriptorParser;
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
