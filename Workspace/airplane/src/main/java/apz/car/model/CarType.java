package apz.car.model;

import java.util.ArrayList;
import java.util.Collections;

public enum CarType {
	
	SEDAN, SUV, SPORT, SUPERCAR, LUXURY;
	
	public static ArrayList<String> getManufacturerList() {
		ArrayList<String> list = new ArrayList<>();
		list.add(getCarTypeName(SEDAN));
		list.add(getCarTypeName(SUV));
		list.add(getCarTypeName(SPORT));
		list.add(getCarTypeName(SUPERCAR));
		list.add(getCarTypeName(LUXURY));
		Collections.sort(list);
		return list;
	}
	
	public static double getTypeRate(String type) {
		if (type.equals("Luxury"))
			return 550.52;
		else if (type.equals("Sedan"))
			return 120.92;
		else if (type.equals("Sport"))
			return 240.21;
		else if (type.equals("Super Car"))
			return 450.34;
		else if (type.equals("SUV"))
			return 198.43;
		return 0;
	}
	
	public static CarType getCarTypeName(String type) {
		if (type.equals("Luxury"))
			return LUXURY;
		else if (type.equals("Sedan"))
			return SEDAN;
		else if (type.equals("Sport"))
			return SPORT;
		else if (type.equals("Super Car"))
			return SUPERCAR;
		else if (type.equals("SUV"))
			return SUV;
		return null;
	}
	
	public static String getCarTypeName(CarType type) {
		if (type == CarType.LUXURY)
			return "Luxury";
		else if (type == CarType.SEDAN)
			return "Sedan";
		else if (type == CarType.SPORT)
			return "Sport";
		else if (type == CarType.SUPERCAR)
			return "Super Car";
		else if (type == CarType.SUV)
			return "SUV";
		return "TURDS";
	}
	
	public static int getCarType(CarType type) {
		int tType = 0;
		if (type == CarType.LUXURY)
			tType = 0;
		else if (type == CarType.SEDAN)
			tType = 1;
		else if (type == CarType.SPORT)
			tType = 2;
		else if (type == CarType.SUPERCAR)
			tType = 3;
		else if (type == CarType.SUV)
			tType = 4;
		return tType;
	}

}
