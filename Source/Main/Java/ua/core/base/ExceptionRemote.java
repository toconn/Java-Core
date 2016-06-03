package ua.core.base;

public class ExceptionRemote extends ExceptionBaseRuntime {

	private static final long serialVersionUID = -8941077157542797278L;

	public ExceptionRemote (Exception e) {
		
		super (e);
	}
	
	public ExceptionRemote (String message) {
		
		super (message);
	}

	public ExceptionRemote (String message, Exception e) {
		
		super (message, e);
	}
}
