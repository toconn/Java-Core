package ua.core.base;


public class ExceptionInvalidValue extends ExceptionBase {

	private static final long serialVersionUID = 6322170300529366221L;

	public ExceptionInvalidValue() {

		super();
	}

	public ExceptionInvalidValue (String message) {

		super (message);
	}

	public ExceptionInvalidValue (String message, Object... messageParamArray) {

		super (message, messageParamArray);
	}
}
