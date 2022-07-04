package tech.kappke.lila.bot.utils;

/**
 * This class defines many utilities for working with objects,
 * such as getting improved readability and performance.
 * 
 * @author ytsol
 * @since 0.0.1
 */
public class ObjectUtils
{
	/**
	 * This method provides a way to improve code readability when trying to
	 * get an object when the first one was null
	 * 
	 * @param <T> implicit type of the provided objects
	 * @param obj the main object
	 * @param other the other object
	 * @return the not null object
	 */
	public static <T> T getIfNotNull(T obj, T other)
	{
		return obj != null ? obj : other;
	}	
}
