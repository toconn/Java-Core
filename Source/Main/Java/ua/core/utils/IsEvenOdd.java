package ua.core.utils;


/**
 * A simple class to flip between even and odd.
 * 
 * Everytime it is inspected, it flips to the opposite.
 * 
 * This is not thread safe.
 * 
 * @author Tadhg
 *
 */
public class IsEvenOdd {
	
	private boolean even = false;

	
	public boolean isEven() {
		even = ! even;
		return ! even;
	}
	
	public boolean isOdd() {
		return ! isEven();
	}
}
