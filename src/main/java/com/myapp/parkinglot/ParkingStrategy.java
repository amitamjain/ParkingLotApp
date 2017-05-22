/**
 * 
 */
package com.myapp.parkinglot;

import java.util.ArrayList;

/**
 * @author amita.jain
 *
 * May 22, 2017
 */
public interface ParkingStrategy {
	

	
	public int getAvailableSlot(ArrayList<Integer> availableSlotList);

}