package org.darioaxel.mapper.code.language;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.code.Datatype;
import org.eclipse.gmt.modisco.omg.kdm.code.LanguageUnit;

public class LanguageUnitCache {

	private final LanguageUnit languageUnit;
	private final Map<String, Datatype>	cache = new HashMap<String, Datatype>();

	public LanguageUnitCache(final LanguageUnit languageUnit) {
		this.languageUnit = languageUnit;
	}

	public LanguageUnit getLanguageUnit() {
		return languageUnit;
	}
	
	public Datatype getDatatypeFromString(final String datatypeName) throws NotInCacheException {
		
		Datatype datatype = cache.get(datatypeName);
		
		if (datatype == null) {
			throw new NotInCacheException();
		}
		return datatype;
	}

	public void addDatatype(final String datatypeName, final Datatype datatype) {
		
		datatype.setName(datatypeName);
		languageUnit.getCodeElement().add(datatype);
		cache.put(datatype.getName(), datatype);
	}

	public void setName(final String name) {
		languageUnit.setName(name);
	}

}