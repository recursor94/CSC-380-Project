package apz.airplane.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

<<<<<<< HEAD:Workspace/airplane/src/main/java/apz/airplane/util/State.java
<<<<<<< HEAD
import apz.airplane.Flight;
import apz.airplane.UserController;
=======
=======
import apz.airplane.model.Airport;
>>>>>>> 3653a0449cabc27b869717ac960f54a2e9e8d298:Workspace/airplane/src/main/java/apz/airplane/util/APZState.java
import apz.airplane.model.UserController;
>>>>>>> fe89f75140c24e17929d657d7213d212565a35d6

public class APZState {
	
	private static String ucFilePath = "userList.apz";
<<<<<<< HEAD:Workspace/airplane/src/main/java/apz/airplane/util/State.java
	private static String flightObject = "flightList.apz";
=======
	private static String airportObject = "airportList.apz";
>>>>>>> 3653a0449cabc27b869717ac960f54a2e9e8d298:Workspace/airplane/src/main/java/apz/airplane/util/APZState.java
	
	public static void saveInformation(UserController uc) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream(ucFilePath);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(uc);
			objectOut.close();
			System.out.println("The Object was successfully written to a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UserController loadInformation() {
		UserController uc = new UserController();
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream(ucFilePath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			System.out.println("The Object has been read from the file");
			objectIn.close();
			uc = (UserController) obj;

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return uc;
	}
	
<<<<<<< HEAD:Workspace/airplane/src/main/java/apz/airplane/util/State.java
	public static ArrayList<Flight> loadFlights() {
		if (new File(flightObject).exists()) {
			ArrayList<Flight> flightList = new ArrayList<>();
			FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(flightObject);
=======
	public static ArrayList<Airport> loadAirports() {
		if (new File(airportObject).exists()) {
			ArrayList<Airport> airportList = new ArrayList<>();
			FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(airportObject);
>>>>>>> 3653a0449cabc27b869717ac960f54a2e9e8d298:Workspace/airplane/src/main/java/apz/airplane/util/APZState.java
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);

				Object obj = objectIn.readObject();
				System.out.println("The Object has been read from the file");
				objectIn.close();
<<<<<<< HEAD:Workspace/airplane/src/main/java/apz/airplane/util/State.java
				flightList = (ArrayList<Flight>) obj;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return flightList;
		}
		return new ArrayList<Flight>();
=======
				airportList = (ArrayList<Airport>) obj;

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return airportList;
		}
		return new ArrayList<Airport>();
	}
>>>>>>> 3653a0449cabc27b869717ac960f54a2e9e8d298:Workspace/airplane/src/main/java/apz/airplane/util/APZState.java

}
}
