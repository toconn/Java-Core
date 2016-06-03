package ua.core.comp.appproperties;

import ua.core.base.ExceptionInvalidValue;
import ua.core.beans.BooleanType;


public abstract class AbstractAppProperties implements ua.core.comp.appproperties.AppPropertiesType {
	
	
	@Override
	public Boolean getBoolean (String name) throws ExceptionInvalidValue {

		BooleanType booleanType;
		
		booleanType = BooleanType.getInstance (getString (name));
		if (booleanType == null) {
			throw new ExceptionInvalidValue();
		}
		
		return booleanType.getValue ();
	}
	
	@Override
	public int getInt (String name) throws ExceptionInvalidValue {

		try {
			return Integer.parseInt (getString (name));
		}
		catch (Exception e) {
			throw new ExceptionInvalidValue ();
		}
	}

	@Override
	public long getLong (String name) throws ExceptionInvalidValue {

		try {
			return Long.parseLong (getString (name));
		}
		catch (Exception e) {
			throw new ExceptionInvalidValue ();
		}
	}

	@Override
	public String getString (String name) {

		return getItem (name);
	}

	@Override
	public boolean hasBoolean (String name) {

		try {
			getInt (name);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean hasInt (String name) {

		try {
			getInt (name);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean hasLong (String name) {

		try {
			getLong (name);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean hasProperty (String name) {

		return hasItem (name);
	}

	@Override
	public void set (String name, boolean value) {

		setItem (name, BooleanType.getInstance (value).toString ());
	}

	@Override
	public void set (String name, int value) {

		setItem (name, Integer.toString (value));	
	}

	@Override
	public void set (String name, long value) {

		setItem (name, Long.toString (value));
	}

	@Override
	public void set (String name, String value) {

		setItem (name, value);
	}
	
	
	
	/**
	 * This method is called by all the other methods to retrieve the item.
	 * @param name
	 * @param value
	 */
	abstract String getItem (String name);
	
	abstract boolean hasItem (String name);
	
	/**
	 * This method is called by all the other methods to store the item.
	 * @param name
	 * @param value
	 */
	abstract void setItem (String name, String value);
}