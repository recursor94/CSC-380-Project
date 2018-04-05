
public enum City {
	
	NY, CA, PA, FL, TX;
	
	public static String getCityName(City city) {
		if (city == NY) 
			return "New York";
		return null;
	}
	
	public static double getRate(City city) {
		if (city == NY)
			return 50;
		else if (city == CA)
			return 90;
		else if (city == PA) 
			return 15;
		return 0;
	}

}
