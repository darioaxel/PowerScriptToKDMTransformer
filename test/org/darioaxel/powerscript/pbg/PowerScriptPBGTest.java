package org.darioaxel.powerscript.pbg;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGLexer;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser;
import org.darioaxel.powerscript.ErrorListenerTest;
import org.darioaxel.project.validator.pbg.PowerbuilderProjectPBGListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
/**
 *
 * @author darioaxel
 */
public class PowerScriptPBGTest {

	private static final Path test_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/advanced/real/myproject/myproject.pbg");
	private static final Path test_02 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/advanced/real/myproject/lib/common/common.pbg");
	private static final Path test_03 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/advanced/real/myproject/lib/mydata/mydata.pbg");
	private static final Path test_04 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/advanced/real/myproject/lib/payroll/payroll.pbg");


	@Test
	public void test_01PowerscriptPBT() throws IOException {

		ErrorListenerTest errorListener = new ErrorListenerTest();
		powerscriptPBGParser.ProgContext context = parsePowerscriptPBG(test_01.toFile(), errorListener);
		assertFalse(errorListener.isFail());    
	}
	
	@Test
	public void test_02PowerscriptPBT() throws IOException {

		ErrorListenerTest errorListener = new ErrorListenerTest();
		powerscriptPBGParser.ProgContext context = parsePowerscriptPBG(test_02.toFile(), errorListener);
		assertFalse(errorListener.isFail());    
	}
	
	@Test
	public void test_03PowerscriptPBT() throws IOException {

		ErrorListenerTest errorListener = new ErrorListenerTest();
		powerscriptPBGParser.ProgContext context = parsePowerscriptPBG(test_03.toFile(), errorListener);
		assertFalse(errorListener.isFail());    
	}
	
	@Test
	public void test_04PowerscriptPBT() throws IOException {

		ErrorListenerTest errorListener = new ErrorListenerTest();
		powerscriptPBGParser.ProgContext context = parsePowerscriptPBG(test_04.toFile(), errorListener);
		assertFalse(errorListener.isFail());    
	}
	
	@Test
	public void testPowerbuilderProjectPBTListener() throws IOException {

		ErrorListenerTest errorListener = new ErrorListenerTest();
		PowerbuilderProjectPBGListener psPBGListener = parsePowerscriptPBGListener(test_01.toFile(), errorListener);
		
		assertEquals(psPBGListener.getFileNames().size(),2);
		assertFalse(errorListener.isFail());    
	}

	private powerscriptPBGParser.ProgContext parsePowerscriptPBG(File program, 
			ErrorListenerTest errorListener) throws IOException {

		TokenStream inputTokenStream = createInputTokenStream(program);
		powerscriptPBGParser parser = new powerscriptPBGParser(inputTokenStream);

		parser.addErrorListener(errorListener);

		powerscriptPBGParser.ProgContext context = parser.prog();
		return context;
	}
	
	private PowerbuilderProjectPBGListener parsePowerscriptPBGListener(File program, 
			ErrorListenerTest errorListener) throws IOException {

		TokenStream inputTokenStream = createInputTokenStream(program);
		powerscriptPBGParser parser = new powerscriptPBGParser(inputTokenStream);

		parser.addErrorListener(errorListener);
		PowerbuilderProjectPBGListener psPBGListener = new PowerbuilderProjectPBGListener();
		parser.addParseListener(psPBGListener);
		parser.prog();
		return psPBGListener;
	}

	private TokenStream createInputTokenStream(File program) throws IOException {

		CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
		TokenSource tokenSource = new powerscriptPBGLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}
}
