package zak;

import java.io.Serializable;

public class TimeZak implements Serializable {
	
	private double time;
	
	public TimeZak(String time) {	// for ex. 3:00 PM converts to 15 in double and 3:30 PM converts to 15.5;
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
		
		if (Integer.valueOf(numSplit[1]) == 30)
			tempTime += .5;
		
		this.time = tempTime;
	}
	
	public double getTimeDouble() {
		return time;
	}
	
	public String getTimeString() {
		return "12:30 AM"; 	// not real input ,check addFlight;
	}

	@Override
	public String toString() {
		return "Time [time=" + time + "]";
	}

}
