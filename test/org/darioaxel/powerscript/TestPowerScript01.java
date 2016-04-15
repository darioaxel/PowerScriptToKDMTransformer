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
import org.darioaxel.grammar.powerscript.powerscript_01Lexer;
import org.darioaxel.grammar.powerscript.powerscript_01Parser;

import static org.junit.Assert.assertFalse;

import org.junit.Test;




/**
 *
 * @author darioaxel
 */
public class TestPowerScript01 {
     
    private static final Path test_forward_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/forward/forward_01.sru");
    private static final Path test_forward_02 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/forward/forward_02.sru");
    private static final Path test_forward_03 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/forward/forward_03.sru");
    
    private static final Path test_globaltype_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/globalType/globaltype_01.sru");
    private static final Path test_globaltype_02 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/globalType/globaltype_02.sru");
    private static final Path test_globaltype_03 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/globalType/globaltype_03.sru");
    
    private static final Path test_string_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/literals/string/string_01.sru");
    private static final Path test_integer_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/literals/integer/integer_01.sru");
   
    private static final Path test_literals_arrays_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/literals/arrays/literalsArrays_01.sru");
   
    @Test
    public void testPowerscript_forward_01() throws IOException {

        TestErrorListener errorListener = new TestErrorListener();
        powerscript_01Parser.CompilationUnitContext context01 = parsePowerscript_01(test_forward_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_forward_02() throws IOException {

        TestErrorListener errorListener = new TestErrorListener();
        powerscript_01Parser.CompilationUnitContext context02 = parsePowerscript_01(test_forward_02.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_forward_03() throws IOException {

        TestErrorListener errorListener = new TestErrorListener();
        powerscript_01Parser.CompilationUnitContext context03 = parsePowerscript_01(test_forward_03.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
     @Test
    public void testPowerscript_globaltype_01() throws IOException {

        TestErrorListener errorListener = new TestErrorListener();
        powerscript_01Parser.CompilationUnitContext context01 = parsePowerscript_01(test_globaltype_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_globaltype_02() throws IOException {

        TestErrorListener errorListener = new TestErrorListener();
        powerscript_01Parser.CompilationUnitContext context02 = parsePowerscript_01(test_globaltype_02.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_globaltype_03() throws IOException {

        TestErrorListener errorListener = new TestErrorListener();
        powerscript_01Parser.CompilationUnitContext context03 = parsePowerscript_01(test_globaltype_03.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_string_01() throws IOException {

        TestErrorListener errorListener = new TestErrorListener();
        powerscript_01Parser.CompilationUnitContext context03 = parsePowerscript_01(test_string_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_integer_01() throws IOException {

        TestErrorListener errorListener = new TestErrorListener();
        powerscript_01Parser.CompilationUnitContext context03 = parsePowerscript_01(test_integer_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
     @Test
    public void testPowerscript_literalsArrays_01() throws IOException {

        TestErrorListener errorListener = new TestErrorListener();
        powerscript_01Parser.CompilationUnitContext context03 = parsePowerscript_01(test_literals_arrays_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    private powerscript_01Parser.CompilationUnitContext parsePowerscript_01(File program, 
            TestErrorListener errorListener) throws IOException {
        
        TokenStream inputTokenStream = createInputTokenStream(program);
        powerscript_01Parser parser = new powerscript_01Parser(inputTokenStream);

        parser.addErrorListener(errorListener);

        powerscript_01Parser.CompilationUnitContext context = parser.compilationUnit();
        return context;
    }
    
      private TokenStream createInputTokenStream(File program) throws IOException {
        
        CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
        TokenSource tokenSource = new powerscript_01Lexer(inputCharStream);
        TokenStream inputTokenStream = new CommonTokenStream(tokenSource);
        
        return inputTokenStream;
    }
}
