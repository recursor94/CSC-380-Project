package apz.car.model;

import java.util.ArrayList;

public class CarModel {
	
	public static ArrayList<String> getModels(CarManufacturer name, CarType type) {
		ArrayList<String> models = new ArrayList<>();
		
		int mType = CarManufacturer.getManufacturerType(name);
		int tType = CarType.getCarType(type);
		
		

		return models;
	}

}
