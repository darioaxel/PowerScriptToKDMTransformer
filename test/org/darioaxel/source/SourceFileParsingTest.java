package org.darioaxel.source;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.darioaxel.mapper.KDMElementFactory;
import org.darioaxel.mapper.code.listener.PowerscriptPhase1Listener;
import org.darioaxel.mapper.code.listener.PowerscriptPhase2Listener;
import org.darioaxel.mapper.code.parser.PowerscriptSourceFileTypeParser;
import org.darioaxel.mapper.source.listener.PowerscriptInventoryModelFileListener;
import org.darioaxel.util.FileUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.junit.Test;

public class SourceFileParsingTest {

	@Test
	public void w_loginParsingTest() {
		
		Path root = Paths.get("/home/darioaxel/git/PowerScriptGrammar/resources/advanced/real/myproject/w_login.srw");
		Path result = Paths.get("/home/darioaxel/git/PowerScriptGrammar/testing_results/createW_loginTest.xmi");

		final SourceFactory sourceFactory = SourceFactory.eINSTANCE;
		InventoryContainer inventoryModel = sourceFactory.createInventoryContainer();
		
		PowerscriptInventoryModelFileListener imfl = new PowerscriptInventoryModelFileListener(inventoryModel, new ArrayList<String>());
		imfl.updateFile(root.toFile());
		
		PowerscriptSourceFileTypeParser typeParser = new PowerscriptSourceFileTypeParser();
		PowerscriptPhase1Listener listener = new PowerscriptPhase1Listener();
		typeParser.addListener(listener);
		
		SourceFile source = (SourceFile) inventoryModel.getInventoryElement().get(0);
		typeParser.parse(source);
		Segment segment = KDMElementFactory.createSegment();
		CodeModel code = KDMElementFactory.createCodeModel("test w_login");
		code.getCodeElement().add( ((PowerscriptPhase1Listener)typeParser.getListener()).getCompilationUnit());
		
		PowerscriptPhase2Listener listener2 = new PowerscriptPhase2Listener();
		typeParser.addListener(listener2);
		typeParser.parse(source, code);
		
		segment.getModel().add(code);
		
		FileUtils.saveEcoreToXMI(segment, result.toString(), new NullProgressMonitor());
		
		assertTrue(result.toFile().exists());
	}
}
