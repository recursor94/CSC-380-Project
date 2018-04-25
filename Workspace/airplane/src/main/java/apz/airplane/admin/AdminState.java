package apz.airplane.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import apz.airplane.model.Airplane;
import apz.airplane.model.Airport;
import apz.airplane.model.Flight;
import apz.airplane.model.UserController;
import apz.airplane.user.APZLauncher;

public class AdminState {

	private static String ucFilePath = "userList.apz";
	private static String planeObject = "planeList.apz";
	private static String airportObject = "airportList.apz";
	private static String flightObject = "flightList.apz";
	
	
	public static void saveInformation(UserController uc) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream(ucFilePath);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(uc);
			objectOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UserController loadInformation() {
		UserController uc = new UserController();
		FileInputStream fileIn;
		try {
			if(!new File(ucFilePath).exists())
				saveInformation(uc);
			fileIn = new FileInputStream(ucFilePath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			objectIn.close();
			uc = (UserController) obj;

		} catch (IOException | ClassNotFoundException e) {
//			e.printStackTrace();
		}
		return uc;
	}

	public static void savePlane(ArrayList<Airplane> plane) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream(planeObject);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(plane);
			objectOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveAirports(ArrayList<Airport> airports) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream(airportObject);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(airports);
			objectOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveFlight(ArrayList<Flight> flight) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream(flightObject);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(flight);
			objectOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Airplane> loadPlanes() {
		if (new File(planeObject).exists()) {
			ArrayList<Airplane> planeList = new ArrayList<>();
			FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(planeObject);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				Object obj = objectIn.readObject();
				objectIn.close();
				planeList = (ArrayList<Airplane>) obj;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return planeList;
		}
		return new ArrayList<Airplane>();
	}

	public static ArrayList<Airport> loadAirports() {
		if (new File(airportObject).exists()) {
			ArrayList<Airport> airportList = new ArrayList<>();
			FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(airportObject);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				Object obj = objectIn.readObject();
				objectIn.close();
				airportList = (ArrayList<Airport>) obj;

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return airportList;
		}
		return new ArrayList<Airport>();
	}

	public static ArrayList<Flight> loadFlights() {
		if (new File(flightObject).exists()) {
			ArrayList<Flight> flightList = new ArrayList<>();
			FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(flightObject);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				Object obj = objectIn.readObject();
				objectIn.close();
				flightList = (ArrayList<Flight>) obj;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return flightList;
		}
		return new ArrayList<Flight>();
	}

}
