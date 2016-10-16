package ua.core.utils.os;

import java.util.List;

import ua.core.utils.StringUtils;


class DefaultOSProperties implements OSProperties, OSConst {
	
	private String osName;
	private String fileSeparator;
	private String newLine;
	private String pathSeparator;
	
	DefaultOSProperties (String osName, String fileSeparator, String newLine, String pathSeparator) {
		
		this.osName = osName;
		this.fileSeparator = fileSeparator;
		this.newLine = newLine;
		this.pathSeparator = pathSeparator;
	}
	
	@Override
	public String formatPath (String path) {
		
		String formattedPath;
		
		formattedPath = path;

		if (path != null) {
			if (isOSWindows()) {
				formattedPath.replace (UNIX_FILE_SEPARATOR, WINDOWS_FILE_SEPARATOR);
			}
			else {
				formattedPath.replace (UNIX_FILE_SEPARATOR, WINDOWS_FILE_SEPARATOR);
			}
		}
		
		return formattedPath;
	}

	@Override
	public String getFileSeparator() {

		return fileSeparator;
	}

	@Override
	public List <String> getFileSeparators() {
		
		return FILE_SEPARATORS;
	}

	@Override
	public String getNewLine() {

		return newLine;
	}

	@Override
	public String getOSName() {

		return osName;
	}

	@Override
	public String getPathSeparator() {

		return pathSeparator;
	}

	@Override
	public List <String> getPathSeparators() {

		return PATH_SEPARATORS;
	}

	@Override
	public boolean isOSOsx() {

		return StringUtils.isEqual (osName, OSX_OS_NAME);
	}

	@Override
	public boolean isOSWindows() {

		return StringUtils.isEqual (osName, WINDOWS_OS_NAME);
	}

}
