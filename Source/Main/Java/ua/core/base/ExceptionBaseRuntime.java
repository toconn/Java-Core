package ua.core.base;

import java.util.ArrayList;
import java.util.List;

import ua.core.utils.MessageUtils;



public abstract class ExceptionBaseRuntime extends RuntimeException {

	private static final long	serialVersionUID = -3138741202946024745L;

	private List <String>		messageList = new ArrayList <String> ();

	
	public ExceptionBaseRuntime() {

		super();
	}
	
	public ExceptionBaseRuntime (Exception e) {
		
		super (e);
		messageList.add (e.getMessage());
	}

	public ExceptionBaseRuntime (String message) {

		super();
		messageList.add (message);
	}

	public ExceptionBaseRuntime (String message, Object... messageParamArray) {

		super();
		addMessage (message, messageParamArray);
	}

	public ExceptionBaseRuntime (List <String> messageList) {

		super();
		this.messageList = messageList;
	}

	
	public void addFirstMessage (String message) {

		messageList.add (0, message);
	}
	
	public void addFirstMessage (String message, Object... messageParamArray) {
		
		messageList.add (0, MessageUtils.toString(message, messageParamArray));
	}

	public void addMessage (String message) {

		messageList.add (message);
	}

	public void addMessage (String message, Object... messageParamArray) {
		
		messageList.add (MessageUtils.toString(message, messageParamArray));
	}

	public void addMessageList (List <String> messageList) {

		this.messageList.addAll (messageList);
	}

	public List <String> getMessageList() {

		return messageList;
	}
}
