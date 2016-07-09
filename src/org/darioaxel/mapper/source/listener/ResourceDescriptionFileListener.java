package org.darioaxel.mapper.source.listener;

import org.eclipse.gmt.modisco.omg.kdm.source.AbstractInventoryElement;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;

public class ResourceDescriptionFileListener extends FileListener{
	
	public int getResourceDescriptorType() {
		return 1;
	}

	@Override
	public void visit(AbstractInventoryElement element) {
		ResourceDescription resourceDescription = (ResourceDescription) element;
		
	}
}

