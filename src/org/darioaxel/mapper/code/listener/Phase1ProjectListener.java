package org.darioaxel.mapper.code.listener;

import org.darioaxel.grammar.powerscript.pbt.powerscriptPBTBaseListener;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;

public class Phase1ProjectListener extends powerscriptPBTBaseListener {

	private final CodeModel codeModel;
	
	public Phase1ProjectListener(CodeModel codeModel) {
		this.codeModel = codeModel;
	}
}
