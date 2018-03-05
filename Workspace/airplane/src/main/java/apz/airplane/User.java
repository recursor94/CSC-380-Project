package apz.airplane;

import java.util.ArrayList;

public class User {
	private static int id = 0;
	private int userId;
	private String username;
	String password;
	private ArrayList<Booking> tripList;	// need add, remove, find function
	private Payment pay;
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		tripList = new ArrayList<>();
		userId = id++;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = generatePasswordHash(password);
	}
	
	private String generatePasswordHash(String password) {
		return "" + password.hashCode();
		
	}
	
	private boolean isCorrectPassword(String userInput) { 
		String inputHash = generatePasswordHash(userInput);
		if(inputHash.equals(password)){ //compare the hash of the inputed hash to the stored password hash.  If they're equal return true
			return true;
		}
		return false;
	}
	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		User.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void addTrip(Booking trip) {
		tripList.add(trip);
	}
	
	public void removeTrip(Flight flight) {
		for(Booking trip : tripList) {
			if(trip.getFlight().equals(flight)) {
				tripList.remove(trip);
				return;
			}
		}
	}
	
	public Booking findTrip(Flight flight) {
		for(Booking trip : tripList) {
			if(trip.getFlight().equals(flight)) {
				return trip;
			}
		}
		return null;
	}
	
	public Payment createPayment(String address, String billingNum) {
		pay = new Payment (address, billingNum);
		return pay;
	}

	@Override
	public String toString() {
		return "Username: " + username + "\tPassword: " + password;
	}

}
