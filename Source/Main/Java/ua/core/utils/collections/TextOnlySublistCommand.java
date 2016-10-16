package ua.core.utils.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ua.core.utils.CollectionResultCommand;
import ua.core.utils.CollectionUtils;
import ua.core.utils.NumberUtils;


/**
 * Returns a list of non integer text elements.
 * 
 * A text element is anything that is not an integer.
 */
public class TextOnlySublistCommand implements CollectionResultCommand <String, List<String>> {

	List <String> intList = new ArrayList <String>();
	
	
	@Override
	public List <String> getResult() {
		
		return intList;
	}
	
	@Override
	public void process (String item) {

		if (NumberUtils.isNotPositiveInt (item)) {
			intList.add (item);
		}
	}
	
	public List <String> process (Collection <String> items) {
		
		return CollectionUtils.iterateCommand (items, this);
	}
}