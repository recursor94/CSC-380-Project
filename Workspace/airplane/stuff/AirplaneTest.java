package apz.airplane.OTHERS;

import static org.junit.jupiter.api.Assertions.assertEquals;

import apz.airplane.Airplane;
import apz.airplane.User;

public class AirplaneTest {

	public void seatFullTest() {
		Airplane plane = new Airplane(0, "American Airlines", 5);
		for (int i = 0; i < 5; i++) {
			plane.getSeats().addTo(new User("Pooper", "Scooper"));
		}
		assertEquals(true, plane.getSeats().isFull());
	}
	
	public void seatFullTestInvalid() {
		Airplane plane = new Airplane(0, "American Airlines", 5);
		for (int i = 0; i < 4; i++) {
			plane.getSeats().addTo(new User("Pooper", "Scooper"));
		}
		assertEquals(false, plane.getSeats().isFull());
	}

}