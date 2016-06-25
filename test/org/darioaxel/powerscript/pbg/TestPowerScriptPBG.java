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
import org.darioaxel.powerscript.TestErrorListener;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
/**
 *
 * @author darioaxel
 */
public class TestPowerScriptPBG {

	private static final Path test = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/advanced/real/myproject/myproject.pbg");

	@Test
	public void testPowerscriptPBT() throws IOException {

		TestErrorListener errorListener = new TestErrorListener();
		powerscriptPBGParser.ProgContext context = parsePowerscriptPBG(test.toFile(), errorListener);
		assertFalse(errorListener.isFail());    
	}

	private powerscriptPBGParser.ProgContext parsePowerscriptPBG(File program, 
			TestErrorListener errorListener) throws IOException {

		TokenStream inputTokenStream = createInputTokenStream(program);
		powerscriptPBGParser parser = new powerscriptPBGParser(inputTokenStream);

		parser.addErrorListener(errorListener);

		powerscriptPBGParser.ProgContext context = parser.prog();
		return context;
	}

	private TokenStream createInputTokenStream(File program) throws IOException {

		CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
		TokenSource tokenSource = new powerscriptPBGLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}
}
