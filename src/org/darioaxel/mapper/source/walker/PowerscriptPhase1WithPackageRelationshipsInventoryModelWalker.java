package org.darioaxel.mapper.source.walker;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.listener.PowerscriptPhase1Listener;
import org.darioaxel.util.enums.EResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeAssembly;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.Module;
import org.eclipse.gmt.modisco.omg.kdm.source.AbstractInventoryElement;
import org.eclipse.gmt.modisco.omg.kdm.source.Directory;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;

public class PowerscriptPhase1WithPackageRelationshipsInventoryModelWalker extends InventoryModelWalker {
	
	private static CodeModel codeModel;
		
	public PowerscriptPhase1WithPackageRelationshipsInventoryModelWalker(final InventoryModel inventoryModel, final CodeModel codeModel) {
		 super(inventoryModel);
		 PowerscriptPhase1WithPackageRelationshipsInventoryModelWalker.codeModel = codeModel;
	}
	
	@Override
	public void beforeWalk() {
		List<AbstractInventoryElement> elements = inventoryModel.getInventoryElement();	
		elements.get(0);
	}
	
	@Override
	public void walk() {
		
		List<AbstractInventoryElement> elements = inventoryModel.getInventoryElement();	
		sourceFilesWalk(elements);
		packageDescriptionWalk(elements);
		codeAssemblyWalk(elements);		
	}
	
	private void sourceFilesWalk(List<AbstractInventoryElement> elements) {
		
		for (AbstractInventoryElement element: elements) {
			if (element instanceof SourceFile) {
				sourceParser.parse((SourceFile) element, codeModel);
			}
			else if (element instanceof Directory) {
				sourceFilesWalk(((Directory) element).getInventoryElement());
			}
		}		
	}	 
	
	private void packageDescriptionWalk(List<AbstractInventoryElement> elements) {
		for (AbstractInventoryElement element: elements) {
			if (element instanceof ResourceDescription) {
				sourceParser.parse((SourceFile) element, codeModel);
			}
			else if (element instanceof Directory) {
				sourceFilesWalk(((Directory) element).getInventoryElement());
			}
		}			
	}
	
	private void codeAssemblyWalk(List<AbstractInventoryElement> elements) {
		// TODO Auto-generated method stub
		
	}
	
	private void walk(SourceFile e, final CodeModel codeModel) {
		super.sourceParser.parse(e, codeModel);
	}		
}