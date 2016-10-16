package ua.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class MoneyUtils {

	private static final int MONEY_DECIMAL_PLACES = 2;
	public static final String MONEY_FORMAT = "0.00";
	public static final BigDecimal MONEY_ZERO = new BigDecimal (MONEY_FORMAT);
	
	public static BigDecimal asBigDecimal (Double value) {
		
		DecimalFormat	formatter;
		BigDecimal		newValue;
		
		formatter = new DecimalFormat (MONEY_FORMAT);
		newValue = new BigDecimal (formatter.format (value));
		
		return newValue;
	}
	
	
	/**
	 * Tests if a value is 0. Is true for all scales ("0", "0.00", etc.)
	 * Null safe.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isZero (BigDecimal value) {
		
		if (value != null) {
			return value.compareTo (BigDecimal.ZERO) == 0;
		}
		else {
			
			return true;
		}
	}
	
	
	/**
	 * Tests if a value is not 0. Is true for all scales ("0", "0.00", etc.)
	 * Null safe.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotZero (BigDecimal value) {
		
		return ! isZero (value);
	}
	
	
	/**
	 * Returns the default 0 value for BigDecimal / Money
	 * 
	 */
	public static BigDecimal newZeroAmount() {
		
		return MONEY_ZERO;
	}
	
	/**
	 * Returns the big decimal value or zero value if it is null.
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal nonNull (BigDecimal value) {
		
		if (value != null) {
			return value;
		}
		else {
			return MONEY_ZERO;
		}
	}
	
	/**
	 * Formats decimals to format '0.00'.
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal normalize (BigDecimal value) {
		
		if (value != null) {
			return value.setScale (MONEY_DECIMAL_PLACES, BigDecimal.ROUND_HALF_EVEN);
		}
		else {
			return MONEY_ZERO;
		}
	}
	
	public static String toString (BigDecimal value) {
		
		if (value != null) {
			return value.toString();
		}
		else {
			return "";
		}
	}
}
