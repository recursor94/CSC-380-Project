package apz.car.model;

import javafx.scene.image.Image;

public class CarModel {
	
	private String model;
	private static Image carImage;
	
	public CarModel(String model) {
		this.model = model;
		// set image depending on what it is in getCarModel
	}
	
	public static String[] getCarModels(CarManufacturer name, CarType type) {
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
				String[] cars = {"2017 EU Mondeo Vignale"};
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

	public static Image getCarImage(String model) {
		if (model.equals("2017 2 Series"))
			carImage = new Image("file:resource/bmw2series.png");
		else if (model.equals("2017 5 Series")) 
			carImage = new Image("file:resource/bmw5series.png");
		else if (model.equals("2017 7 Series"))
			carImage = new Image("file:resource/bmw7series.png");
		else if (model.equals("2017 6 Series"))
			carImage = new Image("file:resource/bmw6series.png");
		else if (model.equals("2017 M6"))
			carImage = new Image("file:resource/bmwm6.png");
		else if (model.equals("2017 i8"))
			carImage = new Image("file:resource/bmwi8.png");
		else if (model.equals("2017 X3"))
			carImage = new Image("file:resource/bmwx3.png");
		else if (model.equals("2017 X6"))
			carImage = new Image("file:resource/bmwx6.png");
		else if (model.equals("2017 EU Mondeo Vignale"))
			carImage = new Image("file:resource/fordmondeo.png");
		else if (model.equals("2013 Fiesta"))
			carImage = new Image("file:resource/fordfiesta.png");
		else if (model.equals("2006 Focus"))
			carImage = new Image("file:resource/fordfocus.png");
		else if (model.equals("2015 Focus RS"))
			carImage = new Image("file:resource/fordfocusrs.png");
		else if (model.equals("2004 Mustang Cobra"))
			carImage = new Image("file:resource/bmw2series.png");
		else if (model.equals("2016 GT"))
			carImage = new Image("file:resource/bmw2series.png");
		else if (model.equals("2017 Explorer"))
			carImage = new Image("file:resource/bmw2series.png");
		else if (model.equals("2017 Edge"))
			carImage = new Image("file:resource/bmw2series.png");
		return carImage;
	}
	
}
