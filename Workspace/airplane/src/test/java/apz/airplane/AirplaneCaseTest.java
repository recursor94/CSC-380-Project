package apz.airplane;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import apz.airplane.model.Airplane;
import apz.airplane.model.Seating;

public class AirplaneCaseTest {

	@Test
	public void seatFullTest() {
		Airplane plane = new Airplane(0, "American Airlines", 5);
		for (int i = 0; i < 5; i++) {
			plane.getSeats().addTo();
		}
		assertEquals(true, plane.getSeats().isFull());
	}
	
	@Test
	public void seatFullTestInvalid() {
		Airplane plane = new Airplane(0, "American Airlines", 5);
		for (int i = 0; i < 4; i++) {
			plane.getSeats().addTo();
		}
		assertEquals(false, plane.getSeats().isFull());
	}
	
	@Test
	public void seatingFullWhenFull() {
		
		Airplane testPlane = new Airplane(0, "Test Airlines", 1);
		Seating seats = testPlane.getSeats();
		seats.addTo();
		assertEquals(true, seats.isFull());
	}

	@Test
	public void seatingFullWhenNotFull() {
		Airplane testPlane = new Airplane(0, "Test Airlines", 10);
		Seating seats = testPlane.getSeats();
		seats.addTo();
		assertEquals(false, seats.isFull());
	}
}
