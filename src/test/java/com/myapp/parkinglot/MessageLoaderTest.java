/**
 * 
 */
package com.myapp.parkinglot;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author amita.jain
 *
 * May 21, 2017
 */
public class MessageLoaderTest {
	
	
	@Test
    public void checkMessagesInitliazed() throws Exception {
    	MessageLoader.intializeMessageLoader();
        assertTrue(MessageLoader.messagesMap.containsKey("error.reading.file"));
        assertTrue(MessageLoader.messagesMap.containsKey("invalid.parking.space"));
    }
	
	@Test
    public void checkgetMessage() throws Exception {
		//when Messages not loaded
    	String msg = MessageLoader.getMessage("invalid.parking.space");
        assertTrue(msg!=null);
    }
	
	
	

}
