package org.darioaxel.project.validator.pbt;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTBaseListener;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.ApplibContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.AppnameContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.LiblistContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.PathContext;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;

public class Phase1ProjectListener extends powerscriptPBTBaseListener{

	private String appname;
	private Path applib;
	private List<Path> liblist = new ArrayList<Path>();
		
	public String getAppname() {
		return appname;
	}
	
	public Path getApplib() {
		return applib;
	}
	
	public List<Path> getLiblist() {
		return liblist;
	}
	
	@Override
	public void exitApplib(ApplibContext ctx) {
		applib = Paths.get((pathToString(ctx.path())));		
	}
	
	@Override
	public void exitAppname(AppnameContext ctx) {		
		appname = ctx.ID().toString();
	}
	
	@Override
	public void exitLiblist(LiblistContext ctx) {
		List<PathContext> paths = ctx.path();
		for(PathContext pathContext : paths) {
			liblist.add(Paths.get(pathToString(pathContext)));
		}		
	}
	
	private String pathToString(PathContext pathContext) {
		
		String file = pathFileToString(pathContext);
		List<TerminalNode> pathTokens = pathContext.ID();
		String path = "/";
		for(TerminalNode dir : pathTokens) {
			path += dir.getText() + "/";
		}
		return path + file;
	}

	private String pathFileToString(PathContext pathContext) {
		String file = pathContext.file().ID(0) + "." + pathContext.file().ID(1);
		return file;
	}	
}
