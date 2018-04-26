package apz.car.model;

public enum CarType {
	
	SEDAN, SUV, SPORT, SUPERCAR, LUXURY;
	
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
