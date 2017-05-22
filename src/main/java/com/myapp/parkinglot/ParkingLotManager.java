/**
 * 
 */
package com.myapp.parkinglot;


/**
 * @author amita.jain
 *
 * May 22, 2017
 */
public class ParkingLotManager {
	
	private static ParkingLot parkingLot;
	
	
	public static ParkingLot intializeParkingLot(){
		parkingLot = new ParkingLot(new ClosestParkingStrategy());
		return parkingLot;
	}
	
	public static ParkingLot getParkingLot(){
		return parkingLot;
	}

}
