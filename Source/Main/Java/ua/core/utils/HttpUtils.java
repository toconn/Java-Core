package ua.core.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import ua.core.base.ExceptionRemote;
import ua.core.base.ExceptionRuntime;

public class HttpUtils {
	
	public static final int HTTP_RESPONSE_OK = 200;
	
	public static String sendDelete (String urlString) throws ExceptionRuntime, ExceptionRemote {

		HttpURLConnection	httpConnection = null;

		httpConnection = newHttpConnection (urlString, "DELETE");
		return connectRead (httpConnection);
	}
	
	public static String sendGet (String urlString) throws ExceptionRuntime, ExceptionRemote {

		HttpURLConnection	httpConnection = null;

		httpConnection = newHttpConnection (urlString, "GET");
		return connectRead (httpConnection);
	}
	
	public static String sendPost (String urlString, String requestData) throws ExceptionRuntime, ExceptionRemote {
		
		HttpURLConnection	httpConnection = null;

		httpConnection = newHttpConnection (urlString, "POST");
		return connectWriteRead (httpConnection, requestData);
	}
	
	
	
	private static String connectRead (HttpURLConnection httpConnnection) throws ExceptionRemote, ExceptionRuntime {
		
		String response;
		
		try {
			
			httpConnnection.connect();
			response = readResponse (httpConnnection);
			validateStatus (httpConnnection);

			return response;
		}
		catch (IOException e) {
	
			throw new ExceptionRemote ("Unable to make http request.", e);
		}
		catch (Exception e) {
			
			throw new ExceptionRuntime (e);
		}
		finally {
			
			if (httpConnnection != null) {
				httpConnnection.disconnect();
			}
		}
	}
	
	private static String connectWriteRead (HttpURLConnection httpConnection, String requestData) throws ExceptionRemote, ExceptionRuntime {
		
		byte[]	dataBytes;
		String	response;
		
		try {
			
			dataBytes = requestData.getBytes (StandardCharsets.UTF_8);

			httpConnection.setDoOutput(true);										// Required to send data.
			httpConnection.setRequestProperty ("charset", "utf-8");
			httpConnection.setRequestProperty ("Content-Length", Integer.toString (dataBytes.length));

			httpConnection.connect();
			
			try	(DataOutputStream dataOutputStream = new DataOutputStream (httpConnection.getOutputStream())) {
				dataOutputStream.write (dataBytes);
			}
			
			response = readResponse (httpConnection);
			
			validateStatus (httpConnection);

			return response;
		}
		catch (IOException e) {
	
			throw new ExceptionRemote ("Unable to make http request.", e);
		}
		catch (Exception e) {
			
			throw new ExceptionRuntime (e);
		}
		finally {
			
			if (httpConnection != null) {
				httpConnection.disconnect();
			}
		}
	}
	
	private static HttpURLConnection newHttpConnection (String urlString, String httpMethod) throws ExceptionRemote {
		
		URL					url;
		HttpURLConnection	httpConnection = null;

		try {
			
			url = new URL (urlString);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod (httpMethod);
		
			return httpConnection;
		}
		catch (IOException e) {
			
			throw new ExceptionRemote ("Unable to setup http request.", e);
		}
	}
	
	private static String readResponse (HttpURLConnection httpConnection) throws IOException {
		
		String				line;
		StringBuilder		stringBuilder;
		BufferedReader		reader = null;
		
		stringBuilder = new StringBuilder();
		
		reader = new BufferedReader (new InputStreamReader (httpConnection.getInputStream()));	
		
		line = null;
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line + "\n");
		}
		
		return stringBuilder.toString();
	}
	
	private static void validateStatus (HttpURLConnection httpConnection) throws ExceptionRemote, IOException {
		
		if (httpConnection.getResponseCode() != HTTP_RESPONSE_OK) {
			throw new ExceptionRemote ("Http GET request faied: " + httpConnection.getResponseCode() + " " + httpConnection.getResponseMessage());
		}
	}
}
