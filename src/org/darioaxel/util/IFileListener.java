package org.darioaxel.util;

import java.io.File;
import org.eclipse.core.runtime.IProgressMonitor;

public interface IFileListener {

	void updateFile(File dir, File file);
	void enterDir(File parent, File dir);
	void exitDir(File parent, File dir);
}