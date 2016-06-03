package ua.core.utils;

// import org.apache.commons.lang3.StringEscapeUtils;


public class XmlUtils {
	
	
	/**
	 * Appends the node to the xml string buffer. It will always add the node regardless of the value.
	 * Null values will be added as empty nodes.
	 * 
	 * the node value is escaped.
	 * 
	 * Ex: appendElement (xmlBuffer, "ElementName", "value") will append "<ElementName>value</ElementName>" to the buffer.
	 */
	public static void appendEscapedElement (StringBuilder xmlBuilder, String nodeName, String value) {
		
		appendElement (xmlBuilder, nodeName, escape (value));
	}
	

	/**
	 * Appends the node to the xmlBuffer if and only if the value is not empty.
	 * Otherwise it will append nothing.
	 * 
	 * The node value is escaped
	 * 
	 * Ex: appendElement (xmlBuffer, "ElementName", "value") will append "<ElementName>value</ElementName>" to the buffer.
	 * 
	 */	public static void appendEscapedNonEmptyElement (StringBuilder xmlBuffer, String nodeName, String value) {
		
		appendNonEmptyElement (xmlBuffer, nodeName, escape (value));
	}

	 
	/**
	 * Appends the node to the xml string buffer. It will always add the node regardless of the value.
	 * Null values will be added as empty nodes.
	 * 
	 * Ex: appendElement (xmlBuffer, "ElementName", "value") will append "<ElementName>value</ElementName>" to the buffer.
	 */
	public static void appendElement (StringBuilder xmlBuilder, String nodeName, String value) {

		xmlBuilder.append ("<").append (nodeName).append (">");
		
		if (value != null) {
			xmlBuilder.append (value);
		}
		
		xmlBuilder.append ("</").append (nodeName).append (">");
	}
	
	/**
	 * Appends the node to the xmlBuffer if and only if the value is not empty.
	 * Otherwise it will append nothing.
	 * 
	 * Ex: appendElement (xmlBuffer, "ElementName", "value") will append "<ElementName>value</ElementName>" to the buffer.
	 * 
	 */
	public static void appendNonEmptyElement (StringBuilder xmlBuilder, String nodeName, String value) {
		
		if (StringUtils.isNotEmpty (value)) {
			appendElement (xmlBuilder, nodeName, value);
		}
	}


	/**
	 * Escapes an xml string.
	 * 
	 * Null safe (returns null).
	 */
	public static String escape (String input) {
		
		// TODO - Escape not implemented!
		
		return input;
	}	
}
