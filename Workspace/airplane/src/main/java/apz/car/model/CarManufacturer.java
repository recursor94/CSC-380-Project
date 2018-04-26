package apz.car.model;

import java.util.ArrayList;

public enum CarManufacturer {

	HONDA, FORD, SUBARU, BMW, BENTLEY, LEXUS;

	public static int getManufacturerType(CarManufacturer name) {
		int mType = 0;

		if (name == CarManufacturer.BENTLEY)
			mType = 0;
		else if (name == CarManufacturer.BMW)
			mType = 1;
		else if (name == CarManufacturer.FORD)
			mType = 2;
		else if (name == CarManufacturer.HONDA)
			mType = 3;
		else if (name == CarManufacturer.LEXUS)
			mType = 4;
		else if (name == CarManufacturer.SUBARU)
			mType = 5;

		return mType;
	}

}
