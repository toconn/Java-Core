package ua.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static ua.core.utils.DateConst.*;

/**
*
* @author Tadhg
*
* Updates:
*
* 2013.11.22 - added toYYYYMMDDHHMMSS
* 2013.11.22 - added toString internal function to handle nulls.
* 2013.12.17 - refactored method names (from YYYYMMDDHHMMSS, etc to Date, Time, Timestamp).
* 2013.12.19 - added toDateFromSeconds, toSeconds, newToday*, newYesterday*.
* 2014.01.23 - added addDays, diffInDays
* 2015.03.20 - changed Calendar.getInstance() -> new GregorianCalendar()
* 2015.03.20 - added toDateFromFormat
* 2015.03.20 - added toUa* toDateFromUa*
* 2015.03.25 - fixed newDate (millisecond time was not set to 0).
* 2015.03.25 - added diffInMs.
*/
public class DateUtils {
	
	public static Date addDays (Date date, int daysToAdd) {
		
		Calendar calendar;
		
		calendar = new GregorianCalendar();
		calendar.setTime (date);
		calendar.add (Calendar.DATE, daysToAdd);
		
		return calendar.getTime();
	}
	
	public static final long age (Date date1) {
		
		return Date2Utils.age (Date2Utils.toDateTime (date1));
	}
	
	public static long dayCount (Date date1, Date date2){
		
		Date date1Noon;
		Date date2Noon;
		
		date1Noon = toTimestampNoon (date1);
		date2Noon = toTimestampNoon (date2);
		
		return diffInDays (date1Noon, date2Noon) + 1;
	}
	
	/**
	 * Returns the difference in days between two dates.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long diffInDays (Date date1, Date date2) {
	    
	    return Math.abs (date1.getTime() - date2.getTime()) / 86400000;
	}
	
	/**
	 * Returns the day (Monday / Tuesday...) 
	 * 
	 * @param date
	 * @return
	 */
	public static String getDay (Date date) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		SimpleDateFormat	dateFormat		= null;
		String				isoDateString	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		/////////////////////////////////
	
		dateFormat		= new SimpleDateFormat ("E");
		isoDateString	= dateFormat.format (date);

		return	isoDateString;
	}
	
	public static long diffInMs  (Date date1, Date date2) {
	    
	    return Math.abs (date1.getTime() - date2.getTime());
	}
	
	/**
	 * Compares two dates and returns true if they are equal.
	 * 
	 * Null safe. If either is null but not both, returns false.
	 * If both are true, returns true.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqual (Date date1, Date date2) {

		if (date1 != null && date2 != null) {
			return date1.equals (date2);
		}
		else if (date1 == null && date2 == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Compares two dates and returns true if they are not equal.
	 * Null safe. If one is null but the other isn't, returns true.
	 * If both are null, returns false.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isNotEqual (Date date1, Date date2) {
		
		return ! isEqual (date1, date2);
	}
	
	/**
	 * Is date on or before the test date.
	 * 
	 * Null safe, returns false.
	 * 
	 */
	public static boolean isOnOrAfter (Date date, Date testDate) {
		
		if (date != null && (date.equals (testDate) || date.after (testDate))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Is date on or before the test date.
	 * 
	 * Null safe, returns false.
	 * 
	 */
	public static boolean isOnOrBefore (Date date, Date testDate) {
		
		if (date != null && (date.equals (testDate) || date.before (testDate))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Is date within a start and end date inclusive.
	 * 
	 * Null safe, returns false.
	 * 
	 */
	public static boolean isWithin (Date date, Date startDate, Date endDate) {
		
		if (date != null && (date.equals (startDate) || date.after (startDate)) && (date.equals (endDate) || date.before (endDate))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Returns the date for the given day with time 00:00:00.
	 * The month is automatically converted to java's crappy 0 based month.
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date newDate (int year, int month, int day) {
		
		Calendar	calendar;
		Date		date;

		calendar = new GregorianCalendar();
		calendar.set (year, month - 1, day, 0, 0, 0);
		calendar.set (Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		
		return date;
	}
	
	/**
	 * Returns the date for the given day and time.
	 * The month is automatically converted to java's crappy 0 based month.
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date newDate (int year, int month, int day, int hour, int minute, int second) {
		
		Calendar	calendar;
		Date		date;

		calendar = new GregorianCalendar();
		calendar.set (year, month - 1, day, hour, minute, second);
		calendar.set (Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		
		return date;
	}
	
	/**
	 * Returns today's date at 00:00:00 midnight.
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date newDateToday() {
		
		Calendar calendar;
		
		calendar = new GregorianCalendar();

		calendar.set (Calendar.HOUR, -12);
		calendar.set (Calendar.MINUTE, 0);
		calendar.set (Calendar.SECOND, 0);
		calendar.set (Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}

	
	/**
	 * Returns today's date at 00:00:00 midnight.
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date newDateTodayNoon() {
		
		Calendar calendar;
		
		calendar = new GregorianCalendar();

		calendar.set (Calendar.HOUR, 0);
		calendar.set (Calendar.MINUTE, 0);
		calendar.set (Calendar.SECOND, 0);
		calendar.set (Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}

	/**
	 * Returns today's date at 00:00:00 midnight.
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date newDateYesterday() {
		
		Calendar calendar;
		
		calendar = new GregorianCalendar();

		calendar.set (Calendar.HOUR, -12);
		calendar.set (Calendar.MINUTE, 0);
		calendar.set (Calendar.SECOND, 0);
		calendar.set (Calendar.MILLISECOND, 0);
		calendar.add (Calendar.DATE, -1);
		
		return calendar.getTime();
	}

	
	public static Date newDateYesterdayNoon() {
		
		Calendar calendar;
		
		calendar = new GregorianCalendar();

		calendar.set (Calendar.HOUR, 0);
		calendar.set (Calendar.MINUTE, 0);
		calendar.set (Calendar.SECOND, 0);
		calendar.set (Calendar.MILLISECOND, 0);
		calendar.add (Calendar.DATE, -1);
		
		return calendar.getTime();
	}
	
	/**
	 * Null safe conversion from sql Timestamp to standard Date.
	 * 
	 * null returns null.
	 * 
	 * @param timestamp
	 * @return
	 */
	public static Date toDate (java.sql.Timestamp timestamp) {
		
		if (timestamp != null) {
			return new Date (timestamp.getTime());
		}
		else {
			return null;
		}
	}

	public static Date toDateFromFormat (String dateString, String format) {
		
		DateFormat dateFormat;
		Date date;
		
		try {
			
			dateFormat = new SimpleDateFormat (dateString, Locale.ENGLISH);
			date = dateFormat.parse (dateString);
		}
		catch (Exception e) {

			date = null;
		}
		
		return date;
	}
	
	public static Date toDateFromUaDate (String dateString) {
		
		return toDateFromFormat (dateString, DATE_FORMAT_UA_DATE);
	}

	public static Date toDateFromUaTime (String dateString) {
		
		return toDateFromFormat (dateString, DATE_FORMAT_UA_TIME);
	}

	public static Date toDateFromUaTimestamp (String dateString) {
		
		return toDateFromFormat (dateString, DATE_FORMAT_UA_DATETIME);
	}

	public static Date toDateFromUaTimestampAmPm (String dateString) {
		
		return toDateFromFormat (dateString, DATE_FORMAT_UA_DATETIME_AMPM);
	}

	/**
	 * Returns the date in date format using the preferred format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateString (Date date) {

		return toString (date, DATE_FORMAT_UA_DATE);
	}
	
	/**
	 * Returns the date in date format using the ISO format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toIsoDateString (Date date) {

		return toString (date, DATE_FORMAT_ISO_DATE);
	}
	
	
	/**
	 * Returns the date in date time format using the ISO format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toIsoTimestampString (Date date) {

		return toString (date, DATE_FORMAT_ISO_DATETIME);
	}

	/**
	 * Returns the date in time format using the ISO format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toIsoTimeString (Date date) {

		return toString (date, DATE_FORMAT_ISO_TIME);
	}
	
	/**
	 * Returns the date in seconds from 1970/1/1.
	 * 
	 * null will return 0.
	 * @param date
	 * @return
	 */
	public static long toSeconds (Date date) {
		
		if (date != null) {
			return date.getTime() / 1000;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Null safe convertion from standard Date to sql Date.
	 * 
	 * Null returns null.
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date toSqlDate (Date date) {
		
		if (date != null) {
			return new java.sql.Date (date.getTime());
		}
		else {
			return null;
		}
	}
	
	/**
	 * Null safe convertion from standard Date to sql Timestamp.
	 * 
	 * Null returns null.
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Timestamp toSqlTimestamp (Date date) {
		
		if (date != null) {
			return new java.sql.Timestamp (date.getTime());
		}
		else {
			return null;
		}
	}
	
	public static String toString (Date date, String format) {
		
		if (date != null) {
			return new SimpleDateFormat (format).format (date);
		}
		else {
			return "";
		}
	}
	
	/**
	 * Returns the date in timestamp format using the preferred format for AM/PM.
	 * 
	 * @param date
	 * @return
	 */
	public static String toTimestampAmPmString (Date date) {
		
		return toString (date, DATE_FORMAT_UA_DATETIME_AMPM);
	}

	/**
	 * Returns the date in date time format using the preferred format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toTimestampString (Date date) {
		
		return toString (date, DATE_FORMAT_UA_DATETIME);
	}

	public static Date toTimestampFirstOfDay (Date date) {

		Calendar calendar;
		
		calendar = new GregorianCalendar();
		calendar.setTime (date);
		calendar.set (Calendar.HOUR, -12);
		calendar.set (Calendar.MINUTE, 0);
		calendar.set (Calendar.SECOND, 0);
		calendar.set (Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
	
	public static Date toTimestampNoon (Date date) {

		Calendar calendar;
		
		calendar = new GregorianCalendar();
		calendar.setTime (date);
		calendar.set (Calendar.HOUR, 0);
		calendar.set (Calendar.MINUTE, 0);
		calendar.set (Calendar.SECOND, 0);
		calendar.set (Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
	
	/**
	 * Returns the date in time format using the preferred format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toTimeString (Date date) {
		
		return toString (date, DATE_FORMAT_UA_TIME);
	}
	
	public static String toUaDateString (Date date) {
		
		return toString (date, DATE_FORMAT_UA_DATE);		
	}

	public static String toUaTimestampString (Date date) {
		
		return toString (date, DATE_FORMAT_UA_DATETIME);		
	}

	public static String toUaTimestampAmPmString (Date date) {
		
		return toString (date, DATE_FORMAT_UA_DATETIME_AMPM);		
	}

	public static String toUaTimeString (Date date) {
		
		return toString (date, DATE_FORMAT_UA_TIME);		
	}
}
