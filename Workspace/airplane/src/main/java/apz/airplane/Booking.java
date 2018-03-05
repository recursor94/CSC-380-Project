package apz.airplane;
import java.util.Date;

public class Booking {
	
	private Flight flight;
	private Date bookDate;
	private Airplane plane;
	
	public Booking(Flight flight, Date bookDate, User user) {
		this.flight = flight;
		this.bookDate = bookDate;
		plane = flight.getPlane();
		plane.getSeatArray().addTo(user);
	}	
	
	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	@Override
	public String toString() {
		return "Booking [flight=" + flight + ", bookDate=" + bookDate + "]";
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
