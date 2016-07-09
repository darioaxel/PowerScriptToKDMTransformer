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

public class ProjectDescriptorTypeParser extends TypeParser{
	
	private PowerbuilderProjectPBTListener listener;
		
	public ProjectDescriptorTypeParser() {
	}
	
	@Override
	public void addListener(Object object) {
		PowerbuilderProjectPBTListener listener = (PowerbuilderProjectPBTListener) object;
		this.listener = listener;
	}
	
	@Override
	public PowerbuilderProjectPBTListener getListener() {
		return listener;
	}
	
	@Override
	public void parse(Object object){
		
		Path path = (Path) object;
		
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
