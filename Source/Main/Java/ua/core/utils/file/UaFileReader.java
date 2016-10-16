package ua.core.utils.file;

import java.io.*;
import java.nio.CharBuffer;

import ua.core.base.ExceptionItemNotFound;
import ua.core.base.MessageConst;
import ua.core.utils.MessageUtils;


public class UaFileReader {
	
	public static String	CLASS_NAME		= UaFileReader.class.getName();	
	
	private String			fileName		= null;
	private String			charEncoding	= null;
	private	int				bufferSize		= 0;
	
	private Reader			reader			= null;
	private	BufferedReader	bufferedReader	= null;

	
	public UaFileReader (String fileName) {
		
		this.fileName	= fileName;
	}
	
	
	public UaFileReader (String fileName, int bufferSize) {
		
		this.fileName	= fileName;
		this.bufferSize	= bufferSize;
	}
	
	
	public UaFileReader (String fileName, String charEncoding) {
		
		this.fileName		= fileName;
		this.charEncoding	= charEncoding;
	}

	
	public UaFileReader (String fileName, String charEncoding, int bufferSize) {
		
		this.fileName		= fileName;
		this.charEncoding	= charEncoding;
		this.bufferSize		= bufferSize;
	}
	
	
	public void close() {
		
		try {
			
			if (this.bufferedReader != null) {
			
				this.bufferedReader.close();
			}
			else if (this.reader != null) {
				
				this.reader.close();
			}
		}
		catch (IOException e) {
			
			// Ignore.
		}
		
		this.bufferedReader	= null;
		this.reader			= null;
	}
	
	
	public void open() throws ua.core.base.ExceptionItemNotFound {
		
		try {
			
			if (this.charEncoding == null) {
				
				this.reader = new FileReader (this.fileName);
			}
			else {
				
				this.reader = new InputStreamReader (new FileInputStream (this.fileName), this.charEncoding);
			}
			
			if (this.bufferSize > 0) {
				
				this.bufferedReader = new BufferedReader (this.reader, this.bufferSize);
			}
			else {
				
				this.bufferedReader = new BufferedReader (this.reader);
			}
		}
		catch (FileNotFoundException e) {

			this.close();
			
			throw new ExceptionItemNotFound (MessageUtils.toString (MessageConst.MESSAGE_FILE_NOT_FOUND, this.fileName));
		}
		catch (UnsupportedEncodingException e) {

			this.close();
			
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Reads a single character.
	 * 
	 * @return
	 * @throws IOException
	 */
	public int read() throws IOException {

		if (this.bufferedReader != null) {
			
			return this.bufferedReader.read();
		}
		else {
			
			throw new IOException();
		}
	}


	/**
	 * Reads characters into an array.
	 * 
	 * @return
	 * @throws IOException
	 */
	public int read (char[] charArray) {

		try {
			
			if (this.bufferedReader != null) {
				
				return this.bufferedReader.read (charArray);
			}
			else {
				
				return 0;
			}
		}
		catch (IOException e) {

			throw new RuntimeException (e);
		}
	}


	/**
	 * Attempts to read characters into the specified character buffer.
	 * 
	 */
	public int read (CharBuffer charBuffer) {
		
		try {
			
			if (this.bufferedReader != null) {
				
				return this.bufferedReader.read (charBuffer);
			}
			else {
				
				return 0;
			}
		}
		catch (IOException e) {

			throw new RuntimeException (e);
		}
	}
	
	
	public String readLine() {
	
		try {
			
			if (this.bufferedReader != null) {
				
				return this.bufferedReader.readLine();
			}
			else {
				
				return null;
			}
		}
		catch (IOException e) {

			throw new RuntimeException (e);
		}
	}
}
