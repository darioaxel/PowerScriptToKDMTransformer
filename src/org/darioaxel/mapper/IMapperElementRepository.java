package org.darioaxel.mapper;

import java.util.Collection;

import org.darioaxel.mapper.source.listener.FileListener;
import org.darioaxel.mapper.source.listener.InventoryModelFileListener;
import org.darioaxel.mapper.source.listener.SourceFileListener;
import org.darioaxel.mapper.source.walker.InventoryModelWalker;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.Directory;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;

public interface IMapperElementRepository {
	
	 SourceFileListener getPhase1SourceFileListener();
	 SourceFileListener getPhase2SourceFileListener();
	 InventoryModelFileListener getInventoryModelFileListener(Directory root, Collection<String> languagesUsed);
	 InventoryModelWalker getPhase1InventoryModelWalker(InventoryModel inventoryModel, CodeModel codeModel);
}
