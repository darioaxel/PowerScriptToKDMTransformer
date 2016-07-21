package org.darioaxel.mapper.code.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.darioaxel.grammar.powerscript.powerscriptLexer;
import org.darioaxel.grammar.powerscript.powerscriptParser;
import org.darioaxel.mapper.code.listener.Phase1SourceListener;
import org.darioaxel.mapper.code.listener.PowerscriptListener;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;


public class SourceFileTypeParserNew extends TypeParser {
	
	private PowerscriptListener listener;
		
	public SourceFileTypeParserNew() {
	}
	
	@Override
	public Object getListener() {
		return listener;
	}
	
	public void addListener(final PowerscriptListener object) {
		this.listener = object;
	}
	
	public void removeListener() {
		this.listener = null;
	}
	
	@Override
	public void parse(Object object){
		
		SourceFile sourceFile = (SourceFile) object;
		
		TokenStream inputTokenStream = null;
		Path path = Paths.get(sourceFile.getPath());
		
		try {
			inputTokenStream = createPowerscriptSourceFileTypeParser(path.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		powerscriptParser parser = new powerscriptParser(inputTokenStream);		
		
		listener.setCompilationUnit(sourceFile);		
		
		if (listener != null){
			parser.addParseListener((ParseTreeListener) listener);
			parser.compilationUnit();
		}		
	}	
			
	private TokenStream createPowerscriptSourceFileTypeParser(File program) throws IOException {

		CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
		TokenSource tokenSource = new powerscriptLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}

	@Override
	public void parse(SourceFile e, CodeModel codeModel) {
		// TODO Auto-generated method stub
		
	}	
}