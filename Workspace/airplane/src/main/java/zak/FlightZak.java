package zak;

import java.io.Serializable;
import java.time.LocalDate;

public class FlightZak implements Serializable {
	
	private AirplaneZak plane;
	private String destinationName, arrivalName;
	private LocalDate arriveDate, departureDate;		// getter and setters
	private TimeZak arrivalTime, departureTime;
	// maybe time here too
	private int flightNum;
	
	
	
	public FlightZak(AirplaneZak plane, String destinationName, String arrivalName, LocalDate arriveDate,
			LocalDate departureDate, TimeZak arrivalTime, TimeZak departureTime, int flightNum) {
		this.plane = plane;
		this.destinationName = destinationName;
		this.arrivalName = arrivalName;
		this.arriveDate = arriveDate;
		this.departureDate = departureDate;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.flightNum = flightNum;
	}

	public AirplaneZak getPlane() {
		return plane;
	}

	public void setPlane(AirplaneZak plane) {
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

	public TimeZak getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(TimeZak arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public TimeZak getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(TimeZak departureTime) {
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
		return "Departing flight on " + departureDate + "\nFrom " + arrivalName + " to " + destinationName;
	}

}
