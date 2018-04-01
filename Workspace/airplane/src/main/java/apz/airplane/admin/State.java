package apz.airplane.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import apz.airplane.Airplane;
import apz.airplane.Airport;
import apz.airplane.Flight;

public class State {

	private static String planeObject = "planeList.apz";
	private static String airportObject = "airportList.apz";
	private static String flightObject = "flightList.apz";

	public static void savePlane(ArrayList<Airplane> plane) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream(planeObject);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(plane);
			objectOut.close();
			System.out.println("The Object was successfully written to a file");
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
			System.out.println("The Object was successfully written to a file");
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
			System.out.println("The Object was successfully written to a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Airplane> loadPlanes() {
		if (new File(planeObject).exists()) {
			System.out.println("YES");
			ArrayList<Airplane> planeList = new ArrayList<>();
			FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(planeObject);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);

				Object obj = objectIn.readObject();
				System.out.println("The Object has been read from the file");
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
				System.out.println("The Object has been read from the file");
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
				System.out.println("The Object has been read from the file");
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
