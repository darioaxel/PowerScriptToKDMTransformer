package org.darioaxel.mapper.code.parser;

public abstract class TypeParser implements ITypeParser
{
	@Override
	public abstract void parse(Object object);

	@Override
	public abstract Object getListener();
	@Override
	public abstract void addListener(Object listener);
}
