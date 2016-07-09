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
import org.darioaxel.grammar.powerscript.powerscriptBaseListener;
import org.darioaxel.grammar.powerscript.powerscriptLexer;
import org.darioaxel.grammar.powerscript.powerscriptParser;
import org.darioaxel.mapper.code.listener.PowerscriptPhase1Listener;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class PowerscriptSourceFileTypeParser extends TypeParser {
	
	private powerscriptBaseListener listener;
	
	public PowerscriptSourceFileTypeParser() {
	}
	
	@Override
	public powerscriptBaseListener getListener() {
		return listener;
	}
	
	@Override
	public void addListener(Object object) {
		this.listener = (powerscriptBaseListener) object;
	}
	
	@Override
	public void parse(Object object){
		
		SourceFile sourceFile = (SourceFile) object;
		
		TokenStream inputTokenStream = null;
		Path path = Paths.get(sourceFile.getPath());
		try {
			inputTokenStream = createPowerscriptSourceFileTypeParser(path.toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		powerscriptParser parser = new powerscriptParser(inputTokenStream);		
		
		if (listener instanceof PowerscriptPhase1Listener) {
			((PowerscriptPhase1Listener) listener).setCompilationUnit(sourceFile);
		}
		
		parser.addParseListener(listener);
		parser.compilationUnit();
	}	
	
		
	private TokenStream createPowerscriptSourceFileTypeParser(File program) throws IOException {

		CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
		TokenSource tokenSource = new powerscriptLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}	
}