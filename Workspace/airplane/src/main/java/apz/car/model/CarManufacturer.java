package apz.car.model;

import java.util.ArrayList;

public enum CarManufacturer {

	FORD, BMW;

	public static int getManufacturerType(CarManufacturer name) {
		int mType = 0;

		if (name == CarManufacturer.BMW)
			mType = 0;
		else if (name == CarManufacturer.FORD)
			mType = 1;

		return mType;
	}

}
