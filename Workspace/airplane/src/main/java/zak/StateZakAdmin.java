package zak;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import apz.airplane.Airplane;
import apz.airplane.Flight;

public class StateZakAdmin {

//	public SaveState(ArrayList<Airplane> plane) throws IOException {
//		FileOutputStream fileOut = new FileOutputStream("planeobject.apz");
//		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
//		objectOut.writeObject(plane);
//		objectOut.close();
//		System.out.println("The Object was succesfully written to a file");
//	}

	public static void savePlane(ArrayList<AirplaneZak> plane) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream("planeObject.zak");
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(plane);
			objectOut.close();
			System.out.println("The Object was succesfully written to a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveAirport(ArrayList<String> airport) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream("airportObject.zak");
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(airport);
			objectOut.close();
			System.out.println("The Object was succesfully written to a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> loadAirports() {
		ArrayList<String> airportList = new ArrayList<>();
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("airportObject.zak");
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

	public static ArrayList<AirplaneZak> loadPlanes() {
		ArrayList<AirplaneZak> planeList = new ArrayList<>();
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("planeObject.zak");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			System.out.println("The Object has been read from the file");
			objectIn.close();
			planeList = (ArrayList<AirplaneZak>) obj;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return planeList;
	}
	
	public static void saveFlight(ArrayList<FlightZak> flight) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			fileOut = new FileOutputStream("flightObject.zak");
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(flight);
			objectOut.close();
			System.out.println("The Object was succesfully written to a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<FlightZak> loadFlights() {
		ArrayList<FlightZak> flightList = new ArrayList<>();
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("flightObject.zak");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Object obj = objectIn.readObject();
			System.out.println("The Object has been read from the file");
			objectIn.close();
			flightList = (ArrayList<FlightZak>) obj;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return flightList;

	}
	
}
