package apz.airplane;

import static org.junit.Assert.*;

import org.junit.Test;

import apz.airplane.model.Airplane;
import apz.airplane.model.Seating;
import apz.airplane.model.User;
import apz.airplane.model.UserController;

public class AirplaneCaseTest {

	@Test
	public void seatFullTest() {
		Airplane plane = new Airplane(0, "American Airlines", 5);
		for (int i = 0; i < 5; i++) {
			plane.getSeats().addTo(new User("Pooper", "Scooper"));
		}
		assertEquals(true, plane.getSeats().isFull());
	}
	
	@Test
	public void seatFullTestInvalid() {
		Airplane plane = new Airplane(0, "American Airlines", 5);
		for (int i = 0; i < 4; i++) {
			plane.getSeats().addTo(new User("Pooper", "Scooper"));
		}
		assertEquals(false, plane.getSeats().isFull());
	}
	
	@Test
	public void userOnboard() {
		User user = new User ("apz", "zpa12");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		Airplane plane = new Airplane(1, "APZ Airlines", 7);
		plane.getSeats().addTo(user);
		Seating seats = plane.getSeats();
		assertEquals(true, seats.isOnBoard(user));
	}
	
	@Test
	public void seatingFullWhenFull() {
		
		Airplane testPlane = new Airplane(0, "Test Airlines", 1);
		User user = new User("Bob the Builder", "asbob");
		
		Seating seats = testPlane.getSeats();
		seats.addTo(user);
		assertEquals(true, seats.isFull());
	}

	@Test
	public void seatingFullWhenNotFull() {
		Airplane testPlane = new Airplane(0, "Test Airlines", 10);
		User user = new User("Bob the Builder", "asbob");
		
		Seating seats = testPlane.getSeats();
		seats.addTo(user);
		assertEquals(false, seats.isFull());
	}

}
