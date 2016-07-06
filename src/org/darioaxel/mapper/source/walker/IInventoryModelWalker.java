package org.darioaxel.mapper.source.walker;

import org.darioaxel.mapper.source.listener.SourceFileListener;

public interface IInventoryModelWalker {
	
	void walk(final SourceFileListener sourceFileListener);
}