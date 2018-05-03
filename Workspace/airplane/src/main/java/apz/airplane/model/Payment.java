package apz.airplane.model;

import java.io.Serializable;

public class Payment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String street;
	private String city;
	private String state;
	private int zipcode;
	private long cardNum;
	private String expirationDate;
	private int CCVNum;
	
	public Payment(String name, String street, String city, String state, int zipcode, long cardNum, String expirationDate, int CCVNum) {
		this.name = name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.cardNum = cardNum;
		this.expirationDate = expirationDate;
		this.CCVNum = CCVNum;
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
	
	public long getCardNum() {
		return cardNum;
	}
	
	public void setCardNum(long cardNum) {
		this.cardNum = cardNum;
	}
	
	public String getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public int getCCVNum() {
		return CCVNum;
	}
	
	public void setCCVNum(int cCVNum) {
		CCVNum = cCVNum;
	}
	
	@Override
	public String toString() {
		return "Card ending in number " + ((Long)cardNum).toString().substring(12);
	}
}
