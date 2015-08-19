package ua.core.beans;

import ua.core.utils.StringMapIgnoreCase;


public enum BooleanType {
	
	TRUE		("True", true),
	FALSE		("False", false);
	
	public static final String[] BOOLEAN_FALSE_STRING_ARRAY = {"false", "f", "no", "n", "0"}; 
	public static final String[] BOOLEAN_TRUE_STRING_ARRAY = {"true", "t", "yes", "y", "1", "-1"}; 

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// STATIC PROPERTIES AND CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// MAPPING HANDLERS:
	
	private static final StringMapIgnoreCase <BooleanType>	LOOKUP_MAP	= new StringMapIgnoreCase <BooleanType> ();

	
	static {
		
		for (String value : BOOLEAN_FALSE_STRING_ARRAY) {		
			LOOKUP_MAP.put (value, FALSE);
		}

		for (String value : BOOLEAN_TRUE_STRING_ARRAY) {		
			LOOKUP_MAP.put (value, TRUE);
		}
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// STATIC METHODS
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Returns the BooleanType for the given code or description.
	 * 
	 * If the key is not found, it will return null.
	 * 
	 * @param code / description.
	 * 
	 * @return FeeCode of the code.
	 */
	public static BooleanType getInstance (String code) {
		
		if (LOOKUP_MAP.containsKey (code)) {
			
			return LOOKUP_MAP.get (code);
		}
		else {
			
			return null;
		}
	}

	public static BooleanType getInstance (boolean value) {
		
		if (value) {
			
			return TRUE;
		}
		else {
			
			return FALSE;
		}
	}

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// INSTANCE PROPERTIES AND METHODS
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private String code;
	private boolean value;
	
	
	private BooleanType (String code, Boolean value) {
		
		this.code = code;
		this.value = value;
	}
	
	
	public String getCode () {
		
		return this.code;
	}
	
	public boolean getValue () {
		
		return this.value;
	}
	
	public String toString () {
		
		return this.code;
	}
}
