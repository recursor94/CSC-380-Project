package apz.car.model;

import java.io.Serializable;

import apz.airplane.util.FilePath;
import javafx.scene.image.Image;

public class CarModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String model;

	public CarModel(String model) {
		this.model = model;
	}

	public static String[] getCarModels(CarManufacturer name, CarType type) {
		if (name == CarManufacturer.BMW) {
			if (type == CarType.LUXURY) {
				String[] cars = { "2017 2 Series" };
				return cars;
			} else if (type == CarType.SEDAN) {
				String[] cars = { "2017 5 Series", "2017 7 Series" };
				return cars;
			} else if (type == CarType.SPORT) {
				String[] cars = { "2017 6 Series", "2017 M6" };
				return cars;
			} else if (type == CarType.SUPERCAR) {
				String[] cars = { "2017 i8" };
				return cars;
			} else if (type == CarType.SUV) {
				String[] cars = { "2017 X3", "2017 X6" };
				return cars;
			}
		} else if (name == CarManufacturer.FORD) {
			if (type == CarType.LUXURY) {
				String[] cars = { "2017 EU Mondeo Vignale" };
				return cars;
			} else if (type == CarType.SEDAN) {
				String[] cars = { "2013 Fiesta", "2006 Focus" };
				return cars;
			} else if (type == CarType.SPORT) {
				String[] cars = { "2015 Focus RS", "2004 Mustang Cobra" };
				return cars;
			} else if (type == CarType.SUPERCAR) {
				String[] cars = { "2016 GT" };
				return cars;
			} else if (type == CarType.SUV) {
				String[] cars = { "2017 Explorer", "2017 Edge" };
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

	public static Image getCarImage(String name) {
		if (name.equals("2017 2 Series"))
			return new Image("file:resource/bmw2series.png");
		else if (name.equals("2017 5 Series"))
			return new Image("file:resource/bmw5series.png");
		else if (name.equals("2017 7 Series"))
			return new Image("file:resource/bmw7series.png");
		else if (name.equals("2017 6 Series"))
			return new Image("file:resource/bmw6series.png");
		else if (name.equals("2017 M6"))
			return new Image("file:resource/bmwm6.png");
		else if (name.equals("2017 i8"))
			return new Image("file:resource/bmwi8.png");
		else if (name.equals("2017 X3"))
			return new Image("file:resource/bmwx3.png");
		else if (name.equals("2017 X6"))
			return new Image("file:resource/bmwx6.png");
		else if (name.equals("2017 EU Mondeo Vignale"))
			return new Image("file:resource/fordmondeo.png");
		else if (name.equals("2013 Fiesta"))
			return new Image("file:resource/fordfiesta.png");
		else if (name.equals("2006 Focus"))
			return new Image("file:resource/fordfocus.png");
		else if (name.equals("2015 Focus RS"))
			return new Image("file:resource/fordfocusrs.png");
		else if (name.equals("2004 Mustang Cobra"))
			return new Image("file:resource/mustangc.png");
		else if (name.equals("2016 GT"))
			return new Image("file:resource/fordgt.png");
		else if (name.equals("2017 Explorer"))
			return new Image("file:resource/explorer.png");
		else if (name.equals("2017 Edge"))
			return new Image("file:resource/edge.png");
		else 
			return new Image(FilePath.DEFAULT_CAR_IMAGE);
	}
}
