package org.darioaxel.project.validator.pbt;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTListener;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.ApplibContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.AppnameContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.FileContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.HeadersContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.LiblistContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.LibrariesContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.ListProjectsContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.PathContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.ProgContext;
import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTParser.ProjectsContext;

public class PowerbuilderProjectPBTListener implements powerscriptPBTListener{

	private String appname;
	private File applib;
	private List<File> liblist = new ArrayList<File>();
	
	public String getAppname() {
		return appname;
	}
	
	public File getApplib() {
		return applib;
	}
	
	public List getLiblist() {
		return liblist;
	}
	
	@Override
	public void exitApplib(ApplibContext ctx) {
		applib = new File(pathToString(ctx.path()));		
	}

	@Override
	public void exitAppname(AppnameContext ctx) {		
		appname = ctx.ID().toString();
	}
	
	@Override
	public void exitLiblist(LiblistContext ctx) {
		List<PathContext> paths = ctx.path();
		for(PathContext pathContext : paths) {
			liblist.add(new File(pathToString(pathContext)));
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
	public void enterLibraries(LibrariesContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitLibraries(LibrariesContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterHeaders(HeadersContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitHeaders(HeadersContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterProjects(ProjectsContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitProjects(ProjectsContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterAppname(AppnameContext ctx) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void enterApplib(ApplibContext ctx) {
		// TODO Auto-generated method stub
		
	}
		
	@Override
	public void enterListProjects(ListProjectsContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitListProjects(ListProjectsContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterLiblist(LiblistContext ctx) {
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
}
