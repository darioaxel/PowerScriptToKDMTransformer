package org.darioaxel.mapper.source.listener;

import org.darioaxel.util.enums.ResourceDescriptionEnum;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;

public class ResourceDescriptionFileListener implements IResourceDescriptionFileListener {

	private ResourceDescription	resourceDescriptionFile = null;
	
	@Override
	public void beforeWalk() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void visitResourceDescriptor(ResourceDescription resourceDescriptionFile) {
		this.resourceDescriptionFile = resourceDescriptionFile;
	}

	@Override
	public void afterWalk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getResourceDescriptorType() {
		
		if (this.resourceDescriptionFile.getVersion().equals(ResourceDescriptionEnum.PROJECT.Description())) {
			return ResourceDescriptionEnum.PROJECT.Type();
		}
		else if (this.resourceDescriptionFile.getVersion().equals(ResourceDescriptionEnum.LIBRARY.Description())) {
			return ResourceDescriptionEnum.PROJECT.Type();
		}
		return -1;
	}

}
