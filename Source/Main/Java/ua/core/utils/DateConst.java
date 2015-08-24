package ua.core.utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class DateConst {

	public static final String				DATE_FORMAT_ISO_DATE				= "yyyy/MM/dd";
	public static final String				DATE_FORMAT_ISO_DATETIME			= "yyyy/MM/dd HH:mm:ss";
	public static final String				DATE_FORMAT_ISO_TIME				= "HH:mm:ss";
	
	public static final String				DATE_FORMAT_UA_DATE					= "yyyy-MM-dd";
	public static final String				DATE_FORMAT_UA_DATETIME				= "yyyy-MM-dd HH:mm:ss";
	public static final String				DATE_FORMAT_UA_DATETIME_ZONE		= "yyyy-MM-dd HH:mm:ss z";
	public static final String				DATE_FORMAT_UA_DATETIME_AMPM		= "yyyy-MM-dd h:mm:ss a";
	public static final String				DATE_FORMAT_UA_TIME					= "HH:mm:ss";
	
	public static final String				DATE_FORMAT_FILE_DATE				= "yyyy.MM.dd";
	public static final String				DATE_FORMAT_FILE_DATETIME			= "yyyy.MM.dd-HH.mm.ss";
	public static final String				DATE_FORMAT_FILE_TIME				= "HH.mm.ss";

	public static final String				DATE_FORMAT_RFC822					= "E, dd MMM yyyy HH:mm:ss Z";

	
	public static final SimpleDateFormat	DATE_FORMATTER_ISO_DATE				= new SimpleDateFormat (DATE_FORMAT_ISO_DATE);
	public static final SimpleDateFormat	DATE_FORMATTER_ISO_DATETIME			= new SimpleDateFormat (DATE_FORMAT_ISO_DATETIME);
	public static final SimpleDateFormat	DATE_FORMATTER_ISO_TIME				= new SimpleDateFormat (DATE_FORMAT_ISO_TIME);

	public static final SimpleDateFormat	DATE_FORMATTER_UA_DATE				= new SimpleDateFormat (DATE_FORMAT_UA_DATE);
	public static final SimpleDateFormat	DATE_FORMATTER_UA_DATETIME			= new SimpleDateFormat (DATE_FORMAT_UA_DATETIME);
	public static final SimpleDateFormat	DATE_FORMATTER_UA_TIME				= new SimpleDateFormat (DATE_FORMAT_UA_TIME);

	public static final SimpleDateFormat	DATE_FORMATTER_RFC822				= new SimpleDateFormat (DATE_FORMAT_RFC822);
	
	public static final SimpleDateFormat	DATE_FORMATTER_DEFAULT_DATE			= DATE_FORMATTER_UA_DATE;
	public static final SimpleDateFormat	DATE_FORMATTER_DEFAULT_DATETIME		= DATE_FORMATTER_UA_DATETIME;
	public static final SimpleDateFormat	DATE_FORMATTER_DEFAULT_TIME			= DATE_FORMATTER_UA_TIME;
	
	
	public static final DateTimeFormatter	DATE2_FORMATTER_ISO_DATE			= DateTimeFormatter.ofPattern (DATE_FORMAT_ISO_DATE);
	public static final DateTimeFormatter	DATE2_FORMATTER_ISO_TIME			= DateTimeFormatter.ofPattern (DATE_FORMAT_ISO_TIME);
	public static final DateTimeFormatter	DATE2_FORMATTER_ISO_TIMESTAMP		= DateTimeFormatter.ofPattern (DATE_FORMAT_ISO_DATETIME);
	
	public static final DateTimeFormatter	DATE2_FORMATTER_UA_DATE				= DateTimeFormatter.ofPattern (DATE_FORMAT_UA_DATE);
	public static final DateTimeFormatter	DATE2_FORMATTER_UA_TIME				= DateTimeFormatter.ofPattern (DATE_FORMAT_UA_TIME);
	public static final DateTimeFormatter	DATE2_FORMATTER_UA_DATETIME			= DateTimeFormatter.ofPattern (DATE_FORMAT_UA_DATETIME);
	public static final DateTimeFormatter	DATE2_FORMATTER_UA_DATETIME_ZONE	= DateTimeFormatter.ofPattern (DATE_FORMAT_UA_DATETIME_ZONE);
	public static final DateTimeFormatter	DATE2_FORMATTER_UA_TIMESTAMP_AMPM	= DateTimeFormatter.ofPattern (DATE_FORMAT_UA_DATETIME_AMPM);
	
	public static final DateTimeFormatter	DATE2_FORMATTER_RFC822				= DateTimeFormatter.ofPattern (DATE_FORMAT_RFC822);
	
	public static final DateTimeFormatter	DATE2_FORMATTER_DEFAULT_DATE		= DATE2_FORMATTER_UA_DATE;
	public static final DateTimeFormatter	DATE2_FORMATTER_DEFAULT_TIME		= DATE2_FORMATTER_UA_TIME;
	public static final DateTimeFormatter	DATE2_FORMATTER_DEFAULT_DATETIME	= DATE2_FORMATTER_UA_DATETIME;
}
