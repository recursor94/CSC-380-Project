package apz.airplane;

import java.util.Date;

public class Flight {
	
	private String destination;
	private Date departure;
	private Date arrival;
	private Airplane plane;
	private int flightNum;
	
	public Flight(String destination, Date departure, Date arrival, Airplane plane, int flightNum) {
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
		this.plane = plane;
		this.flightNum = flightNum;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public int getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
	}
	

}
