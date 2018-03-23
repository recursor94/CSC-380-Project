package apz.airplane;

import java.io.Serializable;
import java.time.LocalDate;

public class Flight implements Serializable {
	
	private Airplane plane;
	private String destinationName, arrivalName;
	private LocalDate arriveDate, departureDate;		// getter and setters
	private Time arrivalTime, departureTime;
	// maybe time here too
	private int flightNum;
	
	public Flight(Airplane plane, String destinationName, String arrivalName, Time arrivalTime, Time departureTime, int flightNum) {
		super();
		this.plane = plane;
		this.destinationName = destinationName;
		this.arrivalName = arrivalName;
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

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getArrivalName() {
		return arrivalName;
	}

	public void setArrivalName(String arrivalName) {
		this.arrivalName = arrivalName;
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
		return "Flight [plane=" + plane + ", destinationName=" + destinationName + ", arrivalTime=" + arrivalTime
				+ ", departureTime=" + departureTime + ", flightNum=" + flightNum + "]";
	}

}
