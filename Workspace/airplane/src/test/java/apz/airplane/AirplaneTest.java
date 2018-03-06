package apz.airplane;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class AirplaneTest {

	@Test
	void seatFullTest() {
		Airplane plane = new Airplane(0, "American Airlines", 5);
		for (int i = 0; i < 5; i++) {
			plane.getSeats().addTo(new User("Pooper", "Scooper"));
		}
		assertEquals(true, plane.getSeats().isFull());
	}
	
	@Test
	void seatFullTestInvalid() {
		Airplane plane = new Airplane(0, "American Airlines", 5);
		for (int i = 0; i < 4; i++) {
			plane.getSeats().addTo(new User("Pooper", "Scooper"));
		}
		assertEquals(false, plane.getSeats().isFull());
	}

}
