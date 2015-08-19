package ua.core.comp.appproperties;

import java.util.List;
import java.util.Set;

import ua.core.base.ExceptionInvalidValue;

/**
 * A store for application properties. Properties must be non case sensitive (ignores case).
 * 
 * Methods are null safe and will return null / 0 / false if no value is found.
 * 
 * @author Tadhg
 *
 */
public interface AppPropertiesType {

	public Set <String> getKeys();
	
	public Boolean getBoolean (String name) throws ExceptionInvalidValue;
	public int getInt (String name) throws ExceptionInvalidValue;
	public long getLong (String name) throws ExceptionInvalidValue;
	public String getString (String name);
	public List <String> getStrings (String name);
	
	public boolean hasBoolean (String name);
	public boolean hasInt (String name);
	public boolean hasLong (String name);
	public boolean hasProperty (String name);
	
	public void set (String name, boolean value);
	public void set (String name, int value);
	public void set (String name, long value);
	public void set (String name, String value);	
}
