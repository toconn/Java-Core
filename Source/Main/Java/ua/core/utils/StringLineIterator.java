package ua.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;


/**
 * For use with multiline strings. Use in conjunction with StringLineIterable to loop through a multiline string in a for loop.
 * 
 * @author Tadhg
 *
 */
public class StringLineIterator implements Iterator <String> {

	BufferedReader	bufferedReader;
	String			nextLine		= null;
	boolean			nextLineRead	= false;
	
	
	public StringLineIterator (String text) {
		
		bufferedReader = new BufferedReader (new StringReader (text));
	}

	@Override
	public boolean hasNext() {
		
		// Has next line been read?
		
		if (! this.nextLineRead) {
			
			this.nextLineRead = true;
			this.nextLine = nextLine();
		}
		
		
		// Has next line?
		
		if (this.nextLine != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String next() {

		// Has next line been read?
		
		if (! this.nextLineRead) {
		
			this.nextLine = nextLine();
		}
		
		// Clean next line flag.
		this.nextLineRead = false;
		
		return this.nextLine;
	}

	@Override
	public void remove() {

		// Ignore. No removing.
	}
	
	
	private String nextLine() {
		
		// Declarations:
	
		String nextLine;
		
		
		// Code:
		
		try {
			nextLine = bufferedReader.readLine();
		}
		catch (IOException e) {
			nextLine = null;
		}
		
		return nextLine;
	}
}
