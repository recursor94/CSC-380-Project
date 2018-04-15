package apz.airplane.model;
import java.io.Serializable;
import java.time.LocalDate;

public class Booking implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Flight flight;
	private LocalDate bookDate;
	private Airplane plane;
	private double tripCost;

	// Payment information in here for selected card used
	
	// CHANGE USER SO WE DON'T NEED THIS AS PARAM
	public Booking(Flight flight, LocalDate bookDate, User user, double tripCost) {
		this.flight = flight;
		this.bookDate = bookDate;
		plane = flight.getPlane();
		plane.getSeats().addTo(user);
		this.tripCost = tripCost;
	}	
	
	public Booking(Flight flight, LocalDate bookDate, User user) {
		this.flight = flight;
		this.bookDate = bookDate;
		plane = flight.getPlane();
		plane.getSeats().addTo(user);
		tripCost = Province.getRate(flight.getDepartureAirport().getCity()) + Province.getRate(flight.getDestinationAirport().getCity());
	}	
	
	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public LocalDate getBookDate() {
		return bookDate;
	}

	public void setBookDate(LocalDate bookDate) {
		this.bookDate = bookDate;
	}
	
	public double getTripCost() {
		return tripCost;
	}
	
	public void setTripCost(double cost) {
		tripCost = cost;
	}

	@Override
	public String toString() {
		return flight.toString() + "\tArrival: " + flight.getArrivalTime().getTimeString() + "\nFlight booked on " + bookDate;
	}

}
