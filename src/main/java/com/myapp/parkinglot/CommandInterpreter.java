package com.myapp.parkinglot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * @author amita.jain
 *
 *         May 21, 2017
 */
public class CommandInterpreter {
	
	static ParkingLot parkingLot;
	

	public CommandInterpreter() {
		Command.loadCommands();
		parkingLot = new ParkingLot();
	}

	public void parseTextInput(String inputString) {
		// Split the input string to get command and input value
		String[] inputs = inputString.split(" ");
		try {
			if (inputs.length == 0) {
				MessageLoader.showMessage("invalid.input");
			} else if (inputs.length == 1) {
				Method method = Command.commandsMap.get(inputString);
				if (method != null) {
					method.invoke(parkingLot);
				} else {
					MessageLoader.showMessage("invalid.input");
				}
			} else if (inputs.length > 1) {
				Object[] methodParams = new Object[inputs.length - 1];
				int firstIndexOfParam = 1;
				for (int i = 0; i < inputs.length - 1; i++) {
					methodParams[i] = inputs[firstIndexOfParam];
					firstIndexOfParam++;
				}
				Method method = Command.commandsMap.get(inputs[0]);
				if (method != null) {
					method.invoke(parkingLot, methodParams);
				} else {
					MessageLoader.showMessage("invalid.input");
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void parseFileInput(String filePath) {
		// Assuming input to be a valid file path.
		File inputFile = new File(filePath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line;
			try {
				while ((line = br.readLine()) != null) {
					parseTextInput(line.trim());
				}
			} catch (IOException ex) {
				MessageLoader.showMessage("error.reading.file");
				ex.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			MessageLoader.showMessage("error.reading.file");
			e.printStackTrace();
		}
	}
}
