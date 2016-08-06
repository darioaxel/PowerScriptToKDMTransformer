package org.darioaxel.mapper;

import java.util.Collection;

import org.darioaxel.mapper.code.listener.PowerscriptPhase1Listener;
//import org.darioaxel.mapper.code.parser.PowerscriptSourceFileTypeParser;
import org.darioaxel.mapper.code.parser.TypeParser;
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
	public PowerscriptPhase1Listener getPhase1SourceFileListener() {
		return new PowerscriptPhase1Listener();
	}

	@Override
	public SourceFileListener getPhase2SourceFileListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryModelFileListener getInventoryModelFileListener(Directory root, Collection<String> languagesUsed) {
		return null;		
	}

	@Override
	public InventoryModelWalker getPhase1InventoryModelWalker(InventoryModel inventoryModel) {
		// TODO Auto-generated method stub
		return new PowerscriptPhase1InventoryModelWalker(inventoryModel, null);
	}

	@Override
	public TypeParser getSouceFileParser() {
	//TODO Dario	return new PowerscriptSourceFileTypeParser();
		return null;
	}

	@Override
	public InventoryModelWalker getPhase2InventoryModelWalker(InventoryModel inventoryModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PowerscriptPhase1Listener getPhase1SourceFileListener(
			CodeModel codeModel) {
		// TODO Auto-generated method stub
		return null;
	} 
}
