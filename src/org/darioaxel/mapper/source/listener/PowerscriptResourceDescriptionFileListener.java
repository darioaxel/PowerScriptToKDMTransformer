package org.darioaxel.mapper.source.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGLexer;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTLexer;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser;
import org.darioaxel.mapper.source.listener.error.ProjectDescriptorErrorListener;
import org.darioaxel.project.validator.pbt.PowerbuilderProjectPBTListener;
import org.darioaxel.util.enums.EResourceDescription;
import org.eclipse.gmt.modisco.omg.kdm.source.ResourceDescription;

public class PowerscriptResourceDescriptionFileListener extends ResourceDescriptionFileListener {
	
	private final PowerbuilderProjectPBTListener pbpPBTListener = new PowerbuilderProjectPBTListener();
	private final ProjectDescriptorErrorListener pDErrorListener = new ProjectDescriptorErrorListener();
	
	@Override
	public void visit(ResourceDescription resourceDescriptionFile) {
		
		TokenStream inputTokenStream;
		try {
			if (resourceDescriptionFile.getVersion().equals(EResourceDescription.PROJECT.Description())) {

				inputTokenStream = createPowerscriptProjectDescriptorTokenStream(new File(resourceDescriptionFile.getPath()));
				powerscriptPBTParser parser = new powerscriptPBTParser(inputTokenStream);

				parser.addErrorListener(pDErrorListener);				
				parser.addParseListener(pbpPBTListener);
				parser.prog();			
			} else {

				inputTokenStream = createPowerscriptLibraryDescriptorTokenStream(new File(resourceDescriptionFile.getPath()));
				powerscriptPBGParser parser = new powerscriptPBGParser(inputTokenStream);

				parser.addErrorListener(pDErrorListener);				
				parser.addParseListener(pbpPBTListener);
				parser.prog();			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private TokenStream createPowerscriptLibraryDescriptorTokenStream(File file) throws IOException {
		
		CharStream inputCharStream = new ANTLRInputStream(new FileReader(file));
		TokenSource tokenSource = new powerscriptPBGLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}

	private TokenStream createPowerscriptProjectDescriptorTokenStream(File program) throws IOException {

		CharStream inputCharStream = new ANTLRInputStream(new FileReader(program));
		TokenSource tokenSource = new powerscriptPBTLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);

		return inputTokenStream;
	}

}
