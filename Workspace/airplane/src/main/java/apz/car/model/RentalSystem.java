package apz.car.model;

import java.io.Serializable;
import java.util.ArrayList;

public class RentalSystem implements Serializable {	// user has this?
	
	private static final long serialVersionUID = 1L;
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
	
	public ArrayList<Rental> getRentalList() {
		return rentalList;
	}

	public void setRentalList(ArrayList<Rental> rentalList) {
		this.rentalList = rentalList;
	}
}
