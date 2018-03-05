package apz.airplane;

public class Payment {
	
	private String address;
	private String cardNum;

	public Payment(String address, String cardNum) {
		this.address = address;
		this.cardNum = cardNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getcardNum() {
		return cardNum;
	}

	public void setcardNum(String cardNum) {
		this.cardNum = cardNum;
	}

}
