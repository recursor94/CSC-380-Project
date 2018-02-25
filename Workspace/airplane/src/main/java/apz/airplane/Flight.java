package apz.airplane;

import java.util.Date;

public class Flight {
	
	private Airplane plane;
	private String destinationName;
	private Date arrival, departure;
	// maybe time here too
	private int flightNum;
	
	public Flight(Airplane plane, String destinationName, Date arrival, Date departure, int flightNum) {
		super();
		this.plane = plane;
		this.destinationName = destinationName;
		this.arrival = arrival;
		this.departure = departure;
		this.flightNum = flightNum;
	}

	public Airplane getPlane() {
		return plane;
	}

	public void setPlane(Airplane plane) {
		this.plane = plane;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public int getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
	}

	@Override
	public String toString() {
		return "Flight [plane=" + plane + ", destinationName=" + destinationName + ", arrival=" + arrival
				+ ", departure=" + departure + ", flightNum=" + flightNum + "]";
	}

}
