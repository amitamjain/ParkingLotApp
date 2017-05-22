package com.myapp.parkinglot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author amita.jain
 *
 *         May 21, 2017
 */
public class Main {
	
	
	public static void main(String[] args) {
		// Initialize Commands, Initialize Messages at application StartUp
		CommandInterpreter commandInterpreter = new CommandInterpreter();
		MessageLoader.intializeMessageLoader();
		ParkingLotManager.intializeParkingLot();
		switch (args.length) {
		case 0:
			MessageLoader.showMessage("welcome.message");
			// Interactive command-line input/output
			// Run an infinite loop until exit.
			for (;;) {
				try {
					BufferedReader bufferRead = new BufferedReader(
							new InputStreamReader(System.in));
					String inputString = bufferRead.readLine();
					if (inputString.equalsIgnoreCase("exit")) {
						break;
					} else if ((inputString == null) || (inputString.isEmpty())) {
						// Do nothing
					} else {
						commandInterpreter.parseTextInput(inputString.trim());
					}
				} catch (IOException e) {
					MessageLoader.showMessage("invalid.input");
					e.printStackTrace();
				}
			}
			break;
		case 1:
			commandInterpreter.parseFileInput(args[0]);
			break;
		default:
			MessageLoader.showMessage("invalid.input.file");
		}
	}
}