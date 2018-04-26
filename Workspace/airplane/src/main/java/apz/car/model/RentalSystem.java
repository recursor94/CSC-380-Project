package apz.car.model;

import java.util.ArrayList;

public class RentalSystem {
	
	private ArrayList<Rental> rentalList;
	
	public RentalSystem() {
		rentalList = new ArrayList<>();
	}
	
	public void addCarRental(Rental rental) {
		rentalList.add(rental);
	}
	
	public void removeCarRental(Rental rental) {
		for (int i = 0; i < rentalList.size(); i++)
			if (rentalList.get(i) == rental) 
				rentalList.remove(rental);
	}
	
	public Rental findCarRental(int receiptNumber) {
		for (int i = 0; i < rentalList.size(); i++)
			if (rentalList.get(i).getReceiptNumber() == receiptNumber)
				return rentalList.get(i);
		return null;
	}

	public ArrayList<Rental> getRentalList() {
		return rentalList;
	}

	public void setRentalList(ArrayList<Rental> rentalList) {
		this.rentalList = rentalList;
	}
	
	

}
