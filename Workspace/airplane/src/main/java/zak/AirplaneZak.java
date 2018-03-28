package zak;

import java.io.Serializable;

public class AirplaneZak implements Serializable {
	
	private int planeNum;
	private String airline;
	private SeatingZak seats;
	
	public AirplaneZak(int planeNum, String airline, int capacity) {
		this.planeNum = planeNum;
		this.airline = airline;
		seats = new SeatingZak(capacity);
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

	public SeatingZak getSeats() {
		return seats;
	}

	public void setSeats(SeatingZak seatArray) {
		this.seats = seatArray;
	}

	@Override
	public String toString() {
		return "Airline: " + airline +"\nPlane # " + planeNum;
	}
	
}
