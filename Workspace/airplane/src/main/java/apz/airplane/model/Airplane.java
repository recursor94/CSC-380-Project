package apz.airplane.model;

import java.io.Serializable;

public class Airplane implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int planeNum;
	private String airline;
	private Seating seats;
	
	public Airplane(int planeNum, String airline, int capacity) {
		this.planeNum = planeNum;
		this.airline = airline;
		seats = new Seating(capacity);
	}

	public int getPlaneNum() {
		return planeNum;
	}

	public void setPlaneNum(int planeNum) {
		this.planeNum = planeNum;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public Seating getSeats() {
		return seats;
	}

	public void setSeats(Seating seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "[" + planeNum + "] " + airline + " "  + " :: " + seats.getCapacity() + " seats";
	}	
}
