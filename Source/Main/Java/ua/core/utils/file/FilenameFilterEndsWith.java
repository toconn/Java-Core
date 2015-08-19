package ua.core.utils.file;

import java.io.File;
import java.io.FilenameFilter;

import ua.core.utils.StringUtils;



public class FilenameFilterEndsWith implements FilenameFilter {
	
	
	private String	nameEnd	= null;
	
	
	public FilenameFilterEndsWith (String nameEnd) {
		
		this.nameEnd = nameEnd;
	}


	public boolean accept (File directory, String fileName) {
		
		return StringUtils.isEndsWith (fileName, this.nameEnd);
	}
}
