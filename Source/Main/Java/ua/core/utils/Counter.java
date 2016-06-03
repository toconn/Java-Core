package ua.core.utils;

public class Counter {

	private long lastValue = 0;
	
	
	public Counter() {
		
		lastValue = 0;
	}
	
	public Counter (boolean startOnOne) {
		
		if (startOnOne) {
			
			lastValue = 1;
		}
		else {
			
			lastValue = 0;
		}
	}
	
	public long getLast() {
		
		return  lastValue;
	}

	public long getNext() {
		
		return lastValue++;
	}
}
