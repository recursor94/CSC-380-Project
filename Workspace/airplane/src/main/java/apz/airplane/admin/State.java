package apz.airplane.admin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import apz.airplane.Airplane;
import apz.airplane.Airport;
import apz.airplane.Flight;

public class State {

//	public SaveState(ArrayList<Airplane> plane) throws IOException {
//		FileOutputStream fileOut = new FileOutputStream("planeobject.apz");
//		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
//		objectOut.writeObject(plane);
//		objectOut.close();
//		System.out.println("The Object was succesfully written to a file");
//	}

	public static void savePlane(ArrayList<Airplane> plane) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream("planeobject.apz");
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(plane);
			objectOut.close();
			System.out.println("The Object was successfully written to a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveAirport(Airport airport) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream("airportobject.apz");
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(airport);
			objectOut.close();
			System.out.println("The Object was successfully written to a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> loadAirports() {
		ArrayList<String> airportList = new ArrayList<>();
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("airportobject.apz");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			System.out.println("The Object has been read from the file");
			objectIn.close();
			airportList = (ArrayList<String>) obj;

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return airportList;
	}

	public static ArrayList<Airplane> loadPlanes() {
		ArrayList<Airplane> planeList = new ArrayList<>();
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("planeobject.apz");
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
	
	public static void saveFlight(ArrayList<Flight> flight) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream("flightObject.apz");
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(flight);
			objectOut.close();
			System.out.println("The Object was successfully written to a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<Flight> loadFlights() {
		ArrayList<Flight> flightList = new ArrayList<>();
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("flightObject.apz");
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

}
