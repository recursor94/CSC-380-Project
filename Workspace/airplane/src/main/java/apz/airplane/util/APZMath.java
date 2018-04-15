package apz.airplane.util;

import apz.airplane.model.Province;

public class APZMath {
	
	public static double getPrice(String provinceA, String provinceB) {
		return Province.getRate(provinceA) + Province.getRate(provinceB);
	}

}
