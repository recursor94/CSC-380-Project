package apz.car.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public enum CarManufacturer implements Serializable {

	FORD, BMW;

	public static ArrayList<String> getManufacturerList() {
		ArrayList<String> list = new ArrayList<>();
		list.add(getManufacturerName(FORD));
		list.add(getManufacturerName(BMW));
		Collections.sort(list);
		return list;
	}
	
	public static CarManufacturer getManufacturerName(String name) {
		if (name.equals("BMW"))
			return BMW;
		else if (name.equals("FORD"))
			return FORD;
		return null;
	}
	
	public static String getManufacturerName(CarManufacturer name) {
		if (name == CarManufacturer.BMW)
			return "BMW";
		else if (name == CarManufacturer.FORD)
			return "FORD";
		return "TURDS";
	}
	
	public static int getManufacturerType(CarManufacturer name) {
		int mType = 0;

		if (name == CarManufacturer.BMW)
			mType = 0;
		else if (name == CarManufacturer.FORD)
			mType = 1;

		return mType;
	}
}
