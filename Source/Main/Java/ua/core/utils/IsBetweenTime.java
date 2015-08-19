package ua.core.utils;

import java.util.Date;

/**
 * Class measures a length of time (between operations) and then allows checks on whether other measured times fall within that period.
 * 
 * Use: Call .setBeginning(), .setEnd(), then test with test methods.
 * 
 * @author Tadhg
 *
 */
public class IsBetweenTime {

	private Date beginTimestamp;
	private Date endTimestamp;

	
	public IsBetweenTime() {
		
	}
	
	
	public IsBetweenTime (boolean autoSetBeginning) {
		
		if (autoSetBeginning == true) {
			setBeginning();
		}
	}
	
	
	public boolean isBetween (Date checkTimestamp) {
		
		if (this.beginTimestamp == null || this.endTimestamp == null) {
			throw new IllegalStateException();
		}
		
		return this.beginTimestamp.getTime() < checkTimestamp.getTime() && checkTimestamp.getTime() < this.endTimestamp.getTime();
	}
	
	public boolean isNotBetween (Date checkTimestamp) {
		
		return ! isBetween (checkTimestamp);
	}

	public boolean isNotOnOrBetween (Date checkTimestamp) {
		
		return ! isOnOrBetween (checkTimestamp);
	}
	
	public boolean isOnOrBetween (Date checkTimestamp) {
		
		if (this.beginTimestamp == null || this.endTimestamp == null) {
			throw new IllegalStateException();
		}
		
		return this.beginTimestamp.getTime() <= checkTimestamp.getTime() && checkTimestamp.getTime() <= this.endTimestamp.getTime();
	}
	
	
	public void setBeginning() {
		
		beginTimestamp = new Date();
	}
	
	
	public void setEnd() {
		
		endTimestamp = new Date();
		
		if (beginTimestamp == null) {
			endTimestamp = null;
			throw new IllegalStateException();
		}
	}
}
