/**
 * Holds Car Details
 */
package com.myapp.parkinglot;

/**
 * @author amita.jain
 *
 *         May 21, 2017
 */
public class Car {

	String registrationNumber;
	String color;

	/**
	 * 
	 */
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param registrationNumber
	 * @param color
	 */
	public Car(String registrationNumber, String color) {
		super();
		this.registrationNumber = registrationNumber;
		this.color = color;
	}

	/**
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * @param registrationNumber
	 *            the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

}
