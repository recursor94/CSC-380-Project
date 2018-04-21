package apz.airplane.model;

import java.io.Serializable;
import java.util.Arrays;

public class Seating2 implements Serializable {
	
	//average plane has 200
	private static final long serialVersionUID = 1L;
	private User[] seating;	// must limit on if more users are trying to be added. maybe boolean return method isFull();
	
	public Seating2(int capacity) {
		seating = new User[capacity];
	}
	
	public void addTo(User user) {
		if (isFull()) {
			System.out.println("FULL LIST");
			return;
		}
		
		for (int i = 0; i < seating.length; i++) {
			if (seating[i] == null) {
				seating[i] = user;
				return;
			}
		}
	}
	
	public void remove(User user) {
		if (isFull()) {
			System.out.println("NO ONE IN LIST TO REMOVE");
			return;
		}
		
		for (int i = 0; i < seating.length; i++) {
			if (seating[i] != null) 
				if (seating[i] == user) {
					seating[i] = null;
					return;
				}
		}
		System.out.println("USER NOT EXISTS");
	}
	
	public boolean isOnBoard(User user) {
		for (int i = 0; i < seating.length; i++) {
			if (seating[i] != null) 
				if (seating[i] == user) {
					return true;
				}
		}
		return false;
	}
	
	public String getSeatName(User user) {
		for (int i = 0; i < seating.length; i++) {
			String letter = setSeatNaming(i);
			
			if (seating[i] != null) 
				if (seating[i] == user) {
					return (i + 1) + "-" + letter;
				}
		}
		return null;
	}
	
	public String setSeatNaming(int index) {
		if (index < (seating.length / 2)) 
			return "a";
		else
			return "b";
	}
	
	public boolean isFull() {
		for (int i = 0; i < seating.length; i++) {
			if (seating[i] == null) {
				return false;
			}
		}
		return true;
	}
	
	public int getCapacity() {
		return seating.length;
	}

	@Override
	public String toString() {
		return "Seating: " + Arrays.toString(seating);
	}
	
}
