package ua.core.utils.file;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.core.base.ExceptionItemNotFound;
import ua.core.base.MessageConst;
import ua.core.utils.MessageUtils;


public class DirectoryUtils {

	/**
	 * Copies the entire folder from the source directory to the target diretory.
	 * 
	 * @param sourceDirectory
	 * @param targetDirectory
	 * @throws IOException 
	 * @throws ExceptionItemNotFound 
	 */
	public static void copyDirectory (String sourceDirectory, String targetDirectory) throws IOException, ExceptionItemNotFound {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		File		sourceDirFile	= null;
		File		targetDirFile	= null;
		
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		sourceDirFile = new File (sourceDirectory);
		targetDirFile = new File (targetDirectory);
		
		copyDirectory (sourceDirFile, targetDirFile);
	}
	
	
	/**
	 * Copies the entire directory to the target directory.
	 * 
	 * @param sourceDirectory
	 * @param targetDirectory
	 * @throws IOException
	 * @throws ExceptionItemNotFound 
	 */
	public static void copyDirectory (File sourceDirectory, File targetDirectory) throws IOException, ExceptionItemNotFound {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String[]	dirList		= null;

		File		sourceFile	= null;
		File		targetFile	= null;
		
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		if (! sourceDirectory.exists ())
			
			throw new ExceptionItemNotFound (MessageUtils.toString (MessageConst.MESSAGE_FILE_NOT_FOUND, sourceDirectory.getName ()));
			
		
		if (sourceDirectory.isDirectory ()) {
			
			// Create destination directory...
			
			if (! targetDirectory.exists ())
				
				targetDirectory.mkdir ();
			
			
			// Read source directory...
			
			dirList = sourceDirectory.list ();
			
			// Copy contents...
			
			for (String dirFile: dirList) {
				
				sourceFile = new File (sourceDirectory,	dirFile);
				targetFile = new File (targetDirectory,	dirFile);
				
				copyDirectory (sourceFile, targetFile);
			}
		}
		else
			
			FileUtils.copyFile (sourceDirectory, targetDirectory);
		
	}
	
	
	public static void createDirectory (String path) {

		// //////////////////////////////////////////////////////////////////////////
		// Declarations:
		// //////////////////////////////////////////////////////////////////////////

		File directory = null;

		// //////////////////////////////////////////////////////////////////////////
		// Code:
		// //////////////////////////////////////////////////////////////////////////

		directory = new File (path);

		if (!directory.exists ()) {

			directory.mkdirs ();
		}

	}

	
	/**
	 * Searches a list if paths to find where a directory is located.
	 * Will return either the path to the directory or null if nothing is found.
	 * 
	 * @param pathList
	 * @param directoryName
	 * @return
	 */
	public static String findDirectoryPath (List<String> pathList, String directoryName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String	directoryPath	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: pathList) {
    		
    		if (isDirectoryExists (FileUtils.getPath (pathName, directoryName))) {
    		
    			directoryPath = pathName;
    			break;
    		}
    	}
    	
    	return directoryPath;
    }
    
    
    /** 
     * Finds one or more paths containing a directory from a list of possible paths.
     * Will return either the path list or null if nothing is found.
     * 
     * @param pathList
     * @param directoryName
     * @return
     */
    public static List<String> findDirectoryPathRList (List<String> pathList, String directoryName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		List<String>	directoryPathList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: pathList) {
    		
    		if (isDirectoryExists (FileUtils.getPath (pathName, directoryName))) {
    			
    			if (directoryPathList == null)
    				
    				directoryPathList = new ArrayList<String> ();
    		
    			directoryPathList.add (pathName);
    			break;
    		}
    	}
    	
    	return directoryPathList;
    }


	/**
	 * Returns a listing of subdirectories only.
	 * 
	 * 
	 * @param directoryName
	 * @param includeSubdirectories
	 * @return
	 */
	public static List <File> getDirectoryDirectoryList (String directory) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File		directoryFile		= null;
		List <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= Arrays.asList (directoryFile.listFiles (new FileFilterDirectory ()));
		
		return directoryFileList;
	}


	/**
	 * Returns a listing of subdirectories only.
	 * 
	 * 
	 * @param directoryName
	 * @param includeSubdirectories
	 * @return
	 */
	public static List <File> getDirectoryDirectoryList (String directory, boolean includeSubdirectories) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File		directoryFile		= null;
		List <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= Arrays.asList (directoryFile.listFiles (new FileFilterDirectory ()));
		
		
		// If include subdirectories...
		
		if (includeSubdirectories) {
		
			for (File subDirFile : directoryFileList) {
				
				directoryFileList.addAll (getDirectoryDirectoryList (subDirFile.getPath (), includeSubdirectories));
			}
		}
		
		return directoryFileList;
	}


	/**
	 * Get the directory listing for the directory.
	 * 
	 * 
	 * @param directory
	 * @param includeSubdirectories
	 * @return
	 */
	public static List <File> getDirectoryFileList (String directory) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File		directoryFile		= null;
		List <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= Arrays.asList (directoryFile.listFiles());

		return directoryFileList;
	}

	
	/**
	 * Get the directory listing for the directory.
	 * Optionally include subdirectories as well.
	 * 
	 * 
	 * @param directory
	 * @param includeSubdirectories
	 * @return
	 */
	public static List <File> getDirectoryFileList (String directory, boolean includeSubdirectories) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File		directoryFile		= null;
		List <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= Arrays.asList (directoryFile.listFiles());
		
		
		// If include subdirectories...
		
		if (includeSubdirectories) {
		
			for (File subFile : directoryFileList) {
				
				if (subFile.isDirectory ()) {
					
					directoryFileList.addAll (getDirectoryFileList (subFile.getPath (), includeSubdirectories));
				}
			}
		}
		
		return directoryFileList;
	}


	/**
	 * Return a filtered file listing of the directory.
	 * Optionally includes subdirectories.
	 * 
	 */
	public static List <File> getDirectoryFileList (String directory, boolean includeSubdirectories, FilenameFilter filter) {

		// ////////////////////////////////////////////////////////////////
		// Declarations
		// ////////////////////////////////////////////////////////////////

		List <File>	directoryFileList		= null;
		List <File>	directoryDirectoryList	= null;

		
		// ////////////////////////////////////////////////////////////////
		// Code
		// ////////////////////////////////////////////////////////////////
		
		// Get the current directory's files...
		
		directoryFileList	= getDirectoryFileList (directory, filter);
		
		
		// Check the subdirectories...
		
		if (includeSubdirectories) {
			
			directoryDirectoryList = getDirectoryDirectoryList (directory);
			
			for (File directoryFile : directoryDirectoryList) {
				
				directoryFileList.addAll (getDirectoryFileList (directoryFile.getPath(), includeSubdirectories, filter));
			}
		}

		return directoryFileList;
	}


	/**
	 * Get the directory listing for the current directory.
	 * 
	 * 
	 * @param directory
	 * @param includeSubdirectories
	 * @return
	 */
	public static List <File> getDirectoryFileList (String directory, FilenameFilter filter) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File			directoryFile		= null;
		List <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= Arrays.asList (directoryFile.listFiles (filter));

		return directoryFileList;
	}

	
	/**
	 * Return a file listing of the directory.
	 * Returns a string list only.
	 * 
	 */
	public static List <String> getDirectoryFileStringList (String directory) {

		// ////////////////////////////////////////////////////////////////
		// Declarations
		// ////////////////////////////////////////////////////////////////

		File			directoryFile	= null;
		List<String>	dirStringList	= null;

		
		// ////////////////////////////////////////////////////////////////
		// Code
		// ////////////////////////////////////////////////////////////////

		directoryFile	= new File (directory);
		dirStringList	= Arrays.asList (directoryFile.list ());

		return dirStringList;
	}

	
	/**
	 * Return a filtered file listing of the directory.
	 * Returns a string list only.
	 * 
	 */
	public static List<String> getDirectoryFileStringList (String directory, FilenameFilter filter) {

		// ////////////////////////////////////////////////////////////////
		//   Declarations
		// ////////////////////////////////////////////////////////////////

		File			directoryFile	= null;
		List<String>	dirStringList	= null;

		
		// ////////////////////////////////////////////////////////////////
		//   Code
		// ////////////////////////////////////////////////////////////////


		directoryFile	= new File (directory);
		dirStringList	= Arrays.asList (directoryFile.list (filter));

		return dirStringList;
	}
	
	
	public static boolean isDirectoryExists (String directoryName) {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		File	directory		= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		directory = new File (directoryName);
		
		return (directory.exists () && directory.isDirectory ());
	}
}
