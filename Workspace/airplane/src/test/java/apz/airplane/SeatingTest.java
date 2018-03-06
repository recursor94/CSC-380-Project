package apz.airplane;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SeatingTest {

	@Test
	public void userOnboard() {
		
		User user = new User ("apz", "zpa12");
		UserController ctrl = new UserController();
		ctrl.addUser(user);
		Airplane plane = new Airplane(1, "APZ Airlines", 7);
		plane.getSeatArray().addTo(user);
		Seating seats = plane.getSeatArray();
		assertEquals(true, seats.isOnBoard(user));
	}
	
	@Test
	public void seatingFullWhenFull() {
		
		Airplane testPlane = new Airplane(0, "Test Airlines", 1);
		User user = new User("Bob the Builder", "asbob");
		
		Seating seats = testPlane.getSeatArray();
		seats.addTo(user);
		assertEquals(true, seats.isFull());
	}
	
	public void seatingFullWhenNotFull() {
		Airplane testPlane = new Airplane(0, "Test Airlines", 10);
		User user = new User("Bob the Builder", "asbob");
		
		Seating seats = testPlane.getSeatArray();
		seats.addTo(user);
		assertEquals(false, seats.isFull());
	}

}
