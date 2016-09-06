package org.darioaxel.mapper.code.listener;

import org.darioaxel.grammar.powerscript.pbg.powerscriptPBGBaseListener;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;

public class Phase1LibraryListener extends powerscriptPBGBaseListener {

	private final CodeModel codeModel;
	
	public Phase1LibraryListener(CodeModel codeModel) {
		this.codeModel = codeModel;
	}

}
