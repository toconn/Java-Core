package ua.core.utils;

import java.util.ArrayList;
import java.util.List;

import ua.core.beans.NameValuePair;

/**
 * Parses a string into a list of strings.
 * The parser will parse on a list of separators.
 * Escape characters can be included. They will be converted from the escape format into the target format.
 * ... NameValuePair (Name = escape string, Value = actual string).
 * 
 * @author Tadhg
 *
 */
public class StringParser {

	/*
	 
	// Example escape characters...
	
		public static	char		FILE_ESCAPE_CHAR	= '/';		
	
	*/
	
	private	boolean					isSkipEmptySegments	= true;	// Skip segments that are 0 length.
	private char					escapeCharacter		= 0;
	private List <NameValuePair>	escapeNameValueList	= null;
	private List <String>			separatorStringList	= null;
	
	public StringParser (List <String> separatorStringList) {
		
		this.separatorStringList = separatorStringList;
	}

	public StringParser (List <String> separatorStringList, boolean isSkipEmptySegments) {
		
		this.separatorStringList	= separatorStringList;
		this.isSkipEmptySegments	= isSkipEmptySegments;
	}

	public StringParser (List <String> separatorStringList, char escapeCharacter, List <NameValuePair> escapeNVStringList) {
		
		this.separatorStringList	= separatorStringList;
		this.escapeCharacter		= escapeCharacter;
		this.escapeNameValueList	= escapeNVStringList;
	}

	public StringParser (List <String> separatorStringList, char escapeCharacter, List <NameValuePair> escapeNVStringList, boolean isSkipEmptySegments) {
		
		this.separatorStringList	= separatorStringList;
		this.escapeCharacter		= escapeCharacter;
		this.escapeNameValueList	= escapeNVStringList;
		this.isSkipEmptySegments	= isSkipEmptySegments;
	}
	
	/**
	 * Parse the text using the parse parameters used to instantiate the parse class.
	 * The results are returned as a StringList.
	 * 
	 * Note: A string list will always be returned.
	 * 
	 * @param text
	 * @return
	 */
	public List <String> parse (String text) {
		
		/////////////////////////////////////////////////////////
		// Declarations:
		/////////////////////////////////////////////////////////

		List <String>	resultStringList		= null;
		StringBuilder	segmentStringBuilder	= null;
		int				segmentIndexCurrent		= 0;
		boolean			isEscapeSequence		= false;
		boolean			isSeparator				= false;


		/////////////////////////////////////////////////////////
		// Code:
		/////////////////////////////////////////////////////////

		resultStringList	= new ArrayList <String> ();
		
		if (StringUtils.isNotEmpty (text)) {
			
			segmentStringBuilder = new StringBuilder ();
			
			// Loop through all characters...
			
			while (segmentIndexCurrent < text.length()) {
				
				if (this.escapeCharacter > 0 && this.escapeCharacter == text.charAt (segmentIndexCurrent) && segmentIndexCurrent < text.length () -1) {
					
					// Escape character found...
					
					// Check is escape sequence...

					isEscapeSequence = false;
					
					for (NameValuePair escapeNVPair: this.escapeNameValueList) {
						
						if (StringUtils.isStartsWith (text.substring (segmentIndexCurrent + 1), escapeNVPair.name)) {
							
							// Match found...
							
							// Add unescaped string...
							segmentStringBuilder.append (escapeNVPair.value);
							
							// Skip to the next character...
							segmentIndexCurrent = segmentIndexCurrent + escapeNVPair.name.length() + 1;
							
							isEscapeSequence = true;
							
							break;
						}
					}
					
					if (! isEscapeSequence) {
					
						// Not an escape sequence.
						
						// Add as normal character...
						segmentStringBuilder.append (text.charAt (segmentIndexCurrent));
						
						// Increment counter...
						segmentIndexCurrent ++;
						
					}
					
				}
				else {
					
					// Check if separator string...
					
					isSeparator = false;
					
					for (String separator: separatorStringList) {
						
						if (StringUtils.isStartsWith (text.substring (segmentIndexCurrent), separator)) {
							
							isSeparator = true;
							break;
						}
					}
					
					if (isSeparator) {
						
						// Segment Separator...
						
						// Save current segment...
						
						if (segmentStringBuilder.length() > 0) {
							
							resultStringList.add (segmentStringBuilder.toString());
							segmentStringBuilder = new StringBuilder ();
						}
						else if (! this.isSkipEmptySegments) {
							
							resultStringList.add ((String) null);
						}
						
						// Increment counter...
						segmentIndexCurrent ++;
						
					}
					else {
						
						// Normal character.
						
						// Add...
						segmentStringBuilder.append (text.charAt (segmentIndexCurrent));
						
						// Increment counter...
						segmentIndexCurrent ++;
					}
				}
			}
			
			if (segmentStringBuilder.length() > 0) {
				
				resultStringList.add (segmentStringBuilder.toString());
			}
		}
		

		return resultStringList;
	}

}
