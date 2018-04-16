package apz.airplane.user;

public class FlightInformation {

	private int flightNumber;
	private String departureAirport;
	private String destinationAirport;
	private double time;
	private String departureTimeString;
	
	public FlightInformation(int flightNumber, String departureAirport, String destinationAirport, double time, String departureTimeString) {
		this.flightNumber = flightNumber;
		this.departureAirport = departureAirport;
		this.destinationAirport = destinationAirport;
		this.time = time;
		this.departureTimeString = departureTimeString;
	}
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}
	public String getDestinationAirport() {
		return destinationAirport;
	}
	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}
	public double getTime() {
		return time;
	}
	public void setDepartureTime(double time) {
		this.time = time;
	}
	public String getDepartureTimeString() {
		return departureTimeString;
	}
	public void setDepartureTimeString(String departureTimeString) {
		this.departureTimeString = departureTimeString;
	}
	
	
	
}
