package apz.airplane.util;

public class IsInteger {
	
	// Definitely don't need a class alone for isInteger. This is a method but we'll find a way to work things in
	
	public static boolean isInteger(String str) {
		boolean result = false;
		if(!(str.isEmpty())) {
			for(int i = 0; i < str.length(); i ++) {
				if(Character.isDigit(str.charAt(i))) {
					result = true;
				}
				else {
					result = false;
					return result;
				}	
			}
		}
		return result;
	}
}
