package org.darioaxel.mapper.code.parser;

public interface ITypeParser {

	void parse(Object object);
	Object getListener();
	void addListener(Object listener);
}
