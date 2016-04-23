package org.darioaxel.mapper.source;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;

public interface ModelCreator<T> {

	T create(final File directory, IProgressMonitor monitor);
	T create(final String dirname, IProgressMonitor monitor);
}