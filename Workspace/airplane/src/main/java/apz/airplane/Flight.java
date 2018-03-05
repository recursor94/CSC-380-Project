package apz.airplane;

import java.time.LocalDateTime;

public class Flight {
	
	private Airplane plane;
	private String destinationName;
	private LocalDateTime arrival, departure;
	// maybe time here too
	private int flightNum;
	
	public Flight(Airplane plane, String destinationName, LocalDateTime arrival, LocalDateTime departure, int flightNum) {
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

	public LocalDateTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalDateTime arrival) {
		this.arrival = arrival;
	}

	public LocalDateTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalDateTime departure) {
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
