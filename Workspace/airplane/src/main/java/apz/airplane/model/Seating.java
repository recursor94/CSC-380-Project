package apz.airplane.model;

import java.io.Serializable;

public class Seating implements Serializable {

	private static final long serialVersionUID = 1L;
	private int capacity, currentSeats;

	public Seating(int capacity) {
		this.capacity = capacity;
		currentSeats = 0;
	}

	public void addTo() {
		if (!isFull()) 
			currentSeats++;
	}

	public void remove() {
		currentSeats--;
	}

	public boolean isFull() {
		if (currentSeats == capacity)
			return true;
		return false;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getCurrentSeats() {
		return currentSeats;
	}
}
