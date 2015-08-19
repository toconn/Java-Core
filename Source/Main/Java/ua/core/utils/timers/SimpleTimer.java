package ua.core.utils.timers;

public class SimpleTimer implements Timer {
	
	private enum TimerStatus {
		None,
		Started,
		Stopped
	}
	
	private long		startTime	= 0;
	private long		stopTime	= 0;
	private TimerStatus	status		= TimerStatus.None;

	
	public SimpleTimer () {

		start ();
	}
	
	
	public void start () {

		this.startTime	= System.currentTimeMillis ();
		this.stopTime	= 0;
		this.status		= TimerStatus.Started;
	}

	public void stop () {

		this.stopTime	= System.currentTimeMillis ();
		this.status		= TimerStatus.Stopped;
	}

	/**
	 * Returns the elapsed time in milliseconds.
	 * 
	 * @return
	 */
	public long getElapsedTimeMS () {

		long elapsed;

		
		switch (this.status) {
			
			case Started:
			
				elapsed = (System.currentTimeMillis () - startTime);
				break;
				
			case Stopped:
				
				elapsed = (stopTime - startTime);
				break;
	
			default:
				
				elapsed = 0;
				break;
		}
			
		return elapsed;
	}
	
	public String toString () {

		return TimerUtils.elapsedTimeSecFormatPrecision (this);
	}
}
