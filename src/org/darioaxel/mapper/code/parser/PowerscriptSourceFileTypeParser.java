package org.darioaxel.mapper.code.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.darioaxel.grammar.powerscript.powerscriptBaseListener;
import org.darioaxel.grammar.powerscript.powerscriptLexer;
import org.darioaxel.grammar.powerscript.powerscriptParser;

public class PowerscriptSourceFileTypeParser extends SourceFileTypeParser {
	
	private final powerscriptBaseListener listener;
	private final Path path;
	
	
	public PowerscriptSourceFileTypeParser(Path path, powerscriptBaseListener listener) {
		this.listener = listener;
		this.path = path;
	}
	
	public powerscriptBaseListener getListener() {
		return listener;
	}
	
	public void parse(){
		
		TokenStream inputTokenStream = null;
		try {
			inputTokenStream = createPowerscriptSourceFileTypeParser(path.toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		powerscriptParser parser = new powerscriptParser(inputTokenStream);
			
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