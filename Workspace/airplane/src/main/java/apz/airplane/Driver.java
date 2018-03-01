package apz.airplane;
import java.util.Date;

public class Driver {

	public static void main(String[] args) {
		Airplane airplane = new Airplane(1, "American Airline", 5);	// create plane with 5 seats
		
		UserController userCtrl = new UserController();
		User me = new User("Andrew Gudlin", "pswrd");
		userCtrl.addUser(me);
		userCtrl.addUser(new User("Zak Spano", "pswrd"));
		userCtrl.addUser(new User("King Jimmy", "pswrd"));
		userCtrl.addUser(new User("Bob Saget", "pswrd"));
		userCtrl.addUser(new User("Marley n Me", "pswrd"));
		//userCtrl.bookTrip(user goes inside here);
		
		//Testing the Date class; the methods to set dates are deprecated
		Date bookDate = new Date();
		Date departure = new Date(118, 2, 12, 7, 45);
		Date arrival = new Date(118, 2, 12, 12, 55);
		System.out.println(bookDate);
		System.out.println(departure);
		System.out.println(arrival);
		
		airplane.getSeatArray().addTo(new User("Pooper", "Scooper"));
		airplane.getSeatArray().addTo(me);
	
		//Creating a flight and a Booking trip and test to toString methods
		// Destination
		Flight flight = new Flight(airplane, "Hawaii", arrival, departure, 40908 );
		Booking trip = new Booking(flight, bookDate);
		System.out.println(trip);
		System.out.println(flight);
		System.out.println(airplane);
	}

}
