package apz.airplane.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Seating implements Serializable {

	// average plane has 200
	private static final long serialVersionUID = 1L;
	private int capacity, currentSeats;

	public Seating(int capacity) {
		this.capacity = capacity;
		currentSeats = 0;
	}

	public void addTo() {
		if (currentSeats == capacity)
			System.out.println("FULL LIST");
		else {
			currentSeats++;
			System.out.println("User added to seat\nNumber of seats available: " + (capacity - currentSeats));
		}
	
	}

	public void remove() {
		currentSeats--;
		System.out.println("User added to seat\nNumber of seats available: " + (capacity - currentSeats));
		// if (isFull()) {
		// System.out.println("NO ONE IN LIST TO REMOVE");
		// return;
		// }

//		if(availableSeats < capacity) {
//			availableSeats ++;
//			System.out.println("User removed\nNumber of seats: " + availableSeats);
//			}
	}
	// public boolean isOnBoard(User user) {
	// for (int i = 0; i < seating.length; i++) {
	// if (seating[i] != null)
	// if (seating[i] == user) {
	// return true;
	// }
	// }
	// return false;
	// }
	//
	// public String getSeatName(User user) {
	// for (int i = 0; i < seating.length; i++) {
	// String letter = setSeatNaming(i);
	//
	// if (seating[i] != null)
	// if (seating[i] == user) {
	// return (i + 1) + "-" + letter;
	// }
	// }
	// return null;
	// }



	// public boolean isFull() {
	// for (int i = 0; i < seating.length; i++) {
	// if (seating[i] == null) {
	// return false;
	// }
	// }
	// return true;
	// }
	public boolean isFull() {
		if (currentSeats == capacity)
			return true;
		return false;
	}
	
	public int getCapacity() {
		return capacity;
	}

}
