package ua.core.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Creation date: (2009.08.10)
 * Recreated date: (2013.07.25)
 * 
 * @author: Tim O'Connell
 * @version: 1.00.00
 * 
 * This is a standard HashMap that only accepts strings as the key. Case is ignored.
 */

public class StringMapIgnoreCase <ValueType> implements Map <String, ValueType> {
		
	
	private Map <String, ValueType> map;	

	
	public StringMapIgnoreCase() {
		
		map = new HashMap <String, ValueType> ();
	}

	public StringMapIgnoreCase (int initialCapacity) {
		
		map = new HashMap <String, ValueType> (initialCapacity);
	}

	public StringMapIgnoreCase (int initialCapacity, float loadFactor) {
		
		map = new HashMap <String, ValueType > (initialCapacity, loadFactor);
	}
	
	public StringMapIgnoreCase (Map <? extends String, ? extends ValueType> map) {
		
		map = new HashMap <String, ValueType> ();
		this.putAll (map);
	}

	
	public ValueType add (String key, ValueType value) {
		return this.put (key, value);
	}

	public void addAll (Map <? extends String, ? extends ValueType> map) {
		this.putAll (map);
	}
	
	@Override
	public void clear () {
		map.clear ();
	}
	
	@Override
	public boolean containsKey (Object key) {

		if (isString (key)) {
			return map.containsKey (getKeyActual ((String) key));
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean containsValue (Object value) {
		return map.containsValue (value);
	}
	
	@Override
	public Set <java.util.Map.Entry <String, ValueType>> entrySet () {
		return map.entrySet ();
	}
	
	@Override
	public ValueType get (Object key) {

		if (isString (key)) {
			return map.get (getKeyActual ((String) key));
		}
		else {
			return null;
		}
	}
	
	public List <String> getSortedKeyList () {
		return CollectionUtils.sort (this.keySet ());
	}

	public boolean hasKey (String key) {
		return containsKey (key);
	}

	@Override
	public boolean isEmpty () {
		return map.isEmpty ();
	}

	@Override
	public Set <String> keySet () {
		return map.keySet ();
	}

	@Override
	public ValueType put (String key, ValueType value) {
		
		return map.put (getKeyActual (key), value);
	}

	@Override
	public void putAll (Map <? extends String, ? extends ValueType> map) {
		for (String key: map.keySet()) {
			put (key, map.get (key));
		}
	}

	@Override
	public ValueType remove (Object key) {

		if (isString (key)) {
			return map.remove (getKeyActual ((String) key));
		}
		else {
			return null;
		}
	}

	@Override
	public int size () {
		return map.size ();
	}
	
	@Override
	public Collection <ValueType> values () {
		return map.values ();
	}

	
	private String getKeyActual (String key) {
		return StringUtils.toLowerCase (key);
	}
	
	private boolean isString (Object object) {
		return  (object instanceof String) ;
	}
}
