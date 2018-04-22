package apz.airplane.model;

import java.io.Serializable;

public class Airport implements Serializable{
	
	private static final long serialVersionUID = 1L;
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
		return name + ", " + city;
	}
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(!(o instanceof Airport)) {
			return false;
		}
		Airport a = (Airport) o;
		if(a.getName().equals(this.getName()) && a.getCity().equals(this.getCity())) {
			return true;
		}
		return false;
	}
}
