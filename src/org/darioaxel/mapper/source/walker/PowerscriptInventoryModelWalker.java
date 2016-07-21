package org.darioaxel.mapper.source.walker;

import java.util.List;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.AbstractInventoryElement;
import org.eclipse.gmt.modisco.omg.kdm.source.Directory;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class PowerscriptInventoryModelWalker extends InventoryModelWalker {
		
	public PowerscriptInventoryModelWalker(final InventoryModel inventoryModel) {
		 super(inventoryModel);
	}
	
	@Override
	public void beforeWalk() {
		List<AbstractInventoryElement> elements = inventoryModel.getInventoryElement();	
		elements.get(0);
	}
	
	@Override
	public void walk() {				
		List<AbstractInventoryElement> elements = inventoryModel.getInventoryElement();	
		
		for(AbstractInventoryElement e : elements) {
			if (e instanceof SourceFile) {
				walk(e);
			}
		}
	}
		
	private void walk(AbstractInventoryElement e) {
		
		if (e instanceof Directory) {
			for(AbstractInventoryElement element : ((Directory) e).getInventoryElement()) {
				walk(element);
			}
		}
		if (e instanceof SourceFile) {
			super.sourceParser.parse((SourceFile) e);
		} else if (e instanceof ResourceDescription){
			super.resourceDescriptionParser.parse(e);
		}			
	}			
}
