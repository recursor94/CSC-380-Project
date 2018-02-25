package apz.airplane;

public class Driver {

	public static void main(String[] args) {
		Airplane airplane = new Airplane(1, "American Airline", 5);	// create plane with 5 seats
		
		UserController userCtrl = new UserController();
		userCtrl.addUser(new User("Andrew Gudlin", "pswrd"));
		userCtrl.addUser(new User("Zack Spano", "pswrd"));
		userCtrl.addUser(new User("King Jimmy", "pswrd"));
		userCtrl.addUser(new User("Bob Saget", "pswrd"));
		userCtrl.addUser(new User("Marley n Me", "pswrd"));
		
		//userCtrl.bookTrip(user goes inside here);
		
		airplane.getSeatArray().addTo(new User("Pooper", "Scooper"));
		System.out.println(airplane);
	}

}
