package ua.core.utils.filereader;


public interface ILineReader {
	
	/**
	 * Processes the line of data.
	 * 
	 * @param data
	 * @param line
	 * @return
	 */
	public void readLine (String lineString);
	public void readClose();
}
