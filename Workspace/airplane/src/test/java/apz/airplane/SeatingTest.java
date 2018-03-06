package apz.airplane;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class SeatingTest {

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

}
