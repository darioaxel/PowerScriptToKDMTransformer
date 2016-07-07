package org.darioaxel.mapper.source.listener;

import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;

public class ResourceDescriptionFileListener extends FileListener{

	public void visit(ResourceDescription resourceDescriptionFile) {
		
	}
	
	public int getResourceDescriptorType() {
		return 1;
	}
}

