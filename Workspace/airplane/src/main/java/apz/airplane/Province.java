package apz.airplane;

public enum Province {
	
	NY, CA, PA, FL, TX;
	
	public static String getCityName(Province city) {
		if (city == NY) 
			return "New York";
		else if (city == PA)
			return "Pennsylvania";
		else if (city == FL)
			return "Florida";
		else if (city == CA)
			return "California";
		else if (city == TX)
			return "Texas";
		return null;
	}
	
	public static double getRate(Province city) {
		if (city == NY)
			return 50;
		else if (city == CA)
			return 90;
		else if (city == PA) 
			return 35;
		else if (city == FL)
			return 60;
		else if (city == TX)
			return 75;
		return 0;
	}

}
