package apz.airplane.model;

import java.io.Serializable;

public class Time implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private double timeDouble;
	private String timeString;
	
	public Time(String time) {	
		timeString = time;
		timeDouble = parseTime(time);
	}
	
	public void setTimeDouble(String time) {
		timeDouble = parseTime(time);
	}
	
	public double getTimeDouble() {
		return timeDouble;
	}
	
	public void setTimeString(String time) {
		timeString = time;
	}
	
	public String getTimeString() {
		return timeString; 	
	}
	
	private double parseTime(String time) {
		double tempTime = 0;

		// Separate to find AM or PM
		String[] timeSplit = time.split(" ");
		if (timeSplit[1].equals("PM")) {
			if (!timeSplit[0].contains("12"))
				tempTime += 12;
		} else if (timeSplit[1].equals("AM") && timeSplit[0].contains("12")) {
				tempTime += 12;
		}
		String[] numSplit = timeSplit[0].split(":");
		tempTime += Double.valueOf(numSplit[0]);
		
		// Add .5 if the time is on the half hour
		if (Integer.valueOf(numSplit[1]) == 30)
			tempTime += .5;
		
		return tempTime;
	}

	@Override
	public String toString() {
		return timeString;
	}
}
