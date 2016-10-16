package ua.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.core.base.ExceptionRuntime;


public class CollectionUtils {
	
	/**
	 * Adds an element to a list.
	 * Null safe. If the list doesn't exist, it will be created.
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> List <T> add (List <T> list, T item) {

		if (list == null) {
			list = new ArrayList <T>();
		}
		
		list.add (item);
		
		return list;
	}
	
	/**
	 * Create a new list with one item in it.
	 * 
	 * @param item
	 * @return
	 */
	public static <T> List <T> asList (T item) {
		
		List <T> list;
		
		list = new ArrayList <T>();
		list.add (item);
		
		return list;
	}

	/**
	 * Create a new list out of an array of items.
	 * 
	 * @param item
	 * @return
	 */
	@SafeVarargs
	public static <T> List <T> asList (T... itemArray) {
		
		List <T> list;
		
		list = new ArrayList <T>();
		
		for (T item: itemArray) {
			list.add (item);
		}
		
		return list;
	}
	
	/**
	 * Creates a new list out of the elements of the collection.
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> List <T> asList (Collection <T> collection) {
		
		List <T> list;
		
		list = new ArrayList <T>();
		list.addAll (collection);
		
		return list;
	}

	/**
	 * Creates a new list out of the elements of the collection.
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> List <T> asList (Map <?, T> map) {
		
		List <T> list;
		
		list = new ArrayList <T>();
		
		for (Object key : map.keySet()) {
			list.add (map.get (key));
		}
		
		return list;
	}


	/**
	 * Create a new set with one item in it.
	 * 
	 * @param item
	 * @return
	 */
	public static <T> Set <T> asSet (T item) {
		
		Set <T> set;
		
		set = new HashSet <T>();
		set.add (item);
		
		return set;
	}

	/**
	 * Create a new set out of an array of items.
	 * 
	 * @param item
	 * @return
	 */
	@SafeVarargs
	public static <T> Set <T> asSet (T... itemArray) {
		
		Set <T> set;
		
		set = new HashSet <T>();
		
		for (T item: itemArray) {
			set.add (item);
		}
		
		return set;
	}

	/**
	 * Creates a new set out of the elements of the collection.
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> Set <T> asSet (Collection <T> collection) {
		
		Set <T> set;
		
		set = new HashSet <T>();
		set.addAll (collection);
		
		return set;
	}
	
	public static <T> String concatenateToString (Collection <T> collection, String separator) {

		IsFirst				first;
		StringBuilder		stringBuilder;

		stringBuilder = new StringBuilder();
		
		if (isNotEmpty (collection)) {
	
			first = new IsFirst();
	
			for (T item : collection) {
				
				if (first.isNotFirst()) {
					stringBuilder.append (separator);
				}
				
				stringBuilder.append (item.toString());
			}
		}
		
		return stringBuilder.toString();
	}

	/**
	 * Tests to see if an array is empty (has no elements or is null).
	 * Null safe.
	 * 
	 * @param array
	 * @return
	 */
	public static <T> boolean isEmpty (T[] array) {
		
		if (array == null || array.length == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Tests to see if a collection is empty (has no elements or is null).
	 * Null safe.
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> boolean isEmpty (Collection <T> collection) {
		
		if (collection == null || collection.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isEmpty (Map <?,?> map) {
		
		return map == null || map.isEmpty();
	}


	/**
	 * Null safe test to see if an array is not empty (has at least one element).
	 * 
	 * @param array
	 * @return
	 */
	public static <T> boolean isNotEmpty (T[] array) {
		
		return ! isEmpty (array);
	}

	/**
	 * Null safe test to see if a collection is not empty (has at least one element).
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> boolean isNotEmpty (Collection <T> collection) {
		
		return ! isEmpty (collection);
	}
	
	public static boolean isNotEmpty (Map <?,?> map) {
		
		return ! isEmpty (map);
	}
	
	
	/**
	 * Runs a command on every element in a collection (iterable group).
	 * 
	 * The results must be retrieved manually (if any).
	 * 
	 * @param items
	 * @param collectionCommand
	 * @return
	 */
	public static <Item> void iterateCommand (Iterable <Item> items, CollectionCommand <Item> collectionCommand) {
		
		try {
			for (Item item: items) {
				collectionCommand.process (item);
			}
		}
		catch (BreakException e) {
			
			// Exit routine.
		}
	}
	
	/**
	 * Runs a command on every element in a collection (iterable group). The results are returned by the call.
	 * 
	 * @param items
	 * @param collectionCommand
	 * @return
	 */
	public static <Item, Result> Result iterateCommand (Iterable <Item> items, CollectionResultCommand <Item, Result> collectionCommand) {
		
		try {
			for (Item item: items) {
				collectionCommand.process (item);
			}
		}
		catch (BreakException e) {
			
			// Exit routine.
		}
			
		return collectionCommand.getResult();
	}
	
	/**
	 * Calls a method on every element in a collection (iterable group).
	 * 
	 * All classes must be the same type exactly
	 * 
	 * @param items
	 * @param listCommand
	 * @return
	 * @throws InternalException 
	 */
	public static <Item> void iterateMethod (Iterable <Item> items, String methodName) throws ExceptionRuntime {
		
		IsFirst first;
		Method method = null;
		
		first = new IsFirst();
		
		for (Item item: items) {
				
			if (first.isFirst()) {
				try {
					method = item.getClass().getMethod (methodName);
				}
				catch (SecurityException | NoSuchMethodException e) {
					throw new ExceptionRuntime (e);
				}
			}
			
			try {
				method.invoke (item);
			}
			catch (IllegalAccessException | InvocationTargetException e) {
				throw new ExceptionRuntime (e);
			}
		}
	}

	
	/**
	 * Returns the size of a collection.
	 * Null safe. Nulls will return 0.
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> int size (Collection <T> collection) {
		
		if (collection != null) {
			return collection.size();
		}
		else {
			return 0;
		}
	}

	
	/**
	 * Sort a string collection.
	 */
	public static List <String> sort (List <String> list) {

		Collections.sort (list); 
		return null;
	}
	

	/**
	 * Sort a string collection.
	 */
	public static List <String> sort (Collection <String> collection) {

		List <String> list;
		
		list = asList (collection);
		return sort (list); 
	}
	
	

	/**
	 * Sort a string list. Ignores case.
	 */
	public static List <String> sortIgnoreCase (List <String> list) {
		
		Collections.sort (list, getStringComparatorIgnoreCase());
		
		return list;
	}
	
	
	/**
	 * Returns a sorted list of the string collection. Ignores case.
	 * 
	 * @param collection
	 * @return
	 */
	public static List <String> sortIgnoreCase (Collection <String> collection) {
		
		List <String> list;
		
		list = asList (collection);
		return sortIgnoreCase (list); 
	}


	private static Comparator <String> getStringComparatorIgnoreCase() {
		
		Comparator<String> comparator;
		
		comparator = new Comparator<String>() {
		    public int compare (String string1, String string2) {
		    	if (string1 != null) {
		    		return string1.compareToIgnoreCase (string2);
		    	}
		    	else if (string2 != null) {
		    		return string2.compareToIgnoreCase (null);
		    	}
		    	else {
		    		return 0;
		    		
		    	}
		    }
		};
		
		return comparator;
	}
}
