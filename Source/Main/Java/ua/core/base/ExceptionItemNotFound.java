package ua.core.base;


public class ExceptionItemNotFound extends ExceptionBase {

	
	private static final long serialVersionUID = -6216136454678273600L;

	
	public ExceptionItemNotFound (String message) {

		super (message);
	}
	
	public ExceptionItemNotFound (String message, Object... messageParamArray) {

		super (message, messageParamArray);
	}
}
