package ua.core.base;


public class ExceptionInvalidConfiguration extends ExceptionBase {

	private static final long serialVersionUID = 6322170300529366221L;

	public ExceptionInvalidConfiguration (String message) {

		super (message);
	}

	public ExceptionInvalidConfiguration (String message, Object... messageParamArray) {

		super (message, messageParamArray);
	}
}
