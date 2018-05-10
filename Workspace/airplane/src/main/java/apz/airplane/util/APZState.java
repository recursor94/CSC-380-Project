package apz.airplane.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import apz.airplane.model.Airport;
import apz.airplane.model.Flight;
import apz.airplane.model.UserController;
import apz.airplane.user.gui.APZLauncher;

public class APZState {

	private static String ucFilePath = "userList.apz";
	private static String airportObject = "airportList.apz";
	private static String flightObject = "flightList.apz";
	private static String planeObject = "planeList.apz";
	private static String homeScreenTextObject = "updateText.txt";

	public static boolean checkFilesExist() {
		if (new File(airportObject).exists() && new File(flightObject).exists() && new File(planeObject).exists()
				&& new File(homeScreenTextObject).exists())
			return true;
		return false;
	}

	public static void saveInformation() {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream(ucFilePath);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(APZLauncher.getUserController());
			objectOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
			if (!new File(ucFilePath).exists())
				saveInformation(uc);
			fileIn = new FileInputStream(ucFilePath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			objectIn.close();
			uc = (UserController) obj;

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return uc;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Airport> loadAirports() {
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

	@SuppressWarnings("unchecked")
	public static ArrayList<Flight> loadFlights() {
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
}
