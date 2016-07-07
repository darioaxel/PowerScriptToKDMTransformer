package org.darioaxel.mapper.source.listener;

import org.eclipse.gmt.modisco.omg.kdm.source.AbstractInventoryElement;

public interface IFileListener {

	void beforeWalk();

	void visit(AbstractInventoryElement element);

	void afterWalk();
}

