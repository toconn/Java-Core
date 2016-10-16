package ua.core.utils;

/**
 * This is a command that is to be iterated over a collection and the result returned in the same step.
 * 
 * Use CollectionUtils.iterateCommand();
 * 
 * @author Tadhg
 *
 * @param <Item>
 * @param <Result>
 */
public interface CollectionResultCommand <Item, Result> {
	
	public Result getResult();
	public void process (Item item) throws BreakException;
}
