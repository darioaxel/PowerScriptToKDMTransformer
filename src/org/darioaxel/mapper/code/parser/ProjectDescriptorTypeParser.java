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
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTLexer;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser;
import org.darioaxel.project.validator.pbt.PowerbuilderProjectPBTListener;

public class ProjectDescriptorTypeParser {
	
	private final PowerbuilderProjectPBTListener listener;
	private final Path path;
	
	
	public ProjectDescriptorTypeParser(Path path, PowerbuilderProjectPBTListener listener) {
		this.listener = listener;
		this.path = path;
	}
	
	public PowerbuilderProjectPBTListener getListener() {
		return listener;
	}
	
	public void parse(){
		
		TokenStream inputTokenStream = null;
		try {
			inputTokenStream = createPowerscriptProjectDescriptorTokenStream(path.toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		powerscriptPBTParser parser = new powerscriptPBTParser(inputTokenStream);
			
		parser.addParseListener(listener);
		parser.prog();
	}	
		
	private TokenStream createPowerscriptProjectDescriptorTokenStream(File program) throws IOException {

		CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
		TokenSource tokenSource = new powerscriptPBTLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}
}
