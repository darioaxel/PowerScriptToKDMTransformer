package org.darioaxel.mapper.source.listener;

import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;

public interface IResourceDescriptionFileListener {

	void beforeWalk();

	void visitResourceDescriptor(ResourceDescription resourceDescriptionFile);

	void afterWalk();

	int getResourceDescriptorType();
}

