/**
 * 
 */
package com.myapp.parkinglot;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author amita.jain
 *
 * May 21, 2017
 */
public class CommandTest {
	 
	    @Test
	    public void checkCommandLoaded() throws Exception {
	    	Command.loadCommands();	 	    	
	        assertTrue(Command.commandsMap.containsKey("create_parking_lot"));
	        assertFalse(Command.commandsMap.containsKey("randomTest"));
	    }

}
