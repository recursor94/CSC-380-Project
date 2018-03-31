package apz.airplane;

import java.io.Serializable;

public class Airport implements Serializable{
	private String name;
	private String city;
	
	public Airport(String name, String city) {
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString() {
		return "Airport name:\t" + getName() + "Airport city:\t" + getCity();
	}
}
