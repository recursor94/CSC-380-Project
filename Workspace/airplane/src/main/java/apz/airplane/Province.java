package apz.airplane;

import java.util.ArrayList;

public enum Province {
	
	NY, CA, PA, FL, TX;
	
	static String strNY = "New York";
	static String strPA = "Pennsylvania";
	static String strFL = "Florida";
	static String strCA = "California";
	static String strTX = "Texas";
	
	public static Province getCityName(String city) {
		if (city.equals(strNY)) 
			return NY;
		else if (city.equals(strPA))
			return PA;
		else if (city.equals(strFL))
			return FL;
		else if (city.equals(strCA))
			return CA;
		else if (city.equals(strTX))
			return TX;
		return null;
	}
	
	public static String getCityName(Province city) {
		if (city == NY) 
			return strNY;
		else if (city == PA)
			return strPA;
		else if (city == FL)
			return strFL;
		else if (city == CA)
			return strCA;
		else if (city == TX)
			return strTX;
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
	
	public static ArrayList<Province> getProvinces() {
		ArrayList<Province> pList = new ArrayList<>();
		pList.add(NY);
		pList.add(CA);
		pList.add(PA);
		pList.add(FL);
		pList.add(TX);
		return pList;
	}

}
