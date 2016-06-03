package ua.core.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class StringSetIgnoreCase implements Set <String> {
	
	private Map <String, String> map;

	public StringSetIgnoreCase() {
		
		init (0);
	}
	
	public StringSetIgnoreCase (Collection <String> items) {
		
		init (items.size());
		addAll (items);
	}

	public StringSetIgnoreCase (String[] items) {
		
		init (items.length);
		addAll (items);
	}

	@Override
	public boolean add (String item) {

		map.put (StringUtils.toLowerCase (item), item);
		
		return true;
	}

	@Override
	public boolean addAll (Collection <? extends String> collection) {

		if (CollectionUtils.isNotEmpty (collection)) {
			for (String item : collection) {
				add (item);
			}
		}
		
		return true;
	}
	
	public boolean addAll (String[] items) {
		
		for (String item : items) {
			add (item);
		}
		
		return true;
	}

	@Override
	public void clear () {

		map.clear();
	}

	@Override
	public boolean contains (Object item) {

		if (item instanceof String) {
			return map.containsKey (StringUtils.toLowerCase ((String) item));
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsAll (Collection <?> collection) {

		if (CollectionUtils.isNotEmpty (collection)) {
			for (Object item : collection) {
				if (! contains (item)) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isEmpty () {
		
		return map.isEmpty();
	}

	@Override
	public Iterator <String> iterator () {

		return map.values().iterator();
	}

	@Override
	public boolean remove (Object item) {

		if (item instanceof String) {
			return (map.remove (StringUtils.toLowerCase ((String) item)) != null);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean removeAll (Collection <?> collection) {

		boolean removedOk;
		boolean removedOkCurrent;
		
		removedOk = false;
		
		for (Object item: collection) {
			
			removedOkCurrent = remove (item);
			
			if (! removedOkCurrent) {
				removedOk = false;
			}
		}
	
		return removedOk;
	}

	@Override
	public boolean retainAll (Collection <?> collection) {

		return false;
	}

	@Override
	public int size () {

		return map.size();
	}

	@Override
	public Object[] toArray () {

		return map.values().toArray();
	}

	@Override
	public <T> T[] toArray (T[] a) {

		return a;
	}
	
	
	private void init (int size) {
	
		if (size > 0) {
			map = new HashMap <String, String> (size);
		}
		else {
			map = new HashMap <String, String> ();
		}
	}
}
