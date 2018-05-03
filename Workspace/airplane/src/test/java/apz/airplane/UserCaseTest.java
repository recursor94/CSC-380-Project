package apz.airplane;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import org.junit.Test;
import apz.airplane.model.Airplane;
import apz.airplane.model.Airport;
import apz.airplane.model.Booking;
import apz.airplane.model.Flight;
import apz.airplane.model.Payment;
import apz.airplane.model.Time;
import apz.airplane.model.User;
import apz.airplane.model.UserController;

public class UserCaseTest {

	@Test
	public void testLogin() {
		UserController uc = new UserController();
		User testUser = new User("asd@asd.org", "Pooper", "Scooper");
		uc.addUser(testUser);
		
		assertEquals(testUser, uc.login("Pooper", "Scooper"));
	}
	
	@Test
	public void testValidPassword() {
		User testUser = new User("asd@asd.org", "Pooper", "Scooper");	
		assertEquals(true, testUser.validatePassword("Scooper"));
	}
	
	@Test
	public void testInvalidPassword() {
		User testUser = new User("asd@asd.org", "Pooper", "Scooper");
		assertEquals(false, testUser.validatePassword("wordpass"));
	}
	
	@Test
	public void testFindAndAddTrip() {
		User user = new User("asd@asd.org", "Pooper", "Scooper");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		Airplane plane = new Airplane(1, "American Airline", 5);
		LocalDate bookDate = LocalDate.now();
		LocalDate departure =  LocalDate.of(2018, 3, 12);
		LocalDate arrival = LocalDate.of(2018, 3, 12);
		Time departTime = new Time ("12:00 PM");
		Time arriveTime = new Time ("7:00 PM");
		Airport departingAirport = new Airport("Syracuse Hancock International", "Syracuse, New York");
		Airport destinationAirport = new Airport("Batman Airport", "Batman, Turkey"); //Yes, this is a real city and airport. 
		Flight flight = new Flight(plane,departingAirport, destinationAirport, arrival, departure, departTime, arriveTime, 62918);
		Booking trip = new Booking(flight, bookDate, 30.0);
		user.addTrip(trip);
		assertEquals(trip, user.findTrip(flight));
	}
	
	@Test
	public void findTripFail() {
		User user = new User("asd@asd.org", "Pooper", "Scooper");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		Airplane plane = new Airplane(1, "American Airline", 5);
		LocalDate bookDate = LocalDate.now();
		LocalDate departure =  LocalDate.of(2018, 3, 12);
		LocalDate arrival = LocalDate.of(2018, 3, 12);
		Time departTime = new Time ("12:00 PM");
		Time arriveTime = new Time ("7:00 PM");
		Airport departingAirport1 = new Airport("Louis Armstrong New Orleans International", "New Orleans, Louisiana");
		Airport destinationAirport1 = new Airport("San Francisco International Airport", "San Francisco, California");
		Airport departingAirport2 = new Airport("Syracuse Hancock International", "Syracuse, New York");
		Airport destinationAirport2 = new Airport("Batman Airport", "Batman, Turkey"); //Yes, this is a real city and airport. 
		Flight flight = new Flight(plane, departingAirport1, destinationAirport1, arrival, departure, departTime, arriveTime, 62918);
		Flight flight2 = new Flight(plane,departingAirport2, destinationAirport2, arrival, departure, departTime, arriveTime, 62919);
		Booking trip = new Booking(flight, bookDate, 30.0);
		user.addTrip(trip);
		
		//flight2 has not been added to the booking list, so it should not be found
		assertEquals(null, user.findTrip(flight2));
	}
	
	@Test
	public void testRemoveTrip() {
		User user = new User("asd@asd.org", "Pooper", "Scooper");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		Airplane airplane = new Airplane(1, "American Airline", 5);
		LocalDate bookDate = LocalDate.now();
		LocalDate departure =  LocalDate.of(2018, 3, 12);
		LocalDate arrival = LocalDate.of(2018, 3, 12);
		Time departTime = new Time ("12:00 PM");
		Time arriveTime = new Time ("7:00 PM");
		Airport departingAirport = new Airport("Syracuse Hancock International", "Syracuse, New York");
		Airport destinationAirport = new Airport("Batman Airport", "Batman, Turkey"); //Yes, this is a real city and airport. 
		Flight flight = new Flight(airplane, departingAirport, destinationAirport, arrival, departure, departTime, arriveTime, 40908 );
		Booking trip = new Booking(flight, bookDate, 30.0);
		user.addTrip(trip);
		assertEquals(true, user.removeTrip(flight));
	}
	
	@Test
	public void removeTripFail() {
		User user = new User("asd@asd.org", "Pooper", "Scooper");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		Airplane plane = new Airplane(1, "American Airline", 5);
		LocalDate bookDate = LocalDate.now();
		LocalDate departure =  LocalDate.of(2018, 3, 12);
		LocalDate arrival = LocalDate.of(2018, 3, 12);
		Time departTime = new Time ("12:00 PM");
		Time arriveTime = new Time ("7:00 PM");
		Airport departingAirport = new Airport("Syracuse Hancock International", "Syracuse, New York");
		Airport destinationAirport = new Airport("Batman Airport", "Batman, Turkey"); //Yes, this is a real city and airport. 
		Airport departingAirport2 = new Airport("Louis Armstrong New Orleans International", "New Orleans, Louisiana");
		Airport destinationAirport2 = new Airport("San Francisco International Airport", "San Francisco, California");
		Flight flight = new Flight(plane,departingAirport, destinationAirport, arrival, departure, departTime, arriveTime, 62918);
		Flight flight2 = new Flight(plane,departingAirport2, destinationAirport2, arrival, departure, departTime, arriveTime, 62919);
		Booking trip = new Booking(flight, bookDate, 30.0);
		user.addTrip(trip);
		
		//flight2 has not been added to the booking list, so no trip should be removed
		assertEquals(false, user.removeTrip(flight2));
	}
	
	@Test public void testRemovePayment() {
		User user = new User("asd@asd.org", "Pooper", "Scooper");
		String name = "Zak";
		String street = "123 Java Avenue";
		String city = "Eclipse City";
		String state = "New York";
		int zip = 14893;
		long cardNum = 1234133333;
		String expirationDate = "01/2021";
		int CCV = 922;
		Payment payment = new Payment(name, street, city, state, zip, cardNum, expirationDate, CCV);
		user.addPayment(payment);
		
		assertEquals(true, user.removePayment(payment));
	}
	
	@Test
	public void removePaymentFail () {
		User user = new User("asd@asd.org", "Pooper", "Scooper");
		String name = "Zak";
		String street = "123 Java Avenue";
		String city = "Eclipse City";
		String state = "New York";
		int zip = 14893;
		long cardNum = 1234133333;
		long cardNum2 = 879341323;
		String expirationDate = "01/2021";
		int CCV = 922;
		Payment payment = new Payment(name, street, city, state, zip, cardNum, expirationDate, CCV);
		Payment payment2 = new Payment(name, street, city, state, zip, cardNum2, expirationDate, CCV);
		user.addPayment(payment);
		assertEquals(false, user.removePayment(payment2));
	}
	@Test 
	public void flightCancellableTrue() {
		Flight flight = new Flight(new Airplane(3, "Test United", 9001), new Airport("test", "test"), new Airport("test","test"), 
				null, null, null, null,3 );
		LocalDate bookDate = LocalDate.now();
		Booking booking = new Booking(flight, bookDate, 3);

		assertEquals(true, booking.isCancellable());
	}
	@Test
	public void flightCancellableFalse() {
		Flight flight = new Flight(new Airplane(3, "Test United", 9001), new Airport("test", "test"), new Airport("test","test"), 
				null, null, null, null,3 );
		LocalDate bookDate = LocalDate.now().plusDays(2);
		Booking booking = new Booking(flight, bookDate, 3);
		assertEquals(false, booking.isCancellable());
	}
}
