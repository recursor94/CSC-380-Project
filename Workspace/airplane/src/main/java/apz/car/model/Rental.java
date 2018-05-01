package apz.car.model;

import java.io.Serializable;

public class Rental implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Car car;
	private int days;
	private double price;
	private static int number = 0;
	private int receiptNumber;
	
	public Rental(Car car, int days, double price) {
		this.car = car;
		this.days = days;
		this.price = price;
		receiptNumber = number++;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static int getNumber() {
		return number;
	}

	public static void setNumber(int number) {
		Rental.number = number;
	}

	public int getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(int receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	
}
