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
import org.darioaxel.grammar.powerscript.powerscript_02Lexer;
import org.darioaxel.grammar.powerscript.powerscript_02Parser;
import org.darioaxel.powerscript.listeners.TokensNotSepparatedListener;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 *
 * @author darioaxel
 */
public class PowerScript02Test {
     
    private static final Path test_forward_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/forward/forward_01.sru");
    private static final Path test_forward_02 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/forward/forward_02.sru");
    private static final Path test_forward_03 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/forward/forward_03.sru");
    
    private static final Path test_globaltype_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/globalType/globaltype_01.sru");
    private static final Path test_globaltype_02 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/globalType/globaltype_02.sru");
    private static final Path test_globaltype_03 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/globalType/globaltype_03.sru");
    
    private static final Path test_string_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/literals/string/string_01.sru");
    private static final Path test_integer_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/literals/integer/integer_01.sru");
    private static final Path test_constants_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/constants/constants_01.sru");
   
    private static final Path test_literals_arrays_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/literals/arrays/literalsArrays_01.sru");
    private static final Path test_variablesDeclaration_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/variablesDeclaration/variablesDeclaration_01.sru");
    private static final Path test_variablesDeclaration_02 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/variablesDeclaration/variablesDeclaration_02.sru");
    
    private static final Path test_typeDeclaration_01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/members/typeDeclaration/typeDeclaration_01.sru");
    
    private static final Path test_tokensNotSepparated01 = FileSystems.getDefault().getPath("../PowerScriptGrammar/resources/advanced/tokensNotSepparated/test1.srw");
    @Test
    public void testPowerscript_forward_01() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context01 = parsePowerscript_02(test_forward_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_forward_02() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context02 = parsePowerscript_02(test_forward_02.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_forward_03() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context03 = parsePowerscript_02(test_forward_03.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
     @Test
    public void testPowerscript_globaltype_01() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context01 = parsePowerscript_02(test_globaltype_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_globaltype_02() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context02 = parsePowerscript_02(test_globaltype_02.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_globaltype_03() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context03 = parsePowerscript_02(test_globaltype_03.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_string_01() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context03 = parsePowerscript_02(test_string_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_integer_01() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context03 = parsePowerscript_02(test_integer_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_literalsArrays_01() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context03 = parsePowerscript_02(test_literals_arrays_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_constants_01() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context03 = parsePowerscript_02(test_constants_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_variablesDeclaration_01() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context03 = parsePowerscript_02(test_variablesDeclaration_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_variablesDeclaration_02() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context03 = parsePowerscript_02(test_variablesDeclaration_02.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
    
    @Test
    public void testPowerscript_typeDeclaration_01() throws IOException {

        ErrorListenerTest errorListener = new ErrorListenerTest();
        powerscript_02Parser.CompilationUnitContext context03 = parsePowerscript_02(test_typeDeclaration_01.toFile(), errorListener);
        assertFalse(errorListener.isFail());    
    }
        
    private powerscript_02Parser.CompilationUnitContext parsePowerscript_02(File program, ErrorListenerTest errorListener) throws IOException {
        
        TokenStream inputTokenStream = createInputTokenStream(program);
        powerscript_02Parser parser = new powerscript_02Parser(inputTokenStream);

        parser.addErrorListener(errorListener);     
        parser.addParseListener(new TokensNotSepparatedListener());

        powerscript_02Parser.CompilationUnitContext context = parser.compilationUnit();
             
        return context;
    }
    
      private TokenStream createInputTokenStream(File program) throws IOException {
        
        CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
        TokenSource tokenSource = new powerscript_02Lexer(inputCharStream);
        TokenStream inputTokenStream = new CommonTokenStream(tokenSource);
        
        return inputTokenStream;
    }
}
