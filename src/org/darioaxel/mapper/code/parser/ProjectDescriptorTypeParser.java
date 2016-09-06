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
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTLexer;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser;
import org.darioaxel.project.validator.pbt.Phase1ProjectListener;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class ProjectDescriptorTypeParser extends TypeParser{
	
	private Phase1ProjectListener listener;
			
	public ProjectDescriptorTypeParser() {
	}
	
	public void addListener(Phase1ProjectListener listener) {
		this.listener = listener;
	}
		
	@Override
	public Phase1ProjectListener getListener() {
		return listener;
	}
	
	@Override
	public void parse(Object object){
		ResourceDescription resourceFile = (ResourceDescription) object;

		if (resourceFile.getVersion().equals("ProjectDescriptor")) {		

			Path path = Paths.get(resourceFile.getPath());

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
	}	
		
	private TokenStream createPowerscriptProjectDescriptorTokenStream(File program) throws IOException {

		CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
		TokenSource tokenSource = new powerscriptPBTLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}

	@Override
	public void parse(SourceFile e, CodeModel codeModel) {
		Path path = Paths.get(e.getPath());
		TokenStream inputTokenStream = null;
		try {
			inputTokenStream = createPowerscriptProjectDescriptorTokenStream(path.toFile());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		powerscriptPBTParser parser = new powerscriptPBTParser(inputTokenStream);
			
		parser.addParseListener(listener);
		parser.prog();
	}
}
