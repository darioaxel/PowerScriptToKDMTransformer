package org.darioaxel.mapper.source.walker;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.darioaxel.mapper.source.listener.SourceFileListener;
import org.darioaxel.util.enums.EResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.AbstractInventoryElement;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class PowerscriptInventoryModelWalker extends InventoryModelWalker {
	
	public PowerscriptInventoryModelWalker(final InventoryModel inventoryModel) {
		 super(inventoryModel);
	}
	
	public void walk(final SourceFileListener visitor) {
		walk(inventoryModel.getInventoryElement(), visitor);		
	}

	private void walk(final List<AbstractInventoryElement> elements, final SourceFileListener visitor) {
				
		Optional<AbstractInventoryElement> projectDescription = elements.stream().filter( e -> e instanceof ResourceDescription 
				&& ((ResourceDescription) e).getVersion().equals(EResourceDescription.PROJECT.Type())).findFirst();
		
		Consumer<AbstractInventoryElement> visit = (pbt) -> super.resourceDescriptorFileVisitor.visitResourceDescriptor((ResourceDescription) pbt);
		projectDescription.ifPresent(visit);
		
		Optional<AbstractInventoryElement> libraryDescription = elements.stream().filter( e -> e instanceof ResourceDescription 
				&& ((ResourceDescription) e).getVersion().equals(EResourceDescription.LIBRARY.Type())).findFirst();
		libraryDescription.ifPresent(visit);

		
/*		
		for (AbstractInventoryElement inventoryElement : elements) {
			if (inventoryElement instanceof InventoryContainer) {
				walk(((InventoryContainer) inventoryElement).getInventoryElement(), visitor);
			} else if (inventoryElement instanceof SourceFile) {
				super.sourceFileVisitor.visitSourceFile((SourceFile) inventoryElement);
			}
		}
	*/
	}
}