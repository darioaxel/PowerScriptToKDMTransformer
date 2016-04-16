package org.darioaxel.util;

import java.io.File;


public final class PowerscriptFileUtil {

	public PowerscriptFileUtil() {
		
	}
	
	public static String getExtension(final File file) {
		
		String name = file.getName();
		int lastIndexOf = name.lastIndexOf('.');
		if (lastIndexOf == -1) {
			return "";
		}
		
		String fileExt = name.substring(lastIndexOf + 1).toLowerCase();
		return fileExt;
	}
}
