package zak;

import java.io.Serializable;
import java.util.Arrays;

public class SeatingZak implements Serializable {
	
	//average plane has 200
	private UserZak[] seating;	// must limit on if more users are trying to be added. maybe boolean return method isFull();
	
	public SeatingZak(int capacity) {
		seating = new UserZak[capacity];
	}
	
	public void addTo(UserZak user) {
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
	
	public void remove(UserZak user) {
		for (int i = 0; i < seating.length; i++) {
			if (seating[i] != null) 
				if (seating[i] == user) {
					seating[i] = null;
					return;
				}
		}
		System.out.println("USER NOT EXISTS");
	}
	
	public boolean isOnBoard(UserZak user) {
		for (int i = 0; i < seating.length; i++) {
			if (seating[i] != null) 
				if (seating[i] == user) {
					return true;
				}
		}
		return false;
	}
	
	public String getSeatName(UserZak user) {
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

	@Override
	public String toString() {
		return "Seating: " + Arrays.toString(seating);
	}
	
}
