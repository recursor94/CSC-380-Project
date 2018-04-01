package apz.airplane;
import java.io.Serializable;
import java.time.LocalDate;

public class Booking implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Flight flight;
	private LocalDate bookDate;
	private Airplane plane;
	
	public Booking(Flight flight, LocalDate bookDate, User user) {
		this.flight = flight;
		this.bookDate = bookDate;
		plane = flight.getPlane();
		plane.getSeats().addTo(user);
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

	@Override
	public String toString() {
		return flight.toString() + "\tArrival: " + flight.getArrivalTime().getTimeString() + "\nFlight booked on " + bookDate;
	}

}
