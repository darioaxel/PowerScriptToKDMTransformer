package org.darioaxel.project.validator.pbg;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGBaseListener;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGListener;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.FileContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.HeaderContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.LibrariesContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.ObjectsContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.PathContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.PathsFromToContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.ProgContext;
import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGParser.QuotedPathContext;

public class PowerbuilderProjectPBGListener extends powerscriptPBGBaseListener{

	private List<Path> fileNames = new ArrayList<Path>();
	
	public List<Path> getFileNames () {
		return fileNames;
	}
	
	@Override
	public void exitObjects(ObjectsContext ctx) {
		List<PathsFromToContext> pathsFTContext = ctx.pathsFromTo();
		
		for(PathsFromToContext pathFTC: pathsFTContext) {
			fileNames.add(Paths.get(pathFileToString(pathFTC.quotedPath(0).path())));						
		}
	}
	
	private String pathFileToString(PathContext pathContext) {
		String file = pathContext.file().ID(0) + "." + pathContext.file().ID(1);
		return file;
	}
}
