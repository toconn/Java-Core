package ua.core.utils.timers;


public interface Timer {

	public void start();
	public void stop();
	public long getElapsedTimeMS();
}
