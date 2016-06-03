package ua.core.utils;

import java.util.Iterator;


/**
 * Returns an iterator that iterates over a multiline string.
 * Use to loop through a multiline string in a for statement.
 * 
 * @author Tadhg
 *
 */
public class StringLineIterable implements Iterable <String> {
	
	private String string;
	
	public StringLineIterable (String string) {
		this.string = string;
	}

	@Override
	public Iterator <String> iterator () {

		return new StringLineIterator (string);
	}

}
