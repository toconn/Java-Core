package ua.core.base;

import java.util.ArrayList;
import java.util.List;

import ua.core.utils.MessageUtils;
import ua.core.utils.StringUtils;



public abstract class ExceptionBase extends Exception {

	
	private static final long	serialVersionUID = -3138741202946024745L;

	private List <String>		messageList = new ArrayList <String> ();

	
	public ExceptionBase() {

		super();
	}

	public ExceptionBase (String message) {

		super();
		messageList.add (message);
	}

	public ExceptionBase (String message, Object... messageParamArray) {

		super();
		addMessage (message, messageParamArray);
	}

	public ExceptionBase (List <String> messageList) {

		super();
		this.messageList = messageList;
	}
	
	public void addFirstMessage (String message) {

		messageList.add (0, message);
	}
	
	public void addFirstMessage (String message, Object... messageParamArray) {
		
		messageList.add (0, MessageUtils.toString (message, messageParamArray));
	}

	public void addMessage (String message) {

		messageList.add (message);
	}

	public void addMessage (String message, Object... messageParamArray) {
		
		messageList.add (MessageUtils.toString (message, messageParamArray));
	}

	public void addMessageList (List <String> messageList) {

		this.messageList.addAll (messageList);
	}

	public List <String> getMessageList() {

		return messageList;
	}
	
	public String getMessage () {
	
		return StringUtils.join (messageList, " ");
	}
}
