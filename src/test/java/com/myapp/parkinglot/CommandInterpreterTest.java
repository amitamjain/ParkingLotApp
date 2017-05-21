/**
 * 
 */
package com.myapp.parkinglot;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author amita.jain
 *
 * May 21, 2017
 */
public class CommandInterpreterTest {
	
	CommandInterpreter commandInterpreter = new CommandInterpreter();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUpStreams() {
    	MessageLoader.intializeMessageLoader();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    @Test
    public void parseTextInput() throws Exception {
    	commandInterpreter.parseTextInput("hello");
        assertEquals("Sorry!Errorinreadingtheinput", outContent.toString().trim().replace(" ", ""));
        
    }

}
