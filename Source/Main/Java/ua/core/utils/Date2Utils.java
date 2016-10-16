package ua.core.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static ua.core.utils.DateConst.*;


/**
*
* @author Tadhg
*
* Updates:
*
* 2015.06.30 - Java 8.0 LocalDateTime
*/
public class Date2Utils {

	public static long age (LocalDateTime localDateTime1) {
		
		return diffInYears (localDateTime1, LocalDateTime.now());
	}
		
	public static long diffInYears (LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
		
		return ChronoUnit.YEARS.between (localDateTime1, localDateTime2);
	}

	/**
	 * Is date after the test date.
	 * 
	 * Null safe, returns false.
	 * 
	 */
	public static boolean isAfter (LocalDateTime dateTime, LocalDateTime testDateTime) {
		
		if (dateTime != null && dateTime.isAfter (testDateTime)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Is date before the test date.
	 * 
	 * Null safe, returns false.
	 * 
	 */
	public static boolean isBefore (LocalDateTime dateTime, LocalDateTime testDateTime) {
		
		if (dateTime != null && dateTime.isBefore (testDateTime)) {
			return true;
		}
		else {
			return false;
		}
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
	public static boolean isEqual (LocalDate date1, LocalDate date2) {

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
	 * Compares two date times and returns true if they are equal.
	 * 
	 * Null safe. If either is null but not both, returns false.
	 * If both are true, returns true.
	 * 
	 * @param dateTime1
	 * @param dateTime1
	 * @return
	 */
	public static boolean isEqual (LocalDateTime dateTime1, LocalDateTime dateTime2) {

		if (dateTime1 != null && dateTime2 != null) {
			return dateTime1.equals (dateTime2);
		}
		else if (dateTime1 == null && dateTime2 == null) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Compares two times and returns true if they are equal.
	 * 
	 * Null safe. If either is null but not both, returns false.
	 * If both are true, returns true.
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isEqual (LocalTime time1, LocalTime time2) {

		if (time1 != null && time2 != null) {
			return time1.equals (time2);
		}
		else if (time1 == null && time2 == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Is date after the test date.
	 * 
	 * Null safe, returns false.
	 * 
	 */
	public static boolean isNotAfter (LocalDateTime dateTime, LocalDateTime testDateTime) {
		
		return ! isAfter (dateTime, testDateTime);
	}

	/**
	 * Is date not before the test date.
	 * 
	 * Null safe, returns false.
	 * 
	 */
	public static boolean isNotBefore (LocalDateTime dateTime, LocalDateTime testDateTime) {
		
		return ! isNotBefore (dateTime, testDateTime);
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
	public static boolean isNotEqual (LocalDate date1, LocalDate date2) {
		
		return ! isEqual (date1, date2);
	}
	
	/**
	 * Compares two date times and returns true if they are not equal.
	 * Null safe. If one is null but the other isn't, returns true.
	 * If both are null, returns false.
	 * 
	 * @param dateTime1
	 * @param dateTime2
	 * @return
	 */
	public static boolean isNotEqual (LocalDateTime dateTime1, LocalDateTime dateTime2) {
		
		return ! isEqual (dateTime1, dateTime2);
	}
	
	/**
	 * Compares two times and returns true if they are not equal.
	 * Null safe. If one is null but the other isn't, returns true.
	 * If both are null, returns false.
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isNotEqual (LocalTime time1, LocalTime time2) {
		
		return ! isEqual (time1, time2);
	}
	
	/**
	 * Is date on or after the test date.
	 * 
	 * Null safe, returns false.
	 * 
	 */
	public static boolean isOnOrAfter (LocalDateTime dateTime, LocalDateTime testDateTime) {
		
		if (dateTime != null && ! dateTime.isBefore (testDateTime)) {
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
	public static boolean isOnOrBefore (LocalDateTime dateTime, LocalDateTime testDateTime) {
		
		return ! isAfter (dateTime, testDateTime);
	}
	
	/**
	 * Is date within a start and end date inclusive.
	 * 
	 * Null safe, returns false.
	 * 
	 */
	public static boolean isWithin (LocalDateTime dateTime, LocalDateTime startDateTime, LocalDateTime endDateTime) {
		
		if (dateTime != null && (dateTime.equals (startDateTime) || dateTime.isAfter (startDateTime)) && (dateTime.equals (endDateTime) || dateTime.isBefore (endDateTime))) {
			return true;
		}
		else {
			return false;
		}
	}
	

	
	/**
	 * Returns today's date at 00:00:00 midnight.
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static LocalDateTime newDateTimeToday() {
		
		LocalDate		dateToday = LocalDate.now();
		LocalTime		timeMidnight = LocalTime.of (0, 0, 0);
		LocalDateTime	dateTimeToday = LocalDateTime.of (dateToday, timeMidnight);
		
		return dateTimeToday;
	}

	
	/**
	 * Returns today's date at 12:00 Noon.
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static LocalDateTime newDateTimeTodayNoon() {
		
		LocalDate		dateToday = LocalDate.now();
		LocalTime		timeMidnight = LocalTime.of (12, 0, 0);
		LocalDateTime	dateTimeToday = LocalDateTime.of (dateToday, timeMidnight);
		
		return dateTimeToday;
	}

	/**
	 * Returns today's date at 00:00:00 midnight.
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static LocalDateTime newDateYesterday() {
		
		LocalDate		dateToday = LocalDate.now().minusDays (1);
		LocalTime		timeMidnight = LocalTime.of (0, 0, 0);
		LocalDateTime	dateTimeToday = LocalDateTime.of (dateToday, timeMidnight);
		
		return dateTimeToday;
	}

	
	public static LocalDateTime newDateYesterdayNoon() {
		
		LocalDate		dateToday = LocalDate.now().minusDays (1);
		LocalTime		timeMidnight = LocalTime.of (0, 0, 0);
		LocalDateTime	dateTimeToday = LocalDateTime.of (dateToday, timeMidnight);
		
		return dateTimeToday;
	}
	
	/**
	 * Null safe conversion from sql Timestamp to standard Date.
	 * 
	 * null returns null.
	 * 
	 * @param timestamp
	 * @return
	 */
	public static LocalDate toDate (java.sql.Timestamp timestamp) {
		
		if (timestamp != null) {
			return timestamp.toLocalDateTime().toLocalDate();
		}
		else {
			return null;
		}
	}

	/**
	 * Null safe conversion from sql Timestamp to standard Date.
	 * 
	 * null returns null.
	 * 
	 * @param timestamp
	 * @return
	 */
	public static LocalDateTime toDateTime (java.util.Date date) {
		
		return LocalDateTime.ofInstant (date.toInstant(), ZoneId.systemDefault());
	}

	/**
	 * Null safe conversion from Instant to Local Date Time.
	 * 
	 * null returns null.
	 * 
	 * @param timestamp
	 * @return
	 */
	public static LocalDateTime toDateTime (Instant instant) {
		
		return LocalDateTime.ofInstant (instant, ZoneId.systemDefault());
	}
	
	/**
	 * Null safe conversion from sql Timestamp to standard Date.
	 * 
	 * null returns null.
	 * 
	 * @param timestamp
	 * @return
	 */
	public static LocalDateTime toDateTime (java.sql.Timestamp timestamp) {
		
		if (timestamp != null) {
			return timestamp.toLocalDateTime();
		}
		else {
			return null;
		}
	}

	public static LocalDateTime toDateTimeFromFormat (String dateString, String format) {
		
		DateTimeFormatter	dateFormat;
		LocalDateTime		dateTime;
		
		try {
			
			dateFormat = DateTimeFormatter.ofPattern (format);
			dateTime = LocalDateTime.parse (dateString, dateFormat);
		}
		catch (Exception e) {

			dateTime = null;
		}
		
		return dateTime;
	}
	
	public static LocalDate toDateFromUaDate (String dateString) {
		
		return LocalDate.parse (dateString, DATE2_FORMATTER_UA_DATE);
	}

	public static LocalDate toDateFromUaTime (String dateString) {
		
		return LocalDate.parse (dateString, DATE2_FORMATTER_UA_TIME);
	}

	public static LocalDate toDateFromUaTimestamp (String dateString) {
		
		return LocalDate.parse (dateString, DATE2_FORMATTER_UA_DATETIME);
	}

	public static LocalDate toDateFromUaTimestampAmPm (String dateString) {
		
		return LocalDate.parse (dateString, DATE2_FORMATTER_UA_TIMESTAMP_AMPM);
	}

	/**
	 * Returns the date in date format using the preferred format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateString (LocalDate date) {

		return date.format(DATE2_FORMATTER_UA_DATE);
	}
	

	/**
	 * Returns the date in date format using the ISO format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toIsoDateString (LocalDate date) {

		return date.format (DATE2_FORMATTER_ISO_DATE);
	}
	
	/**
	 * Returns the date in date format using the ISO format.
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String toIsoDateString (LocalDateTime dateTime) {

		return dateTime.format (DATE2_FORMATTER_ISO_DATE);
	}
	
	
	/**
	 * Returns the date in date time format using the ISO format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toIsoDateTimeString (LocalDate date) {

		return date.format (DATE2_FORMATTER_ISO_TIMESTAMP);
	}

	/**
	 * Returns the date in date time format using the ISO format.
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String toIsoDateTimeString (LocalDateTime dateTime) {

		return dateTime.format (DATE2_FORMATTER_ISO_TIMESTAMP);
	}

	/**
	 * Returns the date in time format using the ISO format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toIsoTimeString (LocalDate date) {

		return date.format (DATE2_FORMATTER_ISO_TIME);
	}
	
	/**
	 * Returns the date in time format using the ISO format.
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String toIsoTimeString (LocalDateTime dateTime) {

		return dateTime.format (DATE2_FORMATTER_ISO_TIME);
	}
	
	/**
	 * Returns the date in time format using the ISO format.
	 * 
	 * @param time
	 * @return
	 */
	public static String toIsoTimeString (LocalTime time) {

		return time.format (DATE2_FORMATTER_ISO_TIME);
	}

	/**
	 * Returns the date in seconds from 1970/1/1.
	 * 
	 * null will return 0.
	 * @param date
	 * @return
	 */
	public static long toSeconds (LocalDateTime dateTime) {
		
		ZoneId zoneId;

		if (dateTime != null) {

			zoneId = ZoneId.systemDefault();
			return dateTime.atZone (zoneId).toEpochSecond();
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
	public static java.sql.Date toSqlDate (LocalDate date) {
		
		if (date != null) {
			return java.sql.Date.valueOf (date);
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
	public static java.sql.Timestamp toSqlTimestamp (LocalDateTime dateTime) {
		
		if (dateTime != null) {
			return java.sql.Timestamp.valueOf (dateTime);
		}
		else {
			return null;
		}
	}
	
	public static String toString (LocalDate date) {
		
		return toString (date, DATE2_FORMATTER_DEFAULT_DATE);		
	}
	
	public static String toString (LocalDateTime dateTime) {
		
		return toString (dateTime, DATE2_FORMATTER_DEFAULT_DATETIME);		
	}
	
	public static String toString (LocalTime time) {
		
		return toString (time, DATE2_FORMATTER_DEFAULT_TIME);		
	}
	
	public static String toString (LocalDate date, DateTimeFormatter formatter) {
		
		if (date != null) {
			return date.format (formatter);
		}
		else {
			return "";
		}		
	}
	
	public static String toString (LocalDateTime dateTime, DateTimeFormatter formatter) {
		
		if (dateTime != null) {
			return dateTime.format (formatter);
		}
		else {
			return "";
		}		
	}
	
	public static String toString (LocalTime time, DateTimeFormatter formatter) {
		
		if (time != null) {
			return time.format (formatter);
		}
		else {
			return "";
		}		
	}
	
	public static String toString (LocalDate date, String format) {
		
		DateTimeFormatter formatter;
		
		if (date != null) {
			formatter = DateTimeFormatter.ofPattern (format);
			return date.format (formatter);
		}
		else {
			return "";
		}
	}
	
	public static String toString (LocalDateTime dateTime, String format) {
		
		DateTimeFormatter formatter;
		
		if (dateTime != null) {
			formatter = DateTimeFormatter.ofPattern (format);
			return dateTime.format (formatter);
		}
		else {
			return "";
		}
	}
	
	public static String toString (LocalTime time, String format) {
		
		DateTimeFormatter formatter;
		
		if (time != null) {
			formatter = DateTimeFormatter.ofPattern (format);
			return time.format (formatter);
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
	public static String toTimestampAmPmString (LocalDateTime date) {
		
		return toString (date, DATE2_FORMATTER_UA_TIMESTAMP_AMPM);
	}

	/**
	 * Returns the date in date time format using the preferred format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toTimestampString (LocalDate date) {
		
		return toString (date, DATE2_FORMATTER_UA_DATETIME);
	}
	
	/**
	 * Returns the date in date time format using the preferred format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toTimestampString (LocalTime date) {
		
		return toString (date, DATE2_FORMATTER_UA_TIME);
	}
	
	/**
	 * Returns the date in date time format using the preferred format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toTimestampString (LocalDateTime date) {
		
		return toString (date, DATE2_FORMATTER_UA_DATETIME);
	}
	
	/**
	 * Returns the date in time format using the preferred format.
	 * 
	 * @param date
	 * @return
	 */
	public static String toTimeString (LocalDateTime date) {
		
		return toString (date, DATE_FORMAT_UA_TIME);
	}
	
	public static String toUaDateString (LocalDateTime date) {
		
		return toString (date, DATE_FORMAT_UA_DATE);		
	}

	public static String toUaTimestampString (LocalDateTime date) {
		
		return toString (date, DATE_FORMAT_UA_DATETIME);		
	}

	public static String toUaTimestampAmPmString (LocalDateTime date) {
		
		return toString (date, DATE_FORMAT_UA_DATETIME_AMPM);		
	}

	public static String toUaTimeString (LocalDateTime date) {
		
		return toString (date, DATE_FORMAT_UA_TIME);		
	}
}