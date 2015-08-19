package ua.core.utils;


public class RandomUtils {
	
	/**
	 * Returns a random number between 0 and max inclusive.
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomInt (int max) {
		return (int) (Math.random() * (max + 1));
	}
	
	/**
	 * Returns a random number between min and max inclusive.
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomInt (int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}
}
