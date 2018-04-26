package apz.car.model;

public enum CarType {
	
	SEDAN, COUPE, TRUCK, VAN, SUV, SPORT, SUPERCAR, LUXURY;
	
	public static int getCarType(CarType type) {
		int tType = 0;
		if (type == CarType.COUPE)
			tType = 0;
		if (type == CarType.LUXURY)
			tType = 1;
		if (type == CarType.SEDAN)
			tType = 2;
		if (type == CarType.SPORT)
			tType = 3;
		if (type == CarType.SUPERCAR)
			tType = 4;
		if (type == CarType.SUV)
			tType = 5;
		if (type == CarType.TRUCK)
			tType = 6;
		if (type == CarType.VAN)
			tType = 7;
		return tType;
	}

}
