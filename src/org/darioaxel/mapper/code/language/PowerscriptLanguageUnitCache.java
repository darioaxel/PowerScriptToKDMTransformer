package org.darioaxel.mapper.code.language;

import org.eclipse.gmt.modisco.omg.kdm.code.CodeFactory;

public class PowerscriptLanguageUnitCache  extends LanguageUnitCache{

	private static final CodeFactory CODE_FACTORY = CodeFactory.eINSTANCE;

	public PowerscriptLanguageUnitCache() {
		super(CODE_FACTORY.createLanguageUnit());
		init();
	}

	private void init() {
		
		setName("PowerscriptLanguage");

		addDatatype("object", CODE_FACTORY.createDatatype());
		addDatatype("dynamic", CODE_FACTORY.createDatatype());		
		addDatatype("string", CODE_FACTORY.createStringType());
		addDatatype("sbyte", CODE_FACTORY.createOctetType());
		addDatatype("short", CODE_FACTORY.createIntegerType());
		addDatatype("integer", CODE_FACTORY.createIntegerType());
		addDatatype("long", CODE_FACTORY.createIntegerType());
		addDatatype("byte", CODE_FACTORY.createOctetType());
		addDatatype("ushort", CODE_FACTORY.createIntegerType());
		addDatatype("uint", CODE_FACTORY.createIntegerType());
		addDatatype("ulong", CODE_FACTORY.createIntegerType());
		addDatatype("float", CODE_FACTORY.createFloatType());
		addDatatype("double", CODE_FACTORY.createFloatType());
		addDatatype("boolean", CODE_FACTORY.createBooleanType());
		addDatatype("char", CODE_FACTORY.createCharType());
		addDatatype("decimal", CODE_FACTORY.createDecimalType());
		addDatatype("void", CODE_FACTORY.createVoidType());
	}

}

