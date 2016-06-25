package org.darioaxel.project.validator.pbg;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGListener;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.FileContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.HeaderContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.LibrariesContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.ObjectsContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.PathContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.PathsFromToContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.ProgContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.QuotedPathContext;

public class PowerbuilderProjectPBGListener implements powerscriptPBGListener{

	private List<String> fileNames = new ArrayList<String>();
	
	public List<String> getFileNames () {
		return fileNames;
	}
	
	@Override
	public void exitObjects(ObjectsContext ctx) {
		List<PathsFromToContext> pathsFTContext = ctx.pathsFromTo();
		
		for(PathsFromToContext pathFTC: pathsFTContext) {
			fileNames.add(pathFileToString(pathFTC.quotedPath(0).path()));						
		}
	}
	
	private String pathFileToString(PathContext pathContext) {
		String file = pathContext.file().ID(0) + "." + pathContext.file().ID(1);
		return file;
	}
	
	@Override
	public void enterEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterProg(ProgContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitProg(ProgContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterPathsFromTo(PathsFromToContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitPathsFromTo(PathsFromToContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterQuotedPath(QuotedPathContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitQuotedPath(QuotedPathContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterPath(PathContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitPath(PathContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterFile(FileContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitFile(FileContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterHeader(HeaderContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitHeader(HeaderContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterLibraries(LibrariesContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitLibraries(LibrariesContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterObjects(ObjectsContext ctx) {
		// TODO Auto-generated method stub
		
	}
}
