package org.darioaxel.mapper;

import java.util.Collection;

import org.darioaxel.mapper.source.listener.InventoryModelFileListener;
import org.darioaxel.mapper.source.listener.PowerscriptInventoryModelFileListener;
import org.darioaxel.mapper.source.listener.SourceFileListener;
import org.darioaxel.mapper.source.walker.InventoryModelWalker;
import org.darioaxel.mapper.source.walker.PowerscriptPhase1InventoryModelWalker;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.Directory;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;

public final class PowerscriptElementRepository implements IMapperElementRepository
{
	@Override
	public SourceFileListener getPhase1SourceFileListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SourceFileListener getPhase2SourceFileListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryModelFileListener getInventoryModelFileListener(Directory root, Collection<String> languagesUsed) {
		return new PowerscriptInventoryModelFileListener(root,languagesUsed);		
	}

	@Override
	public InventoryModelWalker getPhase1InventoryModelWalker(InventoryModel inventoryModel, CodeModel codeModel) {
		// TODO Auto-generated method stub
		return new PowerscriptPhase1InventoryModelWalker(inventoryModel, codeModel);
	} 
}