package apz.airplane.model;
import java.time.LocalDate;
import java.time.LocalDateTime;

import apz.airplane.util.MessageBox;
import javafx.scene.control.Alert.AlertType;

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
		
		String expDate = "04/2018";
		String month;
		String year;
		String currentMonth = LocalDate.now().getMonthValue() + "";
		String currentYear = LocalDate.now().getYear() + "";
		
		if(expDate.charAt(0) == '0') {
			month = expDate.substring(1, 2);
			year = expDate.substring(3);
		}
		else {
			month = expDate.substring(0, 2);
			year = expDate.substring(3);
		}
		
		System.out.println(month);
		System.out.println(year);
		System.out.println(currentMonth);
		System.out.println(currentYear);
	
		
		if(Integer.valueOf(year) < Integer.valueOf(currentYear)) 
				System.out.println("PAYMENT IS EXPIRED!!");
		else if(Integer.valueOf(year).equals(Integer.valueOf(currentYear)) && (Integer.valueOf(month) < Integer.valueOf(currentMonth)))
				System.out.println("PAYMENT IS EXPIRED!!");
			else
				System.out.println("GOOD JOB!");
		
			
		
		//Creating a flight and a Booking trip and test to toString methods
//		Flight flight = new Flight(airplane, "New York", "Hawaii", arrival, departure, 40908 );
//		Booking trip = new Booking(flight, bookDate, me);
//		System.out.println(trip);
//		System.out.println(flight);
//		System.out.println(airplane);
	}

}
