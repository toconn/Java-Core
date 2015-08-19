/*
 * @author TOCONNEL
 * Created on Jul 27, 2004
 *
 */
package ua.core.utils.file;

import java.io.*;
import java.nio.channels.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import ua.core.base.ExceptionRuntime;
import ua.core.beans.NameValuePair;
import ua.core.utils.CollectionUtils;
import ua.core.utils.StringParser;
import ua.core.utils.StringUtils;
import ua.core.utils.os.OSConst;
import ua.core.utils.os.OSProperties;
import ua.core.utils.os.OSPropertiesFactory;


/**
 * @author TOCONNEL Created on Jul 27, 2004
 * 
 */
public class FileUtils {

	public static final void copyFile (File sourceFile, File destinationFile) throws IOException {
		
		// Note:
		
		// File Path = full file name and directory
		// File Name = name of the file without the directory information.
		// File Dir is the directory part of the path with no name.
		

		// //////////////////////////////////////////////////////////////////////////
		// Declarations:
		// //////////////////////////////////////////////////////////////////////////

		FileChannel sourceFileChannel		= null;
		FileChannel destinationFileChannel	= null;

		
		// //////////////////////////////////////////////////////////////////////////
		// Code:
		// //////////////////////////////////////////////////////////////////////////

		if (! destinationFile.exists ()) {

			destinationFile.createNewFile ();
		}

		try {

			sourceFileChannel = new FileInputStream (sourceFile).getChannel ();
			destinationFileChannel = new FileOutputStream (destinationFile).getChannel ();
			destinationFileChannel.transferFrom (sourceFileChannel, 0, sourceFileChannel.size ());

			destinationFile.setLastModified (sourceFile.lastModified ());
		}
		finally {

			if (sourceFileChannel != null) {

				sourceFileChannel.close ();
			}

			if (destinationFileChannel != null) {

				destinationFileChannel.close ();
			}
		}
	}


	/**
	 * Deletes a file. It checks first to see that the file actually exists.
	 * 
	 * @param filePath
	 */
	public static void delete (String filePath) {
		
		File file = new File (filePath);
		
		if (file.exists()) {
			
			file.delete();
		}
	}


	/**
     * Takes a string of ";" separated path and return in a StringList.
     * 
     * @param pathString
     * @return
     */
    public static List<String> expandPathToList (String pathString) {
    	
    	if (pathString != null) {
    	
    		return (new StringParser (OSConst.PATH_SEPARATORS)).parse (pathString);
    	}
    	else {
    		
    		return null;
    	}
    }
    
    
    /** 
     * Finds the path to a file from a list of possible paths.
     * Will return either the path or null if nothing is found.
     * 
     * @param dirList
     * @param fileName
     * @return
     */
    public static String findFileDirectory (List<String> dirList, String fileName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String	fileDirectory	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String directory: dirList) {
    		
    		if (isFileExists (getPath (directory, fileName))) {
    		
    			fileDirectory = directory;
    			break;
    		}
    	}
    	
    	return fileDirectory;
    }
    
    
    /** 
     * Finds one or more paths containing a file from a list of possible paths.
     * Will return either the path list or null if nothing is found.
     * 
     * @param dirList
     * @param fileName
     * @return
     */
    public static List<String> findFileDirectoryList (List<String> dirList, String fileName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		List<String>	fileDirList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: dirList) {
    		
    		if (isFileExists (getPath (pathName, fileName))) {
    			
    			if (fileDirList == null)
    				
    				fileDirList = new ArrayList <String> ();
    		
    			fileDirList.add (pathName);
    			break;
    		}
    	}
    	
    	return fileDirList;
    }
	
	
    public static String findFileInClassPath (String fileName) throws ExceptionRuntime {

		// ////////////////////////////////////////////////////////////////////////////////////
		// Declarations:
		// ////////////////////////////////////////////////////////////////////////////////////

		NameValuePair dummyClass = null; // Used to get class loader.

		URL fileURL = null;
		String filePath = null;

		// ////////////////////////////////////////////////////////////////////////////////////
		// Code:
		// ////////////////////////////////////////////////////////////////////////////////////

		if (fileName != null) {

			try {

				dummyClass = new NameValuePair (null, null);
				fileURL = dummyClass.getClass ().getClassLoader ().getResource (fileName);

				if (fileURL != null) {
					filePath = URLDecoder.decode (fileURL.getPath (), IFileConst.FILE_ENCODING_UTF8);
				}

				if (filePath != null) {
					filePath = FileUtils.getParentPath (filePath);
				}
			}
			catch (Exception e) {

				throw new ExceptionRuntime (e);
			}
		}

		return filePath;
	}


    /**
     * Returns the java canonical form of the path.
     * 
     * @param path
     * @return
     * @throws IOException
     */
	public static String getCanonicalPath (String path) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File file = null;
		String newPath = null;

		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		file = new File (path);
		newPath = file.getCanonicalPath ();

		return newPath;
	}
	
	
	public static String getExtension (String fileName) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		int		extensionSepIndex	= 0;
		String	fileExtension		= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		if (fileName != null) {
			
			extensionSepIndex = fileName.lastIndexOf (IFileConst.FILE_EXTENSION_SEPARATOR);
			
			if (extensionSepIndex != -1 && extensionSepIndex < fileName.length() - 1) {
				
				fileExtension = fileName.substring (extensionSepIndex + 1);
			}
		}

		return fileExtension;
	}


	/**
	 * Returns a list of file names given a list of directories and one file name.
	 * 
	 * Used to get the full file path for the same file in multiple paths.
	 * 
	 * @param directoryList
	 * @param fileName
	 */
	public static List<String> getFileNameList (List<String> directoryNameList, String fileName) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

        List <String> fileNameList			= null;

        
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
        
        if (CollectionUtils.isNotEmpty (directoryNameList)) {
        	
        	fileNameList = new ArrayList<String> ();
        	
        	for (String directoryName: directoryNameList) {
        		
        		fileNameList.add (FileUtils.getPath (directoryName, fileName));
        	}
        	
            return fileNameList;

        }
        else {

            return null;

        }
	}
	
	
	/**
	 * Returns the parent of the path.
	 * 
	 * It must return a directory.
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String getParentPath (String path) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File currentFile = null;
		String currentPath = null;

		File parentFile = null;

		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		currentFile = new File (path);
		currentPath = currentFile.getCanonicalPath ();

		currentFile = new File (currentPath);
		parentFile = currentFile.getParentFile ();

		if (parentFile != null) {

			return parentFile.getCanonicalPath ();
		}
		else {

			return null;
		}
	}
	

	/**
	 * Returns the path (file directory + sparator + file name).
	 * 
	 * Returns the correct format for the os.
	 * 
	 * @param directoryName
	 * @param fileName
	 * @return
	 */
	public static String getPath (String directoryName, String fileName) {
		
		return directoryName + File.separator + fileName;
	}
	
	
	/**
	 * Returns the file directory without the file name.
	 * 
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getPathDirectory (String filePath) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File	file		= null;


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		file	= new File (filePath);
		
		return file.getParent ();
	}	


	/**
	 * Returns the file name only (not full path, no directory).
	 * 
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getPathName (String filePath) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File	file		= null;


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		file	= new File (filePath);
		
		return file.getName ();
	}
	
	
	/**
	 * Returns the name subpart of a file name.
	 * File name = subname.ext. Returns subname.
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSubname (String fileName) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		int		extensionSepIndex	= 0;
		String	subname				= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		if (fileName != null) {
			
			extensionSepIndex = fileName.lastIndexOf (IFileConst.FILE_EXTENSION_SEPARATOR);
			
			if (extensionSepIndex > 0) {
				
				subname = fileName.substring (0, extensionSepIndex);
			}
		}

		return subname;
	}


	/**
	 * Checks to see if the path is an absolute path.
	 * 
	 * Will return false for a relative path.
	 * 
	 * @param path
	 * @return
	 */
	public static final boolean isAbsolutePath (String path) {
		
		OSProperties osProps;

		osProps = OSPropertiesFactory.getInstance ();
		

		if (StringUtils.isNotEmpty (path)) {

			if (! osProps.isOSWindows ()) {

				// Check unix file name...

				if (StringUtils.isEqual (path.charAt (0), osProps.getFileSeparator ().charAt (0))) {

					return true;
				}
			}
			else {

				// Check dos file name...

				if (StringUtils.isEqual (path.charAt (0), osProps.getFileSeparator ().charAt (0))) {

					return true;
				}
				else if (path.length () > 2 && ":".equals (path.substring (1, 2))) {

					return true;
				}

			}

		}

		return false;
	}


	/**
	 * Checks to see if a file exists.
	 * 
	 * Does not check to see what kind of file (file, link, directory).
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFileExists (String fileName) {

		return (new File (fileName)).exists ();
	}
}
