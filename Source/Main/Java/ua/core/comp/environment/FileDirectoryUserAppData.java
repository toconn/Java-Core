package ua.core.comp.environment;

import ua.core.utils.file.FileDirectory;
import ua.core.utils.file.FileUtils;
import ua.core.utils.os.OSPropertiesFactory;

/**
 * Locates the subdirectory in the user app data direcory (from the environment).
 * 
 * @author Tadhg
 *
 */
public class FileDirectoryUserAppData implements FileDirectory {
	
	String subdirectory;
	
	
	public FileDirectoryUserAppData (String subdirectory) {
		
		this.subdirectory = subdirectory;
	}
	

	public String getDirectory() {
		
		if (OSPropertiesFactory.getInstance().isOSWindows()) {

			return FileUtils.getPath (EnvironmentService.evaluateVariables (IEnvironmentConst.ENVIRONMENT_USER_APP_DATA_DIR_WINDOWS), subdirectory);
		}
		else {
			
			return FileUtils.getPath (EnvironmentService.evaluateVariables (IEnvironmentConst.ENVIRONMENT_USER_APP_DATA_DIR_DEFAULT), subdirectory);
		}
	}
}
