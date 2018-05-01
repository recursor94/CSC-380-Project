package apz.car.model;

public class Car {
	
	private CarManufacturer name;
	private CarModel model;
	private CarType type;
	
	public Car(CarManufacturer name, CarModel model, CarType type) {
		this.name = name;
		this.model = model;
		this.type = type;
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

	@Override
	public String toString() {
		return "Car [name=" + name + ", type=" + type;
	}
	
	
	
}
