package ua.core.comp.environment;

import ua.core.utils.file.FileDirectory;
import ua.core.utils.file.FileUtils;
import ua.core.utils.os.OSPropertiesFactory;


public class FileDirectoryUserSharedAppData implements FileDirectory {

	String subdirectory;
	
	
	public FileDirectoryUserSharedAppData (String subdirectory) {
		
		this.subdirectory = subdirectory;
	}
	

	public String getDirectory () {
		
		if (OSPropertiesFactory.getInstance ().isOSWindows ()) {

			return FileUtils.getPath (EnvironmentService.evaluateVariables (IEnvironmentConst.ENVIRONMENT_SHARED_APP_DATA_DIR_WINDOWS), subdirectory);
		}
		else {
			
			return FileUtils.getPath (EnvironmentService.evaluateVariables (IEnvironmentConst.ENVIRONMENT_SHARED_APP_DATA_DIR_DEFAULT), subdirectory);
		}
	}
}
