package org.darioaxel.mapper.source.walker;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.parser.SourceFileTypeParser;
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

public class PowerscriptPhase1InventoryModelWalker extends InventoryModelWalker {
	
	private static CodeModel codeModel;
	private Module parentFirst = null;
	
	public PowerscriptPhase1InventoryModelWalker(final InventoryModel inventoryModel, final CodeModel codeModel) {
		 super(inventoryModel);
		 PowerscriptPhase1InventoryModelWalker.codeModel = codeModel;
	}
	
	@Override
	public void beforeWalk() {
		List<AbstractInventoryElement> elements = inventoryModel.getInventoryElement();	
		elements.get(0);
	}
	
	@Override
	public void walk() {
	
		List<AbstractInventoryElement> elements = inventoryModel.getInventoryElement();	
		if (elements.size() == 1 && parentFirst == null) {
			if ( elements.get(0) instanceof InventoryContainer) {
				walk((InventoryContainer) elements.get(0), null);
			}
		}			
	}
	
	private static InventoryContainer walk(InventoryContainer container, final Module parent) {
		CodeAssembly project = null;
		Package pack = null;
		final Module newparent;
		
		List<AbstractInventoryElement> elements = container.getInventoryElement();
		if (isAnyResourceDescriptionFile(elements)) {
			Optional<AbstractInventoryElement> descriptor = getFirstDescriptorOfType(elements, EResourceDescription.PROJECT);
			if (descriptor.isPresent()) {	
				project = KDMElementFactory.createCodeAssembly(descriptor.get().getName());
				codeModel.getCodeElement().add(project);
			}
		
			Optional<AbstractInventoryElement> library = getFirstDescriptorOfType(elements, EResourceDescription.LIBRARY);
			if( library.isPresent()) {
				pack = KDMElementFactory.createPackageUnit((ResourceDescription)library.get());
				
				if ( project != null && pack != null) {				
					project.getCodeElement().add(pack);
					newparent = pack;					
				} else {
					if ( pack != null && parent != null ) {
						parent.getCodeElement().add(pack);						
					}
					newparent = parent;
				}
			}
			else {
				newparent = parent;
			}			
		} else {
			newparent = parent;
		}
		
		elements.stream().filter(e -> e instanceof SourceFile).map(e -> (SourceFile) e).forEach(sf -> walk(sf, newparent));
		elements.stream().filter(e -> e instanceof Directory).map(e -> (Directory) e).forEach( d -> walk(d , newparent));
		
		return container;
	}
	
	private static SourceFile walk(SourceFile e, Module parent) {
		SourceFileTypeParser sourceParser = new SourceFileTypeParser();
		//	super.sourceFileListener.visit(e);
	//	parent.getCodeElement().add((AbstractCodeElement) sourceFileListener.getCodeModel());
		
		return e;
	}

	private static boolean isAnyResourceDescriptionFile (List<AbstractInventoryElement> elements) {
		Optional<AbstractInventoryElement> resources = elements.stream().filter(e -> e instanceof ResourceDescription).findAny();
		if (resources.isPresent()) return true;
		return false;
	}
	
	private static Optional<AbstractInventoryElement> getFirstDescriptorOfType( List<AbstractInventoryElement> elements, EResourceDescription projectdefinition) {
				
		Stream<AbstractInventoryElement> resources = elements.stream().filter(e -> e instanceof ResourceDescription);
		return resources.filter(r -> r instanceof ResourceDescription 
				&& ((ResourceDescription) r).getVersion().equals(projectdefinition.Description()))
				.findFirst();
	}	
	
}