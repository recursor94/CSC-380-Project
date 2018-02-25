package main;

public class Driver {

	public static void main(String[] args) {
		Airplane airplane = new Airplane("Boeing575", 5);
		airplane.getSeatArray().addTo(new User("Pooper", "Scooper"));
		System.out.println(airplane);
	}

}
