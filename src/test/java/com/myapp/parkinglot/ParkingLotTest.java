/**
 * 
 */
package com.myapp.parkinglot;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;

import java.io.PrintStream;

import org.junit.Before;

import java.io.ByteArrayOutputStream;

/**
 * @author amita.jain
 *
 * May 21, 2017
 */
public class ParkingLotTest { ParkingLot parkingLot = new ParkingLot();
private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
@Before
public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
}

@After
public void cleanUpStreams() {
    System.setOut(null);
}
@Test
public void createParkingLot() throws Exception {
    parkingLot.createParkingLot("6");
    assertEquals(6, parkingLot.MAX_PARKING_SPACE);
    assertEquals(6, parkingLot.availableSlotList.size());
    assertTrue("Createdparkinglotwith6spaces".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
}

@Test
public void park() throws Exception {
    parkingLot.park("KA-01-HH-1234", "White");   
    assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
    parkingLot.createParkingLot("6");
    parkingLot.park("KA-01-HH-1234", "White");
    parkingLot.park("KA-01-HH-9999", "White");
    assertEquals(4, parkingLot.availableSlotList.size());
}

@Test
public void leave() throws Exception {
    parkingLot.leave("2");
    assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
    parkingLot.createParkingLot("6");
    parkingLot.park("KA-01-HH-1234", "White");
    parkingLot.park("KA-01-HH-9999", "White");
    parkingLot.leave("4");
    assertEquals("Sorry,parkinglotisnotcreated\r\n" +
            "\r\n" +
            "Createdparkinglotwith6spaces\r\n" +
            "\r\n" +
            "Allocatedslotnumber:1\r\n" +
            "\r\n" +
            "Allocatedslotnumber:2\r\n" +
            "\r\n" +
            "Slotnumber4isalreadyempty", outContent.toString().trim().replace(" ", ""));
}

@Test
public void status() throws Exception {
    parkingLot.status();
    assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
    parkingLot.createParkingLot("6");
    parkingLot.park("KA-01-HH-1234", "White");
    parkingLot.park("KA-01-HH-9999", "White");
    parkingLot.status();
    assertEquals("Sorry,parkinglotisnotcreated\r\n\r" +
            "\n" +
            "Createdparkinglotwith6spaces\r\n\r" +
            "\n" +
            "Allocatedslotnumber:1\r\n\r" +
            "\n" +
            "Allocatedslotnumber:2\r\n\r" +
            "\n" +
            "SlotNo.\tRegistrationNo.\tColor\r\n" +
            "1\tKA-01-HH-1234\tWhite\r\n" +
            "2\tKA-01-HH-9999\tWhite", outContent.toString().trim().replace(" ", ""));
}

@Test
public void getRegistrationNumbersFromColor() throws Exception {
    parkingLot.getRegistrationNumbersFromColor("White");
    assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
    parkingLot.createParkingLot("6");
    parkingLot.park("KA-01-HH-1234", "White");
    parkingLot.park("KA-01-HH-9999", "White");
    parkingLot.getRegistrationNumbersFromColor("White");
    assertEquals("Sorry,parkinglotisnotcreated\r\n" +
            "\r\n" +
            "Createdparkinglotwith6spaces\r\n" +
            "\r\n" +
            "Allocatedslotnumber:1\r\n" +
            "\r\n" +
            "Allocatedslotnumber:2\r\n" +
            "\r\n" +
            "\r\n" +
            "KA-01-HH-1234,KA-01-HH-9999", outContent.toString().trim().replace(" ", ""));
    parkingLot.getRegistrationNumbersFromColor("Red");
    assertEquals("Sorry,parkinglotisnotcreated\r\n" +
            "\r\n" +
            "Createdparkinglotwith6spaces\r\n" +
            "\r\n" +
            "Allocatedslotnumber:1\r\n" +
            "\r\n" +
            "Allocatedslotnumber:2\r\n" +
            "\r\n" +
            "\r\n" +
            "KA-01-HH-1234,KA-01-HH-9999Notfound", outContent.toString().trim().replace(" ", ""));
}

@Test
public void getSlotNumbersFromColor() throws Exception {
    parkingLot.getSlotNumbersFromColor("White");
    assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
    parkingLot.createParkingLot("6");
    parkingLot.park("KA-01-HH-1234", "White");
    parkingLot.park("KA-01-HH-9999", "White");
    parkingLot.getSlotNumbersFromColor("White");
    assertEquals("Sorry,parkinglotisnotcreated\r\n" +
            "\r\n" +
            "Createdparkinglotwith6spaces\r\n" +
            "\r\n" +
            "Allocatedslotnumber:1\r\n" +
            "\r\n" +
            "Allocatedslotnumber:2\r\n" +
            "\r\n" +
            "\r\n" +
            "1,2", outContent.toString().trim().replace(" ", ""));
    parkingLot.getSlotNumbersFromColor("Red");
    assertEquals("Sorry,parkinglotisnotcreated\r\n" +
            "\r\n" +
            "Createdparkinglotwith6spaces\r\n" +
            "\r\n" +
            "Allocatedslotnumber:1\r\n" +
            "\r\n" +
            "Allocatedslotnumber:2\r\n" +
            "\r\n" +
            "\r\n" +
            "1,2\r\n" +
            "Notfound", outContent.toString().trim().replace(" ", ""));
}

@Test
public void getSlotNumberFromRegNo() throws Exception {
    parkingLot.getSlotNumberFromRegistrationNumber("KA-01-HH-1234");
    assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
    parkingLot.createParkingLot("6");
    parkingLot.park("KA-01-HH-1234", "White");
    parkingLot.park("KA-01-HH-9999", "White");
    parkingLot.getSlotNumberFromRegistrationNumber("KA-01-HH-1234");
    assertEquals("Sorry,parkinglotisnotcreated\r\n" +
            "\r\n" +
            "Createdparkinglotwith6spaces\r\n" +
            "\r\n" +
            "Allocatedslotnumber:1\r\n" +
            "\r\n" +
            "Allocatedslotnumber:2\r\n" +
            "\r\n" +
            "1", outContent.toString().trim().replace(" ", ""));
    parkingLot.getSlotNumberFromRegistrationNumber("KA-01-HH-9999");
    assertEquals("Sorry,parkinglotisnotcreated\r\n" +
            "\r\n" +
            "Createdparkinglotwith6spaces\r\n" +
            "\r\n" +
            "Allocatedslotnumber:1\r\n" +
            "\r\n" +
            "Allocatedslotnumber:2\r\n" +
            "\r\n" +
            "1\r\n" +
            "2", outContent.toString().trim().replace(" ", ""));
    parkingLot.leave("1");
    parkingLot.getSlotNumberFromRegistrationNumber("KA-01-HH-1234");
    assertEquals("Sorry,parkinglotisnotcreated\r\n" +
            "\r\n" +
            "Createdparkinglotwith6spaces\r\n" +
            "\r\n" +
            "Allocatedslotnumber:1\r\n" +
            "\r\n" +
            "Allocatedslotnumber:2\r\n" +
            "\r\n" +
            "1\r\n" +
            "2\r\n" +
            "Slotnumber1isfree\r\n" +
            "\r\n" +
            "Notfound", outContent.toString().trim().replace(" ", ""));
}
	

}
