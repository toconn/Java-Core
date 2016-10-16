package ua.core.comp.environment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ua.core.utils.CollectionUtils;
import ua.core.utils.StringUtils;
import ua.core.utils.UrlUtils;
import ua.core.utils.file.FileUtils;
import ua.core.utils.os.OSProperties;
import ua.core.utils.os.OSPropertiesFactory;
import ua.core.utils.system.JavaConst;


public class EnvironmentService {
	
	@SuppressWarnings ("rawtypes")
	private static Class applicationClass;	// Used to locate the application directory

	
	
	/**
	 * Returns a list of strings with the string environment variables expanded.
	 * 
	 * Null safe (returns empty list).
	 * 
	 * @param textList
	 * @return
	 */
	public static List <String> evaluateVariables (List <String> textList) {
		
		// DECLARATIONS:
		
		List <String>	list;
		
		
		// MAIN:
		
		list = new ArrayList <String>();
		
		if (CollectionUtils.isNotEmpty (textList)) {
			
			for (String text : textList) {
				list.add (evaluateVariables (text));
			}
		}
		
		return list;
	}
	

    /** Process a string and replace all %environment% tokens with the 
     * actual environment variable values (if they exist). 
     * 
     * @param envString
     */
    public static String evaluateVariables (String envString) {
    	

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	String			envName					= null;
    	String			envValue				= null;
    	
		StringBuilder	expandedStringBuilder	= null;
		
		int				previousIndex			= 0;
		int				nextIndex				= 0;
		
		boolean			startingPercentFound	= false;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		if (StringUtils.isNotEmpty (envString)) {
			
			expandedStringBuilder = new StringBuilder();
			
			while (nextIndex > -1 && previousIndex < envString.length()) {
				
		    	// find next %
				
				nextIndex = envString.indexOf ('%', previousIndex);
				
				if (nextIndex > -1) {
		    	
					if (! startingPercentFound) {
						
						// if opening %...
		    	
						// append previous text to return string
						
						expandedStringBuilder.append (envString.substring (previousIndex, nextIndex));
						
						
		    			// update previous index...
					
						previousIndex = nextIndex + 1;
						startingPercentFound = true;
						
					}
					else {
		    		
						// if closing %...
						
						// lookup environment variable...
						
						if (previousIndex < nextIndex - 1) {
						
							envName  = envString.substring (previousIndex, nextIndex);
							envValue = System.getenv (envName);
						}
						else {
							
							envValue = null;
						}
		    	
		    			if (StringUtils.isNotEmpty (envValue)) {
		    				
		    				// Append environment value...
		    				
		    				expandedStringBuilder.append (envValue);
		    			}
		    			else {
		    				
		    				// Has no value. Append as it was in original string...
		    				
		    				expandedStringBuilder.append (envString.substring (previousIndex -1, nextIndex + 1));
		    			}

		    			
		    			// update previous index...
						
						previousIndex = nextIndex + 1;
						startingPercentFound = false;
					}
				}

			}
			
			if (previousIndex < envString.length()) {
				
				// Append remaining text
				expandedStringBuilder.append (envString.substring (previousIndex));
			}
	    	
	    	// return string
				
			return expandedStringBuilder.toString();
			
		}
	    else {
	    	
	    	return null;
	    }
    }
    
    
	public static String getEnvironmentVariable (String variableName) {
		
		return System.getenv (variableName);
	}
	
	
    /**
	 * Returns the application directory. 
	 * Important: The method getAppDir (Class) has to be called first.
	 * 
	 * @return
	 */
	public static String getAppDirectory() {
		
		// Return the directory the application executable / source is located.
		
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		String			className			= null;
		URL				classUrl			= null;
		String			directoryName		= null;
		
		int				trimStart			= 0;
		int				trimEnd				= 0;
		
		OSProperties	osProps;
		
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
		osProps			= OSPropertiesFactory.getInstance();

		className		= getApplicationClass().getSimpleName() + ".class";
		classUrl		= getApplicationClass().getResource (className);
		directoryName	= classUrl.getPath();
		
		// System.out.println ("class path: " + directoryName);
		// System.out.println ("class name: " + getApplicationClass().getName());
		
		
		// Find starting trim location...
		
		if (osProps.isOSWindows()) {
			if (directoryName.contains (":")) {
				
				// Contains URL Descriptor. Remove it.
				trimStart = directoryName.lastIndexOf (':') - 1;
			}
			else {
				// Remove leading '/'.
				trimStart = 1;
			}	
		}
			
		
		
		// Find ending trim location...
		
		if (directoryName.contains ("!")) {
			
			// Is in a jar file. 
			
			// Find '!' location in name...
				
			trimEnd = directoryName.indexOf ('!');
		
			// Find previous '/'.
			
			trimEnd = directoryName.lastIndexOf ('/', trimEnd - 1);
		
		}
		else {
			
			// should be a file.
			
			// Trim to class name.
			
			// trimEnd = directoryName.length() - getApplicationClass().getName().length() - 7;
			trimEnd = directoryName.length() - 7;
		}
		
		
		// Trim and format directory...
		
		/*
		 * Debug - Delete if no problems found.
		 * 

		System.out.println (directoryName);
		System.out.println (getApplicationClass().getName());
		System.out.println (trimStart);
		System.out.println (trimEnd);

		*/
		
		directoryName	= directoryName.substring (trimStart, trimEnd);
		directoryName	= UrlUtils.decode (directoryName);

		
		// Change the correct slashes...
		directoryName = osProps.formatPath (directoryName);


		return directoryName;
	}
	
	/**
	 * Return the user application data directory (directory where the application stores its config information).
	 * 
	 * @return
	 */
    public static String getAppDataAllDir() {
    	
    	// Declarations
    	
    	String	 value;
    	
    	
    	// Main
    	
    	value = System.getenv (IEnvironmentConst.ENVIRONMENT_SHARED_APP_DATA_DIR_WINDOWS);
    	
    	if (StringUtils.isEmpty(value)) {
    		
    		value = System.getenv (IEnvironmentConst.ENVIRONMENT_SHARED_APP_DATA_DIR_DEFAULT);
    	}
    	
        return value;
    }


    /**
     * Return the default user application data directory (directory where the application stores its config information).
     * 
     * Parses the all application data directory environment variable for multiple directories and returns the first.
     * 
     * Note: Returns null if it can't find anything.
     * 
     * @return
     */
    public static String getAppDataAllDirDefault() {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	List <String>	directoryPathList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
    	
    	directoryPathList = FileUtils.expandPathToList (getAppDataAllDir());
  
    	if (directoryPathList != null && directoryPathList.size() > 0) {
    		
    		return directoryPathList.get (0);
    	}
    	else {
    		
    		return null;
    	}
    }


    /**
     * Return the user application data directory (directory where the application stores its config information).
     * 
     * @return
     */
    public static String getAppDataUserDir() {
    	
    	// Declarations
    	
    	String	 value;
    	
    	
    	// Main
    	
    	value = System.getenv (IEnvironmentConst.ENVIRONMENT_USER_APP_DATA_DIR_WINDOWS);
    	
    	if (StringUtils.isEmpty(value)) {
    		
    		value = System.getenv (IEnvironmentConst.ENVIRONMENT_USER_APP_DATA_DIR_DEFAULT);
    	}
    	
    	return value;
    }


    /**
     * Return the default user application data directory (directory where the application stores its config information).
     * 
     * Parses the user application data directory environment variable for multiple directories and returns the first.
     * 
     * Note: Returns null if it can't find anything.
     * 
     * @return
     */
    public static String getAppDataUserDirDefault() {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	List <String>	directoryPathList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
    	
    	directoryPathList = FileUtils.expandPathToList (getAppDataUserDir());
  
    	if (directoryPathList != null && directoryPathList.size() > 0) {
    		
    		return directoryPathList.get (0);
    	}
    	else {
    		
    		return null;
    	}
    }


    public static String getAppDataUserAllDir() {
    	
    	// Return the generic all application config directory.

        return FileUtils.getPath (getAppDataUserDir(), IEnvironmentConst.APP_DATA_SUBDIRECTORY_ALL);
    }
    

    /**
     * Return the working directory (the directory where the program was launched from).
     * 
     * @return
     */
	public static String getWorkingDir() {
		
		return System.getProperty (JavaConst.JAVA_PROPERTIES_APP_DIR);
	}
	
	
	/**
	 * Locate a file in the current directory or in the AppData/All subdirectory.
	 * Returns the full path and file name.
	 *
     * The AppData directory is defined by the environment variable Apps.Data.Directory.All.
	 * 
	 * @param fileName
	 * @return
	 */
    public static String locateFile (String fileName) {

        return locateFile (IEnvironmentConst.APP_DATA_SUBDIRECTORY_ALL, fileName);
    }
	
	
    /** 
     * Locate a file in either the current directory in the supplied subdirectory of AppData directory.
     * Returns the full path and file name.
     * 
     * The AppData directory is defined by the environment variable Apps.Data.Directory.All.
     * 
     * @param appDataSubdirectory
     * @param fileName
     * @return
     */
    public static String locateFile (String appDataSubdirectory, String fileName) {

        String directoryName = null;

        // Attempt to locate the correct directory...
        
        directoryName = locateFileDirectory (appDataSubdirectory, fileName);
        
        
        // If found, append the file name to the directory and return it...

        if (StringUtils.isNotEmpty (directoryName)) {

            return FileUtils.getPath (directoryName, fileName);

        }
        else {

        	// Not found. Return nothing.
        	
            return null;
        }
    }


    public static String locateFileDirectory (String appDataSubdirectory, String fileName) {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	String			fileDirectory			= null;
    	List <String>	directorySearchList		= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

    	
        // Check Application Executable Directory...

        fileDirectory = getAppDirectory();

        if (FileUtils.isFileExists (FileUtils.getPath (fileDirectory, fileName))) {

            return fileDirectory;
        }


        // Check User App User Subdirectory Data Directories...

        directorySearchList = FileUtils.expandPathToList (getAppDataUserDir());
        
        for (String directory: directorySearchList) {
        	
        	fileDirectory = FileUtils.getPath (directory, appDataSubdirectory);

	        if (FileUtils.isFileExists (FileUtils.getPath (fileDirectory, fileName))) {
	
	        	return fileDirectory;
	        }
        }


        // Check Shared App All Data Subdirectory Directories...
        
		directorySearchList = FileUtils.expandPathToList (getAppDataAllDir());
		        
        for (String directory: directorySearchList) {
        	
        	fileDirectory = FileUtils.getPath (directory, appDataSubdirectory);

        	if (FileUtils.isFileExists  (FileUtils.getPath (fileDirectory, fileName))) {

        		return fileDirectory;
        	}
        }


        // Not found...

        return null;
    }
    
    
	/**
	 * Locate one or more files of the same name in either the current directory or in one or more AppData/All subdirectories.
	 *
     * The AppData directory is defined by the environment variable Apps.Data.Directory.All. Directories are separated by ;.
	 * 
	 * @param fileName
	 * @return
	 */
    public static List <String> locateFileList (String fileName) {

        return locateFileList (IEnvironmentConst.APP_DATA_SUBDIRECTORY_ALL, fileName);
    }
	
	
    /** 
     * Locate one or more files of the same name in either the current directory or in one or more of the supplied subdirectory of AppData directory.
     * 
     * The AppData directory is defined by the environment variable Apps.Data.Directory.All. Directories are separated by ;.
     * 
     * @param appDataSubdirectory
     * @param fileName
     * @return
     */
    public static List <String> locateFileList (String appDataSubdirectory, String fileName) {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	List <String> directoryNameList	= null;
    	List <String> fileNameList		= null;

        
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
        
        directoryNameList	= locateFileDirectoryList (appDataSubdirectory, fileName);
        fileNameList		= FileUtils.getFileNameList (directoryNameList, fileName);

        return fileNameList;
    }
    
    

    /**
     * Return one or more directories where the file is located. If in the current directory,
     * no other directories will be searched. Otherwise all possible locations will be searched
     * and all matching files will be returned.
     * 
     * Searches the current directory, the user app subdirectories, the user all subdirectories.
     * 
     * Note: Returns null if none are found.
     * 
     * Note: App User files are placed first over App All files in the returned list.
     * 
     * @param appDataSubdirectory
     * @param fileName
     * @return
     */
    public static List <String> locateFileDirectoryList (String appDataSubdirectory, String fileName) {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	boolean			directoryFound			= false;
    	
    	String			fileDirectory			= null;
    	List <String>	fileDirectoryList		= null;
    	
    	List <String>	directorySearchList		= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

    	
    	fileDirectoryList = new ArrayList <String>();
    	
        // Check Application Executable Directory...

        fileDirectory = getAppDirectory();

        if (FileUtils.isFileExists (FileUtils.getPath (fileDirectory, fileName))) {

        	fileDirectoryList.add (fileDirectory);
        	
            return fileDirectoryList;
        }


        // Check User App User Subdirectory Data Directories...

        directorySearchList = FileUtils.expandPathToList (getAppDataUserDir());
        
        if (CollectionUtils.isNotEmpty(directorySearchList)) {

	        for (String directory: directorySearchList) {
	        	
	        	fileDirectory = FileUtils.getPath (directory, appDataSubdirectory);
	
		        if (FileUtils.isFileExists  (FileUtils.getPath (fileDirectory, fileName))) {
		
		        	fileDirectoryList.add (fileDirectory);
		        	directoryFound = true;
		        }
	        }
    	}
    

        // Check Shared App All Data Subdirectory Directories...
        
		directorySearchList = FileUtils.expandPathToList (getAppDataAllDir());
		        
        if (CollectionUtils.isNotEmpty(directorySearchList)) {

	        for (String directory: directorySearchList) {
	        	
	        	fileDirectory = FileUtils.getPath (directory, appDataSubdirectory);
	
	        	if (FileUtils.isFileExists  (FileUtils.getPath (fileDirectory, fileName))) {
	
	        		fileDirectoryList.add (fileDirectory);
		        	directoryFound = true;
	        	}
	        }
        }


        // Not found...

        if (directoryFound) {
        	
        	return fileDirectoryList;
        }
        else {
        	
        	return null;
        }
        
        
        
    }

    
    /**
     * Set an object that lives in the application local directory.
     * 
     * @param object
     */
    public static void setApplicationClass (@SuppressWarnings ("rawtypes") Class localClass) {
    	
    	EnvironmentService.applicationClass = localClass;
    }
    
    
    @SuppressWarnings ({"rawtypes" })
	static Class getApplicationClass() {
    	
    	if (applicationClass != null) {
    		return applicationClass;
    	}
    	else {
    		throw new IllegalStateException ("Application class not set. Please call EnvironmentService.setApplicationClass before calling EnvironmentService methods.");
    	}
    }
    
    /**
     * Extracts the root directory for a class file.
     * 
     * Class Path      = directory + package + class name + .class (in every form, with jars, without, etc.)
     * Full Class Name = package + class name + .class
     * 
     * @param classPath
     * @return
     */
    static String getClassRootDirectory (String classPath, String fullClassName) {
    	
    	System.out.println ("Class Path : " + classPath);


    	classPath	= UrlUtils.decode (classPath);
    	
		// Find ending trim location...
		
		if (classPath.contains ("!")) {
			
			// A jar file (in format 'directory/jarfile.jar!package/className.class'):
			
			classPath = StringUtils.toLeftOf (classPath, "!");		// Remove '!package/classname.class'
			classPath = StringUtils.toLeftOfLast (classPath, "/");	// Remove jar name.
		}
		else {
			
			// A class file ('appdir/package/className.class'):
			
			classPath = classPath.substring (0, classPath.length() - fullClassName.length() - 7);	// -1 for '.' in front of class name, -6 for .class
		}
		
		if (classPath.contains ("file:")) {
			classPath = classPath.substring (5);
		}
    	
    	return classPath;
    }
}