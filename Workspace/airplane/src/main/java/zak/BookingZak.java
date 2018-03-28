package zak;
import java.io.Serializable;
import java.time.LocalDate;

public class BookingZak implements Serializable {
	
	private FlightZak flight;
	private LocalDate bookDate;
	private AirplaneZak plane;
	
	public BookingZak(FlightZak flight, LocalDate bookDate, UserZak user) {
		this.flight = flight;
		this.bookDate = bookDate;
		plane = flight.getPlane();
		plane.getSeats().addTo(user);
	}	
	
	public FlightZak getFlight() {
		return flight;
	}

	public void setFlight(FlightZak flight) {
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
		return flight.getDestinationName() + "-" + flight.getFlightNum();
	}

//	/**
//	 * Generate itenerary including the flight
//	 * @return
//	 */
//	public String generateItenerary() {
//		String itenerary = "Username:\t" + user.getUsername();
//		//itenerary += "\nSeat:\t" + bookedPlane.getSeatArray().
//		return itenerary;
//	}


}
