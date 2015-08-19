package ua.core.comp.environment;

import ua.core.utils.file.FileDirectory;


public class FileDirectoryApp implements FileDirectory {

	public String getDirectory () {
		
		return EnvironmentService.getAppDirectory ();
	}
}
