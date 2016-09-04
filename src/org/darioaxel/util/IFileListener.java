package org.darioaxel.util;

import java.io.File;

public interface IFileListener {

	void updateFile(File file);
	void enterDir(File parent, File dir);
	void exitDir(File parent, File dir);
}