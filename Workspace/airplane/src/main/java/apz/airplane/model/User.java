package apz.airplane.model;

import java.io.Serializable;
import java.util.ArrayList;

import apz.car.model.RentalSystem;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String email;
	private String username;
	private String password;
	private ArrayList<Booking> tripList;
	private ArrayList<Payment> paymentInformation;
	private RentalSystem rentalSystem;

	public User(String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = generatePasswordHash(password);
		tripList = new ArrayList<>();
		paymentInformation = new ArrayList<>();
		rentalSystem = new RentalSystem();
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
	
	public ArrayList<Payment> getPaymentInformation() {
		return paymentInformation;
	}

	public void setPaymentInformation(ArrayList<Payment> paymentInformation) {
		this.paymentInformation = paymentInformation;
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
		for (int i = 0; i < tripList.size(); i++) {
			if (tripList.get(i).getFlight().equals(flight)) {
				tripList.get(i).getFlight().getPlane().getSeats().remove();
				tripList.remove(tripList.get(i));
				return true;
			}
		}
		return false;
	}
	
	public void addPayment(Payment information) {
		paymentInformation.add(information);
	}
	
	public boolean removePayment(Payment information) {
		for(Payment info : paymentInformation) {
			if(info == information) {
				paymentInformation.remove(info);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public RentalSystem getRentalSystem() {
		return rentalSystem;
	}

	public void setRentalSystem(RentalSystem rentalSystem) {
		this.rentalSystem = rentalSystem;
	}

	public void setTripList(ArrayList<Booking> tripList) {
		this.tripList = tripList;
	}

	@Override
	public String toString() {
		return "Username: " + username + ", Password: " + password + ", Email: " + email;
	}
}
