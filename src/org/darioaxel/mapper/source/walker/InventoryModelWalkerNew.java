package org.darioaxel.mapper.source.walker;

import java.util.ArrayList;
import java.util.List;

import org.darioaxel.mapper.code.parser.TypeParser;
import org.darioaxel.util.enums.EResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.AbstractInventoryElement;
import org.eclipse.gmt.modisco.omg.kdm.source.Directory;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class InventoryModelWalkerNew {

	protected final InventoryModel inventoryModel;
	protected EResourceDescription resource;

	protected List<TypeParser> resourceParser = new ArrayList<TypeParser>();
	protected TypeParser sourceParser;	
	
	public InventoryModelWalkerNew(InventoryModel inventoryModel) {
		this.inventoryModel = inventoryModel;
	}	
	
	public void setSourceFileParser(final TypeParser sourceFileParser){
		this.sourceParser = sourceFileParser;
	}
	
	public void addResourceDescriptionParser(final TypeParser resourceDescriptorParser){
		resourceParser.add(resourceDescriptorParser);
	}
	
	public void walk() {
		
		List<AbstractInventoryElement> elements = inventoryModel.getInventoryElement();	
		walkRecursive(elements);					
	}
	
	private void walkRecursive(List<AbstractInventoryElement> elements) {
			
		for(AbstractInventoryElement e : elements) {
			if (e instanceof Directory) {
				walkRecursive(((Directory)e).getInventoryElement());
			}
			else if (e instanceof SourceFile){
				sourceParser.parse(e);
			}
			else if (e instanceof ResourceDescription) {
				for(TypeParser t : resourceParser){
		//			t.parse(e);
				}				
			}
		}		
}

}