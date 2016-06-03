package ua.core.utils.os;

import java.util.List;

public interface OSProperties {

	/**
	 * Properly formats the path for the current operating system.
	 * It sets the appropriate / or \.
	 * @param path
	 * @return
	 */
	public String formatPath (String path);
	public String getFileSeparator();
	
	/**
	 * Return a list of all possible file separators. (\, /)
	 * 
	 * @return
	 */
	public List <String> getFileSeparators();
	public String getNewLine();
	public String getOSName();
	public String getPathSeparator();
	
	/**
	 * Return a list of all possible path separators.
	 * 
	 * @return
	 */
	public List <String> getPathSeparators();
	public boolean isOSOsx ();
	public boolean isOSWindows ();
}
