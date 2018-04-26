package apz.car.model;

import javafx.scene.image.Image;

public class CarModel {
	
	private String model;
	private Image carImage;
	
	public CarModel(String model) {
		this.model = model;
		// set image depending on what it is in getCarModel
	}
	
	public static String[] getCarModel(CarManufacturer name, CarType type) {
		if (name == CarManufacturer.BMW) {
			if (type == CarType.LUXURY) {
				String[] cars = {"2017 2 Series"};
				return cars;
			} else if (type == CarType.SEDAN) {
				String[] cars = {"2017 5 Series", "2017 7 Series"};
				return cars;
			} else if (type == CarType.SPORT) {
				String[] cars = {"2017 6 series", "2017 M6"};
				return cars;
			} else if (type == CarType.SUPERCAR) {
				String[] cars = {"2017 i8"};
				return cars;
			} else if (type == CarType.SUV) {
				String[] cars = {"2017 X3", "2017 X6"};
				return cars;
			}
		} else if (name == CarManufacturer.FORD) {
			if (type == CarType.LUXURY) {
				String[] cars = {};
				return cars;
			} else if (type == CarType.SEDAN) {
				String[] cars = {"2013 Fiesta", "2006 Focus"};
				return cars;
			} else if (type == CarType.SPORT) {
				String[] cars = {"2015 Focus RS", "2004 Mustang Cobra"};
				return cars;
			} else if (type == CarType.SUPERCAR) {
				String[] cars = {"2016 GT"};
				return cars;
			} else if (type == CarType.SUV) {
				String[] cars = {"2017 Explorer", "2017 Edge"};
				return cars;
			}
		}
		
		return null; 
	}

	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Image getCarImage() {
		return carImage;
	}

	public void setCarImage(Image carImage) {
		this.carImage = carImage;
	}
	
}
