package ua.core.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class NumberUtils {
	
	private static final Pattern INTEGER_PATTERN = Pattern.compile ("[0-9]*");

	
	/**
	 * Converts a string to an int. This will return a 0 if the string is not a number.
	 * Null safe.
	 * 
	 * @param value
	 * @return
	 */
	public static int asInt (String value) {
		
		int i = 0;
		
		try {
			if (StringUtils.isNotBlank (value)) {
				i = Integer.parseInt (value);
			}
		}
		catch (Exception e) {
			// Ignore. Return 0;
		}
		
		return i;
	}
	
	/**
	 * Converts a string to an int. This will return a 0 if the string is not a number.
	 * Null safe.
	 * 
	 * @param value
	 * @return
	 */
	public static int asInt (String value, int defaultValue) {
		
		int i = defaultValue;
		
		try {
			if (StringUtils.isNotBlank (value)) {
				i = Integer.parseInt (value);
			}
		}
		catch (Exception e) {
			// Ignore. Return 0;
		}
		
		return i;
	}
	
	
	/**
	 * Converts a string to a long. This will return a 0 if the string is not a number.
	 * Null safe.
	 * 
	 * @param value
	 * @return
	 */
	public static long asLong (String value) {
		
		long i = 0;
		
		try {
			if (StringUtils.isNotBlank (value)) {
				i = Long.parseLong (value);
			}
		}
		catch (Exception e) {
			// Ignore. Return 0;
		}
		
		return i;
	}

	/**
	 * Converts a string to a long. This will return a 0 if the string is not a number.
	 * Null safe.
	 * 
	 * @param value
	 * @return
	 */
	public static long asLong (String value, long defaultValue) {
		
		long i = defaultValue;
		
		try {
			if (StringUtils.isNotBlank (value)) {
				i = Long.parseLong (value);
			}
		}
		catch (Exception e) {
			// Ignore.
		}
		
		return i;
	}
	
	public static List<Long> asLongs (String[] strings) {
		
		List <Long> longs = new ArrayList <Long>();
		
		for (String string : strings) {
			longs.add (asLong (string));
		}
		
		return longs;
	}
	
	public static boolean isNotNullOrZero (BigDecimal number) {
		
		return ! isNullOrZero (number);
	}

	/**
	 * Null safe check to see if a string is an integer (all digits). Non integers returns false.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNotPositiveInt (String string) {
		
		return ! isPositiveInt (string);
	}

	public static boolean isNotZero (BigDecimal number) {
		
		return ! isZero (number);
	}

	/**
	 * Null safe check to see if a string is an integer (all digits). Negative numbers will return false.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isPositiveInt (String string) {
		
		if (StringUtils.isNotEmpty (string) && INTEGER_PATTERN.matcher (string).matches()) {
		    return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isNullOrZero (BigDecimal number) {
		
		if (number == null) {
			return true;
		}
		else {
			return number.compareTo (BigDecimal.ZERO) == 0;
		}
	}
	
	public static boolean isZero (BigDecimal number) {
		
		if (number == null) {
			return false;
		}
		else {
			return number.compareTo (BigDecimal.ZERO) == 0;
		}
	}
	
	
	/**
	 * Strips all non digits from the number and returns remainder.
	 * 
	 * Does not check the validity of the number, just returns the digits.
	 * 
	 * @param number
	 * @return
	 */
	public static String stripNonDigits (String number) {
		
		if (number != null) {
		
			return number.replaceAll( "[^\\d]", "");
		}
		else {
			
			return null;
		}
	}
	
	
	public static String toString (Double value) {
		
		if (value != null) {
			
			return value.toString();
		}
		else {
			
			return "0.0";
		}
	}
	
	
	public static String toString (Long value) {
		
		if (value != null) {
			
			return value.toString();
		}
		else {
			
			return "0";
		}
	}
	
	/**
	 * Returns the string form of a number. If the number is zero, returns "".
	 * 
	 * @param value
	 * @return
	 */
	public static String toStringNoZero (int value) {
		
		if (value != 0) {
			return Long.toString (value);
		}
		else {
			return "";
		}
	}
	
	/**
	 * Returns the string form of a number (long the primitive type). If the number is zero, returns "".
	 * 
	 * @param value
	 * @return
	 */
	public static String toStringNoZero (long value) {
		
		if (value != 0) {
			
			return Long.toString (value);
		}
		else {
		
			return "";
		}
	}

	/**
	 * Returns the string form of a Long (the object). If the number is zero, returns "".
	 * 
	 * @param value
	 * @return
	 */
	public static String toStringNoZero (Long value) {
		
		if (value != null && value.longValue() != 0) {
			
			return value.toString();
		}
		else {
		
			return "";
		}
	}
}
