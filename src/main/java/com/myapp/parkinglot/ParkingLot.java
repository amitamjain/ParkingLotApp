package com.myapp.parkinglot;


import java.util.*;
/**
 * @author amita.jain
 *
 *         May 21, 2017
 */
public class ParkingLot {
    int maxSlots = 0;
    
    private ParkingStrategy parkingStrategy;
    
    // Available slots list
    ArrayList<Integer> availableSlotList;
    // Map of Slot, Car
    Map<String, Car> slotCarMap;
    // Map of registrationNumber, Slot
    Map<String, String> regSlotMap;
    // Map of Color, List of registrationNumber
    Map<String, ArrayList<String>> colorRegMap;
    

    public ParkingLot(ParkingStrategy parkingStrategy){
    	this.parkingStrategy = parkingStrategy;
    }

    public void createSlot(String maxParkingSpace) {
        try {
            this.maxSlots = Integer.parseInt(maxParkingSpace);
        } catch (Exception e) {
            System.out.println(MessageLoader.getMessage("invalid.parking.space"));
        }
        createAvailableSlots();
        this.slotCarMap = new HashMap<String, Car>();
        this.regSlotMap = new HashMap<String, String>();
        this.colorRegMap = new HashMap<String, ArrayList<String>>();
        System.out.println("Created parking lot with " + maxParkingSpace + " spaces");
        System.out.println();
    }
	/**
	 * 
	 */
	private void createAvailableSlots() {
		this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.maxSlots; i++) {
            availableSlotList.add(i);
        }
	}
    public void park(String registrationNumber, String color) {
        if (this.maxSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.slotCarMap.size() == this.maxSlots) {
            System.out.println("Sorry, parking lot is full");
            System.out.println();
        } else {
            String slot = String.valueOf(parkingStrategy.getAvailableSlot(availableSlotList));
            Car car = new Car(registrationNumber, color);
            this.slotCarMap.put(slot, car);
            this.regSlotMap.put(registrationNumber, slot);
            if (this.colorRegMap.containsKey(color)) {
                ArrayList<String> registrationNumberList = this.colorRegMap.get(color);
                this.colorRegMap.remove(color);
                registrationNumberList.add(registrationNumber);
                this.colorRegMap.put(color, registrationNumberList);
            } else {
                ArrayList<String> registrationNumberList = new ArrayList<String>();
                registrationNumberList.add(registrationNumber);
                this.colorRegMap.put(color, registrationNumberList);
            }
            System.out.println("Allocated slot number: " + slot);
            System.out.println();
        }
    }
    public void leave(String slotNo) {
        if (this.maxSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.slotCarMap.size() > 0) {
            Car carToLeave = this.slotCarMap.get(slotNo);
            if (carToLeave != null) {
                this.slotCarMap.remove(slotNo);
                this.regSlotMap.remove(carToLeave.registrationNumber);
                ArrayList<String> registrationNumberList = this.colorRegMap.get(carToLeave.color);
                if (registrationNumberList.contains(carToLeave.registrationNumber)) {
                    registrationNumberList.remove(carToLeave.registrationNumber);
                }
                // Add the Lot No. back to available slot list.
                this.availableSlotList.add(Integer.parseInt(slotNo));
                System.out.println("Slot number " + slotNo + " is free");
                System.out.println();
            } else {
                System.out.println("Slot number " + slotNo + " is already empty");
                System.out.println();
            }
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }
    public void status() {
        if (this.maxSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.slotCarMap.size() > 0) {
            // Print the current status.
            System.out.println("Slot No.\tRegistration No.\tColor");
            Car car;
            for (int i = 1; i <= this.maxSlots; i++) {
                String key = Integer.toString(i);
                if (this.slotCarMap.containsKey(key)) {
                    car = this.slotCarMap.get(key);
                    System.out.println(i + "\t" + car.registrationNumber + "\t" + car.color);
                }
            }
            System.out.println();
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }
    public void getRegistrationNumbersFromColor(String color) {
        if (this.maxSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.colorRegMap.containsKey(color)) {
            ArrayList<String> registrationNumberList = this.colorRegMap.get(color);
            System.out.println();
            for (int i=0; i < registrationNumberList.size(); i++) {
                if (!(i==registrationNumberList.size() - 1)){
                    System.out.print(registrationNumberList.get(i) + ",");
                } else {
                    System.out.print(registrationNumberList.get(i));
                }
            }
        } else {
            System.out.println("Not found");
            System.out.println();
        }
    }
    public void getSlotNumbersFromColor(String color) {
        if (this.maxSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.colorRegMap.containsKey(color)) {
            ArrayList<String> registrationNumberList = this.colorRegMap.get(color);
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            System.out.println();
            for (int i=0; i < registrationNumberList.size(); i++) {
                slotList.add(Integer.valueOf(this.regSlotMap.get(registrationNumberList.get(i))));
            }
            Collections.sort(slotList);
            for (int j=0; j < slotList.size(); j++) {
                if (!(j == slotList.size() - 1)) {
                    System.out.print(slotList.get(j) + ",");
                } else {
                    System.out.print(slotList.get(j));
                }
            }
            System.out.println();
        } else {
            System.out.println("Not found");
            System.out.println();
        }
    }
    public void getSlotNumberFromRegistrationNumber(String registrationNumber) {
        if (this.maxSlots == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.regSlotMap.containsKey(registrationNumber)) {
            System.out.println(this.regSlotMap.get(registrationNumber));
        } else {
            System.out.println("Not found");
            System.out.println();
        }
    }
}
