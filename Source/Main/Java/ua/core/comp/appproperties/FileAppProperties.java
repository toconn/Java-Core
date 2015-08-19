package ua.core.comp.appproperties;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ua.core.base.ExceptionItemNotFound;
import ua.core.base.ExceptionRuntime;
import ua.core.base.MessageConst;
import ua.core.utils.StringMapIgnoreCase;
import ua.core.utils.StringUtils;
import ua.core.utils.os.OSConst;

/**
 * Reads property files and stores the data as name value maps.
 * The content of the file is preserved allowing the data to be updated and rewritten in pretty much the same format it was read.
 * Comments are preserved.
 * New values are placed at the end of the file.
 * 
 * @author Tadhg
 *
 */
public class FileAppProperties extends AbstractAppProperties {
		
	public static final String		FILE_ENCODING = "UTF-8";

    private static final String		CHARACTERS_WHITE_SPACE		= " \t\r\n\f";
    private static final String		SEPARATORS_KEY_VALUE		= "= \t\r\n\f";
    private static final String 	SEPARATORS_KEY_VALUE_STRICT	= "=";


	
	private static class ItemProperty {
		
		String name;
		String value;
		String text;

		public ItemProperty (String text) {

			this.name = null;
			this.value = null;
			this.text = text;
		}
		
		public ItemProperty (String name, String value) {

			this.name = name;
			this.value = value;
			this.text = name + "=" + value;
		}
		
		public ItemProperty (String name, String value, String text) {

			this.name = name;
			this.value = value;
			this.text = text;
		}

		public String getName() {
		
			return name;
		}
		
		public String getValue() {
		
			return value;
		}
		
		public String getText() {
		
			return text;
		}
	}
	
		
	private StringMapIgnoreCase <ItemProperty> appPropsMap = new StringMapIgnoreCase <ItemProperty>();
	private LinkedList <ItemProperty> appPropsLinkedList = new LinkedList <ItemProperty>();
	
	@Override
	public Set <String> getKeys() {

		Set <String> keySet;
		
		keySet = new HashSet <String>();
		
		for (String key: appPropsMap.keySet()) {
			keySet.add (appPropsMap.get (key).getName());
		}
		
		return null;
	}
	
	/**
	 * Return a list of all the items that match the string.
	 * 
	 * Inefficient hack (time constraints).
	 * 
	 */

	@Override
	public List <String> getStrings (String name) {
		
		List <String> matchingStrings = new ArrayList<>();
		
		for (ItemProperty item: appPropsLinkedList) {
			
			if (StringUtils.isEqual (name, item.getName())) {
				matchingStrings.add (item.getValue());
			}
		}
		
		return matchingStrings;
	}
	
	
	public void load (String fileName) throws ExceptionItemNotFound, ExceptionRuntime {
		
		try {
			
			load (new FileInputStream (fileName));
		}
		catch (FileNotFoundException e) {
			
			throw new ExceptionItemNotFound (MessageConst.MESSAGE_FILE_NOT_FOUND, fileName);
		}
		catch (IOException e) {

			throw new ExceptionRuntime (e);
		}
	}

	public void load (InputStream inputStream) throws IOException {
	
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		BufferedReader	inputReader		= null;
		
		String			inputLine		= null;

		int				inputLineLength;
		int				keyIndex;
		int				separatorIndex;
		int				valueIndex;
	
		char			firstChar;
		char			currentChar;
		
		String			propertyName	= null;
		String			propertyValue	= null;
		
		boolean			lineAdded;
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		
		inputReader	= new BufferedReader (new InputStreamReader (inputStream, FILE_ENCODING));
		
		do {
			
	        // Get next line...
			
			inputLine = inputReader.readLine();
			lineAdded = false;
			
	        if (inputLine != null && inputLine.length() > 0) {
	        	
	            // Continue lines that end in slashes if they are not comments
	        	
	            firstChar = inputLine.charAt(0);
	            
	            if ((firstChar != '#') && (firstChar != '!')) {
	
	                // Find start of key
	                
	                inputLineLength = inputLine.length();
	
	                for (keyIndex = 0; keyIndex < inputLineLength; keyIndex++) {
	                	
	                    if (CHARACTERS_WHITE_SPACE.indexOf (inputLine.charAt (keyIndex)) == -1) {
	                    	
	                        break;
	                    }
	                }
	
	                // Blank lines are ignored...
	                
	                if (keyIndex == inputLineLength) {
	                	
	                    continue;
	                }
	
	                // Find separation between key and value...
	
	                for (separatorIndex = keyIndex; separatorIndex < inputLineLength; separatorIndex++) {
	                	
	                    currentChar = inputLine.charAt (separatorIndex);
	                    
	                    if (currentChar == '\\') {
	                    	
	                        separatorIndex++;
	                    }
	                    else if (SEPARATORS_KEY_VALUE.indexOf (currentChar) != -1) {
	                    	
	                        break;
	                    }
	                }
	
	                // Skip over whitespace after key if any
	
	                for (valueIndex=separatorIndex; valueIndex<inputLineLength; valueIndex++) {
	                	
	                    if (CHARACTERS_WHITE_SPACE.indexOf (inputLine.charAt(valueIndex)) == -1) {
	                    	
	                        break;
	                    }
	                }
	                
	                // Skip over one non whitespace key value separators if any...
	                    
	                if (valueIndex < inputLineLength) {
	                	
	                    if (SEPARATORS_KEY_VALUE_STRICT.indexOf (inputLine.charAt(valueIndex)) != -1) {
	                    	
	                        valueIndex++;
	                    }
	                }
	
	                // Skip over white space after other separators if any...
	                    
	                while (valueIndex < inputLineLength) {
	                	
	                    if (CHARACTERS_WHITE_SPACE.indexOf (inputLine.charAt (valueIndex)) == -1) {
	                    	
	                        break;
	                    }
	                    
	                    valueIndex++;
	                }
	
	                propertyName	= inputLine.substring (keyIndex, separatorIndex);
	                propertyValue	= (separatorIndex < inputLineLength) ? inputLine.substring (valueIndex, inputLineLength) : "";
	
	                // Convert key and value...
	                
	                // propertyName	= convertEscapedCharacters (propertyName);
	                // propertyValue = convertEscapedCharacters (propertyValue);
	                
	                setItem (propertyName, propertyValue, inputLine);
	                lineAdded = true;
	            }
	        }
	        
	        if (! lineAdded) {
	        	setNonItem (inputLine);
	        }
		}
		while (inputLine != null);
	}
	
	
	public void save (String fileName) throws ExceptionRuntime {
		
		try {
			
			save (new FileOutputStream (fileName));
		}
		catch (IOException e) {
			
			throw new ExceptionRuntime (e);
		}
	}

	
	
	public void save (OutputStream outputStream) throws IOException {
		
		byte[] textBytes;
		byte[] newLineBytes;
		
		newLineBytes = OSConst.WINDOWS_NEW_LINE.getBytes();
	
		for (ItemProperty item: appPropsLinkedList) {
			
			textBytes = item.getText().getBytes();
			outputStream.write (textBytes);
			outputStream.write (newLineBytes);
		}

		outputStream.flush();
		outputStream.close();		
	}


	@Override
	String getItem (String name) {

		ItemProperty item;
		
		item = appPropsMap.get (name);
		
		if (item != null) {
			return item.getValue();
		}
		else {
			return null;
		}
	}

	@Override
	boolean hasItem (String name) {

		return appPropsMap.containsKey (name);
	}

	/**
	 * Store normal name and matching value.
	 * 
	 */
	@Override
	void setItem (String name, String value) {

		ItemProperty newItem;
		ItemProperty currentItem;
		
		newItem = new ItemProperty (name, value);
		
		if (! hasItem (name)) {
			appPropsMap.add (name, newItem);
			appPropsLinkedList.add (newItem);
		}
		else {
			appPropsMap.add (name, newItem);
			currentItem = appPropsMap.get (name);			
			appPropsLinkedList.set (appPropsLinkedList.indexOf (currentItem), newItem);
		}
	}
	
	/**
	 * Stores the name, value and original format of the line.
	 * Formatting is preserved so that the file is recreated pretty much as the user wanted it.
	 * 
	 * @param name
	 * @param value
	 * @param text
	 */
	void setItem (String name, String value, String text) {

		ItemProperty newItem;
		
		newItem = new ItemProperty (name, value, text);
		appPropsMap.add (name, newItem);
		appPropsLinkedList.add (newItem);
	}
	
	/**
	 * Store a not property line.
	 * 
	 * Stored to that if the file is written out, comments and spacing can be preserved.
	 * 	
	 * @param text
	 */
	void setNonItem (String text) {

		ItemProperty newItem;
		
		newItem = new ItemProperty (text);
		appPropsLinkedList.add (newItem);		
	}
}