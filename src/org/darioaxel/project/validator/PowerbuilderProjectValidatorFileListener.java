package org.darioaxel.project.validator;

import java.io.File;


import org.darioaxel.util.IFileListener;

import org.darioaxel.util.FileUtils;

public class PowerbuilderProjectValidatorFileListener implements IFileListener{

	private  File pbtFile = null;
	
	public File getPbtFile() {
		return pbtFile;
	}
	@Override
	public void updateFile(File file) {
		
		if (isPBTConfigurationFile(file)) {
			pbtFile = file;
		}		
	}

	@Override
	public void enterDir(File parent, File dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDir(File parent, File dir) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isPBTConfigurationFile(final File file) {
		String fileExt = FileUtils.getFileExtension(file);
		return fileExt.equals("pbt");
	}		
}
