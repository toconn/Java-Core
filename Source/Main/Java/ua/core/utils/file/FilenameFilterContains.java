package ua.core.utils.file;

import java.io.File;
import java.io.FilenameFilter;

import ua.core.utils.StringUtils;


public class FilenameFilterContains implements FilenameFilter {
	
	
	private String	nameContains	= null;
	
	
	public FilenameFilterContains (String nameContains) {
		
		this.nameContains = nameContains;
	}


	public boolean accept (File directory, String fileName) {
		
		return StringUtils.containsIgnoreCase (fileName, this.nameContains);
	}

}
