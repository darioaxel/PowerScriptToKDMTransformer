package org.darioaxel.mapper.code.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import lang.csharp.CSharp4AST;
import lang.csharp.CSharp4AST.compilation_unit_return;
import lang.csharp.CSharp4PreProcessorImpl;
import mapping.IKDMMapper;
import mapping.code.transformator.Phase1Transformator;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.CompilationUnit;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.junit.Assert;

import util.Logger;
import util.Parsing;
import util.unicode.ANTLRFileStreamWithBOM;

public class PowerScriptTypeParser implements ISourceFileTypeParser {

	private static final Logger	LOGGER	= new Logger(CSharpTypeParser.class);

	@Override
	public void readInto(final SourceFile sourceFile, final CodeModel internalCodeModel, final IProgressMonitor monitor) {

		try {
//			CharStream input = new ANTLRFileStreamWithBOM(sourceFile.getPath(), "UTF-8");
//			Lexer lex = new CSharp4PreProcessorImpl(input, "NET_4_0");
//			CommonTokenStream tokens = new CommonTokenStream(lex);
//			// tokens.fill();
//			// System.out.println("tokens: "+tokens.getTokens());
//			CSharp4AST parser = new CSharp4AST(tokens);
//
//			LOGGER.info("Parsing file..." + sourceFile.getPath());
//			compilation_unit_return cunit = parser.compilation_unit();
//
//			Assert.assertEquals(0, parser.getErrors().size());
//
//			if (cunit.getTree() == null) {
//				// file is completely uncommented or empty, skip further processing
//				LOGGER.info("File contains only comments or nothing. We skip it. ("
//						+ sourceFile.getPath() + ")");
//				monitor.worked(1);
//				return;
//			}
//
//			LOGGER.info("Creating AST...");
//			CommonTree tree = Parsing.buildAST(cunit, tokens);
//
//			LOGGER.info("Transforming AST...");
//			IKDMMapper<CompilationUnit> sourceFileTransformator;
//			sourceFileTransformator = new Phase1Transformator(internalCodeModel);
//			sourceFileTransformator.transform(tree, sourceFile);
//			// get the new CompilationUnit (list contains only one CompilationUnit)
//			List<CompilationUnit> compilationUnits = sourceFileTransformator.getMappingResult();
//			// and add it to the internal CodeModel
//			internalCodeModel.getCodeElement().addAll(compilationUnits);

			monitor.worked(1);
		} catch (FileNotFoundException e) {
			LOGGER.info("Source file does not exist ('" + sourceFile.getPath() + "')");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getLanguageString() {
		return "C#";
	}

}