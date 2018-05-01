package apz.airplane.user;

import java.time.LocalDate;

import apz.airplane.model.Airplane;
import apz.airplane.model.Airport;
import apz.airplane.model.Booking;
import apz.airplane.model.Flight;
import apz.airplane.model.Time;
import apz.airplane.model.User;
import apz.airplane.model.UserController;

public class TestRemoveSeat {
	
	public static void main (String [] args) {
		UserController uc = new UserController();
		User user = new User("ha", "ha", "ha");
		User user2 = new User ("what", "is", "this");
		User user3 = new User ("what", "is", "this");
		uc.addUser(user);
		uc.addUser(user2);
		Airport departing = new Airport("Blah", "NY");
		Airport arriving = new Airport("HAH", "PA");
		Airplane plane = new Airplane(12, "Delta", 2);
		Time depTime = new Time("12:30 PM");
		Time arrTime = new Time("5:30 PM");
		LocalDate depDate = LocalDate.of(2019, 3, 9);
		LocalDate arrDate = LocalDate.of(2019, 3, 9);
		Flight flight = new Flight(plane, departing, arriving, depDate, arrDate,depTime,arrTime, 4);
		Booking trip = new Booking(flight, LocalDate.now(), user, 20.00);
		Booking trip2 = new Booking(flight, LocalDate.now(), user2, 20.00);
		Booking trip3 = new Booking(flight, LocalDate.now(), user2, 20.00);
//		user.addTrip(trip);
		trip.getFlight().getPlane().getSeats().remove();
//		user.addTrip(trip);
//		user2.addTrip(trip2);
//		user3.addTrip(trip3);
		
	}

}
