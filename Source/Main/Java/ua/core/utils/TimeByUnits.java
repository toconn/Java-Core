package ua.core.utils;

import java.text.DecimalFormat;

public class TimeByUnits {
	
	private static final DecimalFormat MILLISEC_FORMAT	= new DecimalFormat ("000");

	private long hours = 0;
	private long mins = 0;
	private long secs = 0;
	private long millisecs = 0;

	public TimeByUnits (long totalMillisecs) {

		hours = (long) totalMillisecs / 3600000;
		totalMillisecs = totalMillisecs - hours * 3600000;
		mins = (long) totalMillisecs / 60000;
		totalMillisecs = totalMillisecs - mins * 60000;
		secs = (long) totalMillisecs / 1000;
		millisecs = totalMillisecs - secs * 1000;
	}

	public long getHours() { return hours; }
	public long getMins() { return mins; }
	public long getSecs() { return secs; }
	public long getMillisecs() { return millisecs; }

	@Override
	public String toString() {

		return "TimeByUnits [" +
			"hours=" + Long.toString (hours) +
			", mins=" + Long.toString (mins) +
			", secs=" + Long.toString (secs) +
			", millisecs=" + Long.toString (millisecs) +
			"]";
	}
	
	public String formattedTime() {
		
		StringBuilder builder = new StringBuilder();
		
		if (hours > 0) {
			builder.append (hours).append (hours != 1 ? " hrs " : " hr ");
		}
		
		if ((hours > 0) || (mins > 0)) {
			builder.append (mins).append (mins != 1 ? " mins " : " min ");
		}
		
		builder
			.append (secs)
			.append (".")
			.append (MILLISEC_FORMAT.format (millisecs))
			.append (" secs");
		
		return builder.toString();
	}
}