package ua.core.utils.os;

import java.util.Arrays;
import java.util.List;


public interface OSConst {

	public static final String OSX_OS_NAME				= "Mac OS X";
	public static final String OSX_FILE_SEPARATOR		= "/";
	public static final String OSX_NEW_LINE				= "\n";
	public static final String OSX_PATH_SEPARATOR		= ":";	
	
	public static final String UNIX_OS_NAME				= "????";
	public static final String UNIX_FILE_SEPARATOR		= "/";
	public static final String UNIX_NEW_LINE			= "\n";
	public static final String UNIX_PATH_SEPARATOR		= ":";
	
	public static final String WINDOWS_OS_NAME			= "Windows";
	public static final String WINDOWS_FILE_SEPARATOR	= "\\";
	public static final String WINDOWS_NEW_LINE			= "\r\n";
	public static final String WINDOWS_PATH_SEPARATOR	= ";";
	
	public static final List <String> FILE_SEPARATORS = Arrays.asList (new String [] {OSX_FILE_SEPARATOR, WINDOWS_FILE_SEPARATOR});
	public static final List <String> PATH_SEPARATORS = Arrays.asList (new String [] {OSX_PATH_SEPARATOR, WINDOWS_PATH_SEPARATOR});
	
}
