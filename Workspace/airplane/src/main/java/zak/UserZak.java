package zak;

import java.io.Serializable;
import java.util.ArrayList;

public class UserZak implements Serializable {
	private String username;
	private String password;
	private ArrayList<BookingZak> tripList;	

	public UserZak(String username, String password) {
		this.username = username;
		this.password = generatePasswordHash(password);
		tripList = new ArrayList<>();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {	// change this
		return password;
	}
	
	public void setPassword(String password) {
		this.password = generatePasswordHash(password);
	}
	
	private String generatePasswordHash(String password) {
		return "" + password.hashCode();
		
	}
	
	public boolean validatePassword(String userInput) { 
		String inputHash = generatePasswordHash(userInput);
		if(inputHash.equals(password)){ //compare the hash of the inputed hash to the stored password hash.  If they're equal return true
			return true;
		}
		return false;
	}

	public void addTrip(BookingZak trip) {
		tripList.add(trip);
	}
	
	public boolean removeTrip(FlightZak flight) {
		for(BookingZak trip : tripList) {
			if(trip.getFlight().equals(flight)) {
				tripList.remove(trip);
				return true;
			}
		}
		return false;
	}
	
	public BookingZak findTrip(FlightZak flight) {
		for(BookingZak trip : tripList) {
			if(trip.getFlight().equals(flight)) {
				return trip;
			}
		}
		return null;
	}
	
	public ArrayList<BookingZak> getTripList() {
		return tripList;
	}

	@Override
	public String toString() {
		return "Username: " + username + "\tPassword: " + password;
	}

}
