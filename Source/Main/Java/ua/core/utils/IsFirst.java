package ua.core.utils;

/**
 * A simple class to flip from first to not first.
 * 
 * The class flips from first to not first when first inspected.
 * 
 * This is not thread safe.
 * 
 * @author Tadhg
 *
 */

public class IsFirst {
	
	private boolean first = true;

	public boolean isFirst() {
		
		boolean first = this.first;
		
		if (first) {
			this.first = false;
		}
		
		return first;
	}
	
	public boolean isNotFirst() {

		return ! isFirst();
	}
}
