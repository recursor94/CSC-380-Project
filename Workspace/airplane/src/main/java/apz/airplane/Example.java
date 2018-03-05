package apz.airplane;

public class Example {

	public static void main(String[] args) {
		String dateAndTime = "2018-03-15T07:45";
		String[] dateTimeArr = dateAndTime.split("T");
		for (String a : dateTimeArr) 
			System.out.println(a);
	}

}
