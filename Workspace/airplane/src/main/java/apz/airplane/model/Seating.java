package apz.airplane.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Seating implements Serializable {

	// average plane has 200
	private static final long serialVersionUID = 1L;
	private ArrayList<User> seating;
	private int capacity;

	public Seating(int capacity) {
		this.capacity = capacity;
		seating = new ArrayList<>(capacity);
	}

	public void addTo(User user) {
		if (seating.size() == capacity) {
			System.out.println("FULL LIST");
			return;
		}

		seating.add(user);
	}

	public void remove(User user) {
		// if (isFull()) {
		// System.out.println("NO ONE IN LIST TO REMOVE");
		// return;
		// }

		for (int i = 0; i < seating.size(); i++) {
			if (seating.get(i) == user)
				seating.remove(user);
		}
		System.out.println("USER NOT EXISTS");
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

	public String setSeatNaming(int index) {
		if (index < (seating.size() / 2))
			return "a";
		else
			return "b";
	}

	// public boolean isFull() {
	// for (int i = 0; i < seating.length; i++) {
	// if (seating[i] == null) {
	// return false;
	// }
	// }
	// return true;
	// }
	public boolean isFull() {
		if (capacity == seating.size())

			return true;
		return false;
	}
<<<<<<< HEAD
	
	public int getCapacity() {
		return seating.size();
=======

	public int getCapacity() {
		return capacity;
>>>>>>> 5d061c427c69a20ba8b3efa07cd069f2a018cdd4
	}


	@Override
	public String toString() {
		return "Seating: " + seating;
	}

}
