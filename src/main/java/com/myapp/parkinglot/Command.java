package com.myapp.parkinglot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author amita.jain
 *
 *         May 21, 2017
 */
public class Command {
	public static Map<String, Method> commandsMap;
	private static Command command = null;
	private static String COMMAND_PROPERTIES = "command.properties";
	
	public static void loadCommands(){
		if(command == null){
			command = new Command();
		}
		
	}

	private Command() {
		
		commandsMap = new HashMap<String, Method>();
		Properties prop = new Properties();
		InputStream input = null;
		
		try {

			input = getClass().getClassLoader().getResourceAsStream(COMMAND_PROPERTIES);
			if (input == null) {
				MessageLoader.showMessage("file.load.error"); 
				System.out.println(COMMAND_PROPERTIES);
				return;
			}

			// load a properties file
			prop.load(input);
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String commandName = (String) e.nextElement();
				String[] commandValues = prop.getProperty(commandName).split(
						",");
				String commandMethod = commandValues[0];
				Class<?>[] params = new Class<?>[commandValues.length - 1];
				if (commandValues.length > 1) {
					for (int i = 0; i < commandValues.length - 1; i++) {
						Class<?> cls = Class.forName(commandValues[i + 1]);
						params[i] = cls;
					}
					commandsMap.put(commandName,
							ParkingLot.class.getMethod(commandMethod, params));
				} else
					commandsMap.put(commandName,
							ParkingLot.class.getMethod(commandMethod));
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}