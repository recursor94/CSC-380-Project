/**
 * 
 * @author Zak Gudlin
 * Created 2/9/2018
 *
 */
public class Cars {
	
	private String vehicleType; //The type of vehicle available for rental
	private int rate;			//Daily rate for a car rental

	
	public String getType () {
		return vehicleType;
	}


	public int getRate() {
		return rate;
	}


	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
}
