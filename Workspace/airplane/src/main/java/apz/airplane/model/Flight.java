package apz.airplane.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Flight implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Airplane plane;
	private Airport departureAirport, destinationAirport;
	private LocalDate arriveDate, departureDate;
	private Time arrivalTime, departureTime;
	private int flightNum;
	
	public Flight(Airplane plane, Airport departureAirport, Airport destinationAirport, LocalDate arriveDate,
			LocalDate departureDate, Time arrivalTime, Time departureTime, int flightNum) {
		this.plane = plane;
		this.departureAirport = departureAirport;
		this.destinationAirport = destinationAirport;
		this.arriveDate = arriveDate;
		this.departureDate = departureDate;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.flightNum = flightNum;
	}

	public Airplane getPlane() {
		return plane;
	}

	public void setPlane(Airplane plane) {
		this.plane = plane;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport arrivingAirport) {
		this.departureAirport = arrivingAirport;
	}

	public LocalDate getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(LocalDate arriveDate) {
		this.arriveDate = arriveDate;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public int getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
	}

	@Override
	public String toString() {
		return "Flight from " + departureAirport + " to " + destinationAirport + "\nFlight #" + flightNum
				+ "\tDeparture: " + departureDate + "\nDeparture Time: " + departureTime.getTimeString() 
				+ "\tArrival Time: " + arrivalTime.getTimeString();
	}
}
