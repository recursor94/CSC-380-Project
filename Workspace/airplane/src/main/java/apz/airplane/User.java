package apz.airplane;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private ArrayList<Booking> tripList;	

	public User(String username, String password) {
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

	public void addTrip(Booking trip) {
		tripList.add(trip);
	}
	
	public boolean removeTrip(Flight flight) {
		for(Booking trip : tripList) {
			if(trip.getFlight().equals(flight)) {
				tripList.remove(trip);
				return true;
			}
		}
		return false;
	}
	
	public Booking findTrip(Flight flight) {
		for(Booking trip : tripList) {
			if(trip.getFlight().equals(flight)) {
				return trip;
			}
		}
		return null;
	}
	
	public ArrayList<Booking> getTripList() {
		return tripList;
	}

	@Override
	public String toString() {
		return "Username: " + username + "\tPassword: " + password;
	}

}
