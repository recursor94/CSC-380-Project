package apz.airplane;

import java.io.Serializable;
import java.time.LocalDate;

public class Flight implements Serializable {
	
	private Airplane plane;
	private Airport arrivingAirport, destinationAirport;
	private LocalDate arriveDate, departureDate;		// getter and setters
	private Time arrivalTime, departureTime;
	// maybe time here too
	private int flightNum;
	
	
	
	public Flight(Airplane plane, Airport startingAirport, Airport destinationAirport, LocalDate arriveDate,
			LocalDate departureDate, Time arrivalTime, Time departureTime, int flightNum) {
		this.plane = plane;
		this.arrivingAirport = startingAirport;
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

	public void setDestinationAirport(Airport destinationName) {
		this.destinationAirport = destinationAirport;
	}

	public Airport getArrivingAirport() {
		return arrivingAirport;
	}

	public void setArrivingAirport(Airport arrivingAirport) {
		this.arrivingAirport = arrivingAirport;
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
		return "Flight from " + arrivingAirport + " to " + destinationAirport + "\nFlight # " + flightNum
				+ "\tDeparture: " + departureDate + "\t" + departureTime.getTimeString();
	
	}

}
