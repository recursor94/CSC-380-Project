package apz.airplane;

import java.util.Arrays;

public class Seating {
	
	//average plane has 200
	private User[] seating;	// must limit on if more users are trying to be added. maybe boolean return method isFull();
	
	public Seating(int capacity) {
		seating = new User[capacity];
	}
	
	public void addTo(User user) {
		for (int i = 0; i < seating.length; i++) {
			if (seating[i] == null) {
				seating[i] = user;
				return;
			}
		}
	}
	
	public void remove(User user) {
		for (int i = 0; i < seating.length; i++) {
			if (seating[i] != null) 
				if (seating[i] == user) {
					seating[i] = null;
					return;
				}
		}
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

	@Override
	public String toString() {
		return "Seating: " + Arrays.toString(seating);
	}
	
}
