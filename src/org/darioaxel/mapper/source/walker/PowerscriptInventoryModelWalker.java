package org.darioaxel.mapper.source.walker;

import java.util.List;

import org.darioaxel.mapper.source.listener.ResourceDescriptionFileListener;
import org.darioaxel.mapper.source.listener.SourceFileListener;
import org.darioaxel.util.enums.ResourceDescriptionEnum;
import org.eclipse.gmt.modisco.omg.kdm.source.AbstractInventoryElement;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class PowerscriptInventoryModelWalker extends InventoryModelWalker {
	
	private ResourceDescriptionEnum resource;

	private final InventoryModel inventoryModel;
	private SourceFileListener sourceFileVisitor;
	private ResourceDescriptionFileListener resourceDescriptorFileVisitor;

	public PowerscriptInventoryModelWalker(final InventoryModel inventoryModel) {
		this.inventoryModel = inventoryModel;
	}
	
	public void setSourceFileListener(final SourceFileListener sourceFileVisitor){
		this.sourceFileVisitor = sourceFileVisitor;
	}

	public void walk(final SourceFileListener visitor) {
		walk(inventoryModel.getInventoryElement(), visitor);		
	}

	private void walk(final List<AbstractInventoryElement> elements, final SourceFileListener visitor) {
		//TODO Lambdaaaa!!!
		for (AbstractInventoryElement inventoryElement : elements) {
			if(inventoryElement instanceof ResourceDescription) {
				if(((ResourceDescription) inventoryElement).getVersion().equals(resource.PROJECT)) {
					visitor.visitResourceDescription();
				}
			}
		}
		
		for (AbstractInventoryElement inventoryElement : elements) {
			if (inventoryElement instanceof InventoryContainer) {
				walk(((InventoryContainer) inventoryElement).getInventoryElement(), visitor);
			} else if (inventoryElement instanceof SourceFile) {
				visitor.visitSourceFile((SourceFile) inventoryElement);
			}
		}
	}

}