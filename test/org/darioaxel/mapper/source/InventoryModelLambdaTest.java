package org.darioaxel.mapper.source;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeFactory;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.source.AbstractInventoryElement;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

public class InventoryModelLambdaTest {

	private static final CodeFactory CODE_FACTORY = CodeFactory.eINSTANCE;


	@Test
	public void testingPackageCreator() {
		
		File directory = new File("../PowerScriptGrammar/resources/inventoryModel/nivel0");
		InventoryModel inventoryModel = new InventoryModels().create( directory);
		EList<AbstractInventoryElement> e = inventoryModel.getInventoryElement();
		
		walk(e);
		Package newPBG = CODE_FACTORY.createPackage();
	//	newPBG.createAggregation(null);
	}
		
	private void walk(final List<AbstractInventoryElement> elements) {
		for (AbstractInventoryElement inventoryElement : elements) {
			if (inventoryElement instanceof InventoryContainer) {
				
				walk(((InventoryContainer) inventoryElement).getInventoryElement());
			} else if (inventoryElement instanceof SourceFile) {
				 SourceFile sf = (SourceFile) inventoryElement;
				 System.out.println(sf.getPath());
				 System.out.println(sf.getName());
				 
			} else if (inventoryElement instanceof ResourceDescription) {
				 ResourceDescription sf = (ResourceDescription) inventoryElement;
				 System.out.println(sf.getPath());
				 System.out.println(sf.getName());
			}
		}
	}
}
