package org.darioaxel.mapper.code.language;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.gmt.modisco.omg.kdm.code.LanguageUnit;

public final class LanguageUnitDetector {
	
	
	public static Collection<LanguageUnit> getLanguages(Collection<String> languagesIds) {
		
		Collection<LanguageUnit> languages = new ArrayList<LanguageUnit>();
		
		for(String language : languagesIds) {
			if (language.equals("Powerscript")) {
				languages.add(new PowerscriptLanguageUnitCache().getLanguageUnit());
			}			
		}
		
        return languages;
    }
}
