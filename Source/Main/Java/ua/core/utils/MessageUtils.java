package ua.core.utils;

import java.text.MessageFormat;

/**
 * Message Format "Message item 1 = {0}, item2 = {1}"
 * 
 * 
 * @author Tadhg
 *
 */


public class MessageUtils {
	
	public static String toString (String messageFormat, Object... paramArray) {
		
		// Declarations:
		
		MessageFormat	messageFormatter;
		String			string;
		
		// Main:
		
		messageFormatter	= new MessageFormat (messageFormat);
		string				= messageFormatter.format (paramArray);
		
		return string;		
	}
	
	public static String toString (String messageFormat, String param1, int param2) {

		return toString (messageFormat, new Object[] {param1, Integer.toString (param2)});
	}		
}