package apz.airplane;

public class Payment {
	
	private String address;
	private String billingNum;

	public Payment(String address, String billingNum) {
		this.address = address;
		this.billingNum = billingNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBillingNum() {
		return billingNum;
	}

	public void setBillingNum(String billingNum) {
		this.billingNum = billingNum;
	}

}
