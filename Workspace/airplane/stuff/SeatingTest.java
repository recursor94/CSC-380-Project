package apz.airplane.OTHERS;

import static org.junit.Assert.assertEquals;

import apz.airplane.Airplane;
import apz.airplane.Seating;
import apz.airplane.User;
import apz.airplane.UserController;

public class SeatingTest {

	public void userOnboard() {
		User user = new User ("apz", "zpa12");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		Airplane plane = new Airplane(1, "APZ Airlines", 7);
		plane.getSeats().addTo(user);
		Seating seats = plane.getSeats();
		assertEquals(true, seats.isOnBoard(user));
	}
	
	public void seatingFullWhenFull() {
		
		Airplane testPlane = new Airplane(0, "Test Airlines", 1);
		User user = new User("Bob the Builder", "asbob");
		
		Seating seats = testPlane.getSeats();
		seats.addTo(user);
		assertEquals(true, seats.isFull());
	}

	public void seatingFullWhenNotFull() {
		Airplane testPlane = new Airplane(0, "Test Airlines", 10);
		User user = new User("Bob the Builder", "asbob");
		
		Seating seats = testPlane.getSeats();
		seats.addTo(user);
		assertEquals(false, seats.isFull());
	}

}
