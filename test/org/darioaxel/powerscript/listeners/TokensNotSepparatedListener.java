package org.darioaxel.powerscript.listeners;

import org.darioaxel.grammar.powerscript.powerscript_02BaseListener;
import org.darioaxel.grammar.powerscript.powerscript_02Parser;

public class TokensNotSepparatedListener extends powerscript_02BaseListener {

	
	@Override public void enterVariableDeclaration(powerscript_02Parser.VariableDeclarationContext ctx) {
	
		
	}
	
	@Override public void exitVariableDeclarator(powerscript_02Parser.VariableDeclaratorContext ctx) { 
		System.out.println("a ver: " + ctx.Identifier());
	}
}
