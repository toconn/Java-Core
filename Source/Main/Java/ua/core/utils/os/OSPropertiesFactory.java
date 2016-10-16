package ua.core.utils.os;

import ua.core.utils.system.JavaUtils;


public class OSPropertiesFactory {

	// Singleton Static Parts:
	
	private volatile static OSProperties instance;
	
	public static OSProperties getInstance() {
		
		// Lazy Initialize
		// Double Checked Locking:
		
		if (instance == null) {
		
			synchronized (OSProperties.class) {
				
				if (instance == null) {

					OSProperties newInstance = new DefaultOSProperties (JavaUtils.getOSName(), JavaUtils.getFileSeparator(), JavaUtils.getLineSeparator(), JavaUtils.getPathSeparator());
					instance = newInstance;
				}
			}
		}
		
		return instance;
	}
}
