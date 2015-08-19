package ua.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created 2013.04, Kent WA
 * 
 * @author Tadhg
 *
 */
public class StringUtils {
	
	/**
	 * Concatenates a collection of strings into one string with items separated by the separator.
	 * 
	 * @param collection
	 * @param separator
	 * @return
	 */
	public static String concatenate (Collection <String> collection, String separator) {
		
		StringBuffer	stringBuffer	= new StringBuffer();
		boolean			first			= true;
		
		if (collection != null) {
			for (String item : collection) {
				
				if (! first) {
					stringBuffer.append (separator);
				}
				else {
					first = false;
				}
				
				stringBuffer.append (item);
			}
		}
		
		return stringBuffer.toString();
	}


	/**
	 * Checks to see if a string contains another string.
	 * 
	 * This method is null safe. If both are null, returns true.
	 * 
	 * @param mainString
	 * @param matchString
	 * @return
	 */
	public static boolean contains (String mainString, String matchString) {
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		if (mainString != null && matchString != null) {
			
			return mainString.contains (matchString);
		}
		else if (mainString == null && matchString == null) {
			
			return true;
		}
		else {
		
			return false;
		}
	}

	/**
	 * Checks to see if a string contains any of the strings in the match string array.
	 * 
	 * This method is null safe. If both are null, returns true.
	 * 
	 * @param mainString
	 * @param matchString
	 * @return
	 */
	public static boolean contains (String mainString, String[] matchStringArray) {
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		if (mainString != null && matchStringArray != null) {
			
			for (String matchString : matchStringArray) {
				if (mainString.contains (matchString)) {
					return true;
				}
			}
			
			return false;
		}
		else {
			return false;
		}
	}

	/**
	 * Searches an array of strings for another string.
	 * 
	 * This method is null safe.
	 * 
	 * @param stringArray
	 * @param matchString
	 * @return
	 */
	public static boolean contains (String[] stringArray, String matchString) {
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		if (stringArray != null && matchString != null) {
			
			for (String string: stringArray) {
				
				if (contains (matchString,string)) {
				
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Checks to see if a string contains another string.
	 * 
	 * This method is null safe. If both are null, returns true.
	 * 
	 * @param mainString
	 * @param matchString
	 * @return
	 */
	public static boolean containsIgnoreCase (String mainString, String matchString) {
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		if (mainString != null && matchString != null) {
			
			return mainString.toLowerCase ().contains (matchString.toLowerCase ());
		}
		else if (mainString == null && matchString == null) {
			
			return true;
		}
		else {
		
			return false;
		}
	}

	/**
	 * Checks to see if a string contains any of the strings in the match string array.
	 * 
	 * This method is null safe. If both are null, returns true.
	 * 
	 * @param mainString
	 * @param matchString
	 * @return
	 */
	public static boolean containsIgnoreCase (String mainString, String[] matchStringArray) {
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		if (mainString != null && matchStringArray != null) {
			
			for (String matchString : matchStringArray) {
				if (containsIgnoreCase (mainString, matchString)) {
					return true;
				}
			}
			
			return false;
		}
		else {
			return false;
		}
	}

	/**
	 * Searches an array of strings for another string.
	 * 
	 * This method is null safe.
	 * 
	 * @param stringArray
	 * @param matchString
	 * @return
	 */
	public static boolean containsIgnoreCase (String[] stringArray, String matchString) {
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		if (stringArray != null && matchString != null) {
			
			for (String string: stringArray) {
				
				if (containsIgnoreCase (matchString, string)) {
				
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Expands a string by a given number of characters.
	 * 
	 * Creation date: (2002/04/11 4:54:09 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.lang.String
	 * @param expandChar char
	 * @param expandCount int
	 */
	public static String expand (char expandChar, int expandCount) {
	
		///////////////////////////////////////////
		// Declarations -
		///////////////////////////////////////////
	
		char[]	charArray = null;
		int		i;
	
	
		///////////////////////////////////////////
		// Code -
		///////////////////////////////////////////
	
		if (expandChar != 0 && expandCount > 0) {
	
			charArray = new char[expandCount];
	
	
			for (i = 0; i < expandCount; i++) {
	
				charArray[i] =  expandChar;
			}
	
			return new String (charArray);
	
		}
		else {
			
			return "";
		}
	}
	
	
	/**
	 * Expands a string by a given number of times.
	 * 
	 * Creation date: (2002/04/11 4:54:09 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.lang.String
	 * @param expandString String
	 * @param expandCount int
	 */
	public static String expand (String expandString, int expandCount) {
	
		///////////////////////////////////////////
		// Declarations -
		///////////////////////////////////////////
	
		StringBuffer	expandedStringBuffer = null;
		int				i;
	
	
		///////////////////////////////////////////
		// Code -
		///////////////////////////////////////////
	
		if (expandString != null && expandString.length() > 0 && expandCount > 0) {
	
			// Create buffer to final string size.
	
			expandedStringBuffer = new StringBuffer (expandString.length() * expandCount);
	
	
			// Build string.
			
			for (i = 0; i < expandCount; i++) {
	
				expandedStringBuffer.append (expandString);
			}
	
			return expandedStringBuffer.toString();
	
		}
		else {
			
			return "";
		}
	}
	
	
	/**
	 * Indents a single string.
	 * 
	 * It does not indent multiline strings.
	 * 
	 * @param text
	 * @param indent
	 * @return
	 */
	public static String indent (String text, String indent) {
		
		if (text != null) {
			
			return indent + text;
		}
		else {
			
			return null;
		}
	}
	
	
	/**
	 * Null safe multiline indent function.
	 * Indents all lines.
	 * 
	 * @param text
	 * @param indent
	 * @return
	 */
	public static String indentMultiline (String text, String indent) {
		
		if (text != null) {
			
			return indent + text.replace ("\n", "\n" + indent);
		}
		else {
			
			return null;
		}
	}
	
	
	/**
	 * This is a null safe way to check if a string is nothing but white spaces or null.
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isBlank (String text) {
		
		if (text == null) {
			
			return true;	
		}
		else if (text.trim ().length() == 0) {
			
			return true;
		}
		else {
		
			return false;
		}
	}
	

	/**
	 * This is a null safe way to check if a string is empty ("") or null.
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isEmpty (String text) {
		
		if (text == null) {
			
			return true;
		}
		else if (text.length() == 0) {
			
			return true;
		}
		else {
		
			return false;
		}
	}
 

	/**
	 * Null safe check to see if a string ends in a given character.
	 * 
	 * @param text
	 * @param endingChar
	 * @return
	 */
	public static boolean isEndsWith (String text, char endingChar) {
		
		if (isNotEmpty (text) && text.length() >= 1) {
				
			return text.charAt (text.length () - 1) ==  endingChar;
		}
		
		return false;
	}

	
	/**
	 * Null safe check to see if a string ends with a given string.
	 */
	public static boolean isEndsWith (String text, String endString) {
		
		if (isNotEmpty (text) && isNotEmpty (endString) && text.length() >= endString.length ()) {
				
			return text.endsWith (endString);
		}
		
		return false;
	}

	
	/**
	 * Null safe check to see if a string ends in a given character.
	 * Case is ignored.
	 * 
	 * @param text
	 * @param endingChar
	 * @return
	 */
	public static boolean isEndsWithIgnoreCase (String text, char endingChar) {
		
		if (isNotEmpty (text) && text.length() >= 1) {
				
			return Character.toLowerCase (text.charAt (text.length () - 1)) ==  Character.toLowerCase (endingChar);
		}
		
		return false;
	}

	
	/**
	 * Null safe check to see if a string ends with a given string.
	 * Case is ignored.
	 */
	public static boolean isEndsWithIgnoreCase (String text, String endString) {
		
		if (isNotEmpty (text) && isNotEmpty (endString) && text.length() >= endString.length ()) {
				
			return text.toLowerCase ().endsWith (endString.toLowerCase ());
		}
		
		return false;
	}

	
	/** 
	 * Compares two character variables to see if they are equal.
	 * Included for consistency only.
	 * 
	 * @param char1
	 * @param char2
	 * @return
	 */
	public static boolean isEqual (char char1, char char2) {
		
		return (char1  == char2);		
	}
	
	
	/**
	 * Compares two strings to see if they are equal.
	 * Null safe. If both are null, returns true. Otherwise if either are null, returns false.
	 * 
	 * @param string1
	 * @param string2
	 * @return
	 */
	public static boolean isEqual (String string1, String string2) {
		
		if (isEmpty (string1)) {
			
			return isEmpty (string2);		
		}
		else {
			
			return string1.equals (string2);
		}
	}
	
	
	/**
	 * Compares two strings to see if they are equal. Case is ignored.
	 * Null safe. If both are null, returns true. Otherwise if either are null, returns false.
	 * 
	 * @param char1
	 * @param char2
	 * @return
	 */
	public static boolean isEqualIgnoreCase (char char1, char char2) {
		
		return (Character.toLowerCase(char1)  == Character.toLowerCase(char2));		
	}
	
	
	/**
	 * Null safe comparison of two strings. Case is ignored.
	 * 
	 * @param string1
	 * @param string2
	 * @return
	 */
	public static boolean isEqualIgnoreCase (String string1, String string2) {
		
		if (string1 == null) {
			
			return string2 == null;		
		}
		else {
			
			return string1.equalsIgnoreCase (string2);
		}
	}
	
	
	/**
	 * Checks to see if a string is not blank (not white space only, not null).
	 * 
	 * Null safe.
	 * 
	 * @param Text
	 * @return
	 */
	public static boolean isNotBlank (String Text) {
		
		return ! isEmpty (Text);
	}


	/**
	 * Checks to see if a string is non empty (not "", not null).
	 * 
	 * Null safe.
	 * 
	 * @param Text
	 * @return
	 */
	public static boolean isNotEmpty (String Text) {
		
		return ! isEmpty (Text);
	}


	/**
	 * Null safe check to see if a line starts with a character.
	 * 
	 * @param text
	 * @param startingChar
	 * @return
	 */
	public static boolean isStartsWith (String text, char startingChar) {
		
		if (isNotEmpty (text)) {
			return text.charAt (0) ==  startingChar;
		}
		
		return false;
	}
	
	
	/**
	 * Null safe check to see if a string starts with a given string.
	 * 
	 * @param text
	 * @param startingText
	 * @return
	 */
	public static boolean isStartsWith (String text, String startingText) {
		
		if (length (text) >= startingText.length()) {
				
			return isEqual (text.substring (0, startingText.length()), startingText);
		}
		
		return false;
	}


	/**
	 * Null safe check to see if a line starts with a character.
	 * Case is ignored.
	 * 
	 * @param text
	 * @param startingChar
	 * @return
	 */
	public static boolean isStartsWithIgnoreCase (String text, char startingChar) {
		
		if (isNotEmpty (text)) {
			return Character.toLowerCase (text.charAt (0)) ==  Character.toLowerCase (startingChar);
		}
		return false;
	}
	
	
	/**
	 * Null safe check to see if a string starts with a given string.
	 * Case is ignored.
	 * 
	 * @param text
	 * @param startingText
	 * @return
	 */
	public static boolean isStartsWithIgnoreCase (String text, String startingText) {
		
		if (isNotEmpty (text) && text.length() >= startingText.length()) {
				
			return isEqual (text.substring (0, startingText.length()).toLowerCase (), startingText.toLowerCase ());
		}
		
		return false;
	}
	
	
	/**
	 * Merges the contents of a list together into one string.
	 * Each element in the string is separated by the separator string.
	 * 
	 * @param stringList
	 * @param separator
	 * @return
	 */
	public static String join (List <String> stringList, String separator) {
		
		// Declarations:
		
		StringBuffer	stringBuffer;
		boolean			isFirst;
		
		// Main:
		
		stringBuffer	= new StringBuffer ();
		isFirst			= true;
		
		for (String string : stringList) {
			
			if (isFirst) {
				isFirst = false;
			}
			else {
				stringBuffer.append (separator);
			}
			
			stringBuffer.append (string);
		}
		
		return stringBuffer.toString ();
	}
	
	
	/**
	 * Returns the length of a string.
	 * 
	 * Null safe.
	 * 
	 * @param string
	 * @return
	 */
	public static int length (String string) {
		
		if (string != null) {
			return string.length ();
		}
		else {
			return 0;
		}
	}
	
	
	/**
	 * returns the length of the longest string.
	 * 
	 * @return
	 */
	public int longestLength (Collection <String> strings) {
		
		int longestLength = 0;
		
		for (String string: strings) {
			
			if (string != null && string.length() > longestLength) {
				
				longestLength = string.length();
			}
		}
		
		return longestLength;
	}


	/**
	 * Does a search to ensure that one string is not contained within the other.
	 * 
	 * This method is null safe. It will return false if both are null.
	 * 
	 * @param mainString
	 * @param matchString
	 * @return
	 */
	public static boolean notContains (String mainString, String matchString) {
		
		return ! contains (mainString, matchString);
	}

	
	/**
	 * Returns the string or "" if it is null.
	 * 
	 * @param string
	 * @return
	 */
	public static String notNull (String string) {
		
		if (string != null) {
			return string;
		}
		else {
			return "";
		}
	}
	
	/**
	 * 
	 * 
	 * Creation date: (2002/10/31 10:37:10 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.lang.String
	 * @param text java.lang.String
	 * @param fillChar char
	 * @param totalLength int
	 */
	public static String pad (String text, char fillChar, int totalLength) {
		
		if (text != null) {
			
			return text + expand (fillChar, totalLength - text.length());
		}
		else {
			
			return expand (fillChar, totalLength);
		}
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/10/31 10:36:30 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.lang.String
	 * @param text java.lang.String
	 * @param fillText java.lang.String
	 * @param totalLength int
	 */
	public static String pad (String text, String fillText, int totalLength) {
	
		if (text != null) {
		
			return text + expand (fillText, totalLength - text.length());
		}
		else {
			
			return expand (fillText, totalLength);
		}
	}
	
	
	/**
	 * Splits a string into separate words using the upper case letter to denote a new word.
	 * 
	 * thisLineWouldBeSplitAsFollows.	= this Line Would Be Split As Follows.
	 * 
	 * @param string
	 * @return
	 */
	public static List <String> splitByCaps (String string) {

		List <String>	splitList;

		if (isNotEmpty (string)) {
			splitList = Arrays.asList (string.split ("(?=\\p{Lu})"));		// Works for everything but inserts empty first element if first letter is capitalized.
			
			// splitList = Arrays.asList (string.split ("(?<=[A-Z][a-z].)(?=[A-Z].)"));	// My own concoction.
			// splitList = Arrays.asList (string.split ("(?<=[a-z])([A-Z])"));	// Splits between lower and upper case letters only. Drops the upper case letter :(.
			
			// Fix for not understanding regex very well...
			// Split inserts a black at first record if string has upper case first letter.
			
			if (isEmpty (splitList.get (0))) {
				splitList = new ArrayList <String> (splitList);	// have to change to array list. The generated list does not support remove().
				splitList.remove (0);
			}
		}
		else {
			splitList = new ArrayList <> ();
		}
		
		return splitList;
	}
	
	public static String stripNonAphaNumeric (String string) {
		
		if (isNotEmpty (string)) {
			return string.replaceAll ("[^a-zA-Z0-9\\s]", "");
		}
		else {
			return "";
		}
	}

	/**
	 * Makes the first letter lower case.
	 * 
	 * Null safe.
	 * 
	 * @param string
	 * @return
	 */
	public static String to1stLowerCase (String string) {
		
		if (length (string) > 1) {
			
			return string.substring (0, 1).toLowerCase () + string.substring (1);
		}
		else if (length (string) > 1) {
			
			return string.toLowerCase ();
		}
		else {
			return string;
		}
	}
	
	
	/**
	 * Makes the first letter upper case.
	 * 
	 * Null safe.
	 * 
	 * @param string
	 * @return
	 */
	public static String to1stUpperCase (String string) {
		
		if (length (string) > 1) {
			
			return string.substring (0, 1).toUpperCase () + string.substring (1);
		}
		else if (length (string) == 1) {
			
			return string.toUpperCase ();
		}
		else {
			return string;
		}
	}
	
	
	/**
	 * Returns the left part of a string from the first occurrence of the search text.
	 * If the point is all the way to the left, returns "".
	 * If null, returns null.
	 * 
	 * @param string
	 * @param searchString
	 */
	public static String toLeftOf (String string, String searchString) {
		
		int index;
		
		if (string != null) {
			
			index = string.indexOf (searchString);
			
			if (index > 0) {
				
				return string.substring (0, index);
			}
			if (index == -1) {
				
				return string;
			}
			else {
			
				return "";
			}
		}
		else {
			return string;
		}
	}
	
	/**
	 * Returns the left part of a string from the last occurrence of the search text.
	 * If the point is all the way to the left, returns "".
	 * If null, returns null.
	 * 
	 * @param string
	 * @param searchString
	 */
	public static String toLeftOfLast (String string, String searchString) {
		
		int index;
		
		if (string != null) {
			
			index = string.lastIndexOf (searchString);
			
			if (index > 0) {
				
				return string.substring (0, index);
			}
			if (index == -1) {
				
				return string;
			}
			else {
			
				return "";
			}
		}
		else {
			return string;
		}
	}
	
	/**
	 * Convert string to lower case. Null safe.
	 * 
	 * @param string
	 * @return
	 */
	public static String toLowerCase (String string) {
	
		if (string != null) {
			return string.toLowerCase ();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Returns the right part of a string from a text part.
	 * If the point is all the way to the right, returns "".
	 * If null, returns null.
	 * If not found, returns "".
	 * 
	 * @param string
	 * @param searchString
	 */
	public static String toRightOf (String string, String searchString) {
		
		int index;
		
		if (string != null) {
			
			index = string.indexOf (searchString);
			
			if (index > -1 && (index + searchString.length () < string.length ())) {
				
				return string.substring (index + searchString.length ());
			}
			else {
			
				return "";
			}
		}
		else {
			return string;
		}
	}
	
	/**
	 * Convert string to upper case. Null safe.
	 * 
	 * @param string
	 * @return
	 */
	public static String toUpperCase (String string) {
	
		if (string != null) {
			return string.toUpperCase ();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Returns a string as a list of words with whitespace stripped out.
	 * 
	 * Null safe. Returns empty list.
	 */
	public static List <String> toWords (String text) {
		
		String[] wordArray;
		List <String> words;
		
		if (isNotEmpty (text)) {
	
			// text = text.replaceAll (COMMUNICATION_REGEX_WHITE_SPACE_CHARACTERS, " ");
			// wordArray = text.split (" ");
			wordArray = text.split ("\\P{L}+");
			words = Arrays.asList (wordArray);
		}
		else {
			
			words = new ArrayList <String> ();
		}
		
		return words;
	}
	
	/**
	 * Returns the string as a set of words.
	 */
	public static Set <String> toWordSet (String text) {

		return new HashSet <String> (toWords (text));
	}
	
	/**
	 * Returns the string as a set of words. The set is case insensitive.
	 */
	public static Set <String> toWordSetIgnoreCase (String text) {
		
		return new StringSetIgnoreCase (toWords (text));
	}

	/**
	 * Null save string trim. Returns null if null string passed in.
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.lang.String
	 * @param string java.lang.String
	 */
	public static String trim (String string) {
	
		if (string != null) {
			return string.trim();	
		}
		else {
			return null;
		}
	}
	
	/**
	 * Null safe string trim function. Returns empty string ("") if string is null.
	 * 
	 * Originally Creation date: (2002/04/18 2:25:36 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.lang.String
	 * @param string java.lang.String
	 */
	public static String trimNonNull (String string) {
	
		if (string != null) {
			return string.trim();
		}
		else {
			return "";
		}
	}
}
