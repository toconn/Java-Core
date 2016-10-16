package ua.core.utils.timers;

public class CumulativeTimer implements Timer {
	
	private enum TimerStatus {
		None,
		Started,
		Stopped
	}
	
	// Properties ////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private long		cumulatedTime	= 0;
	private long		startTime		= 0;
	private long		stopTime		= 0;
	private TimerStatus	status			= TimerStatus.None;


	// Main ////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public CumulativeTimer() {
		start();
	}
	
	public void start() {

		this.startTime	= System.currentTimeMillis();
		this.stopTime	= 0;
		this.status		= TimerStatus.Started;
	}

	public void stop() {

		this.stopTime		= System.currentTimeMillis();
		this.status			= TimerStatus.Stopped;
		this.cumulatedTime	= this.cumulatedTime + (this.stopTime - this.startTime);
	}

	/**
	 * Returns the elapsed time in milliseconds.
	 * 
	 * @return
	 */
	public long getElapsedTimeMS() {

		long elapsed;
		
		switch (this.status) {
			
			case Started:
			
				elapsed = (System.currentTimeMillis() - startTime) + this.cumulatedTime;
				break;
				
			case Stopped:
				
				elapsed = this.cumulatedTime;
				break;
	
			default:
				
				elapsed = 0;
				break;
		}
			
		return elapsed;
	}
	
	public String toString() {
		
		return TimerUtils.elapsedTimeSecFormatPrecision (this);
	}
}
