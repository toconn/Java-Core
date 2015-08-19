package ua.core.utils.timers;

import java.text.DecimalFormat;


public class TimerUtils {

	// Constants ////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final DecimalFormat TIME_SECS_PRECISION_FORMAT	= new DecimalFormat ("#0.000s");
	private static final DecimalFormat OPERATION_PER_SECOND_FORMAT	= new DecimalFormat ("#,###,###,###,###,##0.0");

	
	public static float elapsedTimeSec (Timer timer) {

		return timer.getElapsedTimeMS() / 1000f;
	}
	
	public static String elapsedTimeSecFormatPrecision (Timer timer) {

		return TIME_SECS_PRECISION_FORMAT.format (elapsedTimeSec (timer));
	}
	
	public static float operationsPerSec (Timer timer, long operationCount) {
		
		float elapsedTimeSec;
		
		elapsedTimeSec = elapsedTimeSec (timer);
		
		if (elapsedTimeSec != 0) {
			return operationCount / elapsedTimeSec;
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Returns the number of transactions a second.
	 * 
	 * @param transactionCount
	 * @return
	 */
	public String operationsPerSecFormatted (Timer timer, long operationCount) {
		
		float operationsPerSec;
		
		operationsPerSec = operationsPerSec (timer, operationCount);
		
		if (operationsPerSec != -1) {
			return OPERATION_PER_SECOND_FORMAT.format (operationsPerSec);
		}
		else {
			return "?";
		}
	}
}
