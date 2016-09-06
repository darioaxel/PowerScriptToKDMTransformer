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
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGLexer;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTLexer;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser;
import org.darioaxel.project.validator.pbg.Phase1LibraryListener;
import org.darioaxel.project.validator.pbt.Phase1ProjectListener;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public class LibraryDescriptorTypeParser extends TypeParser{
	
	private Phase1LibraryListener listener;
	
	public LibraryDescriptorTypeParser() {
	}
	
	public Object getListener() {
		return listener;
	}
	
	@Override
	public void parse(Object object){

		ResourceDescription resourceFile = (ResourceDescription) object;

		if (resourceFile.getVersion().equals("LibraryDescriptor")) {		

			Path path = Paths.get(resourceFile.getPath());

			TokenStream inputTokenStream = null;
			try {
				inputTokenStream = createPowerscriptProjectDescriptorTokenStream(path.toFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			powerscriptPBGParser parser = new powerscriptPBGParser(inputTokenStream);

			parser.addParseListener(listener);
			parser.prog();
		}
	}	
		
	private TokenStream createPowerscriptProjectDescriptorTokenStream(File program) throws IOException {

		CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
		TokenSource tokenSource = new powerscriptPBGLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}

	@Override
	public void parse(SourceFile e, CodeModel codeModel) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void addListener(Object listener) {
		// TODO Auto-generated method stub		
	}
}
