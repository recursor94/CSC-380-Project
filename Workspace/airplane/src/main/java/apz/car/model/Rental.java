package apz.car.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Rental implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private LocalDate date;
	private Car car;
	private int days;
	private double price;
	
	public Rental(Car car, int days, double price) {
		date = LocalDate.now();
		this.car = car;
		this.days = days;
		this.price = price;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
