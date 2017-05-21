/**
 * 
 */
package com.myapp.parkinglot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author amita.jain
 *
 *         May 21, 2017
 */
public class MessageLoader {
	private static MessageLoader messageLoader = null;
	public static HashMap<String, String> messagesMap = null;
	public static String MESSAGES_FILENAME = "message.properties";
	
	
	public static void intializeMessageLoader(){
		if(messageLoader==null){
			messageLoader = new MessageLoader();
		}
	}
	

	/**
	 * Loading messages for application scope.
	 */
	private MessageLoader() {
		// TODO Auto-generated constructor stub
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = getClass().getClassLoader().getResourceAsStream(
					MESSAGES_FILENAME);
			if (input == null) {
				System.out
						.println("Sorry, unable to find " + MESSAGES_FILENAME);
				return;
			}
			// load a properties file

			prop.load(input);
			messagesMap = new HashMap<String, String>();
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String messageKey = (String) e.nextElement();
				String messageValue = prop.getProperty(messageKey);
				messagesMap.put(messageKey, messageValue);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					System.out.println("Error Loading Message Properties");
					e.printStackTrace();
				}
			}
		}
	}

	public static String getMessage(String key) {
		if(messageLoader==null){
			messageLoader = new MessageLoader();
		}
		if (messagesMap != null) {
			String message = messagesMap.get(key);
			return message;
		} else {
			return null;
		}
	}
	
	public static void showMessage(String key){
		if(messageLoader==null){
			messageLoader = new MessageLoader();
		}
		if (messagesMap != null) {
			String message = messagesMap.get(key);
			System.out.println(message);
		} 
	}

}
