package ua.core.utils;

import java.util.ArrayList;
import java.util.List;

import ua.core.beans.NameValuePair;


public class UrlBuilder {
	
	private String url = null;
	private String subUrl = null;
	private List<NameValuePair> paramList = new ArrayList <NameValuePair> ();

	public UrlBuilder url (String baseUrl) { this.url = baseUrl; return this; }
	public UrlBuilder subUrl (String subUrl) { this.subUrl = subUrl; return this; }
	public UrlBuilder param (String name, String value) { this.paramList.add (new NameValuePair (name, value)); return this; }
	public UrlBuilder param (String value) { return param (null, value); }

	public String build() {
		
		IsFirst first;
		StringBuilder urlStringBuilder;
		
		
		// Build Url:
		
		urlStringBuilder = new StringBuilder ();
		
		urlStringBuilder.append (url);
		
		if (subUrl != null) {
			urlStringBuilder.append (subUrl);
		}
		
		
		// Add Parameters:
		
		first = new IsFirst();
		
		for (NameValuePair nvpair : paramList) {
			
			if (first.isFirst()) {
				urlStringBuilder.append ("?");
			}
			else {
				urlStringBuilder.append ("&");
			}
			
			if (nvpair.getName() != null) {
				urlStringBuilder.append (nvpair.getName()).append ("=");
			}
			
			urlStringBuilder.append (nvpair.getValue());
		}
		
		return urlStringBuilder.toString();
	}

}
