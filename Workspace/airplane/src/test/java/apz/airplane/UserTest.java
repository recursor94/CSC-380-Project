package apz.airplane;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	
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
	public void testFindTrip() {
		User user = new User ("apz", "zpa12");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		LocalDateTime bookDate = LocalDateTime.now();
		LocalDateTime departure =  LocalDateTime.of(2018, 3, 12, 6, 55);
		LocalDateTime arrival = LocalDateTime.of(2018, 3, 12, 11, 35);
		Airplane plane = new Airplane(8, "APZ Airlines", 11);
		Flight flight = new Flight(plane, "Chicago", arrival, departure, 62918 );
		Booking trip = new Booking(flight, bookDate, user);
		user.addTrip(trip);
		assertEquals(trip, user.findTrip(flight));
	}

}
