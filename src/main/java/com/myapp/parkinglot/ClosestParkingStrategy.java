/**
 * 
 */
package com.myapp.parkinglot;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author amita.jain
 *
 * May 22, 2017
 */
public class ClosestParkingStrategy implements ParkingStrategy {

	/* (non-Javadoc)
	 * @see com.myapp.parkinglot.ParkingStrategy#park(java.util.ArrayList)
	 */
	@Override
	public int getAvailableSlot(ArrayList<Integer> availableSlotList) {
		Collections.sort(availableSlotList);
		int slot = availableSlotList.get(0);
		availableSlotList.remove(0);
		return slot;
		
	}

}
