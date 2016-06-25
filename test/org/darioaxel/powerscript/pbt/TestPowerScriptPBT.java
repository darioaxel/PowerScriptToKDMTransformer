package org.darioaxel.powerscript.pbt;

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
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTLexer;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser;
import org.darioaxel.powerscript.TestErrorListener;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
/**
 *
 * @author darioaxel
 */
public class TestPowerScriptPBT {

	private static final Path test = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/advanced/real/myproject/myproject.pbt");

	@Test
	public void testPowerscriptPBT() throws IOException {

		TestErrorListener errorListener = new TestErrorListener();
		powerscriptPBTParser.ProgContext context = parsePowerscriptPBT(test.toFile(), errorListener);
		assertFalse(errorListener.isFail());    
	}

	private powerscriptPBTParser.ProgContext parsePowerscriptPBT(File program, 
			TestErrorListener errorListener) throws IOException {

		TokenStream inputTokenStream = createInputTokenStream(program);
		powerscriptPBTParser parser = new powerscriptPBTParser(inputTokenStream);

		parser.addErrorListener(errorListener);

		powerscriptPBTParser.ProgContext context = parser.prog();
		return context;
	}

	private TokenStream createInputTokenStream(File program) throws IOException {

		CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
		TokenSource tokenSource = new powerscriptPBTLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}
}

