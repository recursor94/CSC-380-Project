package apz.airplane;

import java.time.LocalDateTime;

public class Flight {
	
	private Airplane plane;
	private String destinationName, arrivalName;
	private LocalDateTime arrivalTime, departureTime;
	// maybe time here too
	private int flightNum;
	
	public Flight(Airplane plane, String destinationName, String arrivalName, LocalDateTime arrivalTime, LocalDateTime departureTime, int flightNum) {
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

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
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
