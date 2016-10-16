package ua.core.utils;

import java.text.DecimalFormat;

public class RunMetrics {
	
	// Constants ///////////////////////////////////////////////////////////////
	
	enum StartStopStatus {NONE, STARTED, STOPPED}
	
	private static final DecimalFormat COMMA_SEPARATED_DECIMAL_FORMAT	= new DecimalFormat ("#,###,###,###,###,##0.0");
	private static final DecimalFormat COMMA_SEPARATED_INTEGER_FORMAT	= new DecimalFormat ("#,###,###,###,###,##0");

	
	// Class Proper ////////////////////////////////////////////////////////////
	
	private StartStopStatus status = null;
	private long count = 0l;				// Counts the number of operations
	private long memoryUsedStart = 0l;		// Memory use at the start
	private long memoryUsedEnd = 0l;		// Memory use at the end
	private long timeStartMillisec = 0l;	// Time at the start
	private long timeEndMillisec = 0l;		// Time at the end

	public RunMetrics() {
		start();
	}

	public long getElapsedTimeMillisec() {

		long elapsed;
		
		switch (this.status) {
			
			case STARTED:
				
				elapsed = (System.currentTimeMillis() - timeStartMillisec);
				break;
				
			case STOPPED:
				elapsed = (timeEndMillisec - timeStartMillisec);
				break;
				
			default:
				
				elapsed = 0;
				break;
		}
	
		return elapsed;
	}

	public float getElapsedTimeSec() {

		return getElapsedTimeMillisec() / 1000f;
	}

	public String getFormattedElapsedTime () {

		TimeByUnits time;
		
		time = new TimeByUnits (getElapsedTimeMillisec());
		return time.formattedTime();
	}

	public String getFormattedMemoryUsed() {

		return COMMA_SEPARATED_INTEGER_FORMAT.format (getMemoryUsed());
	}

	public String getFormattedMemoryUsedKilobytes() {

		return COMMA_SEPARATED_DECIMAL_FORMAT.format (getMemoryUsed() / 1024.0f) + " kb";
	}

	public String getFormattedMemoryUsedMegabytes() {

		return COMMA_SEPARATED_DECIMAL_FORMAT.format (getMemoryUsed() / 1048576.0f) + " mb";
	}

	public String getFormattedOperationsPerSec() {

		return COMMA_SEPARATED_DECIMAL_FORMAT.format (getOperationsPerSec());
	}

	public long getMemoryUsed() {

		long memoryUsed;
		
		switch (this.status) {
			
			case STARTED:
				
				memoryUsed = (getMemoryInUse() - memoryUsedStart);
				break;
				
			case STOPPED:
				memoryUsed = (memoryUsedEnd - memoryUsedStart);
				break;
				
			default:
				
				memoryUsed = 0;
				break;
		}
	
		return memoryUsed;
	}
	
	public long getOperationsCount() {

		return count;
	}

	public float getOperationsPerSec() {

		float elapsedTimeSec;
		
		elapsedTimeSec = getElapsedTimeSec();
		
		if (elapsedTimeSec != 0) {
			return count / elapsedTimeSec;
		}
		else {
			return -1;
		}
	}

	public void increment() {

		this.count++;
	}

	public void setOperationCount (long count) {

		this.count = count;
	}

	public void start() {

		this.status = StartStopStatus.STARTED;
		
		this.count = 0;
		this.memoryUsedStart = getMemoryInUse();
		this.timeStartMillisec = System.currentTimeMillis();
	}

	public void stop() {

		this.timeEndMillisec = System.currentTimeMillis();
		this.memoryUsedEnd = getMemoryInUse();
	}
	
	private long getMemoryInUse() {
		
	    return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}
}
