package apz.airplane.model;

public class Payment {
	
	private String name;
	private String street;
	private String city;
	private String state;
	private int zipcode;
	private int cardNum;
	
	public Payment(String name, String street, String city, String state, int zipcode, int cardNum) {
		this.name = name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.cardNum = cardNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public int getCardNum() {
		return cardNum;
	}
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	@Override
	public String toString() {
		return "Payment [name=" + name + ", street=" + street + ", city=" + city + ", state=" + state + ", zipcode="
				+ zipcode + ", cardNum=" + cardNum + "]";
	}
}
