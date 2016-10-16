package ua.core.utils.file;


public class FileDirectoryDefault implements FileDirectory {

	String directoryName;
	
	
	public FileDirectoryDefault (String directoryName) {
		
		this.directoryName = directoryName;
	}
	
	@Override
	public String getDirectory() {

		return directoryName;
	}

}
