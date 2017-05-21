package com.myapp.parkinglot;


import java.util.*;
/**
 * @author amita.jain
 *
 *         May 21, 2017
 */
public class ParkingLot {
    int MAX_PARKING_SPACE = 0;
    
    // Available slots list
    ArrayList<Integer> availableSlotList;
    // Map of Slot, Car
    Map<String, Car> map1;
    // Map of registrationNumber, Slot
    Map<String, String> map2;
    // Map of Color, List of registrationNumber
    Map<String, ArrayList<String>> map3;
    
    Map<String, Object> commandContext;


    public void createParkingLot(String maxParkingSpace) {
        try {
            this.MAX_PARKING_SPACE = Integer.parseInt(maxParkingSpace);
        } catch (Exception e) {
            System.out.println(MessageLoader.getMessage("invalid.parking.space"));
            System.out.println();
        }
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_PARKING_SPACE; i++) {
            availableSlotList.add(i);
        }
        this.map1 = new HashMap<String, Car>();
        this.map2 = new HashMap<String, String>();
        this.map3 = new HashMap<String, ArrayList<String>>();
        System.out.println("Created parking lot with " + maxParkingSpace + " spaces");
        System.out.println();
    }
    public void park(String registrationNumber, String color) {
        if (this.MAX_PARKING_SPACE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map1.size() == this.MAX_PARKING_SPACE) {
            System.out.println("Sorry, parking lot is full");
            System.out.println();
        } else {
            Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            Car car = new Car(registrationNumber, color);
            this.map1.put(slot, car);
            this.map2.put(registrationNumber, slot);
            if (this.map3.containsKey(color)) {
                ArrayList<String> registrationNumberList = this.map3.get(color);
                this.map3.remove(color);
                registrationNumberList.add(registrationNumber);
                this.map3.put(color, registrationNumberList);
            } else {
                ArrayList<String> registrationNumberList = new ArrayList<String>();
                registrationNumberList.add(registrationNumber);
                this.map3.put(color, registrationNumberList);
            }
            System.out.println("Allocated slot number: " + slot);
            System.out.println();
            availableSlotList.remove(0);
        }
    }
    public void leave(String slotNo) {
        if (this.MAX_PARKING_SPACE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map1.size() > 0) {
            Car carToLeave = this.map1.get(slotNo);
            if (carToLeave != null) {
                this.map1.remove(slotNo);
                this.map2.remove(carToLeave.registrationNumber);
                ArrayList<String> registrationNumberList = this.map3.get(carToLeave.color);
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
        if (this.MAX_PARKING_SPACE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map1.size() > 0) {
            // Print the current status.
            System.out.println("Slot No.\tRegistration No.\tColor");
            Car car;
            for (int i = 1; i <= this.MAX_PARKING_SPACE; i++) {
                String key = Integer.toString(i);
                if (this.map1.containsKey(key)) {
                    car = this.map1.get(key);
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
        if (this.MAX_PARKING_SPACE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map3.containsKey(color)) {
            ArrayList<String> registrationNumberList = this.map3.get(color);
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
        if (this.MAX_PARKING_SPACE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map3.containsKey(color)) {
            ArrayList<String> registrationNumberList = this.map3.get(color);
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            System.out.println();
            for (int i=0; i < registrationNumberList.size(); i++) {
                slotList.add(Integer.valueOf(this.map2.get(registrationNumberList.get(i))));
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
        if (this.MAX_PARKING_SPACE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.map2.containsKey(registrationNumber)) {
            System.out.println(this.map2.get(registrationNumber));
        } else {
            System.out.println("Not found");
            System.out.println();
        }
    }
}
