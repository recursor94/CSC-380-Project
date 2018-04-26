package apz.car.model;

public class Car {
	
	private CarManufacturer name;
	private CarModel model;
	private CarType type;
	private double rate;
	
	public Car(CarManufacturer name, CarModel model, CarType type, double rate) {
		this.name = name;
		this.model = model;
		this.type = type;
		this.rate = rate;
	}
	
	public CarManufacturer getName() {
		return name;
	}

	public void setName(CarManufacturer name) {
		this.name = name;
	}

	public CarModel getModel() {
		return model;
	}

	public void setModel(CarModel model) {
		this.model = model;
	}

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", type=" + type + ", rate=" + rate + "]";
	}
	
	
	
}
