package apz.car.model;

import java.io.Serializable;

public class Car implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private CarManufacturer manufacturer;
	private CarModel model;
	private CarType type;
	
	public Car(CarManufacturer name, CarModel model, CarType type) {
		this.manufacturer = name;
		this.model = model;
		this.type = type;
	}
	
	public CarManufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(CarManufacturer name) {
		this.manufacturer = name;
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
		return "[ " + manufacturer + " ] - " + model.getModel() + " - [ " + type + " ]";
	}
}
