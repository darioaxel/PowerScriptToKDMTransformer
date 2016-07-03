package org.darioaxel.mapper.source;

import java.util.List;

import org.darioaxel.mapper.visitors.SourceFileVisitor;
import org.eclipse.gmt.modisco.omg.kdm.source.AbstractInventoryElement;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class InventoryModelWalker {

	private final InventoryModel	inventoryModel;

	public InventoryModelWalker(final InventoryModel inventoryModel) {
		this.inventoryModel = inventoryModel;
	}

	public void walk(final SourceFileVisitor visitor) {
		walk(inventoryModel.getInventoryElement(), visitor);		
	}

	private void walk(final List<AbstractInventoryElement> elements, final SourceFileVisitor visitor) {
		for (AbstractInventoryElement inventoryElement : elements) {
			if (inventoryElement instanceof InventoryContainer) {
				walk(((InventoryContainer) inventoryElement).getInventoryElement(), visitor);
			} else if (inventoryElement instanceof SourceFile) {
				visitor.visitSourceFile((SourceFile) inventoryElement);
			}
		}
	}

}