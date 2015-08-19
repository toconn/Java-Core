package ua.core.utils.filereader;

import ua.core.base.ExceptionItemNotFound;
import ua.core.utils.file.UaFileReader;



public class FileLineReader {

	private ILineReader	lineReader	= null;
	
	
	public FileLineReader (ILineReader dataLineReader) {

		this.lineReader	= dataLineReader;
	}
	
	
	public ILineReader read (String fileName) throws ExceptionItemNotFound {		

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		UaFileReader	fileReader			= null;
		String			lineString			= null;
		
		boolean			hasData				= false;
		

		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		// Open File...
		
		try {
			
			fileReader = new UaFileReader (fileName);
			fileReader.open();
			
			lineString = fileReader.readLine();
			
			while (lineString != null) {
				
				this.lineReader.readLine (lineString);
				
				// read next line...
				lineString = fileReader.readLine();
			}
		}
		finally {
			
			// Close file...
			if (fileReader != null) {
				fileReader.close();
			}
			
			this.lineReader.readClose();
		}
		
		if (hasData) {
			return lineReader;
		}
		else {
			return null;
		}
	}
}
