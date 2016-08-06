package org.darioaxel.mapper.code.language;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.gmt.modisco.omg.kdm.code.LanguageUnit;

public class LanguageUnits {
	
	
	public static Collection<LanguageUnit> getLanguages(Collection<String> languagesIds) {
		
		Collection<LanguageUnit> languages = new ArrayList<LanguageUnit>();
		
		for(String language : languagesIds) {
			if (language.equals("Powerscript")) {
				languages.add(new PowerscriptLanguageUnitCache().getLanguageUnit());
			}			
		}
		
        return languages;
    }
	
	public static LanguageUnit getLanguage(String languageName) {
		if (languageName.equals("Powerscript")) {
		return new PowerscriptLanguageUnitCache().getLanguageUnit();
		}
		else return null;
	}
}
