package ua.core.utils.system;

import java.io.File;


public class JavaUtils {
	
	
	public static String getLineSeparator () {
		
		return System.getProperty (JavaConst.JAVA_PROPERTIES_NEWLINE);
	}
	
	public static String getFileSeparator () {
		
		return File.separator;
	}
	
	public static String getNewline () {
		
		return getLineSeparator ();
	}
	
	public static String getOSName () {
		
		return System.getProperty (JavaConst.JAVA_PROPERTIES_OS_NAME);
	}
	
	public static String getPathSeparator () {
		
		return File.pathSeparator;
	}
}
