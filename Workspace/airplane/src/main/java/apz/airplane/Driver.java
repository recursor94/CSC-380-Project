package apz.airplane;
import java.time.LocalDateTime;

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
	    LocalDateTime bookDate = LocalDateTime.now();
		LocalDateTime departure =  LocalDateTime.of(2018, 3, 12, 7, 45);
		LocalDateTime arrival = LocalDateTime.of(2018, 3, 12, 12, 55);
		System.out.println(bookDate);
		System.out.println(departure);
		System.out.println(arrival);
		
		airplane.getSeats().addTo(new User("Pooper", "Scooper"));
	
		//Creating a flight and a Booking trip and test to toString methods
		Flight flight = new Flight(airplane, "Hawaii", arrival, departure, 40908 );
		Booking trip = new Booking(flight, bookDate, me);
		System.out.println(trip);
		System.out.println(flight);
		System.out.println(airplane);
	}

}
