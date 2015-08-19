package ua.core.base;

public class ExceptionHandled extends ExceptionBaseRuntime {

	private static final long serialVersionUID = -6216136454678273600L;


	public ExceptionHandled (String message) {

		super (message);
	}


	public ExceptionHandled (String message, Object... messageParamArray) {
		
		super(message, messageParamArray);
	}
}
