/**
 * 
 * @author Zak Gudlin
 * Created 2/9/2018
 *
 */
public class Cars {
	
	private String vehicleType; //The type of vehicle available for rental
	private double rate;		// Hourly rate for rental. Double because like $25.10/hr


	public Car(String vehicleType, double rate) {
		this.vehicleType = vehicleType;
		this.rate = rate;
	}
	
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
