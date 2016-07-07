package org.darioaxel.mapper.source.walker;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.util.enums.EResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeAssembly;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.AbstractInventoryElement;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;

public class PowerscriptPhase1InventoryModelWalker extends InventoryModelWalker {
	
	private static CodeModel codeModel;
	private AbstractCodeElement parent = null;
	
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
		
		//If there is only an element and it is a container 
		List<AbstractInventoryElement> elements = inventoryModel.getInventoryElement();	
		if (elements.size() == 1 && parent == null) {
			if ( elements.get(0) instanceof InventoryContainer) {
				walk((InventoryContainer) elements.get(0), null);
			}
		}
				
		Optional<AbstractInventoryElement> projectDescription = elements.stream().filter( e -> e instanceof ResourceDescription 
				&& ((ResourceDescription) e).getVersion().equals(EResourceDescription.PROJECT.Type())).findFirst();
		
		Consumer<AbstractInventoryElement> visit = (pbt) -> super.resourceDescriptorFileVisitor.visit((ResourceDescription) pbt);
		projectDescription.ifPresent(visit);
		
		Optional<AbstractInventoryElement> libraryDescription = elements.stream().filter( e -> e instanceof ResourceDescription 
				&& ((ResourceDescription) e).getVersion().equals(EResourceDescription.LIBRARY.Type())).findFirst();
		libraryDescription.ifPresent(visit);		
	}
	
	private void walk(InventoryContainer container, Package parent) {
		CodeAssembly project = null;
		Package pack = null;
		
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
				if( pack != null) {
					project.getCodeElement().add(pack);
				} else if (parent != null) {
					parent.getCodeElement().add(pack);
				}
			}			
		}	
		
		elements.stream().filter(e -> e instanceof SourceFile).map(e -> walk((SourceFile) e, parent));
	}
	
	private SourceFile walk(SourceFile e, Package parent) {
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