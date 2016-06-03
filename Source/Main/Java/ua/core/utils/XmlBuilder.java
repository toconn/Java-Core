package ua.core.utils;

import java.util.Stack;

// import org.apache.commons.lang3.StringEscapeUtils;


/**
 * Escape not implemented!!!
 * 
 * @author Tadhg
 *
 */
public class XmlBuilder {

	Stack <String>	elementNameStack;	// Tracks elements that have been opened but not closed.
	StringBuilder	xmlBuilder;
	
	
	public XmlBuilder () {
		
		elementNameStack = new Stack <String> ();
		xmlBuilder = new StringBuilder ();
	}
	
	public XmlBuilder (int capacity) {
		
		elementNameStack = new Stack <String> ();
		xmlBuilder = new StringBuilder (capacity);
	}
	
	public XmlBuilder (int capacity, String declaration) {
		
		elementNameStack = new Stack <String> ();
		
		xmlBuilder = new StringBuilder (capacity);
		xmlBuilder.append (declaration);
	}
	
	
	public XmlBuilder (String declaration) {
		
		elementNameStack = new Stack <String> ();
		
		xmlBuilder = new StringBuilder ();
		xmlBuilder.append (declaration);
	}
	
	
	/**
	 * Adds an element.
	 * 
	 * @return
	 */
	public XmlBuilder add (String elementName, int data) {
		
		appendTagOpen (elementName);		
		xmlBuilder.append (Integer.toString (data));
		appendTagClose (elementName);
		
		return this;
	}

	/**
	 * Adds an element.
	 * 
	 * @return
	 */
	public XmlBuilder add (String elementName, long data) {
		
		appendTagOpen (elementName);		
		xmlBuilder.append (Long.toString (data));
		appendTagClose (elementName);
		
		return this;
	}

	/**
	 * Adds an element.
	 * 
	 * @return
	 */
	public XmlBuilder add (String elementName, String data) {
		
		appendTagOpen (elementName);
		
		if (data != null) {
			xmlBuilder.append (data);
		}
		
		appendTagClose (elementName);
		
		return this;
	}

	/**
	 * Adds an element.
	 * 
	 * @return
	 */
	public XmlBuilder addEscaped (String elementName, String data) {
		
		add (elementName, escape (data));
		
		return this;
	}

	/**
	 * Adds an element.
	 * 
	 * @return
	 */
	public XmlBuilder addEscapedNotEmpty (String elementName, String data) {
		
		if (StringUtils.isNotEmpty (data)) {
			add (elementName, escape (data));
		}
		
		return this;
	}

	/**
	 * Adds an element.
	 * 
	 * @return
	 */
	public XmlBuilder addNotEmpty (String elementName, String data) {
		
		if (StringUtils.isNotEmpty (data)) {
			add (elementName, data);
		}
		
		return this;
	}

	/**
	 * Close an element created with the open method.
	 * 
	 * @return
	 */
	public XmlBuilder closeTag () {
		
		appendTagClose (elementNameStack.pop ());
		
		return this;
	}
	
	/**
	 * Creates a new element. It appends the open tag.
	 * 
	 * @return
	 */
	public XmlBuilder openTag (String elementName) {
		
		appendTagOpen (elementName);
		elementNameStack.push (elementName);
	
		return this;
	}
	
	/**
	 * Creates a new element. It appends the open tag.
	 * 
	 * @return
	 */
	public XmlBuilder openTag (String elementName, String attributes) {
		
		appendTagOpen (elementName, attributes);
		elementNameStack.push (elementName);
	
		return this;
	}
	
	/**
	 * Returns the built xml.
	 * 
	 */
	public String toString () {
		
		return xmlBuilder.toString ();
	}
	
	
	private void appendTagClose (String tagName) {
		
		xmlBuilder.append ("</").append (tagName).append (">");
	}

	private void appendTagOpen (String tagName) {
		
		xmlBuilder.append ("<").append (tagName).append (">");
	}
	
	private void appendTagOpen (String tagName, String attributes) {
		
		xmlBuilder.append ("<").append (tagName).append (" ").append (attributes).append (">");
	}
	
	/**
	 * Escapes an xml string.
	 * 
	 * Null safe (returns null).
	 */
	private String escape (String input) {
		
		// TODO - Escape not implemented!!!
		
		return input;
	}	
}
