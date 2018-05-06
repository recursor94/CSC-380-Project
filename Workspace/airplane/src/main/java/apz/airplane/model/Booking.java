package apz.airplane.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Flight flight;
	private LocalDate bookDate;
	private Airplane plane;
	private double tripCost;

	public Booking(Flight flight, LocalDate bookDate, double tripCost) {
		this.flight = flight;
		this.bookDate = bookDate;
		plane = flight.getPlane();
		plane.getSeats().addTo();
		this.tripCost = tripCost;
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
	
	public boolean isCancellable() {
		LocalDate currentDate = LocalDate.now(); 
		if(ChronoUnit.DAYS.between(currentDate, bookDate) > 1) {
			return false;	
		}
		return true;
	}

	@Override
	public String toString() {
		return flight.toString() + "\nFlight booked on " + bookDate;
	}
}
