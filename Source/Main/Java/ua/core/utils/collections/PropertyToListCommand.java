package ua.core.utils.collections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ua.core.base.ExceptionRuntime;
import ua.core.utils.CollectionResultCommand;


public class PropertyToListCommand <Item, Result> implements CollectionResultCommand <Item, List <Result>> {

	private List <Result> resultList = null;
	private Method method = null;
	
	public PropertyToListCommand (int listSize, Class <Item> class1, String methodName) {
		
		try {
			method = class1.getMethod (methodName);
			resultList = new ArrayList <Result> (listSize);
		}
		catch (NoSuchMethodException | SecurityException e) {
			throw new ExceptionRuntime (e);
		}
	}

	@Override
	public List <Result> getResult () {

		return resultList;
	}

	@SuppressWarnings ("unchecked")
	@Override
	public void process (Item item) {

		try {
			resultList.add ((Result) method.invoke (item));
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ExceptionRuntime (e);
		}
	}
}
