package main;

import java.util.Date;

public class Booking {
	
	private User user;
	private Airplane bookedPlane;
	private Date flightDate;
	
	public Booking(User user, Airplane bookedPlane, Date flightDate) {
		this.user = user;
		this.bookedPlane = bookedPlane;
		this.flightDate = flightDate;
	}
	
	/**
	 * Generate itenerary including the flight
	 * @return
	 */
	public String generateItenerary() {
		
		String itenerary = "Username:\t" + user.getUsername();
		//itenerary += "\nSeat:\t" + bookedPlane.getSeatArray().
		return itenerary;
	}


}
