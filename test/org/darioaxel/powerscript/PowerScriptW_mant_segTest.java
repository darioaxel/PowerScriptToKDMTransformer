/*
 * The MIT License
 *
 * Copyright 2016 darioaxel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.darioaxel.powerscript;

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
import org.darioaxel.grammar.powerscript.powerscriptLexer;
import org.darioaxel.grammar.powerscript.powerscriptParser;
import org.darioaxel.grammar.powerscript.powerscript_03Lexer;
import org.darioaxel.grammar.powerscript.powerscript_03Parser;

import static org.junit.Assert.assertFalse;

import org.junit.Test;


/**
 *
 * @author darioaxel
 */
public class PowerScriptW_mant_segTest {

	private static final Path test_forward_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/advanced/w_mant_seg/part1.srw");
	private static final Path test_forward_02 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/advanced/w_mant_seg/part2.srw");

	@Test
	public void testPowerscript_part1() throws IOException {

		ErrorListenerTest errorListener = new ErrorListenerTest();
		powerscriptParser.CompilationUnitContext context01 = parsePowerscript(test_forward_01.toFile(), errorListener);
		assertFalse(errorListener.isFail());    
	}

	@Test
	public void testPowerscript_part2() throws IOException {

		ErrorListenerTest errorListener = new ErrorListenerTest();
		powerscriptParser.CompilationUnitContext context02 = parsePowerscript(test_forward_02.toFile(), errorListener);
		assertFalse(errorListener.isFail());    
	}

	private powerscriptParser.CompilationUnitContext parsePowerscript(File program, 
			ErrorListenerTest errorListener) throws IOException {

		TokenStream inputTokenStream = createInputTokenStream(program);
		powerscriptParser parser = new powerscriptParser(inputTokenStream);

		parser.addErrorListener(errorListener);

		powerscriptParser.CompilationUnitContext context = parser.compilationUnit();
		return context;
	}

	private TokenStream createInputTokenStream(File program) throws IOException {

		CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
		System.out.println(inputCharStream);
		
		TokenSource tokenSource = new powerscriptLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}
}
