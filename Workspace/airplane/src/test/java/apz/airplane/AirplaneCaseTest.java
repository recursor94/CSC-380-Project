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
<<<<<<< HEAD
			plane.getSeats().addTo(new User("asd", "Pooper", "Scooper"));
=======
			plane.getSeats().addTo(new User("asd@asd.org", "Pooper", "Scooper"));
>>>>>>> fb83fb270f069c1be37ab3040f1f7e26d58a73fd
		}
		assertEquals(true, plane.getSeats().isFull());
	}
	
	@Test
	public void seatFullTestInvalid() {
		Airplane plane = new Airplane(0, "American Airlines", 5);
		for (int i = 0; i < 4; i++) {
<<<<<<< HEAD
			plane.getSeats().addTo(new User("asd", "Pooper", "Scooper"));
=======
			plane.getSeats().addTo(new User("asd@asd.org", "Pooper", "Scooper"));
>>>>>>> fb83fb270f069c1be37ab3040f1f7e26d58a73fd
		}
		assertEquals(false, plane.getSeats().isFull());
	}
	
	//@Test
//	public void userOnboard() {
//		User user = new User ("apz", "zpa12");
//		UserController ctrl = new UserController();
//		ctrl.addUser(user);
//		Airplane plane = new Airplane(1, "APZ Airlines", 7);
//		plane.getSeats().addTo(user);
//		Seating seats = plane.getSeats();
//		assertEquals(true, seats.isOnBoard(user));
//	}
	
	@Test
	public void seatingFullWhenFull() {
		
		Airplane testPlane = new Airplane(0, "Test Airlines", 1);
<<<<<<< HEAD
		User user = new User("asd", "Bob the Builder", "asbob");
=======
		User user = new User("asd@asd.org", "Pooper", "Scooper");
>>>>>>> fb83fb270f069c1be37ab3040f1f7e26d58a73fd
		
		Seating seats = testPlane.getSeats();
		seats.addTo(user);
		assertEquals(true, seats.isFull());
	}

	@Test
	public void seatingFullWhenNotFull() {
		Airplane testPlane = new Airplane(0, "Test Airlines", 10);
<<<<<<< HEAD
		User user = new User("asd", "Bob the Builder", "asbob");
=======
		User user = new User("asd@asd.org", "Pooper", "Scooper");
>>>>>>> fb83fb270f069c1be37ab3040f1f7e26d58a73fd
		
		Seating seats = testPlane.getSeats();
		seats.addTo(user);
		assertEquals(false, seats.isFull());
	}

}
