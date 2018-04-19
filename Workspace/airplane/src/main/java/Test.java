import java.time.LocalDate;

import apz.airplane.model.Airplane;
import apz.airplane.model.Airport;
import apz.airplane.model.Booking;
import apz.airplane.model.Flight;
import apz.airplane.model.Time;
import apz.airplane.model.User;
import apz.airplane.model.UserController;

public class Test {

	public static void main(String[] args) {
		UserController uc = new UserController();
		User user = new User("asd", "asd");
		User user2 = new User("dfg", "dfg");
		User user3 = new User("fgh", "fgh");
		uc.addUser(user);
		uc.addUser(user2);
		uc.addUser(user3);
		
		Airplane p = new Airplane(1, "First Airport", 2);
		Airport a = new Airport("a", "b");
		Flight f = new Flight(p, a, a, LocalDate.now(), LocalDate.now(), new Time("12:00 AM"), new Time("12:00 AM"), 5);
		Booking aa = new Booking(f, LocalDate.now(), user, 30);
		Booking ab = new Booking(f, LocalDate.now(), user2, 30);
		Booking ac = new Booking(f, LocalDate.now(), user3, 30);
		
	}

}
