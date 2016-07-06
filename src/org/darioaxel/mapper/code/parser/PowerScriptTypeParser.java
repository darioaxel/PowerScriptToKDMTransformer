package org.darioaxel.mapper.code.parser;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class PowerScriptTypeParser implements ISourceFileTypeParser {

	@Override
	public void readInto(final SourceFile sourceFile, final CodeModel internalCodeModel, final IProgressMonitor monitor) {

	
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

	
	}

	@Override
	public String getLanguageString() {
		return "PowerScript";
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		
	}

}