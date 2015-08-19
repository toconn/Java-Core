package ua.core.utils.system;


public class SystemUtils {

	public static void sleep (long milliseconds) {
		
		try {
			
			Thread.sleep (milliseconds);
		}
		catch (InterruptedException e) {

		}
		
	}
}
