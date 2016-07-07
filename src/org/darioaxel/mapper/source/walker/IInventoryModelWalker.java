package org.darioaxel.mapper.source.walker;

import org.darioaxel.mapper.source.listener.FileListener;

public interface IInventoryModelWalker {
	void beforeWalk();
	void walk();	
	void afterWalk();
}