package main;

import java.util.Arrays;

public class Seating {
	
//	public static void main(String[] args) {
//		Seating seat = new Seating(10);
//		System.out.println(seat.getSeatName(199));
//	}
	
	
	//average plane has 200
	private User[] seating;
	
	public Seating(int capacity) {
		seating = new User[capacity];
	}
	
	public void addTo(User user) {
		for (int i = 0; i < seating.length; i++) {
			if (seating[i] == null)
				seating[i] = user;
		}
	}
	
	public void remove(User user) {
		for (int i = 0; i < seating.length; i++) {
			if (seating[i] != null) 
				if (seating[i] == user) 
					seating[i] = null;
		}
	}

	@Override
	public String toString() {
		return "Seating [seating=" + Arrays.toString(seating) + "]";
	}
	
	public int getUserSeatIndex(User user) {
		for(int i = 0; i < seating.length; i++) {
			
		}
	}
	
//	public String getSeatName(int index) { not flushed in idea yet
//			if (index > (index / 4)) 
//				return index + "C";
//			else if (index > (index / 3)) 
//				return index + "B";
//			else 
//				return index + "A";
//	}
	
}
