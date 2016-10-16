package ua.core.utils.file;

/**
 * A directory. This encapsulates all kinds of directories and is to be used
 * with the File Directory Locator Command.
 * 
 * Directories can be environment specific (user directories, os directories).
 * 
 * @author Tadhg
 *
 */
public interface FileDirectory {

	public String getDirectory();
}
