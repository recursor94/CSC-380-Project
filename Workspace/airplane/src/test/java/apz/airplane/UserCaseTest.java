package apz.airplane;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class UserCaseTest {

	@Test
	public void testLogin() {
		UserController uc = new UserController();
		User testUser = new User("user", "pass");
		uc.addUser(testUser);
		
		assertEquals(testUser, uc.login("user", "pass"));
	}
	
	@Test
	public void testValidPassword() {
		User testUser = new User("user", "password");	
		assertEquals(true, testUser.validatePassword("password"));
	}
	
	@Test
	public void testInvalidPassword() {
		User testUser = new User("test", "password123");
		assertEquals(false, testUser.validatePassword("wordpass"));
	}
	
	@Test
	public void testFindAndAddTrip() {
		User user = new User ("apz", "zpa12");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		Airplane plane = new Airplane(1, "American Airline", 5);
		LocalDate bookDate = LocalDate.now();
		LocalDate departure =  LocalDate.of(2018, 3, 12);
		LocalDate arrival = LocalDate.of(2018, 3, 12);
		Time departTime = new Time ("12:00 PM");
		Time arriveTime = new Time ("7:00 PM");
		Flight flight = new Flight(plane,"New Orleans", "Chicago", arrival, departure, departTime, arriveTime, 62918);
		Booking trip = new Booking(flight, bookDate, user);
		user.addTrip(trip);
		assertEquals(trip, user.findTrip(flight));
	}
	
	@Test
	public void findTripFail() {
		User user = new User ("apz", "zpa12");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		Airplane plane = new Airplane(1, "American Airline", 5);
		LocalDate bookDate = LocalDate.now();
		LocalDate departure =  LocalDate.of(2018, 3, 12);
		LocalDate arrival = LocalDate.of(2018, 3, 12);
		Time departTime = new Time ("12:00 PM");
		Time arriveTime = new Time ("7:00 PM");
		Flight flight = new Flight(plane,"New Orleans", "Chicago", arrival, departure, departTime, arriveTime, 62918);
		Flight flight2 = new Flight(plane,"San Francisco", "Hawaii", arrival, departure, departTime, arriveTime, 62919);
		Booking trip = new Booking(flight, bookDate, user);
		user.addTrip(trip);
		
		//flight2 has not been added to the booking list, so it should not be found
		assertEquals(null, user.findTrip(flight2));
	}
	
	@Test
	public void testRemoveTrip() {
		User user = new User ("apz", "zpa12");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		Airplane airplane = new Airplane(1, "American Airline", 5);
		LocalDate bookDate = LocalDate.now();
		LocalDate departure =  LocalDate.of(2018, 3, 12);
		LocalDate arrival = LocalDate.of(2018, 3, 12);
		Time departTime = new Time ("12:00 PM");
		Time arriveTime = new Time ("7:00 PM");
		Flight flight = new Flight(airplane,"New York", "Hawaii", arrival, departure, departTime, arriveTime, 40908 );
		Booking trip = new Booking(flight, bookDate, user);
		user.addTrip(trip);
		assertEquals(true, user.removeTrip(flight));
	}
	
	@Test
	public void removeTripFail() {
		User user = new User ("apz", "zpa12");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		Airplane plane = new Airplane(1, "American Airline", 5);
		LocalDate bookDate = LocalDate.now();
		LocalDate departure =  LocalDate.of(2018, 3, 12);
		LocalDate arrival = LocalDate.of(2018, 3, 12);
		Time departTime = new Time ("12:00 PM");
		Time arriveTime = new Time ("7:00 PM");
		Flight flight = new Flight(plane,"New Orleans", "Chicago", arrival, departure, departTime, arriveTime, 62918);
		Flight flight2 = new Flight(plane,"San Francisco", "Hawaii", arrival, departure, departTime, arriveTime, 62919);
		Booking trip = new Booking(flight, bookDate, user);
		user.addTrip(trip);
		
		//flight2 has not been added to the booking list, so no trip should be removed
		assertEquals(false, user.removeTrip(flight2));
	}

}
